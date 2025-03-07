package id.ac.ui.cs.advprog.eshop.payment.model;

import java.util.Map;

import id.ac.ui.cs.advprog.eshop.lib.model.ModelAbstract;
import id.ac.ui.cs.advprog.eshop.order.model.Order;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Payment extends ModelAbstract {
    private String method;
    private String status;
    private Order order;
    private Map<String, String> paymentData;

    public Payment(Order order, String method, Map<String, String> paymentData) {
        super();
    }

    public Payment(String id, Order order, String method, Map<String, String> paymentData) {
        this(order, method, paymentData);

        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID must not be empty");
        }

        this.setId(id);
    }

    public boolean validateVoucherCode(String voucherCode) {
        return false;
    }

    public boolean validatePaymentMethod(String method) {
        return false;
    }

    public boolean validatePaymentStatus(String status) {
        return false;
    }

    public boolean validatePaymentData(Map<String, String> paymentData) {
        return false;
    }

    public void addPaymentDataValue(String key, String value) {
    }

    public String getPaymentDataValue(String key) {
        return null;
    }

    public boolean validateOrder(Order order) {
        return false;
    }

    public void setPaymentData(Map<String, String> paymentData) {
    }
}
