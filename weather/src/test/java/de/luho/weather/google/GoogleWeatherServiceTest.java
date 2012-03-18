package de.luho.weather.google;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.luho.weather.Forecast;
import de.luho.weather.ParseException;
import de.luho.weather.google.GoogleWeatherClient;
import de.luho.weather.google.GoogleWeatherParser;
import de.luho.weather.google.GoogleWeatherService;
import static org.mockito.Mockito.*;

public class GoogleWeatherServiceTest {

	private Forecast expectedForecast = new Forecast(new Date());
	@Mock
	private GoogleWeatherClient client;
	@Mock
	private GoogleWeatherParser parser;
	@InjectMocks
	private GoogleWeatherService service = new GoogleWeatherService();;	

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetForecast() {		
		when(client.getForecast("München")).thenReturn("<weatherxml></weatherxml>");
		when(parser.parse("<weatherxml></weatherxml>")).thenReturn(expectedForecast);

		Forecast forecast = service.getForecastForCity("München");
		
		assertEquals(expectedForecast, forecast);
	}
	
	@Test(expected=ParseException.class)
	public void testGetForecastParseException() {		
		when(client.getForecast("München")).thenReturn("<weatherxml></weatherxml>");
		when(parser.parse("<weatherxml></weatherxml>")).thenThrow(new ParseException());

		service.getForecastForCity("München");
	}
}
