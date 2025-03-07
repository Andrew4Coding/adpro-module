package id.ac.ui.cs.advprog.eshop.payment.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.eshop.order.model.Order;
import id.ac.ui.cs.advprog.eshop.order.model.OrderStatus;
import id.ac.ui.cs.advprog.eshop.payment.model.Payment;
import id.ac.ui.cs.advprog.eshop.payment.model.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.payment.repository.PaymentRepositoryImpl;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepositoryImpl paymentRepository;

    @Override
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        Payment currentPayment = paymentRepository.findById(payment.getId());
        if (currentPayment != null) {
            Order order = currentPayment.getOrder();

            if (status.equals(PaymentStatus.SUCCESS.getValue())) {
                order.setStatus(OrderStatus.SUCCESS.getValue());
            } else if (status.equals(PaymentStatus.REJECTED.getValue())) {
                order.setStatus(OrderStatus.FAILED.getValue());
            }

            Payment newPayment = new Payment(order, payment.getMethod(), payment.getPaymentData());
            paymentRepository.save(newPayment);
            return newPayment;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}