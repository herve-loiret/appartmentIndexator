package fr.appartment.indexator.brokers;

import java.util.List;

public interface UrlGenerator {

	String generateUrl(List<String> postalCodes, int minPrice, int maxPrice, int page);

}
