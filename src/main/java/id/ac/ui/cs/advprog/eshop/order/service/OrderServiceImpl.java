package id.ac.ui.cs.advprog.eshop.order.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.eshop.order.model.Order;
import id.ac.ui.cs.advprog.eshop.order.repository.OrderRepositoryImpl;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepositoryImpl orderRepository;

    @Override
    public Order create(Order order) {
        try {
            orderRepository.findById(order.getId()).get();
            return null;
        } catch (NoSuchElementException e) {
            orderRepository.save(order);
            return order;
        }
    }

    @Override
    public List<Order> findAllByAuthor(String author) {
        return orderRepository.findAllByAuthor(author);
    }

    @Override
    public Order updateStatus(String orderId, String status) {
        Order order = orderRepository.findById(orderId).get();
        Order newOrder = new Order(order.getId(), order.getProducts(), order.getOrderTime(), order.getAuthor(),
                status);
        orderRepository.save(newOrder);
        return newOrder;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Optional<Order> findById(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public Optional<Order> update(Order order) {
        return orderRepository.update(order);
    }

    @Override
    public void delete(String id) {
        orderRepository.delete(id);
    }
}
