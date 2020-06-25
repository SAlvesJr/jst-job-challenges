package com.SAlvesJr.webService.resouce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SAlvesJr.webService.model.dto.ComicsDTO;
import com.SAlvesJr.webService.service.ComicMavelService;

@RestController
@RequestMapping(value = "/comics")
public class ComicMavelResouce {

	@Autowired
	ComicMavelService comicsService;

	@GetMapping(value = "/search")
	public ResponseEntity<ComicsDTO> searchForName(@RequestParam(value = "name") String name, @RequestParam(value = "quant", defaultValue = "20") int quant) {
		ComicsDTO result = comicsService.searchComicsName(name, quant);
		return ResponseEntity.ok().body(result);
	}

}
