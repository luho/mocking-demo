package de.hoogle.weather.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import de.hoogle.weather.SystemException;

public class GoogleWeatherClient {

	private static final String BASE_URL = "http://www.google.com/ig/api?weather=";
	
	public String getForecast(String city) {
		String url = BASE_URL + city;
		String forecast;
		try {
			URL google = new URL(url);
			InputStream in = google.openStream();
			forecast = readFromStream(in);
		} catch (MalformedURLException e) {
			throw new SystemException(e);
		} catch (IOException e) {
			throw new SystemException(e);
		}
		return forecast;
	}

	private String readFromStream(InputStream in) throws IOException {
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
