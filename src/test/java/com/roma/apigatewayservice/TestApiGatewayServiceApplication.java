package com.roma.apigatewayservice;

import org.springframework.boot.SpringApplication;

public class TestApiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ApiGatewayServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
