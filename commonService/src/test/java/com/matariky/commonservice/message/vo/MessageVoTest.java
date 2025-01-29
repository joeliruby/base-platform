package com.matariky.commonservice.message.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageVoTest {

    @InjectMocks
    private MessageVo messagevo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetContent() {
        // Given
        String content = "Test Content";

        // When
        messagevo.setContent(content);

        // Then
        assertThat(messagevo.getContent()).isEqualTo(content);
    }

    @Test
    void testSetMsgType() {
        // Given
        Integer msgType = 1;

        // When
        messagevo.setMsgType(msgType);

        // Then
        assertThat(messagevo.getMsgType()).isEqualTo(msgType);
    }

    @Test
    void testSetTarget() {
        // Given
        String target = "Test Target";

        // When
        messagevo.setTarget(target);

        // Then
        assertThat(messagevo.getTarget()).isEqualTo(target);
    }

    @Test
    void testSetCreateTime() {
        // Given
        Long createTime = System.currentTimeMillis();

        // When
        messagevo.setCreateTime(createTime);

        // Then
        assertThat(messagevo.getCreateTime()).isEqualTo(createTime);
    }

    @Test
    void testSetIsRead() {
        // Given
        Boolean isRead = true;

        // When
        messagevo.setIsRead(isRead);

        // Then
        assertThat(messagevo.getIsRead()).isEqualTo(isRead);
    }
}
