package fr.appartment.indexator.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import fr.appartment.indexator.domain.Appartment;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "local")
public class SolrAppartmentRepositoryIT {

	@Autowired
	private SolrAppartmentRepository repository;

	@Test
	public void should_save_and_delete_an_appartment() {
		Appartment appartment = new Appartment();
		appartment.setDescription("test");
		repository.save(appartment);
	}
}
