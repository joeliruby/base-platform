package com.matariky.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class AjaxResultTest {

    @InjectMocks
    private AjaxResult ajaxresult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSuccessWithoutData() {
        // When
        AjaxResult result = AjaxResult.success();

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.SUCCESS);
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(" Operation成功");
        assertThat(result.get(AjaxResult.DATA_TAG)).isNull();
    }

    @Test
    void testSuccessWithData() {
        // Given
        Object data = new Object();

        // When
        AjaxResult result = AjaxResult.success(data);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.SUCCESS);
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(" Operation成功");
        assertThat(result.get(AjaxResult.DATA_TAG)).isEqualTo(data);
    }

    @Test
    void testErrorWithoutData() {
        // When
        AjaxResult result = AjaxResult.error();

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.ERROR);
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(" OperationFailed");
        assertThat(result.get(AjaxResult.DATA_TAG)).isNull();
    }

    @Test
    void testErrorWithData() {
        // Given
        Object data = new Object();

        // When
        AjaxResult result = AjaxResult.error("Error occurred", data);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.ERROR);
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo("Error occurred");
        assertThat(result.get(AjaxResult.DATA_TAG)).isEqualTo(data);
    }

    @Test
    void testPutMethod() {
        // When
        ajaxresult.put("key", "value");

        // Then
        assertThat(ajaxresult.get("key")).isEqualTo("value");
    }

    // Add more test methods for other methods in AjaxResult
}
