package com.SAlvesJr.webService.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comics {

	@JsonProperty(value = "resourceURI")
	private String resourceURI;
	@JsonProperty(value = "title")
	private String title;
	@JsonProperty(value = "description")
	private String description;

	private List<Prices> prices = new ArrayList<>();

	public Comics() {

	}

	public Comics(String resourceURI, String description, String title, List<Prices> prices) {
		this.resourceURI = resourceURI;
		this.description = description;
		this.title = title;
		this.setPrices(prices);
	}

	public String getResourceURI() {
		return resourceURI;
	}

	public void setResourceURI(String resourceURI) {
		this.resourceURI = resourceURI;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Prices> getPrices() {
		return prices;
	}

	public void setPrices(List<Prices> prices) {
		this.prices = prices;
	}

}
