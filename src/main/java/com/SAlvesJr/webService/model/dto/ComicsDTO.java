package com.SAlvesJr.webService.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.SAlvesJr.webService.model.Comics;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int total;
	private List<Comics> comicsList = new ArrayList<>();

	public ComicsDTO() {

	}

	public ComicsDTO(int total, List<Comics> cms) {
		this.total = total;
		this.setComicsList(cms);
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Comics> getComicsList() {
		return comicsList;
	}

	public void setComicsList(List<Comics> comicsList) {
		this.comicsList = comicsList;
	}

}
