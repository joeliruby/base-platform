package com.matariky.commonservice.message.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MessageTest {

    @InjectMocks
    private Message message;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        message.setId(expectedId);

        // When
        Long actualId = message.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetOperatorOrgCode() {
        // Given
        String expectedCode = "ORG123";
        message.setOperatorOrgCode(expectedCode);

        // When
        String actualCode = message.getOperatorOrgCode();

        // Then
        assertThat(actualCode).isEqualTo(expectedCode);
    }

    @Test
    void testGetContent() {
        // Given
        String expectedContent = "This is a message";
        message.setContent(expectedContent);

        // When
        String actualContent = message.getContent();

        // Then
        assertThat(actualContent).isEqualTo(expectedContent);
    }

    @Test
    void testGetMsgType() {
        // Given
        Integer expectedType = 1;
        message.setMsgType(expectedType);

        // When
        Integer actualType = message.getMsgType();

        // Then
        assertThat(actualType).isEqualTo(expectedType);
    }

    @Test
    void testGetCreateTime() {
        // Given
        Long expectedTime = System.currentTimeMillis();
        message.setCreateTime(expectedTime);

        // When
        Long actualTime = message.getCreateTime();

        // Then
        assertThat(actualTime).isEqualTo(expectedTime);
    }

    // Add more test methods for other getters and setters in Message
}
