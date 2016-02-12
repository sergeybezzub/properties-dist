package com.democode.trial.properties.types;

public class BooleanPropertyTypeValidator implements PropertyTypeValidator {

	private static final String FALSE = "false";
	private static final String TRUE = "true";

	@Override
	public boolean isValid(String value) {
		return TRUE.equalsIgnoreCase(value) || FALSE.equalsIgnoreCase(value);
	}

}
