package id.ac.ui.cs.advprog.eshop.payment.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.eshop.payment.model.Payment;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    private Map<String, Payment> paymentsData = new HashMap<>();

    public Payment save(Payment payment) {
        paymentsData.put(payment.getId(), payment);
        return payment;
    }

    public Payment findById(String id) {
        return paymentsData.get(id);
    }

    public List<Payment> findAll() {
        return paymentsData.values().stream().collect(Collectors.toList());
    }
}
