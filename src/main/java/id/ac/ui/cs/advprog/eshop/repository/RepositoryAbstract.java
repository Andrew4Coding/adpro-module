package id.ac.ui.cs.advprog.eshop.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import id.ac.ui.cs.advprog.eshop.model.ModelAbstract;

public abstract class RepositoryAbstract<T extends ModelAbstract> {
    Map<String, T> items = new HashMap<>();

    public T create(T product) {
        items.put(product.getId(), product);
        return product;
    }

    public Iterator<T> findAll() {
        List<T> productList = new ArrayList<>(items.values());
        return productList.iterator();
    }

    public Optional<T> findById(String id) {
        return Optional.ofNullable(items.get(id));
    }

    public Optional<T> update(T product) {
        if (items.containsKey(product.getId())) {
            items.put(product.getId(), product);
            return Optional.of(product);
        }
        return Optional.empty();
    }

    public void delete(String id) {
        items.remove(id);
    }
}