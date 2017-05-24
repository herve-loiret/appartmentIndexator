package fr.appartment.indexator.brokers;

import java.util.List;

import org.springframework.scheduling.annotation.Async;

import fr.appartment.indexator.domain.Appartment;

public interface Indexator {

	@Async
	List<Appartment> processIndex(List<String> postalCodes, Integer minPrice, Integer maxPrice);

}
