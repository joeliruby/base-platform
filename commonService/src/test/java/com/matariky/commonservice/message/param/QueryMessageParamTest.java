package com.matariky.commonservice.message.param;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class QueryMessageParamTest {

    @InjectMocks
    private QueryMessageParam queryMessageParam;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsRead() {
        // Given
        queryMessageParam.setIsRead(true);

        // When
        Boolean isRead = queryMessageParam.getIsRead();

        // Then
        assertThat(isRead).isTrue();
    }

    @Test
    void testSetIsRead() {
        // Given
        Boolean expectedValue = false;

        // When
        queryMessageParam.setIsRead(expectedValue);

        // Then
        assertThat(queryMessageParam.getIsRead()).isEqualTo(expectedValue);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        QueryMessageParam param1 = new QueryMessageParam();
        param1.setIsRead(true);

        QueryMessageParam param2 = new QueryMessageParam();
        param2.setIsRead(true);

        // When & Then
        assertThat(param1).isEqualTo(param2);
        assertThat(param1.hashCode()).isEqualTo(param2.hashCode());
    }

    // Add more test methods for other methods in QueryMessageParam
}
