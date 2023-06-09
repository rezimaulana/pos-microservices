package com.lawencon.fileservice.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.lawencon.fileservice.security.AuthorizationFilter;

@Configuration
public class SecurityConfig {

	@Bean
	public List<RequestMatcher> matchers() {
		final List<RequestMatcher> matchers = new ArrayList<>();
		matchers.add(new AntPathRequestMatcher("/files/download/**", HttpMethod.GET.name()));
		return matchers;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthorizationFilter auth) throws Exception {
		http.cors();
		http.csrf().disable();
		http.addFilterAt(auth, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

    @Bean
	public WebSecurityCustomizer customizer() {
		return web -> matchers().forEach(r -> web.ignoring().requestMatchers(r));
	}
	
}
