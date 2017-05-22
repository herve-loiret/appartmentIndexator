package fr.appartment.indexator.brokers.seloger;

import java.util.ArrayList;
import java.util.List;

import fr.appartment.indexator.brokers.Client;
import fr.appartment.indexator.brokers.DataIndexator;
import fr.appartment.indexator.domain.Appartment;
import fr.appartment.indexator.service.AppartmentService;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO use this abstract class
 *
 */
@Slf4j
public abstract class AbstractIndexator implements DataIndexator {

	private AppartmentService appartmentService;

	private Client client;

	public AbstractIndexator(Client client, AppartmentService appartmentService) {
		this.client = client;
		this.appartmentService = appartmentService;
	}

	@Override
	public List<Appartment> index(List<String> postalCodes, Integer minPrice, Integer maxPrice) {
		List<Appartment> appartements = new ArrayList<>();

		int currentPage = 1;
		int pageTotal = 0;
		boolean continueIndexing = true;
		do {
			String pageContent = client.getPage(postalCodes, minPrice, maxPrice, currentPage);
			List<Appartment> deserializeAppartments = deserializeAppartmentFromPageContent(pageContent);

			for (Appartment appartment : deserializeAppartments) {
				if (appartmentService.isAlreadyInDatabase(appartment)) {
					continueIndexing = false;
					log.info("stop indexing seloger at page {} because {} is already in database", currentPage,
							appartment);
				} else {
					appartment = findDetails(appartment);
					appartements.add(appartment);
				}
			}

			pageTotal = findPageNumberFromPageContent(pageContent);
			currentPage++;
		} while (currentPage <= pageTotal && continueIndexing);

		return appartements;
	}

	protected abstract int findPageNumberFromPageContent(String pageContent);

	protected abstract List<Appartment> deserializeAppartmentFromPageContent(String pageContent);

}
