package com.meggouri.tp_javafx.dao;

import java.util.List;

public interface Dao <T>{
    List<T> findALl();
    T findById(int id);
    T save(T a);
    boolean delete(T a);
    T update(T a);
}
