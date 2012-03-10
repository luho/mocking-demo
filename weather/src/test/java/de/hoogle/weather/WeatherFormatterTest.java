package de.hoogle.weather;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WeatherFormatterTest {

	private WeatherFormatter formatter;
	
	@Before
	public void setUp() {
		formatter = new WeatherFormatter();
	}
	
	@Test
	public void testMakePretty() {
		String pretty = formatter.makePretty();
		assertNotNull(pretty);
	}
}
