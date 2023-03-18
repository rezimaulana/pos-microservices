package com.lawencon.orderservice.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.core.dao.impl.BaseDaoImpl;
import com.lawencon.orderservice.dao.declaration.OrderHdrDao;
import com.lawencon.orderservice.model.OrderHdr;

@Repository
public class OrderHdrDaoImpl extends BaseDaoImpl implements OrderHdrDao{

    @Override
    public OrderHdr insert(OrderHdr data) {
        this.em.persist(data);
        return data;
    }

    @Override
    public OrderHdr update(OrderHdr data) {
        final OrderHdr result = this.em.merge(data);
        this.em.flush();
        return result;
    }

    @Override
    public Optional<OrderHdr> getById(String id) {
        final OrderHdr findOne = this.em.find(OrderHdr.class, id);
        final Optional<OrderHdr> result = Optional.ofNullable(findOne);
        if(result.isPresent()) {
            em.detach(findOne);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<OrderHdr> getAll(Integer page, Integer limit) {
        final String sql = "SELECT * FROM order_hdr doc ";
        final Query query = this.em.createNativeQuery(sql, OrderHdr.class);
        query.setFirstResult((page-1) * limit);
        query.setMaxResults(limit);
        final List<OrderHdr> result = query.getResultList();
        return result;
    }
    
}
