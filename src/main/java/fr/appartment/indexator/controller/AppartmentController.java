package fr.appartment.indexator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.appartment.indexator.domain.Appartment;
import fr.appartment.indexator.service.AppartmentService;

@RestController
@RequestMapping(path = "/appartment", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppartmentController {

	@Autowired
	private AppartmentService appartmentService;

	@DeleteMapping
	public void deleteAllAppartment() {
		appartmentService.deleteAllAppartment();
	}

	@GetMapping
	public List<Appartment> getAllAppartments() {
		return appartmentService.findAllAppartments();
	}

	@GetMapping(value = "/count")
	public Long getCountAppartments() {
		return appartmentService.countAllAppartments();
	}

}
