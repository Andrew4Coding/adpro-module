package id.ac.ui.cs.advprog.eshop.product.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.eshop.product.model.Product;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepositoryImpl productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setId("123e4567-e89b-12d3-a456-556642440000");
        product.setName("Product 1");
        product.setQuantity(100);
        productRepository.create(product);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();

        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> products = productRepository.findAll();
        assertFalse(products.hasNext());
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setId("123e4567-e89b-12d3-a456-556642440000");
        product.setName("Product 1");
        product.setQuantity(100);
        productRepository.create(product);

        Product savedProduct = productRepository.findById(product.getId()).get();

        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setId("123e4567-e89b-12d3-a456-556642440000");
        product1.setName("Product 1");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setId("123e4567-e89b-12d3-a456-556642440001");
        product2.setName("Product 2");
        product2.setQuantity(200);
        productRepository.create(product2);

        Iterator<Product> products = productRepository.findAll();
        
        assertTrue(products.hasNext());

        Product savedProduct = products.next();
        if (savedProduct.getId().equals(product1.getId())) {
            assertEquals(product1.getId(), savedProduct.getId());
            assertEquals(product1.getName(), savedProduct.getName());
            assertEquals(product1.getQuantity(), savedProduct.getQuantity());
        } else {
            assertEquals(product2.getId(), savedProduct.getId());
            assertEquals(product2.getName(), savedProduct.getName());
            assertEquals(product2.getQuantity(), savedProduct.getQuantity());
        }
    }

    // Update
    @Test
    void testUpdate() {
        Product product = new Product();
        product.setId("123e4567-e89b-12d3-a456-556642440000");
        product.setName("Product 1");
        product.setQuantity(100);
        productRepository.create(product);

        product.setName("Product 2");
        product.setQuantity(200);
        productRepository.update(product);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();

        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }

    @Test
    void testUpdateIfEmpty() {
        Product product = new Product();
        product.setId("123e4567-e89b-12d3-a456-556642440000");
        product.setName("Product 1");
        product.setQuantity(100);
        productRepository.update(product);

        Iterator<Product> products = productRepository.findAll();
        assertFalse(products.hasNext());
    }

    @Test
    void testUpdateIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setId("123e4567-e89b-12d3-a456-556642440000");
        product1.setName("Product 1");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setId("123e4567-e89b-12d3-a456-556642440001");
        product2.setName("Product 2");
        product2.setQuantity(200);
        productRepository.create(product2);

        product1.setName("Product 3");
        product1.setQuantity(300);
        productRepository.update(product1);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();
        if (savedProduct.getId().equals(product1.getId())) {
            assertEquals(product1.getId(), savedProduct.getId());
            assertEquals(product1.getName(), savedProduct.getName());
            assertEquals(product1.getQuantity(), savedProduct.getQuantity());
        } else {
            assertEquals(product2.getId(), savedProduct.getId());
            assertEquals(product2.getName(), savedProduct.getName());
            assertEquals(product2.getQuantity(), savedProduct.getQuantity());
        }

        savedProduct = products.next();
        if (savedProduct.getId().equals(product1.getId())) {
            assertEquals(product1.getId(), savedProduct.getId());
            assertEquals(product1.getName(), savedProduct.getName());
            assertEquals(product1.getQuantity(), savedProduct.getQuantity());
        } else {
            assertEquals(product2.getId(), savedProduct.getId());
            assertEquals(product2.getName(), savedProduct.getName());
            assertEquals(product2.getQuantity(), savedProduct.getQuantity());
        }

        assertFalse(products.hasNext());
    }

    // Delete
    @Test
    void testDelete() {
        Product product = new Product();
        product.setId("123e4567-e89b-12d3-a456-556642440000");
        product.setName("Product 1");
        product.setQuantity(100);
        productRepository.create(product);

        productRepository.delete(product.getId());

        Iterator<Product> products = productRepository.findAll();
        assertFalse(products.hasNext());
    }

    @Test
    void testDeleteIfEmpty() {
        productRepository.delete("123e4567-e89b-12d3-a456-556642440000");

        Iterator<Product> products = productRepository.findAll();
        assertFalse(products.hasNext());
    }

    @Test
    void testDeleteIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setId("123e4567-e89b-12d3-a456-556642440000");
        product1.setName("Product 1");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setId("123e4567-e89b-12d3-a456-556642440001");
        product2.setName("Product 2");
        product2.setQuantity(200);
        productRepository.create(product2);

        productRepository.delete(product1.getId());

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();
        assertEquals(product2.getId(), savedProduct.getId());

        assertFalse(products.hasNext());
    }
}
