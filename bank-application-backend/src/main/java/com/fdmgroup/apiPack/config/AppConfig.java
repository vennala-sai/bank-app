package com.fdmgroup.apiPack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
	
	private final String BASE_URL = "https://geocoder.ca";
	@Bean
    public WebClient geocoderWebClient(WebClient.Builder builder) {
        return builder.baseUrl(BASE_URL).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }
}
