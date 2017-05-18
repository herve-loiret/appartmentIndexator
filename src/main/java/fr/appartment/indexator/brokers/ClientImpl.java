package fr.appartment.indexator.brokers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!local")
public class ClientImpl implements Client {

	@Autowired
	private SelogerUrlGenerator urlGenerator;
	
	@Override
	public String getPage(List<String> postalCodes, int minPrice, int maxPrice, int page) {

		String url = urlGenerator.generateUrl(postalCodes, minPrice, maxPrice, page);
		
		System.out.println("seloger client");
		
		return "";
	}

}
