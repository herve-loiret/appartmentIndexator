package fr.appartment.indexator.brokers.seloger;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import fr.appartment.indexator.brokers.Client;
import fr.appartment.indexator.domain.Appartment;
import fr.appartment.indexator.utils.HttpUtils;
import lombok.SneakyThrows;

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

	@Override
	@SneakyThrows
	public String getDetailsPage(Appartment appartment) {
		String baseUrl = "http://www.seloger.com/detail,json,caracteristique_bien.json?idannonce=";
		String json = HttpUtils.performGet(baseUrl + appartment.getExternalId());

		
		return json;
	}

}
