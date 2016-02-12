package com.democode.trial.properties;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import com.democode.trial.properties.util.ErrorMessageUtil;
import com.democode.trial.properties.util.JsonUtil;
import com.democode.trial.properties.util.PropertyTypeUtil;

/**
 * A simple method to load and print properties.
 */
public class TrialAppPropertiesManager implements AppPropertiesManager {

	private static final String FILE_URI_PREFIX = "file://";
	private static final String HTTP_URI_PREFIX = "http://";
	private static final String DEFAULT_CHAR_SET = "UTF-8";
	private static final String JSON_FILE_SUFFIX = ".json";
	private static final String PROPERTIES_FILE_SUFFIX = ".properties";

	@Override
	public AppProperties loadProps(List<String> propUris) {
		TrialAppProperties properties = new TrialAppProperties();
		
		for(String uri : propUris) {
			switch(getUriType(uri)) {
			
				case 0:
					if(uri.endsWith(PROPERTIES_FILE_SUFFIX)) {
						properties = loadPropertiesFromURL(properties, uri);
					} else if(uri.endsWith(JSON_FILE_SUFFIX)) {
						properties = loadJsonFromURI(properties, uri); 
					}
					break;
				default:
					if(uri.endsWith(PROPERTIES_FILE_SUFFIX)) {
						properties = loadPropertiesFromClassPath(properties, uri);
					}  else if(uri.endsWith(JSON_FILE_SUFFIX)) {
						properties = loadJsonFromClassPath(properties, uri); 
					}
			}
		}
		return properties;
	}

	@Override
	public void printProperties(AppProperties props, PrintStream sync) {
		for( Object key : props.getKnownProperties() ) {
			Object value = props.get((String)key);
			sync.println(""+key+","+PropertyTypeUtil.getPropertyType((String)value).getType()+","+value);
		}
	}

	/**
	 * Added to be able to test behavior in easer way 
	 * @param e exception that should be handled
	 * @param errorMessage 
	 */
	public void errorHandler(Exception e, String errorMessage) {
		ErrorMessageUtil.reportError(e, errorMessage);
	}
	
	private TrialAppProperties loadPropertiesFromURL(TrialAppProperties properties, String uri) {
		Properties tmpProp = new Properties();

		try( final InputStream in = createUrlInputStream(uri);
			 Reader reader = new InputStreamReader(in, DEFAULT_CHAR_SET);) {
			
			tmpProp.load(reader);
			properties.putAll(tmpProp);
		} catch (IOException | IllegalArgumentException e) {
			errorHandler(e, "Populate properties from uri["+uri+"] has failed! Root case: " + e);
		}
		
		return properties;
	}
	
	private TrialAppProperties loadPropertiesFromClassPath(TrialAppProperties properties, String uri) {
		Properties tmpProp = new Properties();
		try (final InputStream stream = createClassPathInputStream(uri)) {
			
			tmpProp.load(stream);
			properties.putAll(tmpProp);
		} catch (IOException | IllegalArgumentException e) {
			errorHandler(e, "Populate properties from ClassPath["+uri+"] has failed! Root case: " + e);
		}
		
		return properties;
	}

	private TrialAppProperties loadJsonFromURI(TrialAppProperties properties, String uri) {
	    String result="";
		try( final InputStream in = createUrlInputStream(uri);
			 Reader reader = new InputStreamReader(in, DEFAULT_CHAR_SET);) {

			result = readStreamData(reader);
			Properties tmpProp = JsonUtil.parseJsonToProperties(result);
			properties.putAll(tmpProp);

		} catch (IOException | IllegalArgumentException e) {
			errorHandler(e, "Populate properties from JSON uri["+uri+"] has failed! Root case: " + e);
		}
		
		return properties;
	  }

	private TrialAppProperties loadJsonFromClassPath(TrialAppProperties properties, String uri) {
	    String result="";
		try( final InputStream in = createClassPathInputStream(uri);
			 Reader reader = new InputStreamReader(in, DEFAULT_CHAR_SET);) {

			result = readStreamData(reader);
			Properties tmpProp = JsonUtil.parseJsonToProperties(result);
			properties.putAll(tmpProp);

		} catch (IOException | IllegalArgumentException | NullPointerException e) {
			errorHandler(e, "Populate properties from JSON uri["+uri+"] has failed! Root case: " + e);
		}	

		return properties;
	  }

	private String readStreamData(Reader reader) throws IOException {
		StringBuilder builder = new StringBuilder();
		int value;
		while ((value = reader.read()) != -1) {
		  builder.append((char) value);
		}
		return builder.toString();
	}
	
	private InputStream createUrlInputStream(String uri) throws IOException {
		return new URL(uri).openStream();
	}
	
	private InputStream createClassPathInputStream(String uri) throws IOException {
		return getClass().getResourceAsStream(uri);
	}

	/**
	 * Returns type of URI
	 * @param uri string that contains protocol + path to the properties file
	 * @return 0 - if URI is used URL and returns 1 for classPath and all other cases
	 */
	private int getUriType(String uri) {
		
		if(uri.startsWith(HTTP_URI_PREFIX) || uri.startsWith(FILE_URI_PREFIX)) {
			return 0;
		}
		else {
			return 1;
		}
		
	}
	
}
