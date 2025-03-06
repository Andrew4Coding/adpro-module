package id.ac.ui.cs.advprog.eshop.order.service;

import java.util.List;

import id.ac.ui.cs.advprog.eshop.lib.service.ServiceInterface;
import id.ac.ui.cs.advprog.eshop.order.model.Order;

public interface OrderService extends ServiceInterface<Order> {
    Order updateStatus(String orderId, String status);
    List<Order> findAllByAuthor(String author);
}
