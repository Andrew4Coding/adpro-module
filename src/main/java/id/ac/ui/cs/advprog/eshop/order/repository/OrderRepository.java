package id.ac.ui.cs.advprog.eshop.order.repository;

import java.util.List;

import id.ac.ui.cs.advprog.eshop.lib.repository.RepositoryInterface;
import id.ac.ui.cs.advprog.eshop.order.model.Order;

public interface OrderRepository extends RepositoryInterface<Order> {
    Order save(Order order);
    List<Order> findAllByAuthor(String author);
}
