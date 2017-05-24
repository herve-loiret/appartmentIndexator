package fr.appartment.indexator.brokers;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;

import fr.appartment.indexator.domain.Appartment;

public interface Indexator {

	@Async
	Future<List<Appartment>> processIndex(List<String> postalCodes, Integer minPrice, Integer maxPrice);
	
}
