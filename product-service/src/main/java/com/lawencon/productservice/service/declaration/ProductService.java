package com.lawencon.productservice.service.declaration;

import com.lawencon.core.dto.product.ProductDataDto;
import com.lawencon.core.dto.product.ProductInsertReqDto;
import com.lawencon.core.dto.response.DataListResDto;
import com.lawencon.core.dto.response.DataResDto;
import com.lawencon.core.dto.response.InsertResDto;
import com.lawencon.core.dto.response.TransactionResDto;
import com.lawencon.productservice.model.Product;

public interface ProductService {
    
    public TransactionResDto<InsertResDto> insert(ProductInsertReqDto data);

	public DataResDto<ProductDataDto> getById(String id);

	public DataListResDto<ProductDataDto> getAll(Integer page, Integer limit);

	public ProductDataDto setToDto(Product data);

}
