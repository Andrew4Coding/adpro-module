package id.ac.ui.cs.advprog.eshop.payment.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.order.model.Order;
import id.ac.ui.cs.advprog.eshop.payment.model.Payment;
import id.ac.ui.cs.advprog.eshop.payment.model.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.product.model.Product;

class PaymentRepositoryTest {
    private PaymentRepositoryImpl paymentRepository;
    private List<Payment> testPayments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepositoryImpl();
        testPayments = createSamplePayments();
    }

    private List<Product> createSampleProducts() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setName("Laptop Gaming Pro");
        product.setQuantity(1);
        products.add(product);
        return products;
    }

    private List<Payment> createSamplePayments() {
        List<Product> products = createSampleProducts();

        Order firstOrder = new Order(UUID.randomUUID().toString(), products, 1708561000L, "Dimas Saputra");
        Order secondOrder = new Order(UUID.randomUUID().toString(), products, 1708572000L, "Ayu Lestari");

        Map<String, String> voucherPaymentData = new HashMap<>();
        voucherPaymentData.put("voucherCode", "PROMO2024");

        Map<String, String> bankPaymentData = new HashMap<>();
        bankPaymentData.put("bankName", "Mandiri");
        bankPaymentData.put("referenceCode", "MD123");

        Payment voucherPayment = new Payment(UUID.randomUUID().toString(), firstOrder,
                PaymentMethod.VOUCHER.getValue(), voucherPaymentData);

        Payment bankPayment = new Payment(UUID.randomUUID().toString(), secondOrder,
                PaymentMethod.BANK_TRANSFER.getValue(), bankPaymentData);

        return List.of(voucherPayment, bankPayment);
    }

    @Test
    void shouldSaveAndRetrievePaymentSuccessfully() {
        Payment payment = testPayments.get(0);
        paymentRepository.save(payment);
        Payment retrievedPayment = paymentRepository.findById(payment.getId());

        assertAll("Verify payment save operation",
                () -> assertNotNull(retrievedPayment),
                () -> assertEquals(payment.getId(), retrievedPayment.getId()),
                () -> assertEquals(payment.getMethod(), retrievedPayment.getMethod()),
                () -> assertEquals(payment.getOrder(), retrievedPayment.getOrder()),
                () -> assertEquals(payment.getStatus(), retrievedPayment.getStatus()),
                () -> assertEquals(payment.getPaymentData(), retrievedPayment.getPaymentData()));
    }

    @Test
    void shouldUpdateExistingPayment() {
        Payment originalPayment = testPayments.get(1);
        paymentRepository.save(originalPayment);

        Map<String, String> updatedPaymentData = new HashMap<>();
        updatedPaymentData.put("bankName", "BCA");
        updatedPaymentData.put("referenceCode", "BCA789");

        Payment updatedPayment = new Payment(originalPayment.getId(),
                originalPayment.getOrder(), PaymentMethod.BANK_TRANSFER.getValue(), updatedPaymentData);

        paymentRepository.save(updatedPayment);
        Payment retrievedUpdatedPayment = paymentRepository.findById(originalPayment.getId());

        assertAll("Verify payment update operation",
                () -> assertEquals(updatedPayment.getId(), retrievedUpdatedPayment.getId()),
                () -> assertEquals(updatedPayment.getMethod(), retrievedUpdatedPayment.getMethod()),
                () -> assertEquals(updatedPayment.getOrder(), retrievedUpdatedPayment.getOrder()),
                () -> assertEquals(updatedPayment.getStatus(), retrievedUpdatedPayment.getStatus()),
                () -> assertEquals(updatedPayment.getPaymentData(), retrievedUpdatedPayment.getPaymentData()));
    }

    @Test
    void shouldFindPaymentByIdWhenExists() {
        testPayments.forEach(paymentRepository::save);
        Payment retrievedPayment = paymentRepository.findById(testPayments.get(1).getId());

        assertNotNull(retrievedPayment, "Payment should be found");
        assertEquals(testPayments.get(1).getId(), retrievedPayment.getId());
    }

    @Test
    void shouldReturnNullWhenPaymentNotFound() {
        testPayments.forEach(paymentRepository::save);
        Payment retrievedPayment = paymentRepository.findById("nonexistent-id");

        assertNull(retrievedPayment, "Payment should not be found");
    }

    @Test
    void shouldRetrieveAllPayments() {
        testPayments.forEach(paymentRepository::save);
        List<Payment> retrievedPayments = paymentRepository.findAll();

        assertEquals(testPayments.size(), retrievedPayments.size(), "Number of stored payments should match");
    }
}
