package br.com.pokemon.type.adapter;

import org.springframework.stereotype.Component;

import br.com.pokemon.type.dto.PokemonTypeDto;
import br.com.pokemon.type.entity.PokemonTypeEntity;


@Component
public class PokemonTypeAdapter {
	
	public PokemonTypeEntity adapterDtoToEntity(PokemonTypeDto dto, Long id) throws Exception{
		PokemonTypeEntity entity = new PokemonTypeEntity();
		
		entity.setId(id);
		entity.setTypeName(dto.getTypeName());
		
		return entity;
	}
	
	public PokemonTypeDto adapterEntityToDto(PokemonTypeEntity entity) {
		PokemonTypeDto dto = new PokemonTypeDto();
		
		dto.setTypeName(entity.getTypeName());
		dto.setId(entity.getId());
		
		
		return dto;
	}
}
