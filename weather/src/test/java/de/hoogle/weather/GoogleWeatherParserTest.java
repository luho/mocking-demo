package de.hoogle.weather;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Before;
import org.junit.Test;

public class GoogleWeatherParserTest {

	private GoogleWeatherParser parser;
	
	@Before
	public void setUp() {
		parser = new GoogleWeatherParser();
	}
	
	@Test
	public void testParse() throws IOException {
		String goodInput = readInput("/goodinput.xml");
		Forecast forecast = parser.parse(goodInput);
		assertNotNull(forecast);
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
