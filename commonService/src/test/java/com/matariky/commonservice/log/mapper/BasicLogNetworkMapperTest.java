package com.matariky.commonservice.log.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import java.util.Collections;
import java.util.List;
import com.matariky.commonservice.log.bean.BasicLogNetwork;

@SpringBootTest
public class BasicLogNetworkMapperTest {

    @InjectMocks
    private BasicLogNetworkMapper basiclognetworkmapper;

    @Mock
    private BasicLogNetworkMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicLogNetworkAll() {
        // Given
        List<BasicLogNetwork> expectedList = Collections.singletonList(new BasicLogNetwork());
        when(mockMapper.getBasicLogNetworkAll()).thenReturn(expectedList);

        // When
        List<BasicLogNetwork> result = basiclognetworkmapper.getBasicLogNetworkAll();

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicLogNetwork() {
        // Given
        BasicLogNetwork logNetwork = new BasicLogNetwork();
        when(mockMapper.createBasicLogNetwork(logNetwork)).thenReturn(1);

        // When
        int result = basiclognetworkmapper.createBasicLogNetwork(logNetwork);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicLogNetwork() {
        // Given
        BasicLogNetwork logNetwork = new BasicLogNetwork();
        when(mockMapper.updateBasicLogNetwork(logNetwork)).thenReturn(1);

        // When
        int result = basiclognetworkmapper.updateBasicLogNetwork(logNetwork);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicLogNetworkById() {
        // Given
        int id = 1;
        when(mockMapper.delBasicLogNetworkById(id)).thenReturn(1);

        // When
        int result = basiclognetworkmapper.delBasicLogNetworkById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicLogNetworkById() {
        // Given
        int id = 1;
        BasicLogNetwork expectedLogNetwork = new BasicLogNetwork();
        when(mockMapper.getBasicLogNetworkById(id)).thenReturn(expectedLogNetwork);

        // When
        BasicLogNetwork result = basiclognetworkmapper.getBasicLogNetworkById(id);

        // Then
        assertEquals(expectedLogNetwork, result);
    }

    // Add more test methods for other methods in BasicLogNetworkMapper
}
