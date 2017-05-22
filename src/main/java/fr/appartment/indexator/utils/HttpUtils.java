package fr.appartment.indexator.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import lombok.SneakyThrows;

public final class HttpUtils {

	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT x.y; WOW64; rv:10.0) Gecko/20100101 Firefox/10.0";

	@SneakyThrows
	public static String performGet(String url) {
		
		StringBuilder result = new StringBuilder();

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);

		try (BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		}
		return result.toString();

	}
}
