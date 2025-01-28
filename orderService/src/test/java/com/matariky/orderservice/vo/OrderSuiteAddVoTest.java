package com.matariky.orderservice.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderSuiteAddVoTest {

    @InjectMocks
    private OrderSuiteAddVo ordersuiteaddvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String operatorOrgCode = "ORG123";
        String suiteName = "Suite Name";

        // When
        ordersuiteaddvo.setId(id);
        ordersuiteaddvo.setOperatorOrgCode(operatorOrgCode);
        ordersuiteaddvo.setSuiteName(suiteName);

        // Then
        assertEquals(id, ordersuiteaddvo.getId());
        assertEquals(operatorOrgCode, ordersuiteaddvo.getOperatorOrgCode());
        assertEquals(suiteName, ordersuiteaddvo.getSuiteName());
    }

    @Test
    void testDefaultValues() {
        // Given & When
        OrderSuiteAddVo newOrderSuiteAddVo = new OrderSuiteAddVo();

        // Then
        assertNull(newOrderSuiteAddVo.getId());
        assertNull(newOrderSuiteAddVo.getOperatorOrgCode());
        assertNull(newOrderSuiteAddVo.getSuiteName());
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        OrderSuiteAddVo vo1 = new OrderSuiteAddVo();
        vo1.setId(1L);
        OrderSuiteAddVo vo2 = new OrderSuiteAddVo();
        vo2.setId(1L);

        // When & Then
        assertEquals(vo1, vo2);
        assertEquals(vo1.hashCode(), vo2.hashCode());
    }

    @Test
    void testToString() {
        // Given
        ordersuiteaddvo.setId(1L);
        ordersuiteaddvo.setOperatorOrgCode("ORG123");
        ordersuiteaddvo.setSuiteName("Suite Name");

        // When
        String result = ordersuiteaddvo.toString();

        // Then
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("operatorOrgCode=ORG123"));
        assertTrue(result.contains("suiteName=Suite Name"));
    }

    // Add more test methods for other methods in OrderSuiteAddVo
}
