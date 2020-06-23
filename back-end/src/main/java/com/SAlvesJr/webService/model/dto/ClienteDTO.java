package com.SAlvesJr.webService.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.SAlvesJr.webService.model.Cliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "o tamanho deve ter de 5 a 120 caracteries")
	private String nome;
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message = "email invalido")
	private String email;

	public ClienteDTO() {

	}

	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String name) {
		this.nome = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
