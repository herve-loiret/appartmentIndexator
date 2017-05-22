package fr.appartment.indexator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.appartment.indexator.brokers.DataIndexator;
import fr.appartment.indexator.domain.Appartment;

@Service
public class IndexatorService {

	private List<DataIndexator> indexators;

	private AppartmentService appartmentService;

	public IndexatorService(List<DataIndexator> indexators, AppartmentService appartmentService) {
		this.indexators = indexators;
		this.appartmentService = appartmentService;
	}

	public void index(List<String> postalCodes, Integer minPrice, Integer maxPrice) {
		for (DataIndexator indexator : indexators) {
			List<Appartment> appartments = indexator.index(postalCodes, minPrice, maxPrice);
			appartmentService.save(appartments);
		}
	}

}
