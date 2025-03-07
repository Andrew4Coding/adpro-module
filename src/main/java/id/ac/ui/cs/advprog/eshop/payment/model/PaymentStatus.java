package id.ac.ui.cs.advprog.eshop.payment.model;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    REJECTED("REJECTED"),
    SUCCESS("SUCCESS");

    private final String value;

    private PaymentStatus(String value) {
        this.value = value;
    }

    public static boolean contains(String status) {
        for (PaymentStatus orderStatus : PaymentStatus.values()) {
            if (orderStatus.name().equals(status)) {
                return true;
            }
        }
        return false;
    }
}
