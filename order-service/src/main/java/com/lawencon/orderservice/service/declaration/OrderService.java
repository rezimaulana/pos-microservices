package com.lawencon.orderservice.service.declaration;

import org.springframework.http.ResponseEntity;

import com.lawencon.core.dto.orderdtl.OrderDtlDataDto;
import com.lawencon.core.dto.orderdtl.OrderDtlInsertReqDto;
import com.lawencon.core.dto.orderhdr.OrderHdrDataDto;
import com.lawencon.core.dto.orderhdr.OrderHdrInsertReqDto;
import com.lawencon.core.dto.product.ProductDataDto;
import com.lawencon.core.dto.product.ProductUpdateReqDto;
import com.lawencon.core.dto.response.DataListResDto;
import com.lawencon.core.dto.response.DataResDto;
import com.lawencon.core.dto.response.InsertResDto;
import com.lawencon.core.dto.response.TransactionResDto;
import com.lawencon.core.dto.user.UserDataDto;
import com.lawencon.orderservice.model.OrderDtl;
import com.lawencon.orderservice.model.OrderHdr;

public interface OrderService {
    
    public TransactionResDto<InsertResDto> insert(OrderHdrInsertReqDto data);

	public DataResDto<OrderHdrDataDto> getById(String id);

	public DataListResDto<OrderHdrDataDto> getAll(Integer page, Integer limit);

	public OrderHdrDataDto setToDtoOrderHdr(OrderHdr data);

	public OrderDtlDataDto setToDtoOrderDtl(OrderDtl data);

	public ResponseEntity<DataResDto<UserDataDto>> valFkFoundUser(OrderHdrInsertReqDto data);

	public ResponseEntity<DataResDto<ProductDataDto>> valFkFoundProduct(OrderDtlInsertReqDto data);

	public ResponseEntity<ProductUpdateReqDto> valUpdateProductQuantity(ProductUpdateReqDto data);

    // valInsert
	// valNotNull(data); All required field!
	// valIdNull(data); Id Is Set. Expected Not Set!
	// valBkNotNull(data); BK is required!
	// valBkNotDuplicate(data); BK already exist!
	// valFkFound(data); If key not found throw error!
	
    // valUpdate
	// valIdNotNull(data); Primary Key required!
	// valIdExist(data); Primay Key Id Is Not Exist!
	// valBkNotNull(data); BK is required!
	// valBkNotChange(data); BK cannot be changed!
	// valFkFound(data); If key not found throw error!

}
