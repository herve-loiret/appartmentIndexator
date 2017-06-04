package fr.appartment.indexator.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class IndexationCreationDto {

	private Integer minPrice;
	private Integer maxPrice;
	
	@NotNull
	private List<String> searchKeywords;
	
}
