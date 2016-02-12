package com.democode.trial.properties.util;

import org.junit.Test;

import com.democode.trial.properties.types.SupportedPropertiesType;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class PropertyTypeUtilTest {

	@Test
	public void getIntegerType() {
		assertTrue(SupportedPropertiesType.INTEGER == PropertyTypeUtil.getPropertyType("1234"));
		assertEquals("java.lang.Integer",PropertyTypeUtil.getPropertyType("1234").getType());
	}

	@Test
	public void getDoubleType() {
		assertTrue(SupportedPropertiesType.DOUBLE == PropertyTypeUtil.getPropertyType("12.34"));
		assertEquals("java.lang.Double",PropertyTypeUtil.getPropertyType("12.34").getType());
	}


	@Test
	public void getBooleanType() {
		assertTrue(SupportedPropertiesType.BOOLEAN == PropertyTypeUtil.getPropertyType("true"));
		assertEquals("java.lang.Boolean",PropertyTypeUtil.getPropertyType("false").getType());
	}
	
	@Test
	public void getOtherBooleanType() {
		assertTrue(SupportedPropertiesType.BOOLEAN == PropertyTypeUtil.getPropertyType("FALSE"));
		assertEquals("java.lang.Boolean",PropertyTypeUtil.getPropertyType("TRUE").getType());
	}

	@Test
	public void getAmazonWSRegionsType() {
		assertTrue(SupportedPropertiesType.AWS_REGIONS == PropertyTypeUtil.getPropertyType("us-east-1"));
		assertEquals("com.amazonaws.regions.Regions",PropertyTypeUtil.getPropertyType("us-east-1").getType());
	}

	@Test
	public void getOtherAmazonWSRegionsType() {
		assertTrue(SupportedPropertiesType.AWS_REGIONS == PropertyTypeUtil.getPropertyType("us-west-1"));
		assertEquals("com.amazonaws.regions.Regions",PropertyTypeUtil.getPropertyType("us-west-1").getType());
	}

	@Test
	public void getStringType() {
		assertTrue(SupportedPropertiesType.DEFAULT == PropertyTypeUtil.getPropertyType("abcdefg123"));
		assertEquals("java.lang.String",PropertyTypeUtil.getPropertyType("abcdefg123").getType());		
	}

}
