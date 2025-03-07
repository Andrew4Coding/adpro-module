package id.ac.ui.cs.advprog.eshop.payment.repository;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.eshop.payment.model.Payment;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    private List<Payment> paymentsData = new ArrayList<>();

    public Payment save(Payment payment) {
        return null;
    }

    public Payment findById(String id) {
        return null;
    }

    public List<Payment> findAll() {
        return null;
    }
}