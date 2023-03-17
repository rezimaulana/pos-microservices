package com.lawencon.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.core.dto.response.DataListResDto;
import com.lawencon.core.dto.response.DataResDto;
import com.lawencon.core.dto.user.UserDataDto;
import com.lawencon.userservice.service.declaration.UserService;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<DataResDto<UserDataDto>> getById(@RequestParam(required = true) final String id){
		final DataResDto<UserDataDto> result = userService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("data")
	public ResponseEntity<DataListResDto<UserDataDto>> getAll(@RequestParam(required = true) final Integer page,
			@RequestParam(required = true) final Integer limit) {
		final DataListResDto<UserDataDto> result = userService.getAll(page, limit);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}

