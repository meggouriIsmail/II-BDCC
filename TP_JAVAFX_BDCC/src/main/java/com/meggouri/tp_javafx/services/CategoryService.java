package com.meggouri.tp_javafx.services;


import com.meggouri.tp_javafx.dao.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getALl();
    Category findById(int id);
    Category add(Category a);
    boolean delete(Category a);
    Category update(Category a);
}
