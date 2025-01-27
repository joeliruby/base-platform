package com.matariky.automation.bean;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class DataBaseTableBeanTest {

    @InjectMocks
    private DataBaseTableBean databasetablebean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetName() {
        // Given
        when(databasetablebean.getName()).thenReturn("testName");

        // When
        String name = databasetablebean.getName();

        // Then
        assertThat(name).isEqualTo("testName");
    }

    @Test
    void testSetName() {
        // Given
        String name = "testName";

        // When
        databasetablebean.setName(name);

        // Then
        verify(databasetablebean).setName(name);
    }

    @Test
    void testGetType() {
        // Given
        when(databasetablebean.getType()).thenReturn("testType");

        // When
        String type = databasetablebean.getType();

        // Then
        assertThat(type).isEqualTo("testType");
    }

    @Test
    void testSetType() {
        // Given
        String type = "testType";

        // When
        databasetablebean.setType(type);

        // Then
        verify(databasetablebean).setType(type);
    }

    @Test
    void testGetSize() {
        // Given
        when(databasetablebean.getSize()).thenReturn(123L);

        // When
        long size = databasetablebean.getSize();

        // Then
        assertThat(size).isEqualTo(123L);
    }

    @Test
    void testSetSize() {
        // Given
        long size = 123L;

        // When
        databasetablebean.setSize(size);

        // Then
        verify(databasetablebean).setSize(size);
    }

    // Add more test methods for other getters and setters in DataBaseTableBean
}
