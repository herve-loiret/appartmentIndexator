package fr.appartment.indexator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.appartment.indexator.brokers.DataIndexator;
import fr.appartment.indexator.domain.Appartment;
import fr.appartment.indexator.repository.AppartmentRepository;
import fr.appartment.indexator.repository.solr.AppartmentDocument;
import fr.appartment.indexator.repository.solr.SolrMapper;

@Service
public class IndexatorService {

	private List<DataIndexator> indexators;

	private AppartmentRepository appartmentRepo;
	
	private SolrMapper mapper;

	public IndexatorService(List<DataIndexator> indexators, AppartmentRepository appartmentRepo, SolrMapper mapper) {
		this.indexators = indexators;
		this.appartmentRepo = appartmentRepo;
		this.mapper = mapper;
	}

	public void index(List<String> postalCodes, Integer minPrice, Integer maxPrice) {
		for (DataIndexator indexator : indexators) {
			List<Appartment> appartments = indexator.index(postalCodes, minPrice, maxPrice);
			List<AppartmentDocument> documents = mapper.appartmentToAppartmentDocument(appartments);
			appartmentRepo.save(documents);
		}
	}

}
