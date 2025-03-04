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
    private String status;

    public Order(List<Product> products, Long orderTime, String author) {
        super();

    }

    public Order(List<Product> products, Long orderTime, String author, String status) {
    }
}
