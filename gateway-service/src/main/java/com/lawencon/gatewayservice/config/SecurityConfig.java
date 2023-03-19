package com.lawencon.gatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3001")
						.allowedMethods(HttpMethod.GET.toString(), HttpMethod.POST.toString(),
								HttpMethod.PUT.toString(), HttpMethod.DELETE.toString());
				WebMvcConfigurer.super.addCorsMappings(registry);
			}
		};
	}
	
}
