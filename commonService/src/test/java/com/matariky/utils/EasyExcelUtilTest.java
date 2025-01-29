package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.minio.utils.MinioUtil;

@SpringBootTest
public class EasyExcelUtilTest {

    @InjectMocks
    private EasyExcelUtil easyexcelutil;

    @Mock
    private MinioUtil minioUtil;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExportExcelPath() {
        // Given
        String targetPath = "C:\\temp";
        String excelName = "test";
        String sheetName = "Sheet1";
        Class<?> type = Object.class;
        List<Object> data = new ArrayList<>();

        // When
        EasyExcelUtil.exportExcelPath(targetPath, excelName, sheetName, type, data);

        // Then
        // Verify the file was created (this is a simplified check)
        File file = new File(targetPath + "\\" + excelName + ".xlsx");
        assertTrue(file.exists());
    }

    @Test
    void testExportExcelWeb() {
        // Given
        String excelName = "test";
        String sheetName = "Sheet1";
        Class<?> type = Object.class;
        List<Object> data = new ArrayList<>();

        // When
        EasyExcelUtil.exportExcelWeb(response, excelName, sheetName, type, data);

        // Then
        // Verify response headers were set
        verify(response).setContentType("application/vnd.ms-excel");
        verify(response).setCharacterEncoding("UTF-8");
        verify(response).setHeader(eq("Content-disposition"), contains("attachment;filename=" + excelName));
    }

    @Test
    void testExportExcelUploadPath() {
        // Given
        String excelName = "test";
        String sheetName = "Sheet1";
        Class<?> type = Object.class;
        List<Object> data = new ArrayList<>();

        // When
        EasyExcelUtil.exportExcelUploadPath(excelName, sheetName, type, data, minioUtil);

        // Then
        // Verify the file was uploaded to Minio
        try {
            verify(minioUtil).createBucket("rfidfactory");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            verify(minioUtil).uploadFile(any(InputStream.class), eq("rfidfactory"), eq(excelName + ".xlsx"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testExportExcelStream() {
        // Given
        String fileName = "test";
        String sheetName = "Sheet1";
        Class<?> type = Object.class;
        List<Object> data = new ArrayList<>();

        // When
        FileInputStream result = EasyExcelUtil.exportExcelStream(fileName, sheetName, type, data);

        // Then
        assertNotNull(result);
    }

    @Test
    void testIsIDNumber() {
        // Given
        String validID = "410001199101010112";
        String invalidID = "123456789012345";

        // When
        boolean isValid = EasyExcelUtil.isIDNumber(validID);
        boolean isInvalid = EasyExcelUtil.isIDNumber(invalidID);

        // Then
        assertTrue(isValid);
        assertFalse(isInvalid);
    }

    @Test
    void testIsMobile() {
        // Given
        String validPhone = "13800138000";
        String invalidPhone = "12345678901";

        // When
        boolean isValid = EasyExcelUtil.isMobile(validPhone);
        boolean isInvalid = EasyExcelUtil.isMobile(invalidPhone);

        // Then
        assertTrue(isValid);
        assertFalse(isInvalid);
    }
}
