package fr.appartment.indexator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.appartment.indexator.brokers.DataIndexator;

@Service
public class IndexatorService {

	@Autowired
	private List<DataIndexator> indexators;
	
	public void index(List<String> postalCodes, Integer minPrice, Integer maxPrice) {
		for(DataIndexator indexator : indexators){
			indexator.index(postalCodes, minPrice, maxPrice);
		}
	}

}
