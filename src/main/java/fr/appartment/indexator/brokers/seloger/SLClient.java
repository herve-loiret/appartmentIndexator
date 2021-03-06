package fr.appartment.indexator.brokers.seloger;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import fr.appartment.indexator.brokers.Client;
import fr.appartment.indexator.domain.Appartment;
import fr.appartment.indexator.utils.HttpUtils;

@Component
@Profile("!local")
public class SLClient implements Client {

	@Autowired
	protected SLUrlGenerator urlGenerator;

	@Override
	public String getSearchPage(List<String> postalCodes, Integer minPrice, Integer maxPrice, int page)
			throws IOException {
		String url = urlGenerator.generateSearchUrl(postalCodes, minPrice, maxPrice, page);
		return HttpUtils.performGet(url);
	}

	@Override
	public String getDetailsPage(Appartment appartment) throws IOException {
		return HttpUtils.performGet(urlGenerator.generateDetailsUrl(appartment));
	}

	public String getAutocompletePage(String search) throws IOException {
		return HttpUtils.performGet(urlGenerator.getAutocompleteUrl(search));
	}

}
