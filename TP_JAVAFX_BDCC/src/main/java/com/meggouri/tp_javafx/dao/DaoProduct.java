package com.meggouri.tp_javafx.dao;


import com.meggouri.tp_javafx.dao.entities.Product;

import java.util.List;

public interface DaoProduct extends Dao<Product> {
    public List<Product> getProductsByQuery(String query);
}
