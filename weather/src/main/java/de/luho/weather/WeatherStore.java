package de.luho.weather;

import java.util.Date;

public interface WeatherStore {

	void save(Forecast forecast);
	
	Forecast find(String city, Date date);
}
