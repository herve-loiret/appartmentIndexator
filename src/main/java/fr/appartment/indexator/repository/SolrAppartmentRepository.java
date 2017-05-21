package fr.appartment.indexator.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import fr.appartment.indexator.domain.Appartment;

public interface SolrAppartmentRepository extends SolrCrudRepository<Appartment, Long> {

}
