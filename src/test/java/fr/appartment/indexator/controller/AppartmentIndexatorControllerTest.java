package fr.appartment.indexator.controller;

import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.appartment.indexator.dto.IndexationCreationDto;
import fr.appartment.indexator.service.IndexatorService;
import lombok.SneakyThrows;

@RunWith(SpringRunner.class)
@WebMvcTest(AppartmentIndexatorController.class)
public class AppartmentIndexatorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IndexatorService indexatorService;

	@Test
	@SneakyThrows
	public void should_indexation_creation_work() {
		int minPrice = 100_000;
		int maxPrice = 300_000;
		List<String> postalCodes = Arrays.asList("75011");

		IndexationCreationDto indexationCreationDto = new IndexationCreationDto();
		indexationCreationDto.setMinPrice(minPrice);
		indexationCreationDto.setMaxPrice(maxPrice);
		indexationCreationDto.setPostalCodes(postalCodes);
		String json = new ObjectMapper().writeValueAsString(indexationCreationDto);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/indexator/index").contentType(MediaType.APPLICATION_JSON).content(json));

		verify(indexatorService).index(postalCodes, minPrice, maxPrice);
	}

}
