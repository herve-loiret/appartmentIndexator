package fr.appartment.indexator.brokers;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class ClientStub implements Client {

	@Override
	public String getPage(List<String> postalCodes, int minPrice, int maxPrice, int page) {
		// TODO Auto-generated method stub

		return "";
	}

}
