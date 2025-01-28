package com.matariky.orderservice.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderInfoEditVoTest {

    @InjectMocks
    private OrderInfoEditVo orderinfoeditvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderinfoeditvo = new OrderInfoEditVo();
    }

    @Test
    void testOrderId() {
        orderinfoeditvo.setOrderId("12345");
        assertEquals("12345", orderinfoeditvo.getOrderId());
    }

    @Test
    void testOrderCode() {
        orderinfoeditvo.setOrderCode("OC123");
        assertEquals("OC123", orderinfoeditvo.getOrderCode());
    }

    @Test
    void testPrice() {
        BigDecimal price = new BigDecimal("99.99");
        orderinfoeditvo.setPrice(price);
        assertEquals(price, orderinfoeditvo.getPrice());
    }

    @Test
    void testDiscountPrice() {
        BigDecimal discountPrice = new BigDecimal("79.99");
        orderinfoeditvo.setDiscountPrice(discountPrice);
        assertEquals(discountPrice, orderinfoeditvo.getDiscountPrice());
    }

    @Test
    void testContacts() {
        orderinfoeditvo.setContacts("John Doe");
        assertEquals("John Doe", orderinfoeditvo.getContacts());
    }

    @Test
    void testContactsPhone() {
        orderinfoeditvo.setContactsPhone("1234567890");
        assertEquals("1234567890", orderinfoeditvo.getContactsPhone());
    }

    // Add more test methods for other fields in OrderInfoEditVo
}
