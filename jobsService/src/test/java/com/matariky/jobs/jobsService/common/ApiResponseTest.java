package com.matariky.jobs.jobsService.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApiResponseTest {

    @InjectMocks
    private ApiResponse apiresponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testOf() {
        ApiResponse response = ApiResponse.of("Test message", "Test data");
        assertEquals("Test message", response.getMessage());
        assertEquals("Test data", response.getData());
    }

    @Test
    void testOk() {
        ApiResponse response = ApiResponse.ok("Test data");
        assertEquals("OK", response.getMessage());
        assertEquals("Test data", response.getData());
    }

    @Test
    void testMsg() {
        ApiResponse response = ApiResponse.msg("Test message");
        assertEquals("Test message", response.getMessage());
        assertNull(response.getData());
    }
}
