package de.hoogle.weather;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ConsoleWeatherFormatterTest {

	private Forecast forecast;
	private ConsoleWeatherFormatter formatter;
	private Date date;
	
	private String expectedFormat =
			"Forecast made on 2012-03-10:\n" +
			"  Sat:\n" +
			"    low:        10\n" +
			"    high:       30\n" +
			"    condition:  Warm and sunny\n" +
			"  Sun:\n" +
			"    low:        5\n" +
			"    high:       15\n" +
			"    condition:  Cool and cloudy\n";
	
	@Before
	public void setUp() throws ParseException {
		date = new SimpleDateFormat("yyyy-MM-dd").parse("2012-03-10");
		forecast = new Forecast(date);
		ForecastDay day1 = new ForecastDay("Sat", 10, 30, "Warm and sunny");
		ForecastDay day2 = new ForecastDay("Sun", 5, 15, "Cool and cloudy");
		forecast.addDay(day1);
		forecast.addDay(day2);
		formatter = new ConsoleWeatherFormatter();
	}
	
	@Test
	public void testFormat() {
		String pretty = formatter.format(forecast);
		assertNotNull("formatter returned null", pretty);
		assertEquals(expectedFormat, pretty);
	}
}
