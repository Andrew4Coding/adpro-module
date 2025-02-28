package id.ac.ui.cs.advprog.eshop.product.repository;

import java.util.Iterator;
import java.util.Optional;

import id.ac.ui.cs.advprog.eshop.product.model.Product;

public interface ProductRepository {
    Product create(Product product);
    Iterator<Product> findAll();
    Optional<Product> findById(String carId);
    
    Optional<Product> update(Product product);
    void delete(String carId);
}
