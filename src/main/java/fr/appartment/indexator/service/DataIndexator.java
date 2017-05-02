package fr.appartment.indexator.service;

import java.util.List;

import fr.appartment.indexator.domain.Appartment;

public interface DataIndexator {

	List<Appartment> index(List<String> postalCodes, Integer minPrice, Integer maxPrice);
	
}
