package com.matariky.commonservice.message.mapper;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.matariky.commonservice.message.param.QueryMessageParam;
import com.matariky.commonservice.message.vo.MessageVo;
import org.mockito.Mock;

@SpringBootTest
public class MessageMapperTest {

    @InjectMocks
    private MessageMapper messageMapper;

    @Mock
    private Page<MessageVo> page;

    @Mock
    private QueryMessageParam queryMessageParam;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSelectMessagePage() {
        // Given
        when(messageMapper.selectMessagePage(page, queryMessageParam)).thenReturn(page);

        // When
        Page<MessageVo> result = messageMapper.selectMessagePage(page, queryMessageParam);

        // Then
        assertThat(result).isEqualTo(page);
        verify(messageMapper, times(1)).selectMessagePage(page, queryMessageParam);
    }

    // Add more test methods for other methods in MessageMapper
}
