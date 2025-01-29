package com.matariky.commonservice.log.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.mockito.Mock;
import com.matariky.commonservice.log.bean.BasicLogPrint;
import com.matariky.commonservice.log.mapper.BasicLogPrintMapper;
import com.matariky.constant.PermissionConstant;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class BasicLogPrintServiceTest {

    @InjectMocks
    private BasicLogPrintService basicLogPrintService;

    @Mock
    private BasicLogPrintMapper basicLogPrintMapper;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicLogPrintAll() {
        // Given
        List<BasicLogPrint> expectedList = List.of(new BasicLogPrint());
        when(basicLogPrintMapper.getBasicLogPrintAll()).thenReturn(expectedList);

        // When
        List<BasicLogPrint> result = basicLogPrintService.getBasicLogPrintAll();

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicLogPrint() {
        // Given
        BasicLogPrint bean = new BasicLogPrint();
        when(basicLogPrintMapper.createBasicLogPrint(bean)).thenReturn(1);

        // When
        int result = basicLogPrintService.createBasicLogPrint(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicLogPrint() {
        // Given
        BasicLogPrint bean = new BasicLogPrint();
        when(basicLogPrintMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicLogPrintService.updateBasicLogPrint(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicLogPrintById() {
        // Given
        int id = 1;
        when(basicLogPrintMapper.delBasicLogPrintById(id)).thenReturn(1);

        // When
        int result = basicLogPrintService.delBasicLogPrintById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicLogPrintById() {
        // Given
        int id = 1;
        BasicLogPrint expected = new BasicLogPrint();
        when(basicLogPrintMapper.getBasicLogPrintById(id)).thenReturn(expected);

        // When
        BasicLogPrint result = basicLogPrintService.getBasicLogPrintById(id);

        // Then
        assertEquals(expected, result);
    }

    @Test
    void testCreateBasicLogPrintWithOrg() {
        // Given
        BasicLogPrint bean = new BasicLogPrint();
        when(TokenUtils.extractOrgCode(request)).thenReturn("orgCode");
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(basicLogPrintMapper.createBasicLogPrint(bean)).thenReturn(1);

        // When
        int result = basicLogPrintService.createBasicLogPrintWithOrg(bean, request);

        // Then
        assertEquals(1, result);
        assertEquals("orgCode", bean.getOperatorOrgCode());
        assertEquals("selfOrgCode", bean.getOperatorSelfOrgCode());
    }

    @Test
    void testGetBasicLogPrintDAC() {
        // Given
        Map<String, Object> params = mock(Map.class);
        List<BasicLogPrint> expectedList = List.of(new BasicLogPrint());
        when(basicLogPrintMapper.getBasicLogPrintDAC(params)).thenReturn(expectedList);

        // When
        List<BasicLogPrint> result = basicLogPrintService.getBasicLogPrintDAC(params, request);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicLogPrintDACCount() {
        // Given
        Map<String, Object> params = mock(Map.class);
        when(params.get("strategyCode")).thenReturn(PermissionConstant.COMMON_DATA_ACCESS_PRIVATE);
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(basicLogPrintMapper.getBasicLogPrintDACCount(params)).thenReturn(1L);

        // When
        Long result = basicLogPrintService.getBasicLogPrintDACCount(params, request);

        // Then
        assertEquals(1L, result);
    }
}
