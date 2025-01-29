package com.matariky.excel;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.annotation.Annotation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExcelSelectedTest {

    @InjectMocks
    private ExcelSelected excelselected;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSourceDefault() {
        // Given
        String[] expectedSource = {};

        // When
        String[] actualSource = excelselected.source();

        // Then
        assertArrayEquals(expectedSource, actualSource);
    }

    @Test
    void testFirstRowDefault() {
        // Given
        int expectedFirstRow = 1;

        // When
        int actualFirstRow = excelselected.firstRow();

        // Then
        assertEquals(expectedFirstRow, actualFirstRow);
    }

    @Test
    void testLastRowDefault() {
        // Given
        int expectedLastRow = 0x10000;

        // When
        int actualLastRow = excelselected.lastRow();

        // Then
        assertEquals(expectedLastRow, actualLastRow);
    }

    @Test
    void testSourceClassDefault() {
        // Given
        Class<? extends ExcelDynamicSelect>[] expectedSourceClass = new Class[] {};

        // When
        Class<? extends ExcelDynamicSelect>[] actualSourceClass = excelselected.sourceClass();

        // Then
        assertArrayEquals(expectedSourceClass, actualSourceClass);
    }

    @Test
    void testCustomSource() {
        // Given
        String[] expectedSource = { "Option1", "Option2" };

        // When
        excelselected = new ExcelSelected() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return ExcelSelected.class;
            }

            @Override
            public String[] source() {
                return expectedSource;
            }

            @Override
            public Class<? extends ExcelDynamicSelect>[] sourceClass() {
                return new Class[0];
            }

            @Override
            public int firstRow() {
                return 1;
            }

            @Override
            public int lastRow() {
                return 0x10000;
            }
        };

        String[] actualSource = excelselected.source();

        // Then
        assertArrayEquals(expectedSource, actualSource);
    }

    @Test
    void testCustomFirstRow() {
        // Given
        int expectedFirstRow = 5;

        // When
        excelselected = new ExcelSelected() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return ExcelSelected.class;
            }

            @Override
            public String[] source() {
                return new String[0];
            }

            @Override
            public Class<? extends ExcelDynamicSelect>[] sourceClass() {
                return new Class[0];
            }

            @Override
            public int firstRow() {
                return expectedFirstRow;
            }

            @Override
            public int lastRow() {
                return 0x10000;
            }
        };

        int actualFirstRow = excelselected.firstRow();

        // Then
        assertEquals(expectedFirstRow, actualFirstRow);
    }

    @Test
    void testCustomLastRow() {
        // Given
        int expectedLastRow = 500;

        // When
        excelselected = new ExcelSelected() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return ExcelSelected.class;
            }

            @Override
            public String[] source() {
                return new String[0];
            }

            @Override
            public Class<? extends ExcelDynamicSelect>[] sourceClass() {
                return new Class[0];
            }

            @Override
            public int firstRow() {
                return 1;
            }

            @Override
            public int lastRow() {
                return expectedLastRow;
            }
        };

        int actualLastRow = excelselected.lastRow();

        // Then
        assertEquals(expectedLastRow, actualLastRow);
    }

    // Add more test methods for other methods in ExcelSelected
}
