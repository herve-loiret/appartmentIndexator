package fr.appartment.indexator.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class Appartment {

	private String externalId;
	private LocalDateTime indexationTime;
	private String url;

	private Double price;
	private Double surface;
	private String description;
	private String type;
	private String postalCode;
	private Integer etage;
	private String typeChauffage;
	private String typeCommerce;
	private String typeCuisine;
	private Boolean hasBalcon;
	private Integer nbChambres;
	private Integer nbPieces;
	private Boolean hasSdbain;
	private Boolean hasSdEau;
	private Integer nbPhotos;
	private List<String> typeTransactions;

	public Double getPriceByMeter() {
		return price / surface;
	}

}
