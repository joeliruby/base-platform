package com.matariky.iservice.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ConstantTest {

    @InjectMocks
    private Constant constant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPageConstant() {
        assertThat(Constant.PAGE).isEqualTo("index");
    }

    @Test
    void testLimitConstant() {
        assertThat(Constant.LIMIT).isEqualTo("perPage");
    }

    @Test
    void testOrderFieldConstant() {
        assertThat(Constant.ORDER_FIELD).isEqualTo("orderFields");
    }

    @Test
    void testOrderConstant() {
        assertThat(Constant.ORDER).isEqualTo("order");
    }

    @Test
    void testAscConstant() {
        assertThat(Constant.ASC).isEqualTo("asc");
    }
}
