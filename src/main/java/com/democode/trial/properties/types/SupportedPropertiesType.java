package com.democode.trial.properties.types;

/**
 * Enum describes all supported by application properties types.
 * Supported means - every described bellow type will be recognized and their type-value should be printed to the output.
 * Rest of properties will be represented by DEFAULT type.
 * We can extend the amount of supported by application types and have to do following for that:
 * 1) Implement own PropertyTypeValidator for that new type
 * 2) Add new value to the SupportedPropertiesType enum, see sample below. 
 * 
 * @author sergiy.bezzub
 *
 *TODO In the next releases we could discuss if we would like to use reflection and config file to describe supported files or maybe use Spring autowiring possibilities to do that.
 *Currently, enum implementation looks quite good and quite effortless solution.
 */
public enum SupportedPropertiesType {

	DEFAULT("java.lang.String", null),
	BOOLEAN("java.lang.Boolean", new BooleanPropertyTypeValidator()),
	INTEGER("java.lang.Integer", new IntegerPropertyTypeValidator()),
	DOUBLE("java.lang.Double", new DoublePropertyTypeValidator()),
	AWS_REGIONS("com.amazonaws.regions.Regions", new AwsRegionsPropertyTypeValidator());
	
	private String type;
	private PropertyTypeValidator validator;
	
	private SupportedPropertiesType(String type, PropertyTypeValidator validator)
	{
		this.type=type;
		this.validator=validator;
	}

	public String getType() {
		return type;
	}

	public PropertyTypeValidator getValidator() {
		return validator;
	}
	
}
