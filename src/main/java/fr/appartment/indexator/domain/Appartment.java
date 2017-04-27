package fr.appartment.indexator.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Appartment {

	private Long id;
	private Integer price;
	private String description;
	
	private LocalDateTime indexationTime;
	private String url;
	
}
