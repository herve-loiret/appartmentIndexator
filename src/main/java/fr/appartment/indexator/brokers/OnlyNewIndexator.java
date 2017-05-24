package fr.appartment.indexator.brokers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.AsyncResult;

import fr.appartment.indexator.domain.Appartment;
import fr.appartment.indexator.service.AppartmentService;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO use this abstract class
 *
 */
@Slf4j
public abstract class OnlyNewIndexator implements Indexator {

	private AppartmentService appartmentService;

	private Client client;

	public OnlyNewIndexator(Client client, AppartmentService appartmentService) {
		this.client = client;
		this.appartmentService = appartmentService;
	}

	@Override
	public Future<List<Appartment>> processIndex(List<String> postalCodes, Integer minPrice, Integer maxPrice) {

		List<Appartment> appartementResults = new ArrayList<>();

		int currentPage = 1;
		int totalPage = 0;
		do {

			String searchPage = client.getSearchPage(postalCodes, minPrice, maxPrice, currentPage);
			List<Appartment> partialAppartments = parseAppartmentsFromSearchPage(searchPage);

			for (Appartment appartment : partialAppartments) {
				if (appartmentService.isAlreadyInDatabase(appartment)) {
					log.info("stop indexing seloger at page {} because {} is already in database", currentPage,
							appartment);
					return new AsyncResult<>(appartementResults);
				} else {
					String detailPage = client.getDetailsPage(appartment);
					appartment = parseAppartmentFromDetailPage(appartment, detailPage);
					appartementResults.add(appartment);
				}
			}

			totalPage = findTotalPageFromSearchPaget(searchPage);
			currentPage++;
		} while (currentPage <= totalPage);

		return new AsyncResult<>(appartementResults);
	}

	protected abstract int findTotalPageFromSearchPaget(String searchPage);

	protected abstract List<Appartment> parseAppartmentsFromSearchPage(String pageContent);

	protected abstract Appartment parseAppartmentFromDetailPage(Appartment appartment, String detailPage);

}
