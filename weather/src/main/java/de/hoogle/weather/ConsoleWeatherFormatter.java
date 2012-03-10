package de.hoogle.weather;

import java.text.SimpleDateFormat;
import java.util.List;

public class ConsoleWeatherFormatter implements WeatherFormatter {
	
	private StringBuilder formatted;
	private Forecast forecast;

	public ConsoleWeatherFormatter(Forecast forecast) {
		this.forecast = forecast;
	}
	
	public String format() {
		formatted = new StringBuilder();
		formatFirstLine();
		formatDays();
		return formatted.toString();
	}

	private void formatFirstLine() {
		String date = new SimpleDateFormat("yyyy-MM-dd").format(forecast.getDate());
		formatted.append("Forecast made on ");
		formatted.append(date).append(":\n");
	}

	private void formatDays() {
		List<ForecastDay> days = forecast.getDays();
		for (ForecastDay day : days) {
			formatDay(day);
		}
	}

	private void formatDay(ForecastDay day) {
		formatted.append("  ").append(day.getDayOfWeek()).append(":\n");
		formatted.append("    low:        ").append(day.getLowTemp()).append("\n");
		formatted.append("    high:       ").append(day.getHighTemp()).append("\n");
		formatted.append("    condition:  ").append(day.getCondition()).append("\n");		
	}
}
