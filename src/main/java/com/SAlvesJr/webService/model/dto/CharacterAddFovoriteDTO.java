package com.SAlvesJr.webService.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class CharacterAddFovoriteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 1, max = 100, message = "o tamanho deve ter de 1 a 100 caracteries")
	private Long idCharacter;

	public CharacterAddFovoriteDTO() {

	}

	public Long getIdCharacter() {
		return idCharacter;
	}

	public void setIdCharacter(Long idCharacter) {
		this.idCharacter = idCharacter;
	}

}
