package com.lawencon.orderservice.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lawencon.core.constant.ResponseConst;
import com.lawencon.core.constant.UrlConst;
import com.lawencon.core.dao.impl.BaseDaoImpl;
import com.lawencon.core.dto.orderdtl.OrderDtlDataDto;
import com.lawencon.core.dto.orderhdr.OrderHdrDataDto;
import com.lawencon.core.dto.orderhdr.OrderHdrInsertReqDto;
import com.lawencon.core.dto.product.ProductDataDto;
import com.lawencon.core.dto.product.ProductUpdateReqDto;
import com.lawencon.core.dto.response.DataListResDto;
import com.lawencon.core.dto.response.DataResDto;
import com.lawencon.core.dto.response.InsertResDto;
import com.lawencon.core.dto.response.TransactionResDto;
import com.lawencon.core.dto.user.UserDataDto;
import com.lawencon.core.util.AuthenticationUtil;
import com.lawencon.core.util.GenerateCodeUtil;
import com.lawencon.core.util.RestTemplateUtil;
import com.lawencon.orderservice.dao.declaration.OrderDtlDao;
import com.lawencon.orderservice.dao.declaration.OrderHdrDao;
import com.lawencon.orderservice.model.OrderDtl;
import com.lawencon.orderservice.model.OrderHdr;
import com.lawencon.orderservice.service.declaration.OrderService;

@Service
public class OrderServiceImpl extends BaseDaoImpl implements OrderService {

    @Autowired
    private OrderHdrDao orderHdrDao;

    @Autowired
    private OrderDtlDao orderDtlDao;

    @Autowired
    private GenerateCodeUtil generateCodeUtil;

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Autowired
    private AuthenticationUtil authenticationUtil;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public TransactionResDto<InsertResDto> insert(OrderHdrInsertReqDto data) {
        final TransactionResDto<InsertResDto> responseBe = new TransactionResDto<InsertResDto>();
		try {
            final OrderHdr orderHdr = new OrderHdr();
            orderHdr.setTrxCode(generateCodeUtil.generateAlphaNumeric(6));
            orderHdr.setCustomerName(data.getCustomerName());
            valFkFoundUser(authenticationUtil.getPrincipal().getId());
            orderHdr.setEmployee(authenticationUtil.getPrincipal().getId());
            orderHdr.setCreatedBy(authenticationUtil.getPrincipal().getId());
            BigDecimal grandTotal = new BigDecimal(0);
            final List<OrderDtl> listOrderDtl = new ArrayList<>();
            final List<ProductUpdateReqDto> listProduct= new ArrayList<>();
            for(int i = 0; i<data.getDetail().size(); i++){
                final OrderDtl orderDtl = new OrderDtl();
                final ProductUpdateReqDto productDto = new ProductUpdateReqDto();
                final ResponseEntity<DataResDto<ProductDataDto>> product = valFkFoundProduct(data.getDetail().get(i).getProduct());
                final Optional<DataResDto<ProductDataDto>> productBody = Optional.ofNullable(product.getBody());
                final Optional<ProductDataDto> productData = productBody.map(DataResDto::getData);
                final BigDecimal price = productData.map(ProductDataDto::getPrice).orElse(BigDecimal.ZERO);
                orderDtl.setProduct(data.getDetail().get(i).getProduct());
                if(productData.get().getQuantity() - data.getDetail().get(i).getQuantity() <0){
                    throw new RuntimeException("Out of stock, please restock!");
                }
                orderDtl.setQuantity(data.getDetail().get(i).getQuantity());
                final BigDecimal subTotal = price.multiply(BigDecimal.valueOf(data.getDetail().get(i).getQuantity()));
                orderDtl.setSubTotal(subTotal);
                orderDtl.setCreatedBy(authenticationUtil.getPrincipal().getId());
                grandTotal = grandTotal.add(subTotal);
                if(!listProduct.isEmpty()){
                    for(int j=0; j<listProduct.size(); j++){
                        if(listProduct.get(j).getId().equals(data.getDetail().get(i).getProduct())){
                            throw new RuntimeException("Duplicate Product!");
                        }
                    }
                }
                productDto.setId(data.getDetail().get(i).getProduct());
                productDto.setQuantity(productData.get().getQuantity() - data.getDetail().get(i).getQuantity());
                productDto.setIsActive(data.getDetail().get(i).getIsActive());
                productDto.setVer(data.getDetail().get(i).getVer());
                listOrderDtl.add(orderDtl);
                listProduct.add(productDto);
            }
            orderHdr.setGrandTotal(grandTotal);
            final OrderHdr insertOne = orderHdrDao.insert(orderHdr);
            for(int i = 0; i<listOrderDtl.size(); i++){
                listOrderDtl.get(i).setOrderHdr(orderHdr);
                orderDtlDao.insert(listOrderDtl.get(i));
            }
            for(int i = 0; i<listProduct.size(); i++){
                valUpdateProductQuantity(listProduct.get(i));
            }
			final InsertResDto responseDb = new InsertResDto();
			responseDb.setId(insertOne.getId());
			responseBe.setData(responseDb);
			responseBe.setMessage(ResponseConst.CREATED.getResponse());
		} catch (Exception e) {
            responseBe.setMessage(e.getMessage());
            e.printStackTrace();
            throw e;
		}
		return responseBe;
    }

