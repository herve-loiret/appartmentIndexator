package fr.appartment.indexator.repository.solr;

import org.mapstruct.Mapper;

import fr.appartment.indexator.domain.Appartment;

@Mapper(componentModel = "spring", uses = {})
public interface SolrMapper {

	AppartmentDocument appartmentToAppartmentDocument(Appartment appartment);

}
