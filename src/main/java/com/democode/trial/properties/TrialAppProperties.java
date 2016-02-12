package com.democode.trial.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of TrialAppProperties
 */
public class TrialAppProperties extends Properties implements AppProperties {
	private static final String DOT_SYMBOL = ".";
	private static final String UNDERSCORE_SYMBOL = "_";
	/**
	 * 
	 */
	private static final long serialVersionUID = -8291886414616429168L;

	/**
	 * TODO Due to a time limitation, current implementation uses Hashtable to store properties, so it has not ability to store null values.
	 * Possible in the next release we will do some refactoring to use HashMap instead of Hashtable to be able to store keys 
	 * for missing values.
	 * Currently, that method will always return 0;
	 */
	@Override
	public List<String> getMissingProperties() {
		List<String> list = new ArrayList<String>();
		for(Object key : super.keySet()) {
			String value = (String) get((String)key);
			if(value == null) {
				list.add(key.toString());
			}
		}
		return list;
	}

	@Override
	public List<Object> getKnownProperties() {
		Set<Object> keys = super.keySet();
		List<Object> keyList = keys.stream().sorted().collect(Collectors.toList());	
		return keyList;
	}

	@Override
	public boolean isValid() {
		return super.keySet().size() == super.values().size();
	}

	@Override
	public void clear() {
		super.clear();
	}

	/**
     *  Retrieve the property for the given key. Keys are case-insenstive
     *  and the use of . and _ in property names is interchangable.
     */
	@Override
	public Object get(String key) {
		for( Object k : super.keySet() ) {
			String s = ""+k;
			if( s.replace(UNDERSCORE_SYMBOL, DOT_SYMBOL).equalsIgnoreCase(key.replace(UNDERSCORE_SYMBOL, DOT_SYMBOL))) {
				return super.get(k);
			}
		}

		return null;
	}

}

