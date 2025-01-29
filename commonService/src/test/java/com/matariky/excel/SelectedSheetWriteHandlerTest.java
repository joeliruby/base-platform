package com.matariky.excel;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;

@SpringBootTest
public class SelectedSheetWriteHandlerTest {

    @InjectMocks
    private SelectedSheetWriteHandler selectedSheetWriteHandler;

    @Mock
    private Map<Integer, ExcelSelectedResolve> selectedMap;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBeforeSheetCreate() {
        // Given
        WriteWorkbookHolder writeWorkbookHolder = mock(WriteWorkbookHolder.class);
        WriteSheetHolder writeSheetHolder = mock(WriteSheetHolder.class);

        // When
        selectedSheetWriteHandler.beforeSheetCreate(writeWorkbookHolder, writeSheetHolder);

        // Then
        // No exception should be thrown
    }

    @Test
    void testAfterSheetCreate() {
        // Given
        WriteWorkbookHolder writeWorkbookHolder = mock(WriteWorkbookHolder.class);
        WriteSheetHolder writeSheetHolder = mock(WriteSheetHolder.class);
        Sheet sheet = mock(Sheet.class);
        DataValidationHelper helper = mock(DataValidationHelper.class);
        when(writeSheetHolder.getSheet()).thenReturn(sheet);
        when(sheet.getDataValidationHelper()).thenReturn(helper);

        ExcelSelectedResolve resolve = new ExcelSelectedResolve();
        resolve.setFirstRow(0);
        resolve.setLastRow(10);
        resolve.setSource(new String[] { "Option1", "Option2" });
        selectedMap = new HashMap<>();
        selectedMap.put(0, resolve);
        selectedSheetWriteHandler = new SelectedSheetWriteHandler(selectedMap);

        // When
        selectedSheetWriteHandler.afterSheetCreate(writeWorkbookHolder, writeSheetHolder);

        // Then
        // Verify interactions and expected behavior
        verify(sheet, times(1)).getDataValidationHelper();
    }

    // Add more test methods for other methods in SelectedSheetWriteHandler
}
