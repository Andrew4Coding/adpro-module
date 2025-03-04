package id.ac.ui.cs.advprog.eshop.order.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.product.model.Product;

public class OrderTest {
    private List<Product> products = new ArrayList<>();
    

    @BeforeEach
    void setup() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setName("Sampo Ganteng");
        product1.setQuantity(2);

        Product product2 = new Product();
        product2.setName("Sampo Cantik");
        product2.setQuantity(3);

        this.products.add(product1);
        this.products.add(product2);
    }

    @Test
    void testCreateOrderEmptyProduct() {
        this.products.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            new Order(this.products, 1708560000L, "Hehe");
        });
    }

    @Test
    void testCreateOrderDefaultStatus() {
        Order order = new Order(this.products, 1708560000L, "Hehe");

        assertSame(this.products, order.getProducts());
        assertEquals(2, order.getProducts().size());
        assertEquals("Sampo Ganteng", order.getProducts().get(0).getName());
        assertEquals("Sampo Cantik", order.getProducts().get(1).getName());

        assertEquals(1708560000L, order.getOrderTime());
        assertEquals("Hehe", order.getAuthor());
        assertEquals("WAITING_PAYMENT", order.getStatus());
    }

    @Test
    void testCreateOrderSuccessStatus() {
        Order order = new Order(this.products, 1708560000L, "Hehe", "SUCCESS");
        assertEquals("SUCCESS", order.getStatus());
    }

    @Test
    void testCreateOrderInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Order(this.products, 1708560000L, "Hehe", "INVALID_STATUS");
        });
    }

    @Test
    void testSetStatusToCancelled() {
        Order order = new Order(this.products, 1708560000L, "Hehe");
        order.setStatus("CANCELLED");

        assertEquals("CANCELLED", order.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Order order = new Order(this.products, 1708560000L, "Hehe");

        assertThrows(IllegalArgumentException.class, () -> {
            order.setStatus("INVALID_STATUS");
        });
    }
}
