package fr.appartment.indexator.brokers;

import java.util.List;

public interface Client {
	String getPage(List<String> postalCodes, int minPrice, int maxPrice, int page);
}
