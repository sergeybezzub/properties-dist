package com.democode.trial.properties.types;

public class DoublePropertyTypeValidator implements PropertyTypeValidator {

	@Override
	public boolean isValid(String value) {
		if( PropertyTypeValidator.isEmpty(value) ) {
			return false;
		}
		
		String regex = "([0-9]*)\\.([0-9]*)";
		return value.matches(regex);
	}

}
