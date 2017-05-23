package fr.appartment.indexator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.appartment.indexator.brokers.Indexator;
import fr.appartment.indexator.domain.Appartment;

@Service
public class IndexatorService {

	private List<Indexator> indexators;

	private AppartmentService appartmentService;

	public IndexatorService(List<Indexator> indexators, AppartmentService appartmentService) {
		this.indexators = indexators;
		this.appartmentService = appartmentService;
	}

	public void index(List<String> postalCodes, Integer minPrice, Integer maxPrice) {
		for (Indexator indexator : indexators) {
			List<Appartment> appartments = indexator.processIndex(postalCodes, minPrice, maxPrice);
			appartmentService.save(appartments);
		}
	}

}
