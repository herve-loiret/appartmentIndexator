package fr.appartment.indexator.brokers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.appartment.indexator.domain.Appartment;
import fr.appartment.indexator.service.AppartmentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class OnlyNewIndexator implements Indexator {

	private AppartmentService appartmentService;

	private Client client;

	public OnlyNewIndexator(Client client, AppartmentService appartmentService) {
		this.client = client;
		this.appartmentService = appartmentService;
	}

	@Override
	public List<Appartment> processIndex(List<String> postalCodes, Integer minPrice, Integer maxPrice) {

		List<Appartment> appartementResults = new ArrayList<>();

		int currentPage = 1;
		int totalPage = 0;
		do {

			String searchPage = "";
			try {
				searchPage = client.getSearchPage(postalCodes, minPrice, maxPrice, currentPage);
			} catch (IOException e) {
				log.error(
						"error while get search page for search postalCodes:{}, minPrice:{}, maxPrice:{}, currentPage:{}",
						postalCodes, minPrice, maxPrice, currentPage);
			}
			List<Appartment> partialAppartments = parseAppartmentsFromSearchPage(searchPage);

			for (Appartment appartment : partialAppartments) {
				if (appartmentService.isAlreadyInDatabase(appartment)) {
					log.info("stop indexing seloger at page {} because {} is already in database", currentPage,
							appartment);
					return appartementResults;
				} else {
					try {
						String detailPage = client.getDetailsPage(appartment);
						appartment = parseAppartmentFromDetailPage(appartment, detailPage);
					} catch (IOException e) {
						// TODO
						e.printStackTrace();
					}
					appartementResults.add(appartment);
				}
			}

			totalPage = findTotalPageFromSearchPaget(searchPage);
			currentPage++;
		} while (currentPage <= totalPage);

		return appartementResults;
	}

	protected abstract int findTotalPageFromSearchPaget(String searchPage);

	protected abstract List<Appartment> parseAppartmentsFromSearchPage(String pageContent);

	protected abstract Appartment parseAppartmentFromDetailPage(Appartment appartment, String detailPage);

}
