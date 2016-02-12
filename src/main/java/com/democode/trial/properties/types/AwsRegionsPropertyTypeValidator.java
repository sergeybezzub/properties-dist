package com.democode.trial.properties.types;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

public class AwsRegionsPropertyTypeValidator implements PropertyTypeValidator {

	private static final String UNDERSCORE_SYMBOL = "_";
	private static final String DASH_SYMBOL = "-";
	Region region;

	@Override
	public boolean isValid(String value) {
		if( PropertyTypeValidator.isEmpty(value) ) {
			return false;
		}
		
		value=value.replace(DASH_SYMBOL, UNDERSCORE_SYMBOL);

		for( Regions regions : Regions.values() ) {
			if( value.equalsIgnoreCase(regions.name())) {
				return true;
			}
		}
		
		return false;
	}

}
