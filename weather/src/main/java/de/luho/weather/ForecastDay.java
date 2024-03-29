package de.luho.weather;

public class ForecastDay {

	private String dayOfWeek;
	private int lowTemp;
	private int highTemp;
	private String condition;
	
	public ForecastDay(String dayOfWeek, int lowTemp, int highTemp, String description) {
		this.dayOfWeek = dayOfWeek;
		this.lowTemp = lowTemp;
		this.highTemp = highTemp;
		this.condition = description;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public int getLowTemp() {
		return lowTemp;
	}

	public int getHighTemp() {
		return highTemp;
	}

	public String getCondition() {
		return condition;
	}
}
