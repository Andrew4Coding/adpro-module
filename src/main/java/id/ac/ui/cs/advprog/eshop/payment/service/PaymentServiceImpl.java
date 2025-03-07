package id.ac.ui.cs.advprog.eshop.payment.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.eshop.payment.model.Payment;
import id.ac.ui.cs.advprog.eshop.payment.repository.PaymentRepositoryImpl;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepositoryImpl paymentRepository;

    @Override
    public Payment addPayment(Payment payment) {
        return null;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        return null;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return null;
    }

    @Override
    public List<Payment> getAllPayments() {
        return null;
    }
}