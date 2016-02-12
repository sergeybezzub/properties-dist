package com.democode.trial.properties.util;

import java.util.Map;
import java.util.Properties;

import com.codesnippets4all.json.parsers.JSONParser;
import com.codesnippets4all.json.parsers.JsonParserFactory;

public class JsonUtil {

	public static Properties parseJsonToProperties(String json) {
		JsonParserFactory factory=JsonParserFactory.getInstance();
		JSONParser parser=factory.newJsonParser();
		Properties p = new Properties();

		try {
			@SuppressWarnings("unchecked")
			Map<String, String> jsonMap = parser.parseJson(json);	
			p.putAll(jsonMap);
		} catch(Exception e) {
			String msg="JSON source has incorrect format!\n"+json+"\nRoot case: " + e;
			ErrorMessageUtil.reportError(e, msg);
			throw new IllegalArgumentException(msg, e);
		}

		return p;
	}
	
}
