package fr.appartment.indexator.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories(basePackages = { "fr.appartment.indexator" }, multicoreSupport = true)
public class SolrContext {

	@Value("${solr.host}")
	private String solrHost;

	@Bean
	public SolrClient solrClient() {
		return new HttpSolrClient(solrHost);
	}

}
