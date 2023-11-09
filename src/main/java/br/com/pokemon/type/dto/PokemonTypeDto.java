package br.com.pokemon.type.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PokemonTypeDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Min(message = "Id inválido.", value = 1)
	private Long id;
	
	@NotBlank(message = "insira um tipo válido.")
	private String typeName;
}