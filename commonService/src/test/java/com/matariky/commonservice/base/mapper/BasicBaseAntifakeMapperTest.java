package com.matariky.commonservice.base.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBaseAntifake;
import org.mockito.Mock;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBaseAntifakeMapperTest {

    @InjectMocks
    private BasicBaseAntifakeMapper basicBaseAntifakeMapper;

    @Mock
    private BasicBaseAntifakeMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseAntifakeAll() {
        // Given
        BasicBaseAntifake bean = new BasicBaseAntifake();
        List<BasicBaseAntifake> expectedList = Collections.singletonList(bean);
        when(mockMapper.getBasicBaseAntifakeAll(bean)).thenReturn(expectedList);

        // When
        List<BasicBaseAntifake> result = basicBaseAntifakeMapper.getBasicBaseAntifakeAll(bean);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicBaseAntifake() {
        // Given
        BasicBaseAntifake bean = new BasicBaseAntifake();
        when(mockMapper.createBasicBaseAntifake(bean)).thenReturn(1);

        // When
        int result = basicBaseAntifakeMapper.createBasicBaseAntifake(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseAntifakeById() {
        // Given
        Long id = 1L;
        when(mockMapper.delBasicBaseAntifakeById(id)).thenReturn(1);

        // When
        int result = basicBaseAntifakeMapper.delBasicBaseAntifakeById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseAntifakeById() {
        // Given
        Long id = 1L;
        BasicBaseAntifake expectedBean = new BasicBaseAntifake();
        when(mockMapper.getBasicBaseAntifakeById(id)).thenReturn(expectedBean);

        // When
        BasicBaseAntifake result = basicBaseAntifakeMapper.getBasicBaseAntifakeById(id);

        // Then
        assertEquals(expectedBean, result);
    }
}
