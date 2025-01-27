package com.matariky.automation.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.automation.bean.DbBean;

@SpringBootTest
public class GenerateServiceTest {

    @InjectMocks
    private GenerateService generateservice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetService() {
        // Given
        DbBean model = mock(DbBean.class);
        when(model.getTablename()).thenReturn("TestTable");
        when(model.getCompages()).thenReturn("com.matariky.test");

        // When
        String result = generateservice.get_Service(model);

        // Then
        assertNotNull(result);
        assertTrue(result.contains("TestTable"));
        assertTrue(result.contains("com.matariky.test"));
    }

    @Test
    void testGetServiceWithNullModel() {
        // Given
        DbBean model = null;

        // When
        Exception exception = assertThrows(NullPointerException.class, () -> {
            generateservice.get_Service(model);
        });

        // Then
        assertNotNull(exception);
    }

    // Add more test methods for other methods in GenerateService
}
