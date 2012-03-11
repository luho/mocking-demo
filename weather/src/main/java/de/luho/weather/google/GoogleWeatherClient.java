package de.luho.weather.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import de.luho.weather.SystemException;

public class GoogleWeatherClient {

	private static final String BASE_URL = "http://www.google.com/ig/api?weather=";

	public String getForecast(String city) {
		String url = BASE_URL + city;
		InputStream in = openStream(url);
		String forecast = readFromStream(in);
		return forecast;
	}

	protected InputStream openStream(String url) {
		InputStream in;
		try {
			URL google = new URL(url);
			in = google.openStream();
		} catch (MalformedURLException e) {
			throw new SystemException(e);
		} catch (IOException e) {
			throw new SystemException(e);
		}
		return in;
	}

	private String readFromStream(InputStream in) {
		StringBuilder sb;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					in, "UTF-8"));
			char[] buffer = new char[1024];
			int n;
			sb = new StringBuilder();
			while ((n = reader.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, n));
			}
		} catch (IOException e) {
			throw new SystemException(e);
		}
		return sb.toString();
	}
}
