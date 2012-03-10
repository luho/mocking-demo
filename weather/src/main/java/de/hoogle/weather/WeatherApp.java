package de.hoogle.weather;

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
		System.out.println("this is it");
	}
}
