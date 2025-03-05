package id.ac.ui.cs.advprog.eshop.order.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.order.model.Order;
import id.ac.ui.cs.advprog.eshop.order.model.OrderStatus;
import id.ac.ui.cs.advprog.eshop.product.model.Product;

public class OrderRepositoryTest {
    OrderRepositoryImpl orderRepository;
    List<Order> orders;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepositoryImpl();

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();

        product1.setName("Product 1");
        product1.setQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order(products, 170856000L, "Andrew 1");
        orders.add(order1);

        Order order2 = new Order(products, 170857001L, "Andrew 2");
        orders.add(order2);

        Order order3 = new Order(products, 170858002L, "Andrew 3");
        orders.add(order3);
    }

    @Test
    void testSaveCreate() {
        Order order = orders.get(1);
        Order result = orderRepository.save(order);

        Order findResult = orderRepository.findById(orders.get(1).getId()).get();

        assertEquals(order.getId(), result.getId());
        assertEquals(order.getProducts(), findResult.getProducts());
        assertEquals(order.getOrderTime(), findResult.getOrderTime());
        assertEquals(order.getAuthor(), findResult.getAuthor());
        assertEquals(order.getStatus(), findResult.getStatus());
    }

    @Test
    void testSaveUpdate() {
        Order order = orders.get(1);
        orderRepository.save(order);

        Order newOrder = new Order(order.getProducts(), order.getOrderTime(), order.getAuthor(),
                OrderStatus.SUCCESS.getValue());
        Order result = orderRepository.save(newOrder);

        Order findResult = orderRepository.findById(orders.get(1).getId()).get();

        assertEquals(newOrder.getId(), result.getId());
        assertEquals(newOrder.getProducts(), findResult.getProducts());
        assertEquals(newOrder.getOrderTime(), findResult.getOrderTime());
        assertEquals(newOrder.getAuthor(), findResult.getAuthor());
        assertEquals(OrderStatus.SUCCESS.getValue(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdFound() {

        for (Order order : orders) {
            orderRepository.save(order);
        }

        Order findResult = orderRepository.findById(orders.get(1).getId()).get();

        assertEquals(orders.get(1).getId(), findResult.getId());
        assertEquals(orders.get(1).getProducts(), findResult.getProducts());
        assertEquals(orders.get(1).getOrderTime(), findResult.getOrderTime());
        assertEquals(orders.get(1).getAuthor(), findResult.getAuthor());
        assertEquals(orders.get(1).getStatus(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Order order : orders) {
            orderRepository.save(order);
        }

        Order findResult = orderRepository.findById("Hello").get();

        assertNull(findResult);
    }

    @Test
    void testFindAllByAuthorIfAuthorCorrect() {
        for (Order order : orders) {
            orderRepository.save(order);
        }

        List<Order> orderList = orderRepository.findAllByAuthor(orders.get(1).getAuthor());
        assertEquals(2, orderList.size());
    }
    
    @Test
    void testFindAllByAuthorIfAllLowerCase() {
        orderRepository.save(orders.get(1));
        List<Order> orderList = orderRepository.findAllByAuthor(orders.get(1).getAuthor().toLowerCase());

        assertTrue(orderList.isEmpty());
    }
}
