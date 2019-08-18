package com.jack.service;

import com.jack.domain.Product;

import java.util.List;

public interface IProductService {

    public List<Product> findAll();

    void saveProduct ( Product product);
}
