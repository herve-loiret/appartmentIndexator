package fr.appartment.indexator.brokers.seloger;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.appartment.indexator.brokers.DataIndexator;
import fr.appartment.indexator.domain.Appartment;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SelogerIndexator implements DataIndexator {

	private static final int APPARTMENT_DATA_SCRITP_POSITION = 2;
	@Autowired
	private SelogerClient client;

	@Override
	public List<Appartment> index(List<String> postalCodes, Integer minPrice, Integer maxPrice) {

		int page = 1;
		while (page < 2) {

			String pageContent = client.getPage(postalCodes, minPrice, maxPrice, page);

			deserializeAppartment(pageContent);
			page++;
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private List<Appartment> deserializeAppartment(String pageContent) {

		List<Appartment> appartments = new ArrayList<>();

		Document page = Jsoup.parse(pageContent);
		String scriptContent = page.getElementsByTag("script").get(APPARTMENT_DATA_SCRITP_POSITION).data();
		scriptContent = scriptContent.replaceAll("logged: logged,", "");
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		try {
			engine.eval(scriptContent);
			Map<String, Object> products = (Map<String, Object>) ((Map<String, Object>) engine.get("ava_data"))
					.get("products");
			for (Entry<String, Object> productsEntry : products.entrySet()) {

				Map<String, Object> product = (Map<String, Object>) productsEntry.getValue();
				Appartment appartment = new Appartment();
				appartment.setExternalId(parseString(product.get("idannonce")));
				appartment.setUrl(findAppartmentUrl(page, product));
				appartment.setPostalCode(parseString(product.get("cp")));
				appartment.setEtage(parseInt(product.get("etage")));
				appartment.setPrice(parseDouble(product.get("prix")));
				appartment.setSurface(parseDouble(product.get("surface")));
				appartment.setNbPhotos(parseInt(product.get("nb_photos")));
				System.out.println(appartment);

			}
		} catch (ScriptException e) {
			e.printStackTrace();
			log.error("error while trying to index appartment", e);
		}

		return appartments;
	}

	private Double parseDouble(Object object) {
		NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
		if (object == null) {
			return null;
		} else {
			try {
				return format.parse(String.valueOf(object)).doubleValue();
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	private Integer parseInt(Object object) {
		if (object == null) {
			return null;
		} else {
			return Integer.valueOf(String.valueOf(object));
		}
	}

	private String parseString(Object object) {
		if (object == null) {
			return null;
		} else {
			return String.valueOf(object);
		}
	}

	private String findAppartmentUrl(Document page, Map<String, Object> product) {
		int position = parseInt(product.get("position"));
		Element listInfos = page.getElementsByClass("listing_infos").get(position);
		String url = listInfos.getElementsByTag("a").first().attr("href");
		return url;
	}

}
