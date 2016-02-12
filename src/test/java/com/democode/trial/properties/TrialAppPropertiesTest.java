package com.democode.trial.properties;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TrialAppPropertiesTest {
	
	TrialAppProperties properties;
	
	@Before
	public void init() {
		properties = new TrialAppProperties();
		properties.put("key.1", "key1");
		properties.put("key_2", "key2");
		properties.put("KEY_3", "key3");
		properties.put("KeY_4", "key4");
	}
	
	/**
	 * Checks if Key is handled without case sensitivity. 
	 * Besides that test checks if '.' and '_' are treated as equivalent.
	 */
	@Test
	public void getPropertyPositive() {
		assertEquals("key1", properties.get("key_1"));
		assertEquals("key2", properties.get("key.2"));
		assertEquals("key3", properties.get("kEy.3"));
		assertEquals("key4", properties.get("KEy.4"));
	}

	@Test
	public void missingPropertiesPositive() {
		assertEquals(0, properties.getMissingProperties().size());
	}

	@Test
	public void clearPropertiesPositive() {
		properties.clear();
		assertEquals(0, properties.getKnownProperties().size());
	}

}
