package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.product.model.Product;

public class ProductTest {
    Product product;

    @BeforeEach
    void setup() {
        this.product = new Product();
        this.product.setId("123e4567-e89b-12d3-a456-556642440000");
        this.product.setName("Product 1");
        this.product.setQuantity(100);
    }

    @Test
    public void testgetId() {
        assert(this.product.getId().equals("123e4567-e89b-12d3-a456-556642440000"));
    }

    @Test
    public void testgetName() {
        assert(this.product.getName().equals("Product 1"));
    }

    @Test
    public void testgetQuantity() {
        assert(this.product.getQuantity() == 100);
    }
}
