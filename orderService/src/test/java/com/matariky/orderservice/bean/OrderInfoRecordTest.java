package com.matariky.orderservice.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.math.BigDecimal;

@SpringBootTest
public class OrderInfoRecordTest {

    @InjectMocks
    private OrderInfoRecord orderinforecord;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetId() {
        Long id = 1L;
        orderinforecord.setId(id);
        assertThat(orderinforecord.getId()).isEqualTo(id);
    }

    @Test
    void testSetAndGetOperatorOrgCode() {
        String operatorOrgCode = "ORG123";
        orderinforecord.setOperatorOrgCode(operatorOrgCode);
        assertThat(orderinforecord.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
    }

    @Test
    void testSetAndGetPrice() {
        BigDecimal price = new BigDecimal("99.99");
        orderinforecord.setPrice(price);
        assertThat(orderinforecord.getPrice()).isEqualTo(price);
    }

    @Test
    void testSetAndGetOrderStatus() {
        String orderStatus = "COMPLETED";
        orderinforecord.setOrderStatus(orderStatus);
        assertThat(orderinforecord.getOrderStatus()).isEqualTo(orderStatus);
    }

    @Test
    void testSetAndGetCreatedBy() {
        Long createdBy = 2L;
        orderinforecord.setCreatedBy(createdBy);
        assertThat(orderinforecord.getCreatedBy()).isEqualTo(createdBy);
    }

    @Test
    void testSetAndGetCreateTime() {
        Long createTime = System.currentTimeMillis();
        orderinforecord.setCreateTime(createTime);
        assertThat(orderinforecord.getCreateTime()).isEqualTo(createTime);
    }

    // Add more test methods for other getters and setters in OrderInfoRecord
}
