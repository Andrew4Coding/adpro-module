package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepository {
    // Hashmap to store products
    private Map<String, Product> products = new HashMap<>();

    public Product create(Product product) {
        products.put(product.getId(), product);
        return product;
    }

    public Iterator<Product> findAll() {
        List<Product> productList = new ArrayList<>(products.values());
        return productList.iterator();
    }

    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    public Optional<Product> update(Product product) {
        if (products.containsKey(product.getId())) {
            products.put(product.getId(), product);
            return Optional.of(product);
        }
        return Optional.empty();
    }

    public void delete(String id) {
        products.remove(id);
    }
}
