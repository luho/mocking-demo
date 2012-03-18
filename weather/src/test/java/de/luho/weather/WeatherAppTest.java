package de.luho.weather;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class WeatherAppTest {

	private Forecast forecast = new Forecast(new Date());
	@Mock
	private WeatherService service;
	@Mock
	private WeatherFormatter formatter;
	@Mock
	private WeatherStore store;
	@InjectMocks
	private WeatherApp weatherApp = new WeatherApp();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		forecast.addDay(new ForecastDay("Mon", 0, 12, "Sunny"));
		forecast.addDay(new ForecastDay("Tue", 0, 12, "Sunny"));
		forecast.addDay(new ForecastDay("Wed", 0, 12, "Sunny"));
		forecast.addDay(new ForecastDay("Thu", 0, 12, "Sunny"));
	}
	
	@Test
	public void testGetAndStoreForecastForCity() {
		when(service.getForecastForCity("München")).thenReturn(forecast);
		when(formatter.format(forecast)).thenReturn("-- formatted forecast --");
		
		String formatted = weatherApp.getAndStoreForecastForCity("München");
		
		assertEquals("-- formatted forecast --", formatted);
		
		//ArgumentCaptor<Forecast> argument = ArgumentCaptor.forClass(Forecast.class);
		verify(store).save(forecast);
		//assertEquals(4, argument.getValue().getDays().size());
	}
}
