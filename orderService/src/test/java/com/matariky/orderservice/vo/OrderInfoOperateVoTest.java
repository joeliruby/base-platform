package com.matariky.orderservice.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;

@SpringBootTest
public class OrderInfoOperateVoTest {

    @InjectMocks
    private OrderInfoOperateVo orderinfooperatevo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetOrderId() {
        // Given
        String orderId = "12345";

        // When
        orderinfooperatevo.setOrderId(orderId);

        // Then
        assertThat(orderinfooperatevo.getOrderId()).isEqualTo(orderId);
    }

    @Test
    void testSetAndGetRecordType() {
        // Given
        Long recordType = 1L;

        // When
        orderinfooperatevo.setRecordType(recordType);

        // Then
        assertThat(orderinfooperatevo.getRecordType()).isEqualTo(recordType);
    }

    @Test
    void testSetAndGetPrice() {
        // Given
        BigDecimal price = new BigDecimal("99.99");

        // When
        orderinfooperatevo.setPrice(price);

        // Then
        assertThat(orderinfooperatevo.getPrice()).isEqualTo(price);
    }

    @Test
    void testSetAndGetDiscountPrice() {
        // Given
        BigDecimal discountPrice = new BigDecimal("79.99");

        // When
        orderinfooperatevo.setDiscountPrice(discountPrice);

        // Then
        assertThat(orderinfooperatevo.getDiscountPrice()).isEqualTo(discountPrice);
    }

    @Test
    void testSetAndGetOrderStatus() {
        // Given
        String orderStatus = "COMPLETED";

        // When
        orderinfooperatevo.setOrderStatus(orderStatus);

        // Then
        assertThat(orderinfooperatevo.getOrderStatus()).isEqualTo(orderStatus);
    }

    @Test
    void testSetAndIsRecord() {
        // Given
        boolean isRecord = true;

        // When
        orderinfooperatevo.setRecord(isRecord);

        // Then
        assertThat(orderinfooperatevo.isRecord()).isEqualTo(isRecord);
    }

    // Add more test methods for other fields in OrderInfoOperateVo
}
