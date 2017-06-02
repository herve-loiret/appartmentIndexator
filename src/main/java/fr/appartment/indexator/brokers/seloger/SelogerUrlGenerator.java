package fr.appartment.indexator.brokers.seloger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.appartment.indexator.brokers.UrlGenerator;
import fr.appartment.indexator.brokers.seloger.SelogerIndexator.AppartmentDetail;
import fr.appartment.indexator.domain.Appartment;
import lombok.Data;
import lombok.SneakyThrows;

@Component
public class SelogerUrlGenerator implements UrlGenerator {

	@Autowired
	private SelogerClient selogerClient;
	
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * url explanation
	 * <p>
	 * NATUREBIEN : 1=ancien 2=neuf 4=projet
	 * <p>
	 * IDTYPEBIEN : 1=appartment 2=Maison/Villa 3=parking 4=terrain 6=boutique
	 * 7=local commercial 8=bureau 9=loft/atelier/surface 10=divers 11=immeuble
	 * 11=batiment 12=chateau 13=hotel particulier
	 * <p>
	 * IDTT : 1=louer 2=acheter 3=location temporaire 4=location vacance 5=vente
	 * viager 6=produit investissement 8=vente prestige
	 * <p>
	 * CP : code postal
	 * <p>
	 * DIV : 2238=ile de france
	 * <p>
	 * TRI : a_px/d_px=prix ascendant/descendant a_surface/d_surface=surface
	 * a_ville/d_ville=localite d_dt_crea=date descendante
	 * 
	 * @param searchKeywords
	 * @param minPrice
	 * @param maxPrice
	 * @param page
	 * @return
	 */
	@Override
	public String generateSearchUrl(List<String> searchKeywords, Integer minPrice, Integer maxPrice, int page) {

		searchKeywordsToParameters(searchKeywords);
		
		String baseUrl = "http://www.seloger.com/list.htm?idtt=2&naturebien=1&idtypebien=1,2,9&tri=d_dt_crea";
		baseUrl += "&cp=" + searchKeywords.get(0);
		if (minPrice != null) {
			baseUrl += "&pxmin=" + String.valueOf(minPrice);
		}
		if (maxPrice != null) {
			baseUrl += "&pxmax=" + String.valueOf(maxPrice);
		}

		if (page > 1) {
			baseUrl += "&LISTING-LISTpg=" + String.valueOf(page);
		}

		return baseUrl;
	}

	@Override
	public String generateDetailsUrl(Appartment appartment) {
		return "http://www.seloger.com/detail,json,caracteristique_bien.json?idannonce=" + appartment.getExternalId();
	}

	@SneakyThrows
	private List<Parameter> searchKeywordsToParameters(List<String> postalCodes) {

		List<Parameter> parameters = new ArrayList<>();

		for (String searchKeyword : postalCodes) {
			
			String responsePage = selogerClient.getAutocompletePage(searchKeyword);
			
			
			AutoCompleteResult[] autoCompleteResult = mapper.readValue(responsePage, AutoCompleteResult[].class);
			
		}

		return parameters;
	}

	public String getAutocompleteUrl(String search) {
		return "http://autocomplete.svc.groupe-seloger.com/auto/complete/fra/63/6?text=" + search;
	}
	
	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	static class AutoCompleteResult{
		private String type;
		private List<String> params;
	}

	@Data
	private class Parameter {
		String name;
		String value;
	}

}
