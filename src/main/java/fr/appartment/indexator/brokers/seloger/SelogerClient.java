package fr.appartment.indexator.brokers.seloger;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import fr.appartment.indexator.brokers.Client;
import fr.appartment.indexator.domain.Appartment;
import fr.appartment.indexator.utils.HttpUtils;

@Component
@Profile("!local")
public class SelogerClient implements Client {

	protected SelogerUrlGenerator urlGenerator;

	public SelogerClient(SelogerUrlGenerator urlGenerator) {
		this.urlGenerator = urlGenerator;
	}

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
