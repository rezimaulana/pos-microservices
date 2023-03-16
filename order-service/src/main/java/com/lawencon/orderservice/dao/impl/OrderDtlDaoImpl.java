package com.lawencon.orderservice.dao.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.core.dao.impl.BaseDaoImpl;
import com.lawencon.orderservice.dao.declaration.OrderDtlDao;
import com.lawencon.orderservice.model.OrderDtl;

@Repository
public class OrderDtlDaoImpl extends BaseDaoImpl implements OrderDtlDao{

    @Override
    public OrderDtl insert(OrderDtl data) {
        this.em.persist(data);
        return data;
    }

    @Override
    public Optional<OrderDtl> getById(String id) {
        final OrderDtl findOne = this.em.find(OrderDtl.class, id);
        em.detach(findOne);
        final Optional<OrderDtl> result = Optional.ofNullable(findOne);
        return result;
    }
    
}
