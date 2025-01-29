package com.matariky.aop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetCurrentRequestTest {

    @InjectMocks
    private GetCurrentRequest getcurrentrequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCurrentRequestNotNull() {
        assertNotNull(getcurrentrequest);
    }

    // Add more test methods for other methods in GetCurrentRequest
}
