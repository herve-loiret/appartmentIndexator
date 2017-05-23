package fr.appartment.indexator.brokers;

import java.util.List;

import fr.appartment.indexator.domain.Appartment;

public interface Indexator {

	List<Appartment> processIndex(List<String> postalCodes, Integer minPrice, Integer maxPrice);
	
}
