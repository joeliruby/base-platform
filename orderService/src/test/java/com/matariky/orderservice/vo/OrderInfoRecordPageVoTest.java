package com.matariky.orderservice.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;

@SpringBootTest
public class OrderInfoRecordPageVoTest {

    @InjectMocks
    private OrderInfoRecordPageVo orderinforecordpagevo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        orderinforecordpagevo.setId(1L);
        assertEquals(1L, orderinforecordpagevo.getId());

        orderinforecordpagevo.setOrderCode("ORD123");
        assertEquals("ORD123", orderinforecordpagevo.getOrderCode());

        orderinforecordpagevo.setPrice(new BigDecimal("99.99"));
        assertEquals(new BigDecimal("99.99"), orderinforecordpagevo.getPrice());

        orderinforecordpagevo.setContacts("John Doe");
        assertEquals("John Doe", orderinforecordpagevo.getContacts());

        orderinforecordpagevo.setContactsPhone("1234567890");
        assertEquals("1234567890", orderinforecordpagevo.getContactsPhone());
    }

    @Test
    void testDefaultValues() {
        assertNull(orderinforecordpagevo.getId());
        assertNull(orderinforecordpagevo.getOrderCode());
        assertNull(orderinforecordpagevo.getPrice());
        assertNull(orderinforecordpagevo.getContacts());
        assertNull(orderinforecordpagevo.getContactsPhone());
    }

    // Add more test methods for other fields in OrderInfoRecordPageVo
}
