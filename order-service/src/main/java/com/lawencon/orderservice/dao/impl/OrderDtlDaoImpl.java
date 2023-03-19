package com.lawencon.orderservice.dao.impl;

import java.util.List;
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
        final Optional<OrderDtl> result = Optional.ofNullable(findOne);
        if(result.isPresent()) {
            em.detach(findOne);
        }
        return result;
    }

    @Override
    public List<OrderDtl> getByOrderHdr(final String id) {
        final String sql = "SELECT doc FROM OrderDtl doc "
                + "INNER JOIN FETCH doc.orderHdr hdr "
                + "WHERE hdr.id = :id ";
        final List<OrderDtl> result = this.em.createQuery(sql, OrderDtl.class)
                .setParameter("id", id)
                .getResultList();
        return result;
    }
    
}