package com.SAlvesJr.webService.service;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SAlvesJr.webService.model.CharacterFavorite;
import com.SAlvesJr.webService.model.Cliente;
import com.SAlvesJr.webService.repositories.CharacterFavoriteRepository;
import com.SAlvesJr.webService.repositories.ClienteRepository;

@Service
public class DBService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CharacterFavoriteRepository characterFavorite;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public void instantiateTestDatabase() throws ParseException {

		Cliente cl1 = new Cliente(null, "Maria", "keniyav596@chordmi.com", bCryptPasswordEncoder.encode("12345"));
		Cliente cl2 = new Cliente(null, "Anna", "kicece9915@aprimail.com", bCryptPasswordEncoder.encode("12345"));

		clienteRepository.saveAll(Arrays.asList(cl1));
		clienteRepository.saveAll(Arrays.asList(cl2));

		CharacterFavorite crF1 = new CharacterFavorite(null, "Batman", 70L, cl1);
		CharacterFavorite crF2 = new CharacterFavorite(null, "Agent Bob", 10L, cl1);

		cl1.getCharacterFavorite().addAll(Arrays.asList(crF1, crF2));
		
		crF1.setCliente(cl1);
		crF2.setCliente(cl1);

		characterFavorite.saveAll(Arrays.asList(crF1));
		characterFavorite.saveAll(Arrays.asList(crF2));

	}

}
