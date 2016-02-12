package com.democode.trial.properties.util;

import com.democode.trial.properties.types.SupportedPropertiesType;

public class PropertyTypeUtil {
	/**
	 * Recognizing a type of property.
	 * All supported properties types have to be described in the SupportedPropertiesType enum
	 * @param value of property
	 * @return type of property. Returns default if property type is not described in the SupportedPropertiesType enum
	 */
	public static SupportedPropertiesType getPropertyType(String value) {
	
		for( SupportedPropertiesType type:SupportedPropertiesType.values() ) {
			if(type == SupportedPropertiesType.DEFAULT) {
				continue;
			}
			if(type.getValidator().isValid(value)) {
				return type;
			}
		}

		return SupportedPropertiesType.DEFAULT;
	}
}
