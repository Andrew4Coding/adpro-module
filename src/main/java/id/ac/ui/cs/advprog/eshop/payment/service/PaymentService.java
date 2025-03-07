package id.ac.ui.cs.advprog.eshop.payment.service;

import java.util.List;

import id.ac.ui.cs.advprog.eshop.payment.model.Payment;

public interface PaymentService {
    Payment setStatus(Payment payment, String status);

    Payment getPayment(String paymentId);

    Payment addPayment(Payment payment);

    List<Payment> getAllPayments();
}
