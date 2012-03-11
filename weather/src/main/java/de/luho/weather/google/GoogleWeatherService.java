package de.luho.weather.google;

import de.luho.weather.Forecast;
import de.luho.weather.WeatherService;

public class GoogleWeatherService implements WeatherService {

	private GoogleWeatherClient client;
	private GoogleWeatherParser parser;
	
	@Override
	public Forecast getForecastForCity(String city) {
		String xml = client.getForecast(city);
		return parser.parse(xml);
	}

	public void setGoogleWeatherClient(GoogleWeatherClient client) {
		this.client = client;
	}
	
	public void setGoogleWeatherParser(GoogleWeatherParser parser) {
		this.parser = parser;
	}
}
