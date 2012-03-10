package de.hoogle.weather.google;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import de.hoogle.weather.Forecast;
import de.hoogle.weather.google.GoogleWeatherParser;

public class GoogleWeatherParserTest {

	private GoogleWeatherParser parser;
	
	@Test
	public void testDate() throws IOException, ParseException {
		Date expected = new SimpleDateFormat("yyyy-MM-dd").parse("2012-03-10");
		String goodInput = readInput("/goodinput.xml");
		parser = new GoogleWeatherParser();
		
		Forecast forecast = parser.parse(goodInput);
		assertEquals(expected, forecast.getDate());
	}

	@Test
	public void testNumberOfDays() throws IOException {
		String goodInput = readInput("/goodinput.xml");
		parser = new GoogleWeatherParser();
		
		Forecast forecast = parser.parse(goodInput);
		assertEquals(4, forecast.getDays().size());
	}

	@Test
	public void testSaturday() throws IOException {
		String goodInput = readInput("/goodinput.xml");
		parser = new GoogleWeatherParser();
		
		Forecast forecast = parser.parse(goodInput);
		assertEquals("Sat", forecast.getDays().get(0).getDayOfWeek());
		assertEquals(36, forecast.getDays().get(0).getLowTemp());
		assertEquals(48, forecast.getDays().get(0).getHighTemp());
		assertEquals("Partly Sunny", forecast.getDays().get(0).getCondition());		
	}
	
	@Test(expected=de.hoogle.weather.ParseException.class)
	public void testWrongDateFormat() throws IOException {
		String wrongDateFormat = readInput("/wrongdateinput.xml");
		parser = new GoogleWeatherParser();
		
		parser.parse(wrongDateFormat);		
	}
	
	@Test(expected=de.hoogle.weather.ParseException.class)
	public void testBadInput() throws IOException {
		String badInput = readInput("/badInput.xml");
		parser = new GoogleWeatherParser();
		
		parser.parse(badInput);				
	}
	
	private String readInput(String fileName) throws IOException {
		InputStream in = getClass().getResourceAsStream(fileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		char[] buffer = new char[1024];
		int n;
		StringBuilder sb = new StringBuilder();
		while ((n = reader.read(buffer)) != -1) {
			sb.append(new String(buffer, 0, n));
		}		
		return sb.toString();
	}
}
