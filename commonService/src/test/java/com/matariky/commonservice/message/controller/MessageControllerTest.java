package com.matariky.commonservice.message.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.matariky.commonservice.message.param.QueryMessageParam;
import com.matariky.commonservice.message.service.MessageService;
import com.matariky.commonservice.message.vo.MessageVo;
import com.matariky.model.PrimaryParam;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class MessageControllerTest {

    @InjectMocks
    private MessageController messageController;

    @Mock
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetList() {
        // Given
        QueryMessageParam params = new QueryMessageParam();
        when(messageService.getPage(params))
                .thenReturn(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<MessageVo>());

        // When
        AjaxResult result = (AjaxResult) messageController.getList(params);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        assertThat(result.get(AjaxResult.DATA_TAG)).isNotNull();
    }

    @Test
    void testSetRead() {
        // Given
        PrimaryParam param = new PrimaryParam();
        doNothing().when(messageService).setRead(param);

        // When
        AjaxResult result = (AjaxResult) messageController.setRead(param);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        assertThat(result.get(AjaxResult.DATA_TAG)).isNull();
    }

    // Add more test methods for other methods in MessageController if needed
}
