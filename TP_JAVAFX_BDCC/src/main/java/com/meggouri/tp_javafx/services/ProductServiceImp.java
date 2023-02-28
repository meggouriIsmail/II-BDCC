package com.meggouri.tp_javafx.services;

import com.meggouri.tp_javafx.dao.DaoProduct;
import com.meggouri.tp_javafx.dao.entities.Product;

import java.util.List;

public class ProductServiceImp implements ProductService{

    DaoProduct daoProduct;
    public ProductServiceImp(DaoProduct daoProduct) {
        this.daoProduct = daoProduct;
    }

    @Override
    public List<Product> getALl() {
        return daoProduct.findALl();
    }

    @Override
    public List<Product> getByQuery(String query) {
        return daoProduct.getProductsByQuery(query);
    }

    @Override
    public Product findById(int id) {
        return daoProduct.findById(id);
    }

    @Override
    public Product add(Product a) {
        return daoProduct.save(a);
    }

    @Override
    public boolean delete(Product a) {
        return daoProduct.delete(a);
    }

    @Override
    public Product update(Product a) {
        return daoProduct.update(a);
    }
}
