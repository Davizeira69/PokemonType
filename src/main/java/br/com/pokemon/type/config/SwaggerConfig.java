package br.com.pokemon.type.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title = "Tabela de tipos de pokemon", 
	description = "Selecionar tabela de tipos de pokemon.",
	contact = @Contact(name = "Davi de Moraes",
	url = "http://quotenow.com.br/", email = "lognaut@gmail.com"),
	license = @License(name = "MIT Licence")),
	servers = @Server(url = "http://localhost:10080"))

@SecurityScheme(name = "Token",
	scheme = "Bearer", type = SecuritySchemeType.HTTP,
	in = SecuritySchemeIn.HEADER, bearerFormat = "JWT")
public class SwaggerConfig {
}