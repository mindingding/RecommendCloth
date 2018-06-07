package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
	
	public temperature temp;
	
	public Weather() {}
	public Weather(temperature temp) {
		this.temp = temp;
	}
	
	public temperature gettemp() {
		return temp;
	}
	
	public void settemp(temperature temp) {
		this.temp = temp;
	}
}
