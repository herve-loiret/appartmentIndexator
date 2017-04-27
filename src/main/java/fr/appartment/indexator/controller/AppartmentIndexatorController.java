package fr.appartment.indexator.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.appartment.indexator.dto.IndexationCreationDto;

@RestController
@RequestMapping(path = "/indexator", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppartmentIndexatorController {

	@PostMapping
	public void postTransaction(@RequestBody IndexationCreationDto indexationCreationDto) {
		
	}
}
