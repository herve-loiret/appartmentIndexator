package fr.appartment.indexator.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.appartment.indexator.dto.IndexationCreationDto;
import fr.appartment.indexator.service.IndexatorService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/indexator", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AppartmentIndexatorController {

	private IndexatorService indexatorService;

	@PostMapping(value = "/index")
	public void postTransaction(@RequestBody IndexationCreationDto indexationCreationDto) {
		indexatorService.index(indexationCreationDto.getSearchKeywords(), indexationCreationDto.getMinPrice(),
				indexationCreationDto.getMaxPrice());
	}
}
