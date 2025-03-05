package id.ac.ui.cs.advprog.eshop.order.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import id.ac.ui.cs.advprog.eshop.order.model.Order;

public class OrderRepositoryImpl implements OrderRepository {
    private List<Order> orders = new ArrayList<>();

    public Order create(Order model) {
        orders.add(model);
        return model;
    }

    public Iterator<Order> findAll() {
        return orders.iterator();
    }

    public Optional<Order> findById(String id) {
        Order returnOrder = null;

        for (Order order : orders) {
            if (order.getId().equals(id)) {
                returnOrder = order;
                break;
            }
        }

        return Optional.ofNullable(returnOrder);
    }

    public Optional<Order> update(Order model) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId().equals(model.getId())) {
                orders.set(i, model);
                return Optional.of(model);
            }
        }

        return Optional.empty();
    }

    public void delete(String id) {
        orders.removeIf(order -> order.getId().equals(id));
    }

    public Order save(Order order) {
        int i = 0;
        for (Order orderData : orders) {
            if (orderData.getId().equals(order.getId())) {
                orders.remove(i);
                orders.add(i, order);
                return order;
            }
            i++;
        }

        orders.add(order);
        return order;
    }
    @Override
    public List<Order> findAllByAuthor(String author) {

        List<Order> orders = new ArrayList<>();
        for (Order order : this.orders) {
            if (order.getAuthor().equals(author)) {
                orders.add(order);
            }
        }

        return orders;
    }
}
