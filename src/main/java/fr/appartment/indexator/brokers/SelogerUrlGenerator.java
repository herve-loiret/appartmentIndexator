package fr.appartment.indexator.brokers;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SelogerUrlGenerator implements UrlGenerator {

	@Override
	public String generateUrl(List<String> postalCodes, int minPrice, int maxPrice, int page) {

		String baseUrl = "http://www.seloger.com/list.htm?idtt=2&naturebien=1,2,4&idtypebien=1,2,9&tri=initial";
		baseUrl += "&cp=" + postalCodes.get(0);
		baseUrl += "&pxmin=" + String.valueOf(minPrice);
		baseUrl += "&pxmax=" + String.valueOf(maxPrice);

		if (page > 1) {
			baseUrl += "&LISTING-LISTpg=" + String.valueOf(page);
		}

		return baseUrl;
	}

}
