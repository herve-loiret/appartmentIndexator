package fr.appartment.indexator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.appartment.indexator.domain.Appartment;
import fr.appartment.indexator.repository.AppartmentRepository;
import fr.appartment.indexator.repository.solr.AppartmentDocument;
import fr.appartment.indexator.repository.solr.SolrMapper;

@Service
public class AppartmentService {

	private AppartmentRepository appartmentRepository;

	private SolrMapper mapper;

	public AppartmentService(AppartmentRepository appartmentRepository, SolrMapper mapper) {
		this.appartmentRepository = appartmentRepository;
		this.mapper = mapper;
	}

	public void save(List<Appartment> appartments) {
		List<AppartmentDocument> documents = mapper.appartmentToAppartmentDocument(appartments);
		appartmentRepository.save(documents);
	}

	public boolean isAlreadyInDatabase(Appartment appartment) {
		AppartmentDocument document = appartmentRepository.findByUrl(appartment.getUrl());
		return document != null;
	}

}
