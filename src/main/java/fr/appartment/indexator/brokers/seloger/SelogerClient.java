package fr.appartment.indexator.brokers.seloger;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import fr.appartment.indexator.brokers.Client;
import fr.appartment.indexator.utils.HttpUtils;

@Component
@Profile("!local")
public class SelogerClient implements Client {

	private SelogerUrlGenerator urlGenerator;

	public SelogerClient(SelogerUrlGenerator urlGenerator) {
		this.urlGenerator = urlGenerator;
	}

	@Override
	public String getPage(List<String> postalCodes, Integer minPrice, Integer maxPrice, int page) {
		String url = urlGenerator.generateUrl(postalCodes, minPrice, maxPrice, page);
		return HttpUtils.performGet(url);
	}

}
