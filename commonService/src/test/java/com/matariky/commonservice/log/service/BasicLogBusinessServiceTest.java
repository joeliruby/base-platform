package com.matariky.commonservice.log.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.mockito.Mock;
import com.matariky.commonservice.log.bean.BasicLogBusiness;
import com.matariky.commonservice.log.mapper.BasicLogBusinessMapper;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class BasicLogBusinessServiceTest {

    @InjectMocks
    private BasicLogBusinessService basicLogBusinessService;

    @Mock
    private BasicLogBusinessMapper basicLogBusinessMapper;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicLogBusinessAll() {
        // Given
        List<BasicLogBusiness> expectedList = Collections.emptyList();
        when(basicLogBusinessMapper.getBasicLogBusinessAll()).thenReturn(expectedList);

        // When
        List<BasicLogBusiness> result = basicLogBusinessService.getBasicLogBusinessAll();

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicLogBusinessAllCount() {
        // Given
        int expectedCount = 10;
        when(basicLogBusinessMapper.getBasicLogBusinessAllCount()).thenReturn(expectedCount);

        // When
        int result = basicLogBusinessService.getBasicLogBusinessAllCount();

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void testCreateBasicLogBusiness() {
        // Given
        BasicLogBusiness bean = new BasicLogBusiness();
        when(basicLogBusinessMapper.createBasicLogBusiness(bean)).thenReturn(1);

        // When
        int result = basicLogBusinessService.createBasicLogBusiness(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testCreateBasicLogBusinessWithOrg() {
        // Given
        BasicLogBusiness bean = new BasicLogBusiness();
        when(TokenUtils.extractOrgCode(request)).thenReturn("orgCode");
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(basicLogBusinessMapper.createBasicLogBusiness(bean)).thenReturn(1);

        // When
        int result = basicLogBusinessService.createBasicLogBusinessWithOrg(bean, request);

        // Then
        assertEquals(1, result);
        assertEquals("orgCode", bean.getOperatorOrgCode());
        assertEquals("selfOrgCode", bean.getOperatorSelfOrgCode());
    }

    @Test
    void testUpdateBasicLogBusiness() {
        // Given
        BasicLogBusiness bean = new BasicLogBusiness();
        when(basicLogBusinessMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicLogBusinessService.updateBasicLogBusiness(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicLogBusinessById() {
        // Given
        int id = 1;
        when(basicLogBusinessMapper.delBasicLogBusinessById(id)).thenReturn(1);

        // When
        int result = basicLogBusinessService.delBasicLogBusinessById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicLogBusinessById() {
        // Given
        int id = 1;
        BasicLogBusiness expectedBean = new BasicLogBusiness();
        when(basicLogBusinessMapper.getBasicLogBusinessById(id)).thenReturn(expectedBean);

        // When
        BasicLogBusiness result = basicLogBusinessService.getBasicLogBusinessById(id);

        // Then
        assertEquals(expectedBean, result);
    }

    @Test
    void testGetBasicLogBusinessDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        List<BasicLogBusiness> expectedList = Collections.emptyList();
        when(basicLogBusinessMapper.getBasicLogBusinessDAC(params)).thenReturn(expectedList);

        // When
        List<BasicLogBusiness> result = basicLogBusinessService.getBasicLogBusinessDAC(params, request);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicLogBusinessDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        long expectedCount = 10L;
        when(basicLogBusinessMapper.getBasicLogBusinessDACCount(params)).thenReturn(expectedCount);

        // When
        long result = basicLogBusinessService.getBasicLogBusinessDACCount(params, request);

        // Then
        assertEquals(expectedCount, result);
    }
}
