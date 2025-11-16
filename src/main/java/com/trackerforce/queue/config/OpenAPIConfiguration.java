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

	private final ServiceConfig.Docs docs;

	public OpenAPIConfiguration(ServiceConfig serviceConfig) {
		this.docs = serviceConfig.docs();
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.addServersItem(new Server().url(docs.url()))
				.info(getInfo());
	}

	private Info getInfo() {
		return new Info()
				.title(docs.title())
				.description(docs.description())
				.version(docs.version())
				.contact(getContact())
				.license(getLicense());
	}

	private License getLicense() {
		return new License()
				.name(docs.license().type())
				.url(docs.license().url());
	}
	
	private Contact getContact() {
		return new Contact()
				.name(docs.contact().author())
				.email(docs.contact().email());
	}
    
}