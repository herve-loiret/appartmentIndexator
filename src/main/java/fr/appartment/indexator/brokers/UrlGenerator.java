package fr.appartment.indexator.brokers;

import java.util.List;

import fr.appartment.indexator.domain.Appartment;

public interface UrlGenerator {

	String generateSearchUrl(List<String> postalCodes, Integer minPrice, Integer maxPrice, int page);

	String generateDetailsUrl(Appartment appartment);
	
}
