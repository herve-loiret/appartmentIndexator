package fr.appartment.indexator.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import fr.appartment.indexator.domain.Appartment;
import fr.appartment.indexator.repository.solr.AppartmentDocument;
import fr.appartment.indexator.repository.solr.SolrMapper;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "local")
public class AppartmentRepositoryIT {
	/**
	 * TODO mock solr
	 */
	@Autowired
	private SolrMapper mapper;

	@Autowired
	private AppartmentRepository repository;

	@Test
	public void should_save_and_delete_an_appartment() {
		Appartment appartment = new PodamFactoryImpl().manufacturePojo(Appartment.class);
		AppartmentDocument document = mapper.appartmentToAppartmentDocument(appartment);
		AppartmentDocument result = repository.save(document);
		assertThat(result).isNotNull();
	}
}
