package com.lawencon.productservice.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.core.dao.impl.BaseDaoImpl;
import com.lawencon.productservice.dao.declaration.ProductDao;
import com.lawencon.productservice.model.Product;

@Repository
public class ProductDaoImpl extends BaseDaoImpl implements ProductDao{

    @Override
    public Product insert(Product data) {
        this.em.persist(data);
        return data;
    }

    @Override
    public Product update(Product data) {
        final Product result = this.em.merge(data);
        this.em.flush();
        return result;
    }

    @Override
    public Optional<Product> getById(String id) {
        final Product findOne = this.em.find(Product.class, id);
        final Optional<Product> result = Optional.ofNullable(findOne);
        if(result.isPresent()) {
            em.detach(findOne);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getAll(Integer page, Integer limit) {
        final String sql = "SELECT * FROM products doc WHERE doc.is_active = true";
        final Query query = this.em.createNativeQuery(sql, Product.class);
        query.setFirstResult((page-1) * limit);
        query.setMaxResults(limit);
        final List<Product> result = query.getResultList();
        return result;
    }

    @Override
    public Integer countAll(){
        final String sql = "SELECT COUNT(*) FROM products doc WHERE doc.is_active = true";
        final Query query = this.em.createNativeQuery(sql);
        BigInteger countData = (BigInteger) query.getSingleResult();
		Integer count = countData.intValue();
		return count;
    }    
    
}
