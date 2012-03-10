package de.hoogle.weather;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class GoogleWeatherServiceTest {

	private Forecast forecast = new Forecast(new Date());
	@Mock
	private GoogleWeatherClient client;
	@Mock
	private GoogleWeatherParser parser;
	@InjectMocks
	private GoogleWeatherService service = new GoogleWeatherService();;	

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(client.getForecast("München")).thenReturn("<weatherxml></weatherxml>");
		when(parser.parse("<weatherxml></weatherxml>")).thenReturn(forecast);
	}
	
	@Test
	public void testGetForecast() {
		Forecast forecast = service.getForecastForCity("München");
		assertNotNull("forecast is null", forecast);
	}
}
