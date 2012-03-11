package de.luho.weather;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.luho.weather.Forecast;
import de.luho.weather.WeatherApp;
import de.luho.weather.WeatherFormatter;
import de.luho.weather.WeatherService;

import static org.mockito.Mockito.*;

public class WeatherAppTest {

	@Mock
	private WeatherService service;
	@Mock
	private WeatherFormatter formatter;
	@InjectMocks
	private WeatherApp weatherApp = new WeatherApp();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetForecastForCity() {
		weatherApp.getForecastForCity("München");
		
		verify(service).getForecastForCity("München");
		verify(formatter).format(any(Forecast.class));
	}
}
