package com.linktic.prueba.tecnica.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain  securityFilterChain(ServerHttpSecurity http) throws Exception {
		http.csrf().disable().authorizeExchange().anyExchange().authenticated().and().oauth2Login();
		return http.build();
	}
}
