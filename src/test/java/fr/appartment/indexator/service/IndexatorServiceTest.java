package fr.appartment.indexator.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "local")
public class IndexatorServiceTest {

	  @Autowired
	  private IndexatorService indexatorService;
	  
	  @Test
	  public void should_(){
		  indexatorService.index(null, null, null);
	  }
	  
}
