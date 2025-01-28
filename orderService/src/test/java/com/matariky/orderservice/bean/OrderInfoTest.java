package com.matariky.orderservice.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;

@SpringBootTest
public class OrderInfoTest {

    @InjectMocks
    private OrderInfo orderinfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAndSetId() {
        Long id = 1L;
        orderinfo.setId(id);
        assertEquals(id, orderinfo.getId());
    }

    @Test
    void testGetAndSetOperatorOrgCode() {
        String operatorOrgCode = "ORG123";
        orderinfo.setOperatorOrgCode(operatorOrgCode);
        assertEquals(operatorOrgCode, orderinfo.getOperatorOrgCode());
    }

    @Test
    void testGetAndSetOrderCode() {
        String orderCode = "ORDER123";
        orderinfo.setOrderCode(orderCode);
        assertEquals(orderCode, orderinfo.getOrderCode());
    }

    @Test
    void testGetAndSetPrice() {
        BigDecimal price = new BigDecimal("100.00");
        orderinfo.setPrice(price);
        assertEquals(price, orderinfo.getPrice());
    }

    @Test
    void testGetAndSetDiscountPrice() {
        BigDecimal discountPrice = new BigDecimal("80.00");
        orderinfo.setDiscountPrice(discountPrice);
        assertEquals(discountPrice, orderinfo.getDiscountPrice());
    }

    @Test
    void testGetAndSetPaymentTime() {
        Long paymentTime = 1627849200000L;
        orderinfo.setPaymentTime(paymentTime);
        assertEquals(paymentTime, orderinfo.getPaymentTime());
    }

    @Test
    void testGetAndSetOrderStatus() {
        String orderStatus = "PAID";
        orderinfo.setOrderStatus(orderStatus);
        assertEquals(orderStatus, orderinfo.getOrderStatus());
    }

    @Test
    void testGetAndSetRemark() {
        String remark = "Test remark";
        orderinfo.setRemark(remark);
        assertEquals(remark, orderinfo.getRemark());
    }

    // Add more test methods for other getters and setters in OrderInfo
}
