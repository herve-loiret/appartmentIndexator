package fr.appartment.indexator.repository.solr;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fr.appartment.indexator.domain.Appartment;

@Mapper(componentModel = "spring", uses = {})
public interface SolrMapper {


	List<AppartmentDocument> appartmentToAppartmentDocument(List<Appartment> appartment);
	
	@Mapping(source="externalId", target="id")
	AppartmentDocument appartmentToAppartmentDocument(Appartment appartment);

}
