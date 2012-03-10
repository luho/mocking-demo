package de.hoogle.weather.google;

import de.hoogle.weather.Forecast;
import de.hoogle.weather.WeatherService;

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
