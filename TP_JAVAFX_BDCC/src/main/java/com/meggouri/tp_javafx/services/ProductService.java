package com.meggouri.tp_javafx.services;

import com.meggouri.tp_javafx.dao.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getALl();
    List<Product> getByQuery(String query);
    Product findById(int id);
    Product add(Product a);
    boolean delete(Product a);
    Product update(Product a);
}
