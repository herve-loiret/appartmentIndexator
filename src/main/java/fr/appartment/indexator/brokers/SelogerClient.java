package fr.appartment.indexator.brokers;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!local")
public class SelogerClient implements Client {

	@Override
	public void test() {
		System.out.println("seloger client");
	}

	// http://www.seloger.com/list.htm?ci=750110&tri=initial&idtt=2&idtypebien=1,2&naturebien=1,2,4&LISTING-LISTpg=22

	public String generateUrl(List<String> postalCodes, int minPrice, int maxPrice, int page) {
		
		
		String baseUrl = "http://www.seloger.com/list.htm?idtt=2&naturebien=1,2,4&idtypebien=1,2,9&tri=initial";
		baseUrl += "&ci=" + postalCodes.get(0);
		baseUrl += "&pxmin=" + String.valueOf(minPrice);
		baseUrl += "&pxmax=" + String.valueOf(maxPrice);
		
		if(page > 1){
			baseUrl += "&LISTING-LISTpg=" + String.valueOf(page);
		}
		
		return baseUrl;
	}

}
