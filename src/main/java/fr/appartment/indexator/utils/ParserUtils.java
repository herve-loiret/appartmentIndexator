package fr.appartment.indexator.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ParserUtils {

	public static Boolean parseNumberBoolean(Object object) {
		return object == null ? null : "1".equals(parseString(object));
	}

	public static Double parseDouble(Object object) {
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

	public static Integer parseInt(Object object) {
		return object == null ? null : Integer.valueOf(String.valueOf(object));
	}

	public static String parseString(Object object) {
		return object == null ? null : String.valueOf(object);
	}

	public static List<String> parseStringArray(Object[] objects) {
		if (objects == null) {
			return null;
		}
		return Arrays.asList(objects).stream()
				.map(Object::toString)
				.collect(Collectors.toList());
	}

}
