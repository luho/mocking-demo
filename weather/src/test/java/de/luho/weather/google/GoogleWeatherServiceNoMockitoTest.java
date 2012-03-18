package de.luho.weather.google;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import de.luho.weather.Forecast;

public class GoogleWeatherServiceNoMockitoTest {

	private static Forecast expectedForecast = new Forecast(new Date());
	private GoogleWeatherClient client;
	private GoogleWeatherParser parser;
	private GoogleWeatherService service = new GoogleWeatherService();;	

	@Before
	public void setUp() {
		client = new StubbedGoogleWeatherClient();
		parser = new StubbedGoogleWeatherParser();
		service = new GoogleWeatherService();
		service.setGoogleWeatherClient(client);
		service.setGoogleWeatherParser(parser);
	}
	
	@Test
	public void testGetForecast() {		
		Forecast forecast = service.getForecastForCity("München");		
		assertEquals(expectedForecast, forecast);
	}
	
	static class StubbedGoogleWeatherClient extends GoogleWeatherClient {
		@Override public String getForecast(String city) {
			if (city.equals("München")) {
				return "<weatherxml></weatherxml>";
			} else {
				return null;
			}
		}
	}
	
	static class StubbedGoogleWeatherParser extends GoogleWeatherParser {
		@Override
		public Forecast parse(String xml) {
			if (xml.equals("<weatherxml></weatherxml>")) {
				return expectedForecast;
			} else {
				return null;
			}
		}
	}

}
