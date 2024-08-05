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
				.addServersItem(new Server().url(configProperties.url()))
				.info(getInfo());
	}

	private Info getInfo() {
		return new Info()
				.title(configProperties.title())
				.description(configProperties.description())
				.version(configProperties.version())
				.contact(getContact())
				.license(getLicense());
	}

	private License getLicense() {
		return new License()
				.name(configProperties.license().type())
				.url(configProperties.license().url());
	}
	
	private Contact getContact() {
		return new Contact()
				.name(configProperties.contact().author())
				.email(configProperties.contact().email());
	}
    
}