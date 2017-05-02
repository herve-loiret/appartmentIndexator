package fr.appartment.indexator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.appartment.indexator.brokers.Client;

@Service
public class IndexatorService {

	@Autowired
	private List<Client> clients;
	
	public void index(List<String> postalCode, Integer minPrice, Integer maxPrice) {
		for(Client client : clients){
			client.test();
		}
	}

}
