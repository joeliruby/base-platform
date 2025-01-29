package com.matariky.constant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class RedisKeyTest {

    @InjectMocks
    private RedisKey redisKey;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCommonNoSeq() {
        // Given
        String expectedValue = "common_no_seq:%s:%s";

        // When
        String actualValue = RedisKey.COMMON_NO_SEQ;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testReaderExecutionTask() {
        // Given
        String expectedValue = "reader_execution_task:";

        // When
        String actualValue = RedisKey.READER_EXECUTION_TASK;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testReaderTaskEpc() {
        // Given
        String expectedValue = "reader_task_epc:";

        // When
        String actualValue = RedisKey.READER_TASK_EPC;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testReaderTaskLabel() {
        // Given
        String expectedValue = "reader_task_label:";

        // When
        String actualValue = RedisKey.READER_TASK_LABEL;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testTaskStockEpc() {
        // Given
        String expectedValue = "task_stock_epc:";

        // When
        String actualValue = RedisKey.TASK_STOCK_EPC;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testTaskReaderCode() {
        // Given
        String expectedValue = "task_reader_code:";

        // When
        String actualValue = RedisKey.TASK_READER_CODE;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testAppInOutInfo() {
        // Given
        String expectedValue = "app_inout_info";

        // When
        String actualValue = RedisKey.APP_INOUT_INFO;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testLibraryId() {
        // Given
        String expectedValue = "library_id:";

        // When
        String actualValue = RedisKey.LIBRARY_ID;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testCheckIn() {
        // Given
        String expectedValue = "check_in";

        // When
        String actualValue = RedisKey.CHECK_IN;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testCheckOut() {
        // Given
        String expectedValue = "check_out";

        // When
        String actualValue = RedisKey.CHECK_OUT;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testEpcTidMap() {
        // Given
        String expectedValue = "epc_tid_map:";

        // When
        String actualValue = RedisKey.EPC_TID_MAP;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testInventoryCheck() {
        // Given
        String expectedValue = "inventory_check";

        // When
        String actualValue = RedisKey.INVENTORY_CHECK;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testLibraryBulk() {
        // Given
        String expectedValue = "library_bulk";

        // When
        String actualValue = RedisKey.LIBRARY_BULK;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testMenuNames() {
        // Given
        String expectedValue = "qsl_menu_names:";

        // When
        String actualValue = RedisKey.MENU_NAMES;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testUserLastActiveTimePrefix() {
        // Given
        String expectedValue = "LAST_ACTIVE_TIME";

        // When
        String actualValue = RedisKey.USER_LAST_ACTIVE_TIME_PREFIX;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testApplicationActivityTimeoutPrefix() {
        // Given
        String expectedValue = "USER_ACTIVITY_TIMEOUT";

        // When
        String actualValue = RedisKey.APPLICATION_ACTIVITY_TIMEOUT_PREFIX;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }
}
