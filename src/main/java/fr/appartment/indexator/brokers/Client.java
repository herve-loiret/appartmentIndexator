package fr.appartment.indexator.brokers;

import java.util.List;

public interface Client {
	String getPage(List<String> postalCodes, Integer minPrice, Integer maxPrice, int page);
}
