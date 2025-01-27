package com.matariky.automation.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
public class GenerateControllerTest {

    @InjectMocks
    private GenerateController generatecontroller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetController() {
        // Given
        DbBean model = mock(DbBean.class);
        when(model.getTablename()).thenReturn("TestTable");
        when(model.getCompages()).thenReturn("com.matariky");

        // When
        String result = generatecontroller.get_controller(model);

        // Then
        assertNotNull(result);
        assertTrue(result.contains("TestTable"));
        assertTrue(result.contains("com.matariky"));
    }

    @Test
    void testFirstLetterLowerCase() {
        // Given
        String className = "TestClass";

        // When
        String result = generatecontroller.firstLetterLowerCase(className);

        // Then
        assertEquals("testClass", result);
    }

    // Add more test methods for other methods in GenerateController
}
