package com.SAlvesJr.webService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.SAlvesJr.webService.model.CharacterFavorite;
import com.SAlvesJr.webService.model.CharacterOBJ;
import com.SAlvesJr.webService.model.CharacterOBJList;
import com.SAlvesJr.webService.model.Cliente;
import com.SAlvesJr.webService.repositories.CharacterFavoriteRepository;
import com.SAlvesJr.webService.service.excepiton.ObjectNotFoundException;

@Service
public class CharacterService {

	@Autowired
	private CharacterFavoriteRepository repoFavorite;

	@Autowired
	ClienteService clienteService;

	@Value("${access.token.superHero}")
	private String token;

	private static final String GET_FIND_CARACTED = "https://superheroapi.com/api/";
	private static final String GET_SEARCH_NAME = "https://superheroapi.com/api/";

	public CharacterFavorite findById(Long id) {
		Optional<CharacterFavorite> obj = repoFavorite.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + CharacterFavorite.class.getName()));
	}

	public List<CharacterOBJ> searchName(String name) {
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = GET_SEARCH_NAME + token + "/search/" + name;
		CharacterOBJList response = restTemplate.getForObject(fooResourceUrl, CharacterOBJList.class);
		return response.getListCharacterOBJ();
	}

	public CharacterOBJ findCaractedId(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = GET_FIND_CARACTED + token + "/" + id;
		CharacterOBJ response = restTemplate.getForObject(fooResourceUrl, CharacterOBJ.class);
		return response;
	}

	public List<CharacterFavorite> listFavorite(Long idCli) {
		return clienteService.listFavorite(idCli);
	}

	public void saveFavorite(CharacterFavorite characterFavorite) {
		repoFavorite.save(characterFavorite);
	}
	
	public CharacterFavorite addCharacterFavorite(Long idCliente, Long idCharacter) {
		Cliente cli = clienteService.findById(idCliente);
		CharacterOBJ crObj = findCaractedId(idCharacter);
		CharacterFavorite crFavorite = null;
		List<Long> ids = new ArrayList<>();
		if (crObj != null) {
			crFavorite = new CharacterFavorite(null, crObj.getName(), Long.parseLong(crObj.getId()), cli);
			for (CharacterFavorite element : cli.getCharacterFavorite()) {
				ids.add(element.getIdCharacter());
			}
			if(!ids.contains(crFavorite.getIdCharacter())) {
				saveFavorite(crFavorite);
			}
		}
		return crFavorite;
	}

	public void deleteList(Long id, Long idCharacte) {
		clienteService.findById(id);
		CharacterFavorite chFv = findById(idCharacte);
		repoFavorite.delete(chFv);
	}

}
