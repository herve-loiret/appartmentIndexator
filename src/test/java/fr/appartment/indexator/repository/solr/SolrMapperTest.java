package fr.appartment.indexator.repository.solr;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mapstruct.factory.Mappers;

import fr.appartment.indexator.domain.Appartment;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class SolrMapperTest {

	private SolrMapper mapper = Mappers.getMapper(SolrMapper.class);

	@Test
	public void should_mapper_map_Appartment_into_AppartmentDocument() {
		
		List<Appartment> appartments = Arrays.asList(new PodamFactoryImpl().manufacturePojo(Appartment.class));
		
		List<AppartmentDocument> appartmentDocuments = mapper.appartmentToAppartmentDocument(appartments);

		assertThat(appartments).size().isEqualTo(appartmentDocuments.size());
		AppartmentDocument appartmentDocument = appartmentDocuments.get(0);
		Appartment appartment = appartments.get(0);
		assertThat(appartmentDocument.getDescription()).isEqualTo(appartment.getDescription());
		assertThat(appartmentDocument.getEtage()).isEqualTo(appartment.getEtage());
		assertThat(appartmentDocument.getExternalId()).isEqualTo(appartment.getExternalId());
		assertThat(appartmentDocument.getHasBalcon()).isEqualTo(appartment.getHasBalcon());
		assertThat(appartmentDocument.getHasSdbain()).isEqualTo(appartment.getHasSdbain());
		assertThat(appartmentDocument.getHasSdEau()).isEqualTo(appartment.getHasSdEau());
		assertThat(appartmentDocument.getId()).isEqualTo(String.valueOf(appartment.getId()));
		assertThat(appartmentDocument.getIndexationTime()).isEqualTo(appartment.getIndexationTime());
		assertThat(appartmentDocument.getNbChambres()).isEqualTo(appartment.getNbChambres());
		assertThat(appartmentDocument.getNbPhotos()).isEqualTo(appartment.getNbPhotos());
		assertThat(appartmentDocument.getNbPieces()).isEqualTo(appartment.getNbPieces());
		assertThat(appartmentDocument.getPostalCode()).isEqualTo(appartment.getPostalCode());
		assertThat(appartmentDocument.getPriceByMeter()).isEqualTo(appartment.getPriceByMeter());
		assertThat(appartmentDocument.getSurface()).isEqualTo(appartment.getSurface());
		assertThat(appartmentDocument.getType()).isEqualTo(appartment.getType());
		assertThat(appartmentDocument.getTypeChauffage()).isEqualTo(appartment.getTypeChauffage());
		assertThat(appartmentDocument.getTypeCommerce()).isEqualTo(appartment.getTypeCommerce());
		assertThat(appartmentDocument.getTypeCuisine()).isEqualTo(appartment.getTypeCuisine());
		assertThat(appartmentDocument.getUrl()).isEqualTo(appartment.getUrl());
	}
}
