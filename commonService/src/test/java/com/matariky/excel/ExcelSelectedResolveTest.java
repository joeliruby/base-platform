package com.matariky.excel;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.base.service.ExcelService;

@SpringBootTest
public class ExcelSelectedResolveTest {

    @InjectMocks
    private ExcelSelectedResolve excelSelectedResolve;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testResolveSelectedSourceWithNullExcelSelected() {
        // Given
        ExcelSelected excelSelected = null;
        ExcelService excelService = mock(ExcelService.class);

        // When
        String[] result = excelSelectedResolve.resolveSelectedSource(excelSelected, excelService);

        // Then
        assertNull(result);
    }

    @Test
    void testResolveSelectedSourceWithFixedSource() {
        // Given
        ExcelSelected excelSelected = mock(ExcelSelected.class);
        when(excelSelected.source()).thenReturn(new String[] { "Option1", "Option2" });
        ExcelService excelService = mock(ExcelService.class);

        // When
        String[] result = excelSelectedResolve.resolveSelectedSource(excelSelected, excelService);

        // Then
        assertArrayEquals(new String[] { "Option1", "Option2" }, result);
    }

    @Test
    void testResolveSelectedSourceWithDynamicSource() throws InstantiationException, IllegalAccessException {
        // Given
        ExcelSelected excelSelected = mock(ExcelSelected.class);
        when(excelSelected.source()).thenReturn(new String[] {});
        ExcelDynamicSelect dynamicSelectMock = mock(ExcelDynamicSelect.class);
        when(dynamicSelectMock.getSource(any(ExcelService.class)))
                .thenReturn(new String[] { "DynamicOption1", "DynamicOption2" });
        when(excelSelected.sourceClass()).thenReturn(new Class[] { dynamicSelectMock.getClass() });
        ExcelService excelService = mock(ExcelService.class);

        // Mock the instantiation of the dynamic select class
        when(excelSelected.sourceClass()).thenReturn(new Class[] { ExcelDynamicSelect.class });
        when(ExcelDynamicSelect.class.newInstance()).thenReturn(dynamicSelectMock);

        // When
        String[] result = excelSelectedResolve.resolveSelectedSource(excelSelected, excelService);

        // Then
        assertArrayEquals(new String[] { "DynamicOption1", "DynamicOption2" }, result);
    }

    @Test
    void testResolveSelectedSourceWithEmptySource() {
        // Given
        ExcelSelected excelSelected = mock(ExcelSelected.class);
        when(excelSelected.source()).thenReturn(new String[] {});
        when(excelSelected.sourceClass()).thenReturn(new Class[] {});
        ExcelService excelService = mock(ExcelService.class);

        // When
        String[] result = excelSelectedResolve.resolveSelectedSource(excelSelected, excelService);

        // Then
        assertNull(result);
    }

    // Add more test methods for other scenarios if needed
}
