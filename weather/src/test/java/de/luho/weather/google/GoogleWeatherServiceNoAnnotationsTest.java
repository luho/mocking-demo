package de.luho.weather.google;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import de.luho.weather.Forecast;

public class GoogleWeatherServiceNoAnnotationsTest {

	private Forecast expectedForecast = new Forecast(new Date());
	private GoogleWeatherClient client;
	private GoogleWeatherParser parser;
	private GoogleWeatherService service = new GoogleWeatherService();;	

	@Before
	public void setUp() {
		client = mock(GoogleWeatherClient.class);
		parser = mock(GoogleWeatherParser.class);
		service.setGoogleWeatherClient(client);
		service.setGoogleWeatherParser(parser);
	}
	
	@Test
	public void testGetForecast() {		
		when(client.getForecast("München")).thenReturn("<weatherxml></weatherxml>");
		when(parser.parse("<weatherxml></weatherxml>")).thenReturn(expectedForecast);

		Forecast forecast = service.getForecastForCity("München");
		
		assertEquals(expectedForecast, forecast);
	}

}
