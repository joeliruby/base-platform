package com.matariky.orderservice.param;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QueryTapeOrderInfoParamTest {

    @InjectMocks
    private QueryTapeOrderInfoParam querytapeorderinfoparam;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsPageDefaultValue() {
        assertFalse(querytapeorderinfoparam.isPage());
    }

    @Test
    void testSetAndGetTenantName() {
        String tenantName = "TestTenant";
        querytapeorderinfoparam.setTenantName(tenantName);
        assertEquals(tenantName, querytapeorderinfoparam.getTenantName());
    }

    @Test
    void testSetAndGetOrderCode() {
        String orderCode = "Order123";
        querytapeorderinfoparam.setOrderCode(orderCode);
        assertEquals(orderCode, querytapeorderinfoparam.getOrderCode());
    }

    @Test
    void testSetAndGetSuiteName() {
        String suiteName = "SuiteA";
        querytapeorderinfoparam.setSuiteName(suiteName);
        assertEquals(suiteName, querytapeorderinfoparam.getSuiteName());
    }

    @Test
    void testSetAndGetStartTime() {
        String startTime = "2023-01-01T00:00:00";
        querytapeorderinfoparam.setStartTime(startTime);
        assertEquals(startTime, querytapeorderinfoparam.getStartTime());
    }

    @Test
    void testSetAndGetEndTime() {
        String endTime = "2023-01-01T23:59:59";
        querytapeorderinfoparam.setEndTime(endTime);
        assertEquals(endTime, querytapeorderinfoparam.getEndTime());
    }

    @Test
    void testSetAndGetOrderStatus() {
        String orderStatus = "Completed";
        querytapeorderinfoparam.setOrderStatus(orderStatus);
        assertEquals(orderStatus, querytapeorderinfoparam.getOrderStatus());
    }

    @Test
    void testSetAndGetOrderTenantId() {
        String orderTenantId = "Tenant123";
        querytapeorderinfoparam.setOrderTenantId(orderTenantId);
        assertEquals(orderTenantId, querytapeorderinfoparam.getOrderTenantId());
    }
}
