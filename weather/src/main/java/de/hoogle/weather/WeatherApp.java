package de.hoogle.weather;

import de.hoogle.weather.google.GoogleWeatherClient;
import de.hoogle.weather.google.GoogleWeatherParser;
import de.hoogle.weather.google.GoogleWeatherService;

public class WeatherApp {

	private WeatherService service;
	private WeatherFormatter formatter;
	
	public String getForecastForCity(String city) {
		Forecast forecast = service.getForecastForCity(city);
		String formatted = formatter.format(forecast);
		return formatted;
	}

	public void setWeatherService(WeatherService service) {
		this.service = service;
	}

	public void setWeatherFormatter(WeatherFormatter formatter) {
		this.formatter = formatter;
	}

	public static void main(String[] args) {
		GoogleWeatherService service = new GoogleWeatherService();
		service.setGoogleWeatherClient(new GoogleWeatherClient());
		service.setGoogleWeatherParser(new GoogleWeatherParser());
		
		WeatherApp wapp = new WeatherApp();
		wapp.setWeatherService(service);
		wapp.setWeatherFormatter(new ConsoleWeatherFormatter());
		
		String forecast = wapp.getForecastForCity("München");
		System.out.println(forecast);
	}
}
