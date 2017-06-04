package fr.appartment.indexator.brokers;

import java.util.ArrayList;
import java.util.List;

import fr.appartment.indexator.domain.Appartment;
import fr.appartment.indexator.service.AppartmentService;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class PageIndexator implements Indexator {

	@Getter
	private Client client;

	@Getter
	private int currentPage = 1;

	@Getter
	private int totalPage = -1;
	
	@Getter
	private AppartmentService appartmentService;

	public PageIndexator(Client client, AppartmentService appartmentService) {
		this.client = client;
		this.appartmentService = appartmentService;
	}

	@Override
	@SneakyThrows
	public List<Appartment> processIndex(List<String> postalCodes, Integer minPrice, Integer maxPrice) {

		List<Appartment> appartementResults = new ArrayList<>();

		do {
			waitSomeTimeForNextPageIndexation();

			String searchPage = client.getSearchPage(postalCodes, minPrice, maxPrice, currentPage);

			List<Appartment> partialAppartments = parseAppartmentsFromSearchPage(searchPage);

			for (Appartment appartment : partialAppartments) {
				if (shouldIndexDetailsOfThisAppartment(appartment)) {
					waitSomeTimeForDetailIndexation();
					String detailPage = client.getDetailsPage(appartment);
					appartment = parseAppartmentFromDetailPage(appartment, detailPage);
					appartementResults.add(appartment);
				}
			}

			totalPage = findTotalPageFromSearchPaget(searchPage);
			currentPage++;
		} while (currentPage <= totalPage);

		return appartementResults;
	}

	protected void waitSomeTimeForDetailIndexation() {
		try {
			Thread.sleep(1_000);
		} catch (InterruptedException e) {
			log.error("error while trying to sleep thread");
		}
	}

	protected void waitSomeTimeForNextPageIndexation() {
		try {
			Thread.sleep(1_000);
		} catch (InterruptedException e) {
			log.error("error while trying to sleep thread");
		}
	}

	protected abstract boolean shouldIndexDetailsOfThisAppartment(Appartment appartment);

	protected abstract int findTotalPageFromSearchPaget(String searchPage);

	protected abstract List<Appartment> parseAppartmentsFromSearchPage(String pageContent);

	protected abstract Appartment parseAppartmentFromDetailPage(Appartment appartment, String detailPage);

}
