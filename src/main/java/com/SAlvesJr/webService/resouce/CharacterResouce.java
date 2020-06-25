package com.SAlvesJr.webService.resouce;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.SAlvesJr.webService.model.CharacterFavorite;
import com.SAlvesJr.webService.model.CharacterOBJ;
import com.SAlvesJr.webService.model.dto.CharacterAddFovoriteDTO;
import com.SAlvesJr.webService.service.CharacterService;

@RestController
@RequestMapping(value = "/characters")
public class CharacterResouce {

	@Autowired
	CharacterService characterService;

	@GetMapping(value = "search/{name}")
	public ResponseEntity<List<CharacterOBJ>> searchForName(@PathVariable String name) {
		List<CharacterOBJ> result = characterService.searchName(name);
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CharacterOBJ> findById(@PathVariable Long id) {
		CharacterOBJ cObj = characterService.findCaractedId(id);
		return ResponseEntity.ok().body(cObj);
	}

	@GetMapping(value = "/{idCli}/favoriteList")
	public ResponseEntity<List<CharacterFavorite>> findFavorite(@PathVariable Long idCli) {
		List<CharacterFavorite> obj = characterService.listFavorite(idCli);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping(value = "/{idCli}/addFavorite")
	public ResponseEntity<Void> addFavorite(@RequestBody CharacterAddFovoriteDTO crFavorite, @PathVariable Long idCli) {
		CharacterFavorite obj = characterService.addCharacterFavorite(idCli, crFavorite.getIdCharacter());
		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("characters/{idCli}/favoriteList")
				.buildAndExpand(obj.getCliente()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "{idCli}/deleteList/{idCharacte}")
	public ResponseEntity<Void> deleteCharacter(@PathVariable Long idCli, @PathVariable Long idCharacte) {
		characterService.deleteList(idCli, idCharacte);
		return ResponseEntity.noContent().build();
	}

}
