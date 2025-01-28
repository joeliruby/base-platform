package com.matariky.orderservice.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderSuiteTest {

    @InjectMocks
    private OrderSuite ordersuite;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        Long id = 1L;
        String operatorOrgCode = "ORG123";
        String operatorSelfOrgCode = "SELF123";
        String tenantId = "TENANT123";
        String suiteCode = "SUITE123";
        String suiteName = "Suite Name";
        String suiteNotes = "Suite Notes";
        String suiteStatus = "Active";
        Long createdBy = 2L;
        Long createTime = 1627849200000L;
        Long updateBy = 3L;
        Long updateTime = 1627849300000L;
        Long deleteTime = 1627849400000L;

        ordersuite.setId(id);
        ordersuite.setOperatorOrgCode(operatorOrgCode);
        ordersuite.setOperatorSelfOrgCode(operatorSelfOrgCode);
        ordersuite.setTenantId(tenantId);
        ordersuite.setSuiteCode(suiteCode);
        ordersuite.setSuiteName(suiteName);
        ordersuite.setSuiteNotes(suiteNotes);
        ordersuite.setSuiteStatus(suiteStatus);
        ordersuite.setCreatedBy(createdBy);
        ordersuite.setCreateTime(createTime);
        ordersuite.setUpdateBy(updateBy);
        ordersuite.setUpdateTime(updateTime);
        ordersuite.setDeleteTime(deleteTime);

        assertEquals(id, ordersuite.getId());
        assertEquals(operatorOrgCode, ordersuite.getOperatorOrgCode());
        assertEquals(operatorSelfOrgCode, ordersuite.getOperatorSelfOrgCode());
        assertEquals(tenantId, ordersuite.getTenantId());
        assertEquals(suiteCode, ordersuite.getSuiteCode());
        assertEquals(suiteName, ordersuite.getSuiteName());
        assertEquals(suiteNotes, ordersuite.getSuiteNotes());
        assertEquals(suiteStatus, ordersuite.getSuiteStatus());
        assertEquals(createdBy, ordersuite.getCreatedBy());
        assertEquals(createTime, ordersuite.getCreateTime());
        assertEquals(updateBy, ordersuite.getUpdateBy());
        assertEquals(updateTime, ordersuite.getUpdateTime());
        assertEquals(deleteTime, ordersuite.getDeleteTime());
    }

    // Add more test methods for other methods in OrderSuite
}
