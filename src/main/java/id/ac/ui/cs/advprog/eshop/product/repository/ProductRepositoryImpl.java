package id.ac.ui.cs.advprog.eshop.product.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.eshop.product.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private Map<String, Product> cars = new HashMap<>();

    public Product create(Product model) {
        cars.put(model.getId(), model);
        return model;
    }

    public Iterator<Product> findAll() {
        List<Product> modelList = new ArrayList<>(cars.values());
        return modelList.iterator();
    }

    public Optional<Product> findById(String id) {
        return Optional.ofNullable(cars.get(id));
    }

    public Optional<Product> update(Product model) {
        if (cars.containsKey(model.getId())) {
            cars.put(model.getId(), model);
            return Optional.of(model);
        }
        return Optional.empty();
    }

    public void delete(String id) {
        cars.remove(id);
    }
}
