package com.lawencon.productservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lawencon.core.constant.ResponseConst;
import com.lawencon.core.constant.SystemConst;
import com.lawencon.core.constant.UrlConst;
import com.lawencon.core.dao.impl.BaseDaoImpl;
import com.lawencon.core.dto.file.FileInsertReqDto;
import com.lawencon.core.dto.product.ProductDataDto;
import com.lawencon.core.dto.product.ProductInsertReqDto;
import com.lawencon.core.dto.product.ProductUpdateReqDto;
import com.lawencon.core.dto.response.DataListResDto;
import com.lawencon.core.dto.response.DataResDto;
import com.lawencon.core.dto.response.InsertResDto;
import com.lawencon.core.dto.response.TransactionResDto;
import com.lawencon.core.dto.response.UpdateResDto;
import com.lawencon.core.util.AuthenticationUtil;
import com.lawencon.core.util.RestTemplateUtil;
import com.lawencon.productservice.dao.declaration.ProductDao;
import com.lawencon.productservice.model.Product;
import com.lawencon.productservice.service.declaration.ProductService;

@Service
public class ProductServiceImpl extends BaseDaoImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

	@Autowired
	private AuthenticationUtil authenticationUtil;

	@Autowired
	private RestTemplateUtil restTemplateUtil;

	@SuppressWarnings("null")
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
			if(data.getEncode()!=null && data.getExtension()!=null) {
				final FileInsertReqDto file = new FileInsertReqDto();
				file.setEncode(data.getEncode());
				file.setExtension(data.getExtension());
				ResponseEntity<TransactionResDto<InsertResDto>> result = valInsertFile(file);
				if(result.getStatusCode() == HttpStatus.CREATED){
					product.setFile(result.getBody().getData().getId());
				}
			}
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

	@SuppressWarnings("null")
	@Transactional(rollbackOn = Exception.class)
    @Override
    public TransactionResDto<UpdateResDto> update(ProductUpdateReqDto data) {
        final TransactionResDto<UpdateResDto> responseBe = new TransactionResDto<UpdateResDto>();
		final Optional<Product> optional = productDao.getById(data.getId());
		Product updateOne = null;
		if (optional.isPresent()) {
			updateOne = optional.get();
			try {
				if(data.getName()!=null){
					updateOne.setName(data.getName());
				}
				if(data.getQuantity()!=null){
					updateOne.setQuantity(data.getQuantity());
				}
				if(data.getPrice()!=null){
					updateOne.setPrice(data.getPrice());
				}
				if(data.getEncode()!=null && data.getExtension()!=null){
					final FileInsertReqDto file = new FileInsertReqDto();
					file.setEncode(data.getEncode());
					file.setExtension(data.getExtension());
					ResponseEntity<TransactionResDto<InsertResDto>> result = valInsertFile(file);
					if(result.getStatusCode() == HttpStatus.CREATED){
						updateOne.setFile(result.getBody().getData().getId());
					}
				}				
				updateOne.setUpdatedBy(authenticationUtil.getPrincipal().getId());
				updateOne.setIsActive(data.getIsActive());
				updateOne.setVer(data.getVer());
				updateOne = productDao.update(updateOne);
				final UpdateResDto responseDb = new UpdateResDto();
				responseDb.setVer(updateOne.getVer());
				responseBe.setData(responseDb);
				responseBe.setMessage(ResponseConst.UPDATED.getResponse());
			} catch (Exception e) {
				responseBe.setMessage(e.getMessage());
				e.printStackTrace();
			}
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
		responseBe.setCount(productDao.countAll());
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
		if(data.getFile()==null){
			dto.setFile(SystemConst.FILE_DEFAULT.getName());
		} else {
			dto.setFile(data.getFile());
		}
		dto.setVer(data.getVer());
		dto.setIsActive(data.getIsActive());
		return dto;
	}

	@Override
	public ResponseEntity<TransactionResDto<InsertResDto>> valInsertFile(FileInsertReqDto data) {
		ParameterizedTypeReference<TransactionResDto<InsertResDto>> entityTypeRef = new ParameterizedTypeReference<TransactionResDto<InsertResDto>>() {};
		ResponseEntity<TransactionResDto<InsertResDto>> result = restTemplateUtil.post(
			entityTypeRef, 
			UrlConst.GATEWAY_FILE_INSERT.getUri(),
			data,
			authenticationUtil.getPrincipal().getToken(),
			UrlConst.GATEWAY_BASE.getUri()
		);
		return result;
	}
    
}
