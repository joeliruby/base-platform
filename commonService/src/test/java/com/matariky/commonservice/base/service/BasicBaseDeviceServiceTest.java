package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import com.matariky.commonservice.base.bean.BasicBaseDevice;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceMapper;
import com.matariky.commonservice.base.vo.BasicBaseDeviceAddVO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceInfoVO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceListVO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceUpdateVO;
import com.matariky.commonservice.base.vo.CodeOptionListVO;
import com.matariky.commonservice.base.vo.DbmVO;
import com.matariky.commonservice.base.vo.DeviceCodeInfo;
import com.matariky.commonservice.base.vo.PrintOptionInfo;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class BasicBaseDeviceServiceTest {

    @InjectMocks
    private BasicBaseDeviceService basicBaseDeviceService;

    @Mock
    private BasicBaseDeviceMapper basicBaseDeviceMapper;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseDeviceAll() {
        // Given
        BasicBaseDeviceListVO vo = new BasicBaseDeviceListVO();
        when(request.getHeader("id")).thenReturn("12345");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(basicBaseDeviceMapper.getBasicBaseDeviceAll(vo)).thenReturn(Collections.emptyList());

        // When
        List<BasicBaseDeviceInfoVO> result = basicBaseDeviceService.getBasicBaseDeviceAll(vo);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateBasicBaseDeviceWithOrg() {
        // Given
        BasicBaseDeviceAddVO addVO = new BasicBaseDeviceAddVO();
        addVO.setDeviceCode("deviceCode");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(basicBaseDeviceMapper.selectCount(any())).thenReturn(0L);
        when(basicBaseDeviceMapper.createBasicBaseDevice(any())).thenReturn(1);

        // When
        int result = basicBaseDeviceService.createBasicBaseDeviceWithOrg(addVO);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseDevice() {
        // Given
        BasicBaseDeviceUpdateVO updateVO = new BasicBaseDeviceUpdateVO();
        updateVO.setDeviceCode("deviceCode");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(basicBaseDeviceMapper.selectCount(any())).thenReturn(0L);
        when(basicBaseDeviceMapper.updateById(any())).thenReturn(1);

        // When
        int result = basicBaseDeviceService.updateBasicBaseDevice(updateVO);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseDeviceById() {
        // Given
        Long id = 1L;
        when(basicBaseDeviceMapper.selectCountFromPrint(id)).thenReturn(0);
        when(basicBaseDeviceMapper.selectById(id)).thenReturn(new BasicBaseDevice());
        when(basicBaseDeviceMapper.delBasicBaseDeviceById(id)).thenReturn(1);

        // When
        int result = basicBaseDeviceService.delBasicBaseDeviceById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDownLoadTemplate() {
        // Given
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");

        // When
        basicBaseDeviceService.downLoadTemplate();

        // Then
        // Verify that the method completes without exceptions
    }

    @Test
    void testContainsImage() {
        // Given
        MultipartFile file = mock(MultipartFile.class);

        // When
        boolean result = BasicBaseDeviceService.containsImage(file);

        // Then
        assertFalse(result);
    }

    @Test
    void testImportData() {
        // Given
        MultipartFile file = mock(MultipartFile.class);
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(TokenUtils.extractUserIdFromHttpReqeust(request)).thenReturn("1");

        // When
        basicBaseDeviceService.importData(file);

        // Then
        // Verify that the method completes without exceptions
    }

    @Test
    void testGetDbmOption() {
        // Given
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");

        // When
        List<DbmVO> result = basicBaseDeviceService.getDbmOption();

        // Then
        assertNotNull(result);
    }

    @Test
    void testGetCodeOption() {
        // Given
        CodeOptionListVO vo = new CodeOptionListVO();
        when(request.getHeader("id")).thenReturn("12345");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");

        // When
        List<DeviceCodeInfo> result = basicBaseDeviceService.getCodeOption(vo);

        // Then
        assertNotNull(result);
    }

    @Test
    void testGetPrintOptionList() {
        // Given
        when(request.getHeader("id")).thenReturn("12345");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");

        // When
        List<PrintOptionInfo> result = basicBaseDeviceService.getPrintOptionList();

        // Then
        assertNotNull(result);
    }
}
