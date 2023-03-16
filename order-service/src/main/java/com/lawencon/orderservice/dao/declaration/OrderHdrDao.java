package com.lawencon.orderservice.dao.declaration;

import java.util.List;
import java.util.Optional;

import com.lawencon.orderservice.model.OrderHdr;

public interface OrderHdrDao {
    
    public OrderHdr insert(OrderHdr data);

    public OrderHdr update(OrderHdr data);

    public Optional<OrderHdr> getById(String id);

    public List<OrderHdr> getAll(Integer page, Integer limit);

}
