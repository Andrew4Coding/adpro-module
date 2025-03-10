package id.ac.ui.cs.advprog.eshop.product.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.eshop.product.model.Product;
import id.ac.ui.cs.advprog.eshop.product.repository.ProductRepositoryImpl;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepositoryImpl productRepository;

    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> products = new ArrayList<>();

        productIterator.forEachRemaining(products::add);
        return products;
    }

    @Override
    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> update(Product product) {
        return productRepository.update(product);
    }

    @Override
    public void delete(String id) {
        productRepository.delete(id);
    }
}
