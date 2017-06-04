package fr.appartment.indexator.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "local")
public class IndexatorServiceIT {

	@Autowired
	private IndexatorService indexatorService;

	@Test
	public void should_index() {
		List<String> postalCodes = Arrays.asList("ile");
		int minPrice = 1000;
		int maxPrice = 300000;

		indexatorService.index(postalCodes, minPrice, maxPrice);
	}

}
