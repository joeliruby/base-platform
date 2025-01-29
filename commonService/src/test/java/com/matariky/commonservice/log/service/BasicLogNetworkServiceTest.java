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
import com.matariky.commonservice.log.bean.BasicLogNetwork;
import com.matariky.commonservice.log.mapper.BasicLogNetworkMapper;
import com.matariky.constant.PermissionConstant;

@SpringBootTest
public class BasicLogNetworkServiceTest {

    @InjectMocks
    private BasicLogNetworkService basicLogNetworkService;

    @Mock
    private BasicLogNetworkMapper basicLogNetworkMapper;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicLogNetworkAll() {
        // Given
        List<BasicLogNetwork> expectedList = List.of(new BasicLogNetwork());
        when(basicLogNetworkMapper.getBasicLogNetworkAll()).thenReturn(expectedList);

        // When
        List<BasicLogNetwork> result = basicLogNetworkService.getBasicLogNetworkAll();

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicLogNetwork() {
        // Given
        BasicLogNetwork bean = new BasicLogNetwork();
        when(basicLogNetworkMapper.createBasicLogNetwork(bean)).thenReturn(1);

        // When
        int result = basicLogNetworkService.createBasicLogNetwork(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicLogNetwork() {
        // Given
        BasicLogNetwork bean = new BasicLogNetwork();
        when(basicLogNetworkMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicLogNetworkService.updateBasicLogNetwork(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicLogNetworkById() {
        // Given
        int id = 1;
        when(basicLogNetworkMapper.delBasicLogNetworkById(id)).thenReturn(1);

        // When
        int result = basicLogNetworkService.delBasicLogNetworkById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicLogNetworkById() {
        // Given
        int id = 1;
        BasicLogNetwork expected = new BasicLogNetwork();
        when(basicLogNetworkMapper.getBasicLogNetworkById(id)).thenReturn(expected);

        // When
        BasicLogNetwork result = basicLogNetworkService.getBasicLogNetworkById(id);

        // Then
        assertEquals(expected, result);
    }

    @Test
    void testGetBasicLogNetworkDAC() {
        // Given
        Map<String, Object> params = Map.of();
        List<BasicLogNetwork> expectedList = List.of(new BasicLogNetwork());
        when(basicLogNetworkMapper.getBasicLogNetworkDAC(params)).thenReturn(expectedList);

        // When
        List<BasicLogNetwork> result = basicLogNetworkService.getBasicLogNetworkDAC(params, request);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicLogNetworkDACCount() {
        // Given
        Map<String, Object> params = Map.of("strategyCode", PermissionConstant.COMMON_DATA_ACCESS_PRIVATE);
        when(basicLogNetworkMapper.getBasicLogNetworkDACCount(params)).thenReturn(1L);

        // When
        Long result = basicLogNetworkService.getBasicLogNetworkDACCount(params, request);

        // Then
        assertEquals(1L, result);
    }
}
