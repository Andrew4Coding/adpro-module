package id.ac.ui.cs.advprog.eshop.lib.service;

import java.util.List;
import java.util.Optional;

import id.ac.ui.cs.advprog.eshop.lib.model.ModelAbstract;

public interface ServiceInterface<T extends ModelAbstract> {
    T create(T item);

    List<T> findAll();

    Optional<T> findById(String id);

    Optional<T> update(T item);

    void delete(String id);
}
