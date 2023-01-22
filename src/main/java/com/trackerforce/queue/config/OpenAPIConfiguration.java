package com.trackerforce.queue.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

	private final ConfigProperties configProperties;

	public OpenAPIConfiguration(ConfigProperties configProperties) {
		this.configProperties = configProperties;
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.addServersItem(new Server().url(configProperties.getUrl()))
				.info(getInfo());
	}

	private Info getInfo() {
		return new Info()
				.title(configProperties.getTitle())
				.description(configProperties.getDescription())
				.version(configProperties.getVersion())
				.contact(getContact())
				.license(getLicense());
	}

	private License getLicense() {
		return new License()
				.name(configProperties.getLicense().getType())
				.url(configProperties.getLicense().getUrl());
	}
	
	private Contact getContact() {
		return new Contact()
				.name(configProperties.getContact().getAuthor())
				.email(configProperties.getContact().getEmail());
	}
    
}