package com.matariky.orderservice.param;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QueryTapeOrderSuiteParamTest {

    @InjectMocks
    private QueryTapeOrderSuiteParam querytapeordersuiteparam;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsPageDefaultValue() {
        assertFalse(querytapeordersuiteparam.isPage());
    }

    @Test
    void testSetIsPage() {
        querytapeordersuiteparam.setPage(true);
        assertTrue(querytapeordersuiteparam.isPage());
    }

    @Test
    void testGetSuiteName() {
        querytapeordersuiteparam.setSuiteName("Test Suite");
        assertEquals("Test Suite", querytapeordersuiteparam.getSuiteName());
    }

    @Test
    void testGetSuiteStatus() {
        querytapeordersuiteparam.setSuiteStatus("Active");
        assertEquals("Active", querytapeordersuiteparam.getSuiteStatus());
    }

    // Add more test methods for other methods in QueryTapeOrderSuiteParam
}
