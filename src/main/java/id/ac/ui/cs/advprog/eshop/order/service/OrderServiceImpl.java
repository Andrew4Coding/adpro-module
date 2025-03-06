package id.ac.ui.cs.advprog.eshop.order.service;

import java.util.List;
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
        return null;
    }

    @Override
    public List<Order> findAllByAuthor(String author) {
        return null;
    }

    @Override
    public Order updateStatus(String orderId, String status) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Optional<Order> findById(String id) {
        return null;
    }

    @Override
    public Optional<Order> update(Order order) {
        return null;
    }

    @Override
    public void delete(String id) {}
}
