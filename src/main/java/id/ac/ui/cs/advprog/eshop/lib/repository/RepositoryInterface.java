package id.ac.ui.cs.advprog.eshop.lib.repository;

import java.util.Iterator;
import java.util.Optional;

import id.ac.ui.cs.advprog.eshop.lib.model.ModelAbstract;

public interface RepositoryInterface<T extends ModelAbstract> {
    T create(T item);
    Iterator<T> findAll();
    Optional<T> findById(String id);
    
    Optional<T> update(T item);
    void delete(String id);   
}