package com.lawencon.orderservice.dao.declaration;

import java.util.Optional;

import com.lawencon.orderservice.model.OrderDtl;

public interface OrderDtlDao {

    public OrderDtl insert(OrderDtl data);

    public Optional<OrderDtl> getById(String id);

}
