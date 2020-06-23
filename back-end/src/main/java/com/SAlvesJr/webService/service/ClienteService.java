package com.SAlvesJr.webService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SAlvesJr.webService.model.Cliente;
import com.SAlvesJr.webService.model.dto.ClienteDTO;
import com.SAlvesJr.webService.repositories.ClienteRepository;
import com.SAlvesJr.webService.service.excepiton.ObjectNotFoundException;


@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente findById(Long id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente cli) {
		cli.setId(null);
		return clienteRepository.save(cli);
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return clienteRepository.save(obj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Long id) {
		findById(id);
		clienteRepository.deleteById(id);
	}

	public Cliente findByEmail(String email) {
		Cliente cli = clienteRepository.findByEmail(email);
		if (cli == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return cli;
	}

	public Cliente fromDTO(ClienteDTO obj, Long id) {
		return new Cliente(id, obj.getNome(), obj.getEmail(), null);
	}
}
