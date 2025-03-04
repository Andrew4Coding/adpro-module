package id.ac.ui.cs.advprog.eshop.order.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
    WAITING_PAYMENT("WAITING_PAYMENT"),
    FAILED("FAILED"),
    CANCELLED("CANCELLED"),
    SUCCESS("SUCCESS");

    private final String value;

    private OrderStatus(String value) {
        this.value = value;
    }

    public static boolean contains(String status) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.name().equals(status)) {
                return true;
            }
        }
        return false;
    }
}
