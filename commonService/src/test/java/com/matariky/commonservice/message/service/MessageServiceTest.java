package com.matariky.commonservice.message.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Collections;
import java.util.List;
import org.mockito.Mock;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.matariky.commonservice.message.bean.Message;
import com.matariky.commonservice.message.bean.UserMessage;
import com.matariky.commonservice.message.mapper.MessageMapper;
import com.matariky.commonservice.message.mapper.UserMessageMapper;
import com.matariky.commonservice.message.param.QueryMessageParam;
import com.matariky.commonservice.message.vo.MessageVo;
import com.matariky.model.PrimaryParam;
import com.matariky.model.QueryDataIsolation;
import com.matariky.utils.BeanUtils;
import com.matariky.utils.NumberUtils;

@SpringBootTest
public class MessageServiceTest {

    @InjectMocks
    private MessageService messageService;

    @Mock
    private MessageMapper messageMapper;

    @Mock
    private UserMessageMapper userMessageMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLastMessageList() {
        // Given
        QueryDataIsolation params = mock(QueryDataIsolation.class);
        List<Message> messageList = Collections.singletonList(new Message());
        when(messageMapper.selectList(any(), any())).thenReturn(messageList);
        when(BeanUtils.copyProperties(messageList, MessageVo.class))
                .thenReturn(Collections.singletonList(new MessageVo()));

        // When
        List<MessageVo> result = messageService.getLastMessageList(params, 10);

        // Then
        assertThat(result).isNotEmpty();
        verify(messageMapper).selectList(any(), any());
    }

    @Test
    void testGetPage() {
        // Given
        QueryMessageParam params = new QueryMessageParam();
        Page<MessageVo> page = new Page<>();
        when(messageMapper.selectMessagePage(any(Page.class), eq(params))).thenReturn(page);

        // When
        Page<MessageVo> result = messageService.getPage(params);

        // Then
        assertThat(result).isEqualTo(page);
        verify(messageMapper).selectMessagePage(any(Page.class), eq(params));
    }

    @Test
    void testSetRead() {
        // Given
        PrimaryParam param = new PrimaryParam();
        param.setApplication(NumberUtils.INTEGER_TWO);
        param.setUserId(1L);
        param.setId(1L);
        UserMessage userMessage = new UserMessage();
        userMessage.setId(1L);
        userMessage.setUserId(1L);
        userMessage.setMessageId(1L);
        userMessage.setIsRead(Boolean.TRUE);

        // When
        messageService.setRead(param);

        // Then
        verify(userMessageMapper).saveOrUpdate(any(UserMessage.class));
    }
}
