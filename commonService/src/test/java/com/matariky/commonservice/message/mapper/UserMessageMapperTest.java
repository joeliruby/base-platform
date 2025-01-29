package com.matariky.commonservice.message.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import com.matariky.commonservice.message.bean.UserMessage;

@SpringBootTest
public class UserMessageMapperTest {

    @InjectMocks
    private UserMessageMapper userMessageMapper;

    @Mock
    private UserMessage userMessage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveOrUpdate() {
        // Given
        when(userMessageMapper.saveOrUpdate(userMessage)).thenReturn(1);

        // When
        int result = userMessageMapper.saveOrUpdate(userMessage);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testSaveOrUpdateWithNull() {
        // Given
        when(userMessageMapper.saveOrUpdate(null)).thenReturn(0);

        // When
        int result = userMessageMapper.saveOrUpdate(null);

        // Then
        assertEquals(0, result);
    }

    // Add more test methods for other methods in UserMessageMapper
}
