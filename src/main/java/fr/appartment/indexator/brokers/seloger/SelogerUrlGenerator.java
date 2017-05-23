package fr.appartment.indexator.brokers.seloger;

import java.util.List;

import org.springframework.stereotype.Component;

import fr.appartment.indexator.brokers.UrlGenerator;
import fr.appartment.indexator.domain.Appartment;

@Component
public class SelogerUrlGenerator implements UrlGenerator {

	@Override
	public String generateSearchUrl(List<String> postalCodes, Integer minPrice, Integer maxPrice, int page) {

		String baseUrl = "http://www.seloger.com/list.htm?idtt=2&naturebien=1,2,4&idtypebien=1,2,9&tri=d_dt_crea";
		baseUrl += "&cp=" + postalCodes.get(0);
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

}
