package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<T> {
    T create(T product);

    List<T> findAll();
    
    Optional<T> findById(String id);

    Optional<T> update(T product);

    void delete(String id);
}
