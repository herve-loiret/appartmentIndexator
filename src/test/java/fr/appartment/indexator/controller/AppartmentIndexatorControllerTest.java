package fr.appartment.indexator.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import fr.appartment.indexator.controller.AppartmentIndexatorController;
import lombok.SneakyThrows;

@RunWith(SpringRunner.class)
@WebMvcTest(AppartmentIndexatorController.class)
public class AppartmentIndexatorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@SneakyThrows
	public void should_indexation_creation_work() {
		assertThat(false).isTrue(); // TODO
	}

}
