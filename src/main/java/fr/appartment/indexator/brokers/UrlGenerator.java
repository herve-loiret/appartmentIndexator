package fr.appartment.indexator.brokers;

import java.util.List;

public interface UrlGenerator {

	String generateUrl(List<String> postalCodes, Integer minPrice, Integer maxPrice, int page);

}
