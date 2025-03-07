package id.ac.ui.cs.advprog.eshop.payment.model;

import java.util.HashMap;
import java.util.Map;

import id.ac.ui.cs.advprog.eshop.lib.model.ModelAbstract;
import id.ac.ui.cs.advprog.eshop.order.model.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment extends ModelAbstract {
    private String method;
    private String status;
    private Order order;
    private Map<String, String> paymentData = new HashMap<>();

    public Payment(Order order, String method, Map<String, String> paymentData) {
        super();

        if (order == null) {
            throw new IllegalArgumentException("Order must not be null");
        }

        if (method == null || method.isEmpty() || !validatePaymentMethod(method)) {
            throw new IllegalArgumentException("Method must not be empty");
        }

        if (paymentData == null) {
            throw new IllegalArgumentException("Payment data must not be null");
        }
        
        this.method = method;
        this.order = order;
        if (validatePaymentData(paymentData)) {
            status = PaymentStatus.SUCCESS.getValue();
            this.paymentData = paymentData;
        } else {
            status = PaymentStatus.REJECTED.getValue();
            this.paymentData = null; // Set paymentData to null if validation fails
        }
    }

    public Payment(String id, Order order, String method, Map<String, String> paymentData) {
        this(order, method, paymentData);

        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID must not be empty");
        }

        this.setId(id);
    }

    private boolean validatePaymentMethod(String method) {
        if (method == null || method.isEmpty()) {
            return false;
        }

        return PaymentMethod.contains(method);
    }

    public boolean validateVoucherCode(String voucherCode) {
        if (voucherCode == null || voucherCode.isEmpty()) {
            return false;
        }

        if (voucherCode.length() != 16) {
            return false;
        }
        
        if (!voucherCode.startsWith("ESHOP")) {
            return false;
        }

        int numCounter = 0;
        for (int i = 5; i < voucherCode.length(); i++) {
            if (Character.isDigit(voucherCode.charAt(i))) {
                numCounter++;
            }
        }

        if (numCounter != 8) {
            return false;
        }

        return true;
    }

    public boolean validatePaymentData(Map<String, String> paymentData) {
        if (paymentData == null || paymentData.isEmpty()) {
            return false;
        }

        if (paymentData.containsKey("voucherCode")) {
            return validateVoucherCode(paymentData.get("voucherCode"));
        }

        if (paymentData.containsKey("bankName") && paymentData.containsKey("referenceCode")) {
            return paymentData.get("bankName") != null && !paymentData.get("bankName").isEmpty() &&
                    paymentData.get("referenceCode") != null && !paymentData.get("referenceCode").isEmpty();
        }

        return false;
    }

    public void addPaymentDataValue(String key, String value) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key must not be empty");
        }

        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value must not be empty");
        }

        this.paymentData.put(key, value);
    }

    public String getPaymentDataValue(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key must not be empty");
        }

        return this.paymentData.get(key);
    }

    public void setPaymentData(Map<String, String> paymentData) {
        if (paymentData == null || paymentData.isEmpty()) {
            throw new IllegalArgumentException("Payment data must not be empty");
        }

        if (validatePaymentData(paymentData)) {
            this.paymentData = paymentData;
        } else {
            throw new IllegalArgumentException("Invalid payment data");
        }
    }
}
