package com.democode.trial.properties.types;

public interface PropertyTypeValidator {

	boolean isValid(String value);
	
	public static boolean isEmpty(String value) {

		return value == null || value.isEmpty();
	}

}
