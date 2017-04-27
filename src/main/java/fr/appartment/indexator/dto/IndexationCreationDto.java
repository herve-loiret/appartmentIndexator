package fr.appartment.indexator.dto;

import java.util.List;

import lombok.Data;

@Data
public class IndexationCreationDto {

	private Integer minPrice;
	private Integer maxPrice;
	private List<String> postalCodes;
	
}
