package com.company;

import java.util.List;

public interface Imetier<T> {
    Produit add(T o);
    List<T> getAll();
    T findById(T id);
    void delete(T id);
    void saveAll();
}
