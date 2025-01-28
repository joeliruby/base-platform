package com.matariky.orderservice.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.math.BigDecimal;

@SpringBootTest
public class OrderSuiteConfigVoTest {

    @InjectMocks
    private OrderSuiteConfigVo ordersuiteconfigvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNumberStart() {
        // Given
        Long expectedNumberStart = 100L;
        ordersuiteconfigvo.setNumberStart(expectedNumberStart);

        // When
        Long actualNumberStart = ordersuiteconfigvo.getNumberStart();

        // Then
        assertThat(actualNumberStart).isEqualTo(expectedNumberStart);
    }

    @Test
    void testNumberEnd() {
        // Given
        Long expectedNumberEnd = 200L;
        ordersuiteconfigvo.setNumberEnd(expectedNumberEnd);

        // When
        Long actualNumberEnd = ordersuiteconfigvo.getNumberEnd();

        // Then
        assertThat(actualNumberEnd).isEqualTo(expectedNumberEnd);
    }

    @Test
    void testYearPrice() {
        // Given
        BigDecimal expectedYearPrice = new BigDecimal("999.99");
        ordersuiteconfigvo.setYearPrice(expectedYearPrice);

        // When
        BigDecimal actualYearPrice = ordersuiteconfigvo.getYearPrice();

        // Then
        assertThat(actualYearPrice).isEqualTo(expectedYearPrice);
    }

    @Test
    void testAveragePrice() {
        // Given
        BigDecimal expectedAveragePrice = new BigDecimal("49.99");
        ordersuiteconfigvo.setAveragePrice(expectedAveragePrice);

        // When
        BigDecimal actualAveragePrice = ordersuiteconfigvo.getAveragePrice();

        // Then
        assertThat(actualAveragePrice).isEqualTo(expectedAveragePrice);
    }

    @Test
    void testSuiteConfigCode() {
        // Given
        String expectedSuiteConfigCode = "SC123";
        ordersuiteconfigvo.setSuiteConfigCode(expectedSuiteConfigCode);

        // When
        String actualSuiteConfigCode = ordersuiteconfigvo.getSuiteConfigCode();

        // Then
        assertThat(actualSuiteConfigCode).isEqualTo(expectedSuiteConfigCode);
    }
}
