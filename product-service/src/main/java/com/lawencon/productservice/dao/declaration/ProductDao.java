package com.lawencon.productservice.dao.declaration;

import java.util.List;
import java.util.Optional;

import com.lawencon.productservice.model.Product;

public interface ProductDao {
    
    public Product insert(Product data);

    public Product update(Product data);

    public Optional<Product> getById(String id);

    public List<Product> getAll(Integer page, Integer limit);

    public Integer countAll();

}
