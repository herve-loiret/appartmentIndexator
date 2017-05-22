package fr.appartment.indexator.brokers;

import java.util.List;

import fr.appartment.indexator.domain.Appartment;

public interface Client {
	String getPage(List<String> postalCodes, Integer minPrice, Integer maxPrice, int page);

	String getDetailsPage(Appartment appartment);
}
