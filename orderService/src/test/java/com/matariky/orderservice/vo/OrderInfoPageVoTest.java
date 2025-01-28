package com.matariky.orderservice.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;

@SpringBootTest
public class OrderInfoPageVoTest {

    @InjectMocks
    private OrderInfoPageVo orderinfopagevo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        orderinfopagevo.setId(1L);
        assertEquals(1L, orderinfopagevo.getId());

        orderinfopagevo.setOperatorOrgCode("ORG123");
        assertEquals("ORG123", orderinfopagevo.getOperatorOrgCode());

        orderinfopagevo.setPrice(new BigDecimal("99.99"));
        assertEquals(new BigDecimal("99.99"), orderinfopagevo.getPrice());

        orderinfopagevo.setDelete(true);
        assertTrue(orderinfopagevo.isDelete());
    }

    @Test
    void testOrderStatus() {
        orderinfopagevo.setOrderStatus("Pending");
        assertEquals("Pending", orderinfopagevo.getOrderStatus());

        orderinfopagevo.setOrderStatus("Completed");
        assertEquals("Completed", orderinfopagevo.getOrderStatus());
    }

    @Test
    void testBooleanFlags() {
        orderinfopagevo.setRenewal(true);
        assertTrue(orderinfopagevo.isRenewal());

        orderinfopagevo.setDelay(false);
        assertFalse(orderinfopagevo.isDelay());

        orderinfopagevo.setExpansion(true);
        assertTrue(orderinfopagevo.isExpansion());

        orderinfopagevo.setAborted(false);
        assertFalse(orderinfopagevo.isAborted());
    }

    @Test
    void testExpirationTimes() {
        orderinfopagevo.setExpirationStartTime(1625097600000L);
        assertEquals(1625097600000L, orderinfopagevo.getExpirationStartTime());

        orderinfopagevo.setExpirationEndTime(1627689600000L);
        assertEquals(1627689600000L, orderinfopagevo.getExpirationEndTime());
    }

    // Add more test methods for other methods in OrderInfoPageVo
}
