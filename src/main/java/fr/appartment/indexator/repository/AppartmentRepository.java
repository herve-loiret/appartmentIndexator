package fr.appartment.indexator.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import fr.appartment.indexator.repository.solr.AppartmentDocument;

public interface AppartmentRepository extends SolrCrudRepository<AppartmentDocument, Long> {

}
