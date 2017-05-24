package fr.appartment.indexator.service;

import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

import fr.appartment.indexator.brokers.Indexator;
import fr.appartment.indexator.domain.Appartment;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IndexatorService {

	private List<Indexator> indexators;

	private AppartmentService appartmentService;

	public IndexatorService(List<Indexator> indexators, AppartmentService appartmentService) {
		this.indexators = indexators;
		this.appartmentService = appartmentService;
	}

	@SneakyThrows
	public void index(List<String> postalCodes, Integer minPrice, Integer maxPrice) {

		if (indexators.size() == 0) {
			log.error("There is not indexators");
			return;
		}

		ExecutorService executorService = Executors.newFixedThreadPool(indexators.size());
		CompletionService<List<Appartment>> completionService = new ExecutorCompletionService<>(executorService);
		for (Indexator indexator : indexators) {
			completionService.submit(() -> indexator.processIndex(postalCodes, minPrice, maxPrice));
		}

		for (int i = 0; i < indexators.size(); i++) {
			Future<List<Appartment>> appartments = completionService.take();
			appartmentService.save(appartments.get());
		}

	}

}
