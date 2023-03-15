package com.lawencon.productservice.controller;

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

import com.lawencon.core.dto.response.DataListResDto;
import com.lawencon.core.dto.response.DataResDto;
import com.lawencon.core.dto.response.InsertResDto;
import com.lawencon.core.dto.response.TransactionResDto;
import com.lawencon.productservice.dto.product.ProductDataDto;
import com.lawencon.productservice.dto.product.ProductInsertReqDto;
import com.lawencon.productservice.service.declaration.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
    
    @Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<TransactionResDto<InsertResDto>> insert(@RequestBody @Valid final ProductInsertReqDto data){
		final TransactionResDto<InsertResDto> result = productService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<DataResDto<ProductDataDto>> getById(@RequestParam(required = true) final String id){
		final DataResDto<ProductDataDto> result = productService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("data")
	public ResponseEntity<DataListResDto<ProductDataDto>> getAll(@RequestParam(required = true) final Integer page,
			@RequestParam(required = true) final Integer limit) {
		final DataListResDto<ProductDataDto> result = productService.getAll(page, limit);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
