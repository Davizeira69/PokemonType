package br.com.pokemon.type.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pokemon.type.dto.PokemonTypeDto;
import br.com.pokemon.type.dto.ResponseDto;
import br.com.pokemon.type.service.PokemonTypeService;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/pokemon-type/v1/")
public class PokemonTypeController {
	
	@Autowired
	PokemonTypeService service;

	@PostMapping
	public ResponseEntity<ResponseDto<Long>> create(@RequestBody @Valid PokemonTypeDto dto) throws Exception {
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(new ResponseDto<Long>(service.create(dto), null));
	}

	@GetMapping
	public ResponseEntity<ResponseDto<List<PokemonTypeDto>>> findAll() throws Exception {
			return ResponseEntity
			.ok(new ResponseDto<List<PokemonTypeDto>>(service.findAll(), null));
	}
	
	@GetMapping("/{id}/")
	public ResponseEntity<ResponseDto<PokemonTypeDto>> findById(@PathVariable("id") Long id) throws Exception{
		return ResponseEntity
			.ok(new ResponseDto<PokemonTypeDto>(service.findById(id), null));
	}
	
	@PutMapping("/{id}/")
	public ResponseEntity<ResponseDto<String>> update(@RequestBody @Valid PokemonTypeDto dto, @PathVariable("id") Long id) throws Exception {		
		return ResponseEntity
			.ok(new ResponseDto<String>(service.update(dto, id), null));
	}
	
	@DeleteMapping("/{id}/")
	public ResponseEntity<ResponseDto<String>> delete(@PathVariable("id") Long id) throws Exception {
		return ResponseEntity
			.ok(new ResponseDto<String>(service.delete(id), null));
	}
}
