package fr.appartment.indexator.repository.solr;

import java.time.LocalDateTime;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.Data;

@Data
@SolrDocument(solrCoreName = "appartmentIndexator")
public class AppartmentDocument {

	@Id
	@Field
	private Long id;
	@Field
	private String externalId;
	@Field
	private LocalDateTime indexationTime;
	@Field
	private String url;
	@Field
	private Double price;
	@Field
	private Double surface;
	@Field
	private String description;
	@Field
	private String type;
	@Field
	private String postalCode;
	@Field
	private Integer etage;
	@Field
	private String typeChauffage;
	@Field
	private String typeCommerce;
	@Field
	private String typeCuisine;
	@Field
	private Boolean hasBalcon;
	@Field
	private Integer nbChambres;
	@Field
	private Integer nbPieces;
	@Field
	private Boolean hasSdbain;
	@Field
	private Boolean hasSdEau;
	@Field
	private Integer nbPhotos;
	@Field
	private Double priceByMeter;

}
