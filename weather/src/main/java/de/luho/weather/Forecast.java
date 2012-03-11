package de.luho.weather;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Forecast {

	private Date forecastDate;
	private List<ForecastDay> days;
	
	public Forecast(Date date) {
		forecastDate = new Date(date.getTime());
		days = new ArrayList<ForecastDay>(4);
	}
	
	public void addDay(ForecastDay day) {
		days.add(day);
	}
	
	public List<ForecastDay> getDays() {
		return Collections.unmodifiableList(days);
	}
	
	public Date getDate() {
		return new Date(forecastDate.getTime());
	}	
}
