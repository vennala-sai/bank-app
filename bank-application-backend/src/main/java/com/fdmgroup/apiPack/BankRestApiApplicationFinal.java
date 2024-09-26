package com.fdmgroup.apiPack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class BankRestApiApplicationFinal {
	public static void main(String[] args) {
		SpringApplication.run(BankRestApiApplicationFinal.class, args);
	}

}
