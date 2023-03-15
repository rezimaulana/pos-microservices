package com.lawencon.productservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.core.constant.ResponseConst;
import com.lawencon.core.dao.impl.BaseDaoImpl;
import com.lawencon.core.dto.response.DataListResDto;
import com.lawencon.core.dto.response.DataResDto;
import com.lawencon.core.dto.response.InsertResDto;
import com.lawencon.core.dto.response.TransactionResDto;
import com.lawencon.core.util.AuthenticationUtil;
import com.lawencon.productservice.dao.declaration.ProductDao;
import com.lawencon.productservice.dto.product.ProductDataDto;
import com.lawencon.productservice.dto.product.ProductInsertReqDto;
import com.lawencon.productservice.model.Product;
import com.lawencon.productservice.service.declaration.ProductService;

@Service
public class ProductServiceImpl extends BaseDaoImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

	@Autowired
	private AuthenticationUtil authenticationUtil;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public TransactionResDto<InsertResDto> insert(ProductInsertReqDto data) {
        final TransactionResDto<InsertResDto> responseBe = new TransactionResDto<InsertResDto>();
		try {
			final Product product = new Product();
            product.setName(data.getName());
            product.setPrice(data.getPrice());
            product.setQuantity(data.getQuantity());
			product.setCreatedBy(authenticationUtil.getPrincipal().getId());
			final Product insertOne = productDao.insert(product);
			final InsertResDto responseDb = new InsertResDto();
			responseDb.setId(insertOne.getId());
			responseBe.setData(responseDb);
			responseBe.setMessage(ResponseConst.CREATED.getResponse());
		} catch (Exception e) {
			responseBe.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseBe;
    }

    @Override
    public DataResDto<ProductDataDto> getById(String id) {
        final Optional<Product> optional = productDao.getById(id);
		Product findOne = null;
		if (optional.isPresent()) {
			findOne = optional.get();
			final ProductDataDto responseDb = setToDto(findOne);
			final DataResDto<ProductDataDto> responseBe = new DataResDto<ProductDataDto>();
			responseBe.setData(responseDb);
			return responseBe;
		} else {
			throw new RuntimeException("Not found!");
		}
    }

    @Override
    public DataListResDto<ProductDataDto> getAll(Integer page, Integer limit) {
        final List<ProductDataDto> responseDb = new ArrayList<>();
		final List<Product> find = productDao.getAll(page, limit);
		for (int i = 0; i < find.size(); i++) {
			final Product product = find.get(i);
			final ProductDataDto result = setToDto(product);
			responseDb.add(result);
		}
		final DataListResDto<ProductDataDto> responseBe = new DataListResDto<ProductDataDto>();
		responseBe.setData(responseDb);
		return responseBe;
    }

	@Override
	public ProductDataDto setToDto(Product data) {
		final ProductDataDto dto = new ProductDataDto();
		dto.setId(data.getId());
		dto.setName(data.getName());
        dto.setPrice(data.getPrice());
        dto.setQuantity(data.getQuantity());
		dto.setVer(data.getVer());
		dto.setIsActive(data.getIsActive());
		return dto;
	}
    
}
