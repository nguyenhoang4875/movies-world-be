package com.movies.service;

import com.movies.entity.dao.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAllProduct();

    Optional<Product> findById(Integer id);

    void save(Product product);

    void remove(Product product);

}
