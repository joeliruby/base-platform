package com.matariky.bizservice.job;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ResultTest {

    @InjectMocks
    private Result result;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        String code = "200";
        String message = "Success";
        Boolean success = true;

        // When
        result.setCode(code);
        result.setMessage(message);
        result.setSuccess(success);

        // Then
        assertThat(result.getCode()).isEqualTo(code);
        assertThat(result.getMessage()).isEqualTo(message);
        assertThat(result.getSuccess()).isEqualTo(success);
    }

    @Test
    void testDefaultConstructor() {
        // Given
        Result defaultResult = new Result();

        // Then
        assertThat(defaultResult.getCode()).isNull();
        assertThat(defaultResult.getMessage()).isNull();
        assertThat(defaultResult.getSuccess()).isNull();
    }

    @Test
    void testAllArgsConstructor() {
        // Given
        String code = "200";
        String message = "Success";
        Boolean success = true;

        // When
        Result allArgsResult = new Result();
        allArgsResult.setCode(code);
        allArgsResult.setMessage(message);
        allArgsResult.setSuccess(success);

        // Then
        assertThat(allArgsResult.getCode()).isEqualTo(code);
        assertThat(allArgsResult.getMessage()).isEqualTo(message);
        assertThat(allArgsResult.getSuccess()).isEqualTo(success);
    }
}
