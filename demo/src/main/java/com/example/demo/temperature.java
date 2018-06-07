package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

public class temperature {

	public float tc;
	public float tmax;
	public float tmin;
	
	public temperature() {}
	public temperature(float tc, float tmax, float tmin) {
		this.tc = tc;
		this.tmax = tmax;
		this.tmin = tmin;
	}
	
	public float gettc() {
		return tc;
	}
	
	public void settc(float tc) {
		this.tc = tc;
	}
	
	public float gettmax() {
		return tmax;
	}
	
	public void settmax(float tmax) {
		this.tmax = tmax;
	}
	
	public float gettmin() {
		return tmin;
	}
	
	public void settmin(float tmin) {
		this.tmin = tmin;
	}
}
