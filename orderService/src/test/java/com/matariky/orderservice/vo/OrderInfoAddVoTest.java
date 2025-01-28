package com.matariky.orderservice.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;

@SpringBootTest
public class OrderInfoAddVoTest {

    @InjectMocks
    private OrderInfoAddVo orderinfoaddvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testOrderCode() {
        orderinfoaddvo.setOrderCode("OC123");
        assertEquals("OC123", orderinfoaddvo.getOrderCode());
    }

    @Test
    void testOrderShowCode() {
        orderinfoaddvo.setOrderShowCode("OSC123");
        assertEquals("OSC123", orderinfoaddvo.getOrderShowCode());
    }

    @Test
    void testContractCode() {
        orderinfoaddvo.setContractCode("CC123");
        assertEquals("CC123", orderinfoaddvo.getContractCode());
    }

    @Test
    void testTenantId() {
        orderinfoaddvo.setTenantId("TID123");
        assertEquals("TID123", orderinfoaddvo.getTenantId());
    }

    @Test
    void testOrderTenantId() {
        orderinfoaddvo.setOrderTenantId("OTID123");
        assertEquals("OTID123", orderinfoaddvo.getOrderTenantId());
    }

    @Test
    void testTenantShowId() {
        orderinfoaddvo.setTenantShowId("TSID123");
        assertEquals("TSID123", orderinfoaddvo.getTenantShowId());
    }

    @Test
    void testSuiteCode() {
        orderinfoaddvo.setSuiteCode("SC123");
        assertEquals("SC123", orderinfoaddvo.getSuiteCode());
    }

    @Test
    void testContacts() {
        orderinfoaddvo.setContacts("John Doe");
        assertEquals("John Doe", orderinfoaddvo.getContacts());
    }

    @Test
    void testContactsPhone() {
        orderinfoaddvo.setContactsPhone("1234567890");
        assertEquals("1234567890", orderinfoaddvo.getContactsPhone());
    }

    @Test
    void testExpirationStartTime() {
        orderinfoaddvo.setExpirationStartTime("2023-01-01");
        assertEquals("2023-01-01", orderinfoaddvo.getExpirationStartTime());
    }

    @Test
    void testExpirationEndTime() {
        orderinfoaddvo.setExpirationEndTime("2023-12-31");
        assertEquals("2023-12-31", orderinfoaddvo.getExpirationEndTime());
    }

    @Test
    void testAccountNumber() {
        orderinfoaddvo.setAccountNumber(10);
        assertEquals(10, orderinfoaddvo.getAccountNumber());
    }

    @Test
    void testChargeMode() {
        orderinfoaddvo.setChargeMode("Monthly");
        assertEquals("Monthly", orderinfoaddvo.getChargeMode());
    }

    @Test
    void testPrice() {
        orderinfoaddvo.setPrice(new BigDecimal("100.00"));
        assertEquals(new BigDecimal("100.00"), orderinfoaddvo.getPrice());
    }

    @Test
    void testDiscountPrice() {
        orderinfoaddvo.setDiscountPrice(new BigDecimal("90.00"));
        assertEquals(new BigDecimal("90.00"), orderinfoaddvo.getDiscountPrice());
    }

    @Test
    void testPaymentTime() {
        orderinfoaddvo.setPaymentTime("2023-01-15");
        assertEquals("2023-01-15", orderinfoaddvo.getPaymentTime());
    }

    @Test
    void testPaymentVoucherPath() {
        orderinfoaddvo.setPaymentVoucherPath("/path/to/voucher");
        assertEquals("/path/to/voucher", orderinfoaddvo.getPaymentVoucherPath());
    }

    @Test
    void testRemark() {
        orderinfoaddvo.setRemark("Test remark");
        assertEquals("Test remark", orderinfoaddvo.getRemark());
    }
}
