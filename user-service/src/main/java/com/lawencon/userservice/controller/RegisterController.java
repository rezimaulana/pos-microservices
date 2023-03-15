package com.lawencon.userservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.core.dto.response.InsertResDto;
import com.lawencon.core.dto.response.TransactionResDto;
import com.lawencon.userservice.dto.user.UserInsertReqDto;
import com.lawencon.userservice.service.declaration.UserService;

@RestController
@RequestMapping("register")
public class RegisterController {
    
    @Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<TransactionResDto<InsertResDto>> insert(@RequestBody @Valid final UserInsertReqDto data){
		final TransactionResDto<InsertResDto> result = userService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

}
