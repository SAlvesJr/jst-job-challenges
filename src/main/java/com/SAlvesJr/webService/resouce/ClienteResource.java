package com.SAlvesJr.webService.resouce;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.SAlvesJr.webService.model.CharacterFavorite;
import com.SAlvesJr.webService.model.Cliente;
import com.SAlvesJr.webService.model.dto.ClienteDTO;
import com.SAlvesJr.webService.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	ClienteService clienteService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Long id) {
		Cliente obj = clienteService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/{id}/favoriteList")
	public ResponseEntity<List<CharacterFavorite>> findFavorite(@PathVariable Long id) {
		List<CharacterFavorite> obj = clienteService.listFavorite(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping(value = "/{id}/addFavorite")
	public ResponseEntity<Void> addFavorite(@RequestBody CharacterFavorite crFavorite, @PathVariable Long id) {
		CharacterFavorite obj = clienteService.addCharacterFavorite(id, crFavorite.getId());
		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("clientes/{id}/favoriteList")
				.buildAndExpand(obj.getCliente()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping(value = "/email")
	public ResponseEntity<Cliente> findByEmail(@RequestParam(value = "value") String email) {
		Cliente obj = clienteService.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Void> insertCliente(@Valid @RequestBody Cliente objDTO) {
		Cliente cli = clienteService.insert(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cli.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> putCliente(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Long id) {
		Cliente cat = clienteService.fromDTO(objDTO, id);
		cat = clienteService.update(cat);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cliente> deleteCliente(@PathVariable Long id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
