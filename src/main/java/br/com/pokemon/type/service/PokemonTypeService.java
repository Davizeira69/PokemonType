package br.com.pokemon.type.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import br.com.pokemon.type.adapter.PokemonTypeAdapter;
import br.com.pokemon.type.dto.PokemonTypeDto;
import br.com.pokemon.type.entity.PokemonTypeEntity;
import br.com.pokemon.type.exception.BadRequestException;
import br.com.pokemon.type.exception.InternalServerError;
import br.com.pokemon.type.exception.NotFoundException;
import br.com.pokemon.type.repository.PokemonTypeRepository;

@Service
public class PokemonTypeService {

	@Autowired
	PokemonTypeRepository repository;

	@Autowired
	PokemonTypeAdapter adapter;

	public Long create(PokemonTypeDto dto) throws Exception {
		Long id = 0L;
		try {
			PokemonTypeEntity entity = adapter.adapterDtoToEntity(dto, id);
			id = repository.save(entity).getId();
		} catch (IllegalArgumentException | OptimisticLockingFailureException e) {
			throw new BadRequestException(e.getMessage());
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}
		return id;
	}

	public List<PokemonTypeDto> findAll() throws Exception {
		List<PokemonTypeDto> dtos = new ArrayList<>();
		try {
			List<PokemonTypeEntity> entities = repository.findAll();
			notFoundChecker(entities.size());
			entities.forEach(entity -> dtos.add(adapter.adapterEntityToDto(entity)));
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}
		return dtos;
	}

	public PokemonTypeDto findById(Long id) throws Exception {
		PokemonTypeDto dto;
		try {
			PokemonTypeEntity entity = repository.findById(id)
					.orElseThrow(() -> new NotFoundException("Nenhum registro encontrado."));
			dto = adapter.adapterEntityToDto(entity);
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}
		return dto;
	}

	public String update(PokemonTypeDto dto, Long id) throws Exception {
		try {

			PokemonTypeEntity entity = repository.findById(dto.getId())
					.orElseThrow(() -> new NotFoundException("Nenhum registro encontrado."));
			entity.setTypeName(dto.getTypeName());

			repository.save(entity);
		} catch (IllegalArgumentException | OptimisticLockingFailureException e) {
			throw new BadRequestException(e.getMessage());
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}

		return "Alterações realizadas com êxito.";
	}

	public String delete(Long id) throws Exception {
		try {
			repository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new BadRequestException(e.getMessage());
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}
		return "Dados excluídos com êxito.";
	}

	private static void notFoundChecker(int paramForCheck) throws NotFoundException {
		if (paramForCheck == 0) {
			throw new NotFoundException("Nenhum registro encontrado.");
		}
	}
}
