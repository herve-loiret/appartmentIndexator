package fr.appartment.indexator.brokers;

import java.io.IOException;
import java.util.List;

import fr.appartment.indexator.domain.Appartment;

public interface Client {

	String getSearchPage(List<String> postalCodes, Integer minPrice, Integer maxPrice, int page) throws IOException;

	String getDetailsPage(Appartment appartment) throws IOException;
}
