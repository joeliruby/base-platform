package com.matariky.orderservice.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderSuiteEditVoTest {

    @InjectMocks
    private OrderSuiteEditVo ordersuiteeditvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String operatorOrgCode = "ORG123";
        String suiteName = "Test Suite";

        // When
        ordersuiteeditvo.setId(id);
        ordersuiteeditvo.setOperatorOrgCode(operatorOrgCode);
        ordersuiteeditvo.setSuiteName(suiteName);

        // Then
        assertEquals(id, ordersuiteeditvo.getId());
        assertEquals(operatorOrgCode, ordersuiteeditvo.getOperatorOrgCode());
        assertEquals(suiteName, ordersuiteeditvo.getSuiteName());
    }

    @Test
    void testToString() {
        // Given
        ordersuiteeditvo.setId(1L);
        ordersuiteeditvo.setSuiteName("Test Suite");

        // When
        String result = ordersuiteeditvo.toString();

        // Then
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("suiteName=Test Suite"));
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        OrderSuiteEditVo vo1 = new OrderSuiteEditVo();
        vo1.setId(1L);
        OrderSuiteEditVo vo2 = new OrderSuiteEditVo();
        vo2.setId(1L);

        // When & Then
        assertEquals(vo1, vo2);
        assertEquals(vo1.hashCode(), vo2.hashCode());
    }

    @Test
    void testNotEquals() {
        // Given
        OrderSuiteEditVo vo1 = new OrderSuiteEditVo();
        vo1.setId(1L);
        OrderSuiteEditVo vo2 = new OrderSuiteEditVo();
        vo2.setId(2L);

        // When & Then
        assertNotEquals(vo1, vo2);
    }

    // Add more test methods for other methods in OrderSuiteEditVo
}
