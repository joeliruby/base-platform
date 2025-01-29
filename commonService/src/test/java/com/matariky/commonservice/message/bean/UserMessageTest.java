package com.matariky.commonservice.message.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserMessageTest {

    @InjectMocks
    private UserMessage usermessage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        Long messageId = 2L;
        Long userId = 3L;
        Boolean isRead = true;

        // When
        usermessage.setId(id);
        usermessage.setMessageId(messageId);
        usermessage.setUserId(userId);
        usermessage.setIsRead(isRead);

        // Then
        assertThat(usermessage.getId()).isEqualTo(id);
        assertThat(usermessage.getMessageId()).isEqualTo(messageId);
        assertThat(usermessage.getUserId()).isEqualTo(userId);
        assertThat(usermessage.getIsRead()).isEqualTo(isRead);
    }

    @Test
    void testDefaultValues() {
        // Given
        UserMessage defaultUserMessage = new UserMessage();

        // Then
        assertThat(defaultUserMessage.getId()).isNull();
        assertThat(defaultUserMessage.getMessageId()).isNull();
        assertThat(defaultUserMessage.getUserId()).isNull();
        assertThat(defaultUserMessage.getIsRead()).isNull();
    }

    @Test
    void testIsRead() {
        // Given
        usermessage.setIsRead(true);

        // When
        Boolean isRead = usermessage.getIsRead();

        // Then
        assertThat(isRead).isTrue();
    }

    // Add more test methods for other methods in UserMessage
}
