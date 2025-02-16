package id.ac.ui.cs.advprog.eshop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.eshop.model.Product;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product.setProductName("Product 1");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();

        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> products = productRepository.findAll();
        assertFalse(products.hasNext());
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product.setProductName("Product 1");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product savedProduct = productRepository.findById(product.getProductId()).get();

        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product1.setProductName("Product 1");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("123e4567-e89b-12d3-a456-556642440001");
        product2.setProductName("Product 2");
        product2.setProductQuantity(200);
        productRepository.create(product2);

        Iterator<Product> products = productRepository.findAll();
        
        assertTrue(products.hasNext());

        Product savedProduct = products.next();
        if (savedProduct.getProductId().equals(product1.getProductId())) {
            assertEquals(product1.getProductId(), savedProduct.getProductId());
            assertEquals(product1.getProductName(), savedProduct.getProductName());
            assertEquals(product1.getProductQuantity(), savedProduct.getProductQuantity());
        } else {
            assertEquals(product2.getProductId(), savedProduct.getProductId());
            assertEquals(product2.getProductName(), savedProduct.getProductName());
            assertEquals(product2.getProductQuantity(), savedProduct.getProductQuantity());
        }
    }

    // Update
    @Test
    void testUpdate() {
        Product product = new Product();
        product.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product.setProductName("Product 1");
        product.setProductQuantity(100);
        productRepository.create(product);

        product.setProductName("Product 2");
        product.setProductQuantity(200);
        productRepository.update(product);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();

        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testUpdateIfEmpty() {
        Product product = new Product();
        product.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product.setProductName("Product 1");
        product.setProductQuantity(100);
        productRepository.update(product);

        Iterator<Product> products = productRepository.findAll();
        assertFalse(products.hasNext());
    }

    @Test
    void testUpdateIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product1.setProductName("Product 1");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("123e4567-e89b-12d3-a456-556642440001");
        product2.setProductName("Product 2");
        product2.setProductQuantity(200);
        productRepository.create(product2);

        product1.setProductName("Product 3");
        product1.setProductQuantity(300);
        productRepository.update(product1);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();
        if (savedProduct.getProductId().equals(product1.getProductId())) {
            assertEquals(product1.getProductId(), savedProduct.getProductId());
            assertEquals(product1.getProductName(), savedProduct.getProductName());
            assertEquals(product1.getProductQuantity(), savedProduct.getProductQuantity());
        } else {
            assertEquals(product2.getProductId(), savedProduct.getProductId());
            assertEquals(product2.getProductName(), savedProduct.getProductName());
            assertEquals(product2.getProductQuantity(), savedProduct.getProductQuantity());
        }

        savedProduct = products.next();
        if (savedProduct.getProductId().equals(product1.getProductId())) {
            assertEquals(product1.getProductId(), savedProduct.getProductId());
            assertEquals(product1.getProductName(), savedProduct.getProductName());
            assertEquals(product1.getProductQuantity(), savedProduct.getProductQuantity());
        } else {
            assertEquals(product2.getProductId(), savedProduct.getProductId());
            assertEquals(product2.getProductName(), savedProduct.getProductName());
            assertEquals(product2.getProductQuantity(), savedProduct.getProductQuantity());
        }

        assertFalse(products.hasNext());
    }

    // Delete
    @Test
    void testDelete() {
        Product product = new Product();
        product.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product.setProductName("Product 1");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.delete(product.getProductId());

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
        product1.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product1.setProductName("Product 1");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("123e4567-e89b-12d3-a456-556642440001");
        product2.setProductName("Product 2");
        product2.setProductQuantity(200);
        productRepository.create(product2);

        productRepository.delete(product1.getProductId());

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());

        assertFalse(products.hasNext());
    }
}
