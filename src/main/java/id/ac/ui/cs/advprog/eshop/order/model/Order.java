package id.ac.ui.cs.advprog.eshop.order.model;

import java.util.List;

import id.ac.ui.cs.advprog.eshop.lib.model.ModelAbstract;
import id.ac.ui.cs.advprog.eshop.product.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Order extends ModelAbstract {
    private List<Product> products;
    private Long orderTime;
    private String author;

    @Setter
    private String status = "WAITING_PAYMENT";

    public Order(List<Product> products, Long orderTime, String author) {
        super();

        if (products.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one product");
        }

        this.products = products;
        this.orderTime = orderTime;
        this.author = author;
    }

    public Order(List<Product> products, Long orderTime, String author, String status) {
        this(products, orderTime, author);
        this.status = "WAITING_PAYMENT";
    }
}
