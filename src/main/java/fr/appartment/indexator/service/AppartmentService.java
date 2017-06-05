package fr.appartment.indexator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import fr.appartment.indexator.domain.Appartment;
import fr.appartment.indexator.repository.AppartmentRepository;
import fr.appartment.indexator.repository.solr.AppartmentDocument;
import fr.appartment.indexator.repository.solr.SolrMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppartmentService {

	private AppartmentRepository appartmentRepository;

	private SolrMapper mapper;

	public AppartmentService(AppartmentRepository appartmentRepository, SolrMapper mapper) {
		this.appartmentRepository = appartmentRepository;
		this.mapper = mapper;
	}

	public void save(List<Appartment> appartments) {
		List<AppartmentDocument> documents = mapper.appartmentToAppartmentDocument(appartments);
		if (documents != null) {
			for (AppartmentDocument document : documents) {

				System.out.println("saving : " + document);
				appartmentRepository.save(document);
			}
		} else {
			log.error("error solr");
		}
	}

	public boolean isAlreadyInDatabase(Appartment appartment) {
		AppartmentDocument document = appartmentRepository.findByUrl(appartment.getUrl());
		return document != null;
	}

	public void deleteAllAppartment() {
		appartmentRepository.deleteAll();
	}

	public List<Appartment> findAllAppartments() {
		Iterable<AppartmentDocument> appartmentDocuments = appartmentRepository.findAll();
		return mapper.appartmentDocumentToAppartment(Lists.newArrayList(appartmentDocuments));
	}
	
	public Long countAllAppartments() {
		return appartmentRepository.count();
	}

}
