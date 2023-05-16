package com.lawencon.fileservice.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.core.dto.response.ErrorResDto;
import com.lawencon.core.pojo.AuthorizationPojo;
import com.lawencon.core.util.JwtUtil;

@Component
public class AuthorizationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private List<RequestMatcher> requestMatcher;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		long count = requestMatcher.stream().filter(m->m.matches(request)).collect(Collectors.counting());
		if(!request.getRequestURI().equals("login") && count == 0) {
			String header = request.getHeader("Authorization");
			final String[] parts = header.split(" ");
			try {
				final Map<String, Object> parse = jwtUtil.parseJwt(parts[1]);
				GrantedAuthority authority = new SimpleGrantedAuthority(parse.get("roleCode").toString());
				List<GrantedAuthority> authorities = Arrays.asList(authority);
				final AuthorizationPojo authorizationPojo = new AuthorizationPojo();
				authorizationPojo.setToken(parts[1]);
				authorizationPojo.setId(parse.get("id").toString());
				final Authentication auth = new UsernamePasswordAuthenticationToken(authorizationPojo, null, authorities);
				SecurityContextHolder.getContext().setAuthentication(auth);
			} catch (Exception e) {
				e.printStackTrace();
				ErrorResDto errorResDto = new ErrorResDto();
				errorResDto.setMessage("Invalid Token!");
				response.getWriter().append(objectMapper.writeValueAsString(errorResDto));
				response.setContentType("application/json");
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

}