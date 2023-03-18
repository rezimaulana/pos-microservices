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
import com.lawencon.core.dto.orderdtl.OrderDtlInsertReqDto;
import com.lawencon.core.dto.orderhdr.OrderHdrDataDto;
import com.lawencon.core.dto.orderhdr.OrderHdrInsertReqDto;
import com.lawencon.core.dto.product.ProductDataDto;
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
            valFkFoundUser(data);
            orderHdr.setEmployee(data.getEmployee());
            orderHdr.setCreatedBy(authenticationUtil.getPrincipal().getId());
            BigDecimal grandTotal = new BigDecimal(0);
            final List<OrderDtl> listOrderDtl = new ArrayList<>();
            final List<String> productId = new ArrayList<>();
            final List<Integer> productQty = new ArrayList<>();
            for(int i = 0; i<data.getDetail().size(); i++){
                OrderDtl orderDtl = new OrderDtl();
                final ResponseEntity<DataResDto<ProductDataDto>> product = valFkFoundProduct(data.getDetail().get(i));
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
                if(!productId.isEmpty()){
                    for(int j=0; j<productId.size(); j++){
                        if(productId.get(j).equals(data.getDetail().get(i).getProduct())){
                            throw new RuntimeException("Duplicate Product!");
                        }
                    }
                    productQty.add(productData.get().getQuantity() - data.getDetail().get(i).getQuantity());
                }
                productId.add(data.getDetail().get(i).getProduct());
                listOrderDtl.add(orderDtl);
            }
            orderHdr.setGrandTotal(grandTotal);
            final OrderHdr insertOne = orderHdrDao.insert(orderHdr);
            for(int i = 0; i<listOrderDtl.size(); i++){
                listOrderDtl.get(i).setOrderHdr(orderHdr);
                orderDtlDao.insert(listOrderDtl.get(i));
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public DataListResDto<OrderHdrDataDto> getAll(Integer page, Integer limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public OrderHdrDataDto setToDtoOrderHdr(OrderHdr data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setToDtoOrderHdr'");
    }

    @Override
    public OrderDtlDataDto setToDtoOrderDtl(OrderDtl data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setToDtoOrderDtl'");
    }

    @Override
    // @SuppressWarnings({ "rawtypes", "unchecked" })
    public ResponseEntity<DataResDto<UserDataDto>> valFkFoundUser(OrderHdrInsertReqDto data) {  
        // System.out.println(resultUser.getBody().getData().getId());
        ResponseEntity<DataResDto<UserDataDto>> result = restTemplateUtil.get(new ParameterizedTypeReference<DataResDto<UserDataDto>>() {},
            UrlConst.GATEWAY_USER_GET_BY_ID.getUri() + data.getEmployee(), 
            authenticationUtil.getPrincipal().getToken(), UrlConst.GATEWAY_BASE.getUri());
        return result;
    }

    @Override
    public ResponseEntity<DataResDto<ProductDataDto>> valFkFoundProduct(OrderDtlInsertReqDto data) {  
        ResponseEntity<DataResDto<ProductDataDto>> result = restTemplateUtil.get(new ParameterizedTypeReference<DataResDto<ProductDataDto>>() {},
            UrlConst.GATEWAY_PRODUCT_GET_BY_ID.getUri() + data.getProduct(), 
            authenticationUtil.getPrincipal().getToken(), UrlConst.GATEWAY_BASE.getUri());
        return result;
    }

}
