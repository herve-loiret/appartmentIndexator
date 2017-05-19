package fr.appartment.indexator.brokers.seloger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!local")
public class SelogerClientImpl implements SelogerClient {

	@Autowired
	private SelogerUrlGenerator urlGenerator;
	
	@Override
	public String getPage(List<String> postalCodes, Integer minPrice, Integer maxPrice, int page) {

//		String url = urlGenerator.generateUrl(postalCodes, minPrice, maxPrice, page);
//		
//		System.out.println("seloger client");
		
		return "";
	}

}
