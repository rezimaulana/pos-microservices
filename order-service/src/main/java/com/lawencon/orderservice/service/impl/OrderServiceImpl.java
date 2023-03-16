package com.lawencon.orderservice.service.impl;

import java.math.BigDecimal;
import java.util.Map;

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
import com.lawencon.core.util.GenerateCodeUtil;
import com.lawencon.core.util.RestTemplateUtil;
import com.lawencon.orderservice.dao.declaration.OrderDtlDao;
import com.lawencon.orderservice.dao.declaration.OrderHdrDao;
import com.lawencon.orderservice.dto.orderdtl.OrderDtlDataDto;
import com.lawencon.orderservice.dto.orderhdr.OrderHdrDataDto;
import com.lawencon.orderservice.dto.orderhdr.OrderHdrInsertReqDto;
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
            valInsert(data);
			final OrderHdr orderHdr = new OrderHdr();
            orderHdr.setTrxCode(generateCodeUtil.generateAlphaNumeric(6));
            orderHdr.setCustomerName(data.getCustomerName());
            orderHdr.setEmployee(data.getEmployee());
            orderHdr.setGrandTotal(new BigDecimal(0));
            orderHdr.setCreatedBy(authenticationUtil.getPrincipal().getId());
            final OrderHdr insertOne = orderHdrDao.insert(orderHdr);
            for(int i = 0; i<data.getDetail().size(); i++){
                OrderDtl orderDtl = new OrderDtl();
                orderDtl.setOrderHdr(orderHdr);
                orderDtl.setProduct(data.getDetail().get(i).getProduct());
                orderDtl.setQuantity(data.getDetail().get(i).getQuantity());
                orderDtl.setSubTotal(new BigDecimal(0));
                orderDtl.setCreatedBy(authenticationUtil.getPrincipal().getId());
                orderDtl = orderDtlDao.insert(orderDtl);
            }
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
    public void valInsert(OrderHdrInsertReqDto data) {
        valFkFound(data);
    }

    @Override
    public void valFkFound(OrderHdrInsertReqDto data) {
        restTemplateUtil.get(Map.class, "/users?="+data.getEmployee(), authenticationUtil.getPrincipal().getToken(), "http://localhost:5016");
        System.out.println("test");
        // final UserType userType = userTypeDao.getByIdAndDetach(UserType.class, data.getUserType().getId());
		// final Optional<UserType> userTypeOptional = Optional.ofNullable(userType);
		// if (!userTypeOptional.isPresent()) {
		// 	throw new RuntimeException("User Type Not Found.");
		// }
    }
    
}
