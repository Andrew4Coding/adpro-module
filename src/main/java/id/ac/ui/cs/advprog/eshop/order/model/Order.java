package id.ac.ui.cs.advprog.eshop.order.model;

import java.util.List;

import id.ac.ui.cs.advprog.eshop.lib.model.ModelAbstract;
import id.ac.ui.cs.advprog.eshop.product.model.Product;
import lombok.Getter;

@Getter
public class Order extends ModelAbstract {
    private List<Product> products;
    private Long orderTime;
    private String author;

    private String status;

    public Order(List<Product> products, Long orderTime, String author) {
        super();

        this.orderTime = orderTime;
        this.author = author;
        this.status = OrderStatus.WAITING_PAYMENT.getValue();

        if (products.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one product");
        } else {
            this.products = products;
        }
    }

    public Order(List<Product> products, Long orderTime, String author, String status) {
        this(products, orderTime, author);
        this.setStatus(status);
    }

    public void setStatus(String status) {
        if (OrderStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid status");
        }
    }
}
