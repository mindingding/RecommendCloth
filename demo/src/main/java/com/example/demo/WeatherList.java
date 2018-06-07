package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherList {
	
	public Weather weather;
	
	public WeatherList() {}
	public WeatherList(Weather weather) {
		this.weather = weather;
	}
	
	public Weather getweather() {
		return weather;
	}
	
	public void setweather(Weather weather) {
		this.weather = weather;
	}

}