    @Override
    public DataResDto<OrderHdrDataDto> getById(String id) {
        final Optional<OrderHdr> optional = orderHdrDao.getById(id);
		OrderHdr findOne = null;
		if (optional.isPresent()) {
			findOne = optional.get();
			final OrderHdrDataDto responseDb = setToDtoOrderHdr(findOne);
			final DataResDto<OrderHdrDataDto> responseBe = new DataResDto<OrderHdrDataDto>();
			responseBe.setData(responseDb);
			return responseBe;
		} else {
			throw new RuntimeException("Not found!");
		}
    }

    @Override
    public DataListResDto<OrderHdrDataDto> getAll(Integer page, Integer limit) {
        final List<OrderHdrDataDto> responseDb = new ArrayList<>();
		final List<OrderHdr> find = orderHdrDao.getAll(page, limit);
		for (int i = 0; i < find.size(); i++) {
			final OrderHdr orderHdr = find.get(i);
			final OrderHdrDataDto result = setToDtoOrderHdr(orderHdr);
			responseDb.add(result);
		}
		final DataListResDto<OrderHdrDataDto> responseBe = new DataListResDto<OrderHdrDataDto>();
		responseBe.setCount(orderHdrDao.countAll());
        responseBe.setData(responseDb);
		return responseBe;
    }

    @Override
    public OrderHdrDataDto setToDtoOrderHdr(OrderHdr data) {
        final OrderHdrDataDto dto = new OrderHdrDataDto();
		dto.setId(data.getId());
        dto.setTrxCode(data.getTrxCode());
        dto.setCustomerName(data.getCustomerName());
        dto.setGrandTotal(data.getGrandTotal());
        final ResponseEntity<DataResDto<UserDataDto>> employee = valFkFoundUser(data.getEmployee());
        final Optional<DataResDto<UserDataDto>> employeeBody = Optional.ofNullable(employee.getBody());
        final Optional<UserDataDto> employeeData = employeeBody.map(DataResDto::getData);
        employeeData.ifPresent(dto::setEmployee);
        final List<OrderDtl> listOrderDtl = orderDtlDao.getByOrderHdr(data.getId());
        final List<OrderDtlDataDto> listOrderDtlDto = new ArrayList<>(); 
        for(int i = 0; i<listOrderDtl.size(); i++){
            final OrderDtlDataDto orderDtlDto = setToDtoOrderDtl(listOrderDtl.get(i));
            listOrderDtlDto.add(orderDtlDto);
        }
        dto.setDetail(listOrderDtlDto);
		dto.setVer(data.getVer());
		dto.setIsActive(data.getIsActive());
		return dto;
    }

    @Override
    public OrderDtlDataDto setToDtoOrderDtl(OrderDtl data) {
        final OrderDtlDataDto dto = new OrderDtlDataDto();
        dto.setId(data.getId());
        final ResponseEntity<DataResDto<ProductDataDto>> prodcut = valFkFoundProduct(data.getProduct());
        final Optional<DataResDto<ProductDataDto>> productBody = Optional.ofNullable(prodcut.getBody());
        final Optional<ProductDataDto> productData = productBody.map(DataResDto::getData);
        productData.ifPresent(dto::setProduct);
        dto.setQuantity(data.getQuantity());
        dto.setSubTotal(data.getSubTotal());
        dto.setIsActive(data.getIsActive());
        dto.setVer(data.getVer());
        return dto;
    }

    @Override
    // @SuppressWarnings({ "rawtypes", "unchecked" })
    public ResponseEntity<DataResDto<UserDataDto>> valFkFoundUser(String id) {  
        // System.out.println(resultUser.getBody().getData().getId());
        final ParameterizedTypeReference<DataResDto<UserDataDto>> entityTypeRef = new ParameterizedTypeReference<DataResDto<UserDataDto>>() {};
        final ResponseEntity<DataResDto<UserDataDto>> result = restTemplateUtil.get(
            entityTypeRef,
            UrlConst.GATEWAY_USER_GET_BY_ID.getUri() + id, 
            authenticationUtil.getPrincipal().getToken(),
            UrlConst.GATEWAY_BASE.getUri());
        return result;
    }

    @Override
    public ResponseEntity<DataResDto<ProductDataDto>> valFkFoundProduct(String id) {  
        final ParameterizedTypeReference<DataResDto<ProductDataDto>> entityTypeRef = new ParameterizedTypeReference<DataResDto<ProductDataDto>>() {};
        final ResponseEntity<DataResDto<ProductDataDto>> result = restTemplateUtil.get(
            entityTypeRef,
            UrlConst.GATEWAY_PRODUCT_GET_BY_ID.getUri() + id, 
            authenticationUtil.getPrincipal().getToken(),
            UrlConst.GATEWAY_BASE.getUri());
        return result;
    }

    @Override
    public ResponseEntity<ProductUpdateReqDto> valUpdateProductQuantity(ProductUpdateReqDto data) {
        final ParameterizedTypeReference<ProductUpdateReqDto> entityTypeRef = new ParameterizedTypeReference<ProductUpdateReqDto>() {};
        final ResponseEntity<ProductUpdateReqDto> result = restTemplateUtil.put(
            entityTypeRef, 
            UrlConst.GATEWAY_PRODUCT_UPDATE.getUri(),
            data,
            authenticationUtil.getPrincipal().getToken(),
            UrlConst.GATEWAY_BASE.getUri());
        return result;
    }

}
