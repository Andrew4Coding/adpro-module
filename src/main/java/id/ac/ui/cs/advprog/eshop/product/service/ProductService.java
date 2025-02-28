package id.ac.ui.cs.advprog.eshop.product.service;

import java.util.List;
import java.util.Optional;

import id.ac.ui.cs.advprog.eshop.product.model.Product;

public interface ProductService {
    Product create(Product car);

    List<Product> findAll();

    Optional<Product> findById(String carId);

    Optional<Product> update(Product car);

    void delete(String carId);
}
