package id.ac.ui.cs.advprog.eshop.payment.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.eshop.order.model.Order;
import id.ac.ui.cs.advprog.eshop.payment.model.Payment;
import id.ac.ui.cs.advprog.eshop.payment.model.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.payment.model.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.payment.repository.PaymentRepositoryImpl;
import id.ac.ui.cs.advprog.eshop.product.model.Product;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepositoryImpl paymentRepository;

    List<Payment> payments;
    List<Order> orders;
    Map<String, String> paymentData1;
    Map<String, String> paymentData2;

    @BeforeEach
    void setUp() {
        payments = new ArrayList<>();
        orders = new ArrayList<>();

        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(2);
        products.add(product);

        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");

        Order order2 = new Order("cf6071f3-4c39-4e7b-9c2c-91572c98b506",
                products, 1708570000L, "Bambang Pamungkas");

        orders.add(order1);
        orders.add(order2);

        paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP12345678ABC");

        paymentData2 = new HashMap<>();
        paymentData2.put("bankName", "BNI");
        paymentData2.put("referenceCode", "BNIMURAH");

        Payment payment1 = new Payment(UUID.randomUUID().toString(), order1,
                PaymentMethod.VOUCHER.getValue(), paymentData1);

        Payment payment2 = new Payment(UUID.randomUUID().toString(), order2,
                PaymentMethod.BANK_TRANSFER.getValue(), paymentData2);

        payments.add(payment1);
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        Order order = orders.get(0);
        String method = PaymentMethod.VOUCHER.getValue();
        Payment expectedPayment = new Payment(order, method, paymentData1);

        doReturn(expectedPayment).when(paymentRepository).save(any(Payment.class));

        Payment tempResult = new Payment(order, method, paymentData1);

        Payment result = paymentService.addPayment(tempResult);

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(expectedPayment, result);
    }

    @Test
    void testSetStatus() {
        Payment payment = payments.get(1);
        String newStatus = PaymentStatus.SUCCESS.getValue();

        Payment updatedPayment = new Payment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());

        doReturn(payment).when(paymentRepository).findById(payment.getId());
        doReturn(updatedPayment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(payment, newStatus);

        assertEquals(newStatus, result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatusPaymentNotFound() {
        Payment payment = payments.get(1);
        String newStatus = PaymentStatus.SUCCESS.getValue();

        doReturn(null).when(paymentRepository).findById(payment.getId());

        assertThrows(NoSuchElementException.class,
                () -> paymentService.setStatus(payment, newStatus));

        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testGetPayment() {
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment, result);
    }

    @Test
    void testGetPaymentNotFound() {
        doReturn(null).when(paymentRepository).findById("non-existent-id");
        Payment result = paymentService.getPayment("non-existent-id");
        assertNull(result);
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> result = paymentService.getAllPayments();
        assertEquals(2, result.size());
        assertEquals(payments, result);
    }
}