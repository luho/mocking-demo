package de.luho.weather.google;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

public class GoogleWeatherClientTest {
	
	private static final String XML = "<weather></weather>";
	private static final String URL = "http://www.google.com/ig/api?weather=München";
	
	@Test
	public void testGetForecastWithSubclass() {
		GoogleWeatherClient client = new GoogleWeatherClient() {
			@Override protected InputStream openStream(String url) {
				return new ByteArrayInputStream(XML.getBytes());
			}
		};
		
		String forecast = client.getForecast("München");
		
		assertEquals(XML, forecast);
	}
	
	@Test
	public void testGetForecastWithSpy() {
		GoogleWeatherClient client = spy(new GoogleWeatherClient());
		InputStream stream = new ByteArrayInputStream(XML.getBytes());
		
		when(client.openStream(URL)).thenReturn(stream);
		
		String forecast = client.getForecast("München");
		
		assertEquals(XML, forecast);
	}
}
