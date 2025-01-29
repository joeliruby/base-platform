package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBaseAntifake;
import com.matariky.commonservice.base.mapper.BasicBaseAntifakeMapper;
import com.matariky.commonservice.base.mapper.BasicBaseAntifakeDetailMapper;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import org.mockito.Mock;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBaseAntifakeServiceTest {

    @InjectMocks
    private BasicBaseAntifakeService basicBaseAntifakeService;

    @Mock
    private BasicBaseAntifakeMapper basicBaseAntifakeMapper;

    @Mock
    private BasicBaseAntifakeDetailMapper basicBaseAntifakeDetailMapper;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private CommonDictTypeService commonDictTypeService;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseAntifakeAll() {
        // Given
        BasicBaseAntifake bean = new BasicBaseAntifake();
        when(request.getHeader("id")).thenReturn("12345");
        when(basicBaseAntifakeMapper.getBasicBaseAntifakeAll(bean)).thenReturn(Collections.emptyList());

        // When
        List<BasicBaseAntifake> result = basicBaseAntifakeService.getBasicBaseAntifakeAll(bean, 1, 10);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateBasicBaseAntifakeWithOrg() {
        // Given
        BasicBaseAntifake bean = new BasicBaseAntifake();
        when(request.getHeader("id")).thenReturn("12345");
        when(basicBaseAntifakeMapper.createBasicBaseAntifake(bean)).thenReturn(1);

        // When
        int result = basicBaseAntifakeService.createBasicBaseAntifakeWithOrg(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseAntifake() {
        // Given
        BasicBaseAntifake bean = new BasicBaseAntifake();
        when(basicBaseAntifakeMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicBaseAntifakeService.updateBasicBaseAntifake(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseAntifakeById() {
        // Given
        Long id = 1L;
        when(basicBaseAntifakeMapper.delBasicBaseAntifakeById(id)).thenReturn(1);

        // When
        int result = basicBaseAntifakeService.delBasicBaseAntifakeById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseAntifakeById() {
        // Given
        Long id = 1L;
        BasicBaseAntifake expected = new BasicBaseAntifake();
        when(basicBaseAntifakeMapper.getBasicBaseAntifakeById(id)).thenReturn(expected);

        // When
        BasicBaseAntifake result = basicBaseAntifakeService.getBasicBaseAntifakeById(id);

        // Then
        assertEquals(expected, result);
    }

    // Add more test methods for other methods in BasicBaseAntifakeService
}
