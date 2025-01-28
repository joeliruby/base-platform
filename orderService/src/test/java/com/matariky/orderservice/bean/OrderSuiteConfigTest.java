package com.matariky.orderservice.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;

@SpringBootTest
public class OrderSuiteConfigTest {

    @InjectMocks
    private OrderSuiteConfig ordersuiteconfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        Long id = 1L;
        String suiteConfigCode = "SC001";
        String suiteCode = "S001";
        Long numberStart = 100L;
        Long numberEnd = 200L;
        BigDecimal yearPrice = new BigDecimal("1000.00");
        BigDecimal averagePrice = new BigDecimal("500.00");

        ordersuiteconfig.setId(id);
        ordersuiteconfig.setSuiteConfigCode(suiteConfigCode);
        ordersuiteconfig.setSuiteCode(suiteCode);
        ordersuiteconfig.setNumberStart(numberStart);
        ordersuiteconfig.setNumberEnd(numberEnd);
        ordersuiteconfig.setYearPrice(yearPrice);
        ordersuiteconfig.setAveragePrice(averagePrice);

        assertEquals(id, ordersuiteconfig.getId());
        assertEquals(suiteConfigCode, ordersuiteconfig.getSuiteConfigCode());
        assertEquals(suiteCode, ordersuiteconfig.getSuiteCode());
        assertEquals(numberStart, ordersuiteconfig.getNumberStart());
        assertEquals(numberEnd, ordersuiteconfig.getNumberEnd());
        assertEquals(yearPrice, ordersuiteconfig.getYearPrice());
        assertEquals(averagePrice, ordersuiteconfig.getAveragePrice());
    }

    // Add more test methods for other methods in OrderSuiteConfig
}
