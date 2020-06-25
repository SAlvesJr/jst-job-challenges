package com.SAlvesJr.webService.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterOBJList {

	@JsonProperty(value = "results")
	private List<CharacterOBJ> listCharacterOBJ;

	public CharacterOBJList() {
		listCharacterOBJ = new ArrayList<>();
	}

	public List<CharacterOBJ> getListCharacterOBJ() {
		return listCharacterOBJ;
	}

	public void setListCharacterOBJ(List<CharacterOBJ> listCharacterOBJ) {
		this.listCharacterOBJ = listCharacterOBJ;
	}

}
