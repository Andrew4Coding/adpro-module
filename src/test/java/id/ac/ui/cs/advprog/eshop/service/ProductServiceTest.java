package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("123e4567-e89b-12d3-a456-556642440000");
        product.setProductName("Product 1");
        product.setProductQuantity(100);
    }

    @Test
    void testCreate() {
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertEquals(product.getProductId(), createdProduct.getProductId());
        assertEquals(product.getProductName(), createdProduct.getProductName());
        assertEquals(product.getProductQuantity(), createdProduct.getProductQuantity());
        verify(productRepository, times(1)).create(createdProduct);
    }

    @Test
    void testFindAll() {
        Product product2 = new Product();
        product2.setProductId("123e4567-e89b-12d3-a456-556642440001");
        product2.setProductName("Product 2");
        product2.setProductQuantity(200);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product, product2).iterator());

        List<Product> products = productService.findAll();

        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));

        Product foundProduct = productService.findById(product.getProductId()).get();

        assertEquals(product.getProductId(), foundProduct.getProductId());
        assertEquals(product.getProductName(), foundProduct.getProductName());
        assertEquals(product.getProductQuantity(), foundProduct.getProductQuantity());
        verify(productRepository, times(1)).findById(product.getProductId());
    }

    @Test
    void testUpdate() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("123e4567-e89b-12d3-a456-556642440000");
        updatedProduct.setProductName("Product 1");
        updatedProduct.setProductQuantity(200);

        when(productRepository.update(updatedProduct)).thenReturn(Optional.of(updatedProduct));

        Optional<Product> productOptional = productService.update(updatedProduct);

        assertTrue(productOptional.isPresent());
        assertEquals(updatedProduct.getProductId(), productOptional.get().getProductId());
        assertEquals(updatedProduct.getProductName(), productOptional.get().getProductName());
        assertEquals(updatedProduct.getProductQuantity(), productOptional.get().getProductQuantity());

        verify(productRepository, times(1)).update(updatedProduct);
    }

    @Test
    void testDelete() {
        doNothing().when(productRepository).delete(product.getProductId());

        productService.delete(product.getProductId());

        verify(productRepository, times(1)).delete(product.getProductId());
    }
}
