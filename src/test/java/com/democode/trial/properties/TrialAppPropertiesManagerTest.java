package com.democode.trial.properties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TrialAppPropertiesManagerTest {
	
	AppPropertiesManager manager;
	TrialAppPropertiesManager managerMock;

	List<String> uriList;
	
	@Before
	public void init() {
		 manager = new TrialAppPropertiesManager();
		 uriList = new ArrayList<String>();
		 uriList.add("/aws.properties");
		 uriList.add("/config.json");
		 uriList.add("/jdbc.properties");
		 uriList.add("/incorrect.properties");

		 managerMock = Mockito.spy(new TrialAppPropertiesManager());
	}

	@Test
	public void loadPropertiesPositive() {
	
		AppProperties properties = manager.loadProps(uriList);
		assertEquals(14, properties.getKnownProperties().size());		

		assertEquals("broadcast",properties.get("sns_broadcast_topic.name"));
		assertEquals("us-east-1",properties.get("Aws.Region.Id"));
		assertEquals("jdbc:mysql://localhost/test",properties.get("jdbc.url"));
	}

	@Test
	public void printPropertiesPositive() throws IOException {
	
		File tempFile = File.createTempFile("prefix-", "-suffix");
		tempFile.deleteOnExit();
		
		AppProperties properties = manager.loadProps(uriList);
		assertEquals(14, properties.getKnownProperties().size());		

		AppProperties mockProperties = Mockito.spy(properties);
		
		manager.printProperties(mockProperties, new PrintStream(new FileOutputStream(tempFile)));
		
		Mockito.verify(mockProperties, Mockito.times(1)).getKnownProperties();
		Mockito.verify(mockProperties, Mockito.times(properties.getKnownProperties().size())).get(Mockito.any());
	}

	
	@Test
	public void incorrectJsonClassPathLoadingShouldCallErrorHandlerMethod() {
		List<String> uris = new ArrayList<String>();
		uris.add("/incorrect.json");
	
			
		managerMock.loadProps(uris);
		
		Mockito.verify(managerMock, Mockito.atLeast(1)).errorHandler(Mockito.any(Exception.class), Mockito.anyString());
	}

	@Test
	public void correctJsonClassPathLoadingShouldNotCallErrorHandlerMethod() {
		List<String> uris = new ArrayList<String>();
		uris.add("/config.json");
	
		managerMock.loadProps(uris);
		
		Mockito.verify(managerMock, Mockito.times(0)).errorHandler(Mockito.any(Exception.class), Mockito.anyString());
	}


	@Test
	public void notExistJsonClassPathLoadingShouldntCallErrorHandlerMethod() {
		List<String> uris = new ArrayList<String>();
		uris.add("/notexist.json");
	
		managerMock.loadProps(uris);
		
		Mockito.verify(managerMock, Mockito.times(1)).errorHandler(Mockito.any(Exception.class), Mockito.anyString());
	}

	@Test
	public void notExistJsonURILoadingShouldCallErrorHandlerMethod() {
		List<String> uris = new ArrayList<String>();
		uris.add("file:///c:/temp/notexist.json");
	
		managerMock.loadProps(uris);
		
		Mockito.verify(managerMock, Mockito.times(1)).errorHandler(Mockito.any(Exception.class), Mockito.anyString());
	}

	@Test
	public void notExistPropertiesURILoadingShouldCallErrorHandlerMethod() {
		List<String> uris = new ArrayList<String>();
		uris.add("file:///c:/temp/notexist.properties");
	
		managerMock.loadProps(uris);
		
		Mockito.verify(managerMock, Mockito.times(1)).errorHandler(Mockito.any(Exception.class), Mockito.anyString());
	}

	@Test
	public void incorectPropertiesLoadingShouldCallErrorHandlerMethod() {
		List<String> uris = new ArrayList<String>();
		uris.add("/incorrect.properties");
	
		managerMock.loadProps(uris);
		
		Mockito.verify(managerMock, Mockito.times(1)).errorHandler(Mockito.any(Exception.class), Mockito.anyString());
	}

	@Test
	public void corectPropertiesLoadingShouldNotCallErrorHandlerMethod() {
		List<String> uris = new ArrayList<String>();
		uris.add("/jdbc.properties");
	
		managerMock.loadProps(uris);
		
		Mockito.verify(managerMock, Mockito.times(0)).errorHandler(Mockito.any(Exception.class), Mockito.anyString());
	}
	
}
