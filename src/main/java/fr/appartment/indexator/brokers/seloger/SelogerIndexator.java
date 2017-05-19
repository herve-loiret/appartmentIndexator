package fr.appartment.indexator.brokers.seloger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.appartment.indexator.brokers.DataIndexator;
import fr.appartment.indexator.domain.Appartment;

@Component
public class SelogerIndexator implements DataIndexator {

	@Autowired
	private SelogerClient client;

	@Override
	public List<Appartment> index(List<String> postalCodes, Integer minPrice, Integer maxPrice) {

		int page = 1;
		while (page < 2) {

			String pageContent = client.getPage(postalCodes, minPrice, maxPrice, page);

			page++;
		}

		return null;
	}

}
