package id.ac.ui.cs.advprog.eshop.payment.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.order.model.Order;
import id.ac.ui.cs.advprog.eshop.order.model.OrderStatus;
import id.ac.ui.cs.advprog.eshop.product.model.Product;

public class PaymentTest {
    private Payment payment;
    private Order order;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setName("Sampo Hiya hiya");
        product.setQuantity(2);
        products.add(product);

        order = new Order("c564148a-a11f-4865-9744-60ade538c37b", products,
                System.currentTimeMillis(), "Sumanto", OrderStatus.WAITING_PAYMENT.getValue());

        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678ABC");

        payment = new Payment(order, PaymentMethod.VOUCHER.getValue(), paymentData);
    }

    @Test
    void testPaymentCreation() {
        assertEquals(order, this.payment.getOrder());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testPaymentCreationVoucherREJECTED() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP123456789ABC");
        Payment payment = new Payment(UUID.randomUUID().toString(), order,
                PaymentMethod.VOUCHER.getValue(), paymentData);
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertNull(payment.getPaymentData());
    }

    @Test
    void testPaymentCreationVoucherNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Payment(UUID.randomUUID().toString(), order,
                PaymentMethod.VOUCHER.getValue(), null);
        });

        assertEquals("Payment data must not be null", exception.getMessage());}

    @Test
    void testPaymentCreationOrderNull() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP123456789ABC");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Payment(UUID.randomUUID().toString(), null,
                    PaymentMethod.VOUCHER.getValue(), paymentData);
        });

        assertEquals("Order must not be null", exception.getMessage());
    }

    @Test
    void testSetOrder() {
        List<Product> newProducts = new ArrayList<>();
        Product newProduct = new Product();
        newProduct.setId("new-product-id");
        newProduct.setName("New Product");
        newProduct.setQuantity(1);
        newProducts.add(newProduct);

        Order newOrder = new Order("new-order-id", newProducts,
                System.currentTimeMillis(), "Setiawan", OrderStatus.WAITING_PAYMENT.getValue());

        payment.setOrder(newOrder);
        assertEquals(newOrder, payment.getOrder());
        assertNotEquals(order, payment.getOrder());
    }

    @Test
    void testSetMethod() {
        payment.setMethod(PaymentMethod.BANK_TRANSFER.getValue());
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), payment.getMethod());
    }

    @Test
    void testSetStatus() {
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentData() {
        Map<String, String> newPaymentData = new HashMap<>();
        newPaymentData.put("voucherCode", "ESHOP87654321XYZ");

        payment.setPaymentData(newPaymentData);
        assertEquals(newPaymentData, payment.getPaymentData());
        assertNotEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testInvalidVoucherCode() {
        Map<String, String> invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "INVALID12345");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            payment.setPaymentData(invalidVoucherData);
        });

        assertEquals("Invalid payment data", exception.getMessage());
        assertEquals("ESHOP12345678ABC", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testEmptyVoucherCode() {
        assertEquals("ESHOP12345678ABC", payment.getPaymentData().get("voucherCode"));

        Map<String, String> emptyVoucherData = 
        new HashMap<>();
        emptyVoucherData.put("voucherCode", "");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            payment.setPaymentData(emptyVoucherData);
        });

        assertEquals("Invalid payment data", exception.getMessage());
        assertEquals("ESHOP12345678ABC", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testNullVoucherCode() {
        Map<String, String> nullVoucherData = new HashMap<>();
        nullVoucherData.put("voucherCode", null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            payment.setPaymentData(nullVoucherData);
        });

        assertEquals("Invalid payment data", exception.getMessage());
        assertEquals("ESHOP12345678ABC", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testVoucherThatDoesNotStartWithESHOP() {
        Map<String, String> invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "SHOP1234567890AB");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            payment.setPaymentData(invalidVoucherData);
        });

        assertEquals("Invalid payment data", exception.getMessage());
        assertEquals("ESHOP12345678ABC", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testVoucherShorterThan16Characters() {
        Map<String, String> invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "ESHOP12345");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            payment.setPaymentData(invalidVoucherData);
        });

        assertEquals("Invalid payment data", exception.getMessage());
        assertEquals("ESHOP12345678ABC", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testVoucherLongerThan16Characters() {
        Map<String, String> invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "ESHOP12345678901234");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            payment.setPaymentData(invalidVoucherData);
        });

        assertEquals("Invalid payment data", exception.getMessage());
        assertEquals("ESHOP12345678ABC", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testVoucherWithLessThan8Numbers() {
        Map<String, String> invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "ESHOP123ABCDEFGHI");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            payment.setPaymentData(invalidVoucherData);
        });

        assertEquals("Invalid payment data", exception.getMessage());
        assertEquals("ESHOP12345678ABC", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testVoucherWithMoreThan8Numbers() {
        Map<String, String> invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "ESHOP1234567890A");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            payment.setPaymentData(invalidVoucherData);
        });

        assertEquals("Invalid payment data", exception.getMessage());
        assertEquals("ESHOP12345678ABC", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testVoucherCodeWithLowerCasePrefix() {
        Map<String, String> invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "eshop12345678ABCD");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            payment.setPaymentData(invalidVoucherData);
        });

        assertEquals("Invalid payment data", exception.getMessage());
        assertEquals("ESHOP12345678ABC", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testBankTransferConstructorWithMissingBankName() {
        Map<String, String> invalidBankData = new HashMap<>();
        invalidBankData.put("referenceCode", "REF123456789");

        Payment payment = new Payment(UUID.randomUUID().toString(), order,
                PaymentMethod.BANK_TRANSFER.getValue(), invalidBankData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertNull(payment.getPaymentData());
    }

    @Test
    void testBankTransferConstructorWithMissingReferenceCode() {
        Map<String, String> invalidBankData = new HashMap<>();
        invalidBankData.put("bankName", "BCA");

        Payment payment = new Payment(UUID.randomUUID().toString(), order,
                PaymentMethod.BANK_TRANSFER.getValue(), invalidBankData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertNull(payment.getPaymentData());
    }

    @Test
    void testBankTransferConstructorWithNullData() {
        Map<String, String> invalidBankData = new HashMap<>();
        invalidBankData.put("bankName", null);
        invalidBankData.put("referenceCode", null);

        Payment payment = new Payment(UUID.randomUUID().toString(), order,
                PaymentMethod.BANK_TRANSFER.getValue(), invalidBankData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertNull(payment.getPaymentData());
    }

    @Test
    void testBankTransferConstructorWithEmptyData() {
        Map<String, String> invalidBankData = new HashMap<>();
        invalidBankData.put("bankName", "");
        invalidBankData.put("referenceCode", "");

        Payment payment = new Payment(UUID.randomUUID().toString(), order,
                PaymentMethod.BANK_TRANSFER.getValue(), invalidBankData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertNull(payment.getPaymentData());
    }

    @Test
    void testValidBankTransferPayment() {
        Map<String, String> validBankData = new HashMap<>();
        validBankData.put("bankName", "BCA");
        validBankData.put("referenceCode", "REF123456789");

        Payment payment = new Payment(UUID.randomUUID().toString(), order,
                PaymentMethod.BANK_TRANSFER.getValue(), validBankData);

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(validBankData, payment.getPaymentData());
    }
}