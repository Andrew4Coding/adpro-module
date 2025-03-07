package id.ac.ui.cs.advprog.eshop.payment.repository;

import java.util.List;

import id.ac.ui.cs.advprog.eshop.payment.model.Payment;

public interface PaymentRepository {
    Payment save(Payment payment);

    Payment findById(String id);

    List<Payment> findAll();
}
