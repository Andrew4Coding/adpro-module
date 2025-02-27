package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        product.setId("123e4567-e89b-12d3-a456-556642440000");
        product.setName("Product 1");
        product.setQuantity(100);
    }

    @Test
    void testCreate() {
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertEquals(product.getId(), createdProduct.getId());
        assertEquals(product.getName(), createdProduct.getName());
        assertEquals(product.getQuantity(), createdProduct.getQuantity());
        verify(productRepository, times(1)).create(createdProduct);
    }

    @Test
    void testFindAll() {
        Product product2 = new Product();
        product2.setId("123e4567-e89b-12d3-a456-556642440001");
        product2.setName("Product 2");
        product2.setQuantity(200);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product, product2).iterator());

        List<Product> products = productService.findAll();

        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        Product foundProduct = productService.findById(product.getId()).get();

        assertEquals(product.getId(), foundProduct.getId());
        assertEquals(product.getName(), foundProduct.getName());
        assertEquals(product.getQuantity(), foundProduct.getQuantity());
        verify(productRepository, times(1)).findById(product.getId());
    }

    @Test
    void testUpdate() {
        Product updatedProduct = new Product();
        updatedProduct.setId("123e4567-e89b-12d3-a456-556642440000");
        updatedProduct.setName("Product 1");
        updatedProduct.setQuantity(200);

        when(productRepository.update(updatedProduct)).thenReturn(Optional.of(updatedProduct));

        Optional<Product> productOptional = productService.update(updatedProduct);

        assertTrue(productOptional.isPresent());
        assertEquals(updatedProduct.getId(), productOptional.get().getId());
        assertEquals(updatedProduct.getName(), productOptional.get().getName());
        assertEquals(updatedProduct.getQuantity(), productOptional.get().getQuantity());

        verify(productRepository, times(1)).update(updatedProduct);
    }

    @Test
    void testDelete() {
        doNothing().when(productRepository).delete(product.getId());

        productService.delete(product.getId());

        verify(productRepository, times(1)).delete(product.getId());
    }
}
