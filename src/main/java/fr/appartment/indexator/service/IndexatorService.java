package fr.appartment.indexator.service;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;

import javax.xml.transform.Result;

import org.springframework.stereotype.Service;

import fr.appartment.indexator.brokers.Indexator;
import fr.appartment.indexator.domain.Appartment;
import lombok.SneakyThrows;

@Service
public class IndexatorService {

	private List<Indexator> indexators;

	private AppartmentService appartmentService;

	public IndexatorService(List<Indexator> indexators, AppartmentService appartmentService) {
		this.indexators = indexators;
		this.appartmentService = appartmentService;
	}

	@SneakyThrows
	public void index(List<String> postalCodes, Integer minPrice, Integer maxPrice) {
		for (Indexator indexator : indexators) {
			Future<List<Appartment>> appartments = indexator.processIndex(postalCodes, minPrice, maxPrice);
			appartmentService.save(appartments.get());
		}

		
		// TODO use that :
//		CompletionService<Result> ecs = new ExecutorCompletionService<Result>(e);
//		for (Callable<Result> s : solvers){
//			ecs.submit(s);
//		}
//		int n = solvers.size();
//		for (int i = 0; i < n; ++i) {
//			Result r = ecs.take().get();
//			if (r != null){
//				use(r);
//			}
//		}
	}

}
