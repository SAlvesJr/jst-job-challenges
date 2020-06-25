package com.SAlvesJr.webService.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Prices {

	@JsonProperty(value = "price")
	private int price;
	@JsonProperty(value = "type")
	private String type;

	public Prices() {

	}

	public Prices(int price, String type) {
		super();
		this.price = price;
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
