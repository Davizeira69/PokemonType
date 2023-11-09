package br.com.pokemon.type.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pokemon.type.entity.PokemonTypeEntity;

@Repository
public interface PokemonTypeRepository extends JpaRepository<PokemonTypeEntity, Long> {
}
