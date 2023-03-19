package com.lawencon.userservice.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.lawencon.userservice.security.AuthorizationFilter;
import com.lawencon.userservice.service.impl.UserServiceImpl;

@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public List<RequestMatcher> matchers() {
		final List<RequestMatcher> matchers = new ArrayList<>();
		matchers.add(new AntPathRequestMatcher("/login/**", HttpMethod.POST.name()));
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

    @Bean
	public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder pwd, UserServiceImpl userService)
			throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userService)
				.passwordEncoder(pwd).and().build();
	}
	
}
