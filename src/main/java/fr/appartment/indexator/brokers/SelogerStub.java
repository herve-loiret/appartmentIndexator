package fr.appartment.indexator.brokers;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class SelogerStub implements Client {

	@Override
	public void test() {
		System.out.println("seloger stub");
	}
	

}
