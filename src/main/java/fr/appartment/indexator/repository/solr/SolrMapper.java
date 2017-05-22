package fr.appartment.indexator.repository.solr;

import java.util.List;

import org.mapstruct.Mapper;

import fr.appartment.indexator.domain.Appartment;

@Mapper(componentModel = "spring", uses = {})
public interface SolrMapper {


	List<AppartmentDocument> appartmentToAppartmentDocument(List<Appartment> appartment);
	
	AppartmentDocument appartmentToAppartmentDocument(Appartment appartment);

}
