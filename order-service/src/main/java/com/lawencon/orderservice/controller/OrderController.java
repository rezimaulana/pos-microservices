package com.lawencon.orderservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.core.dto.orderhdr.OrderHdrDataDto;
import com.lawencon.core.dto.orderhdr.OrderHdrInsertReqDto;
import com.lawencon.core.dto.response.DataListResDto;
import com.lawencon.core.dto.response.DataResDto;
import com.lawencon.core.dto.response.InsertResDto;
import com.lawencon.core.dto.response.TransactionResDto;
import com.lawencon.orderservice.service.declaration.OrderService;

@RestController
@RequestMapping("orders")
public class OrderController {
    
    @Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<TransactionResDto<InsertResDto>> insert(@RequestBody @Valid final OrderHdrInsertReqDto data){
		final TransactionResDto<InsertResDto> result = orderService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<DataResDto<OrderHdrDataDto>> getById(@RequestParam(required = true) final String id){
		final DataResDto<OrderHdrDataDto> result = orderService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("data")
	public ResponseEntity<DataListResDto<OrderHdrDataDto>> getAll(@RequestParam(required = true) final Integer page,
			@RequestParam(required = true) final Integer limit) {
		final DataListResDto<OrderHdrDataDto> result = orderService.getAll(page, limit);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
