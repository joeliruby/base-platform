package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseRfidBindingBatchVOTest {

    @InjectMocks
    private BasicBaseRfidBindingBatchVO basicBaseRfidBindingBatchVO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEpcGetterSetter() {
        // Given
        String epc = "testEpc";

        // When
        basicBaseRfidBindingBatchVO.setEpc(epc);

        // Then
        assertThat(basicBaseRfidBindingBatchVO.getEpc()).isEqualTo(epc);
    }

    @Test
    void testTidGetterSetter() {
        // Given
        String tid = "testTid";

        // When
        basicBaseRfidBindingBatchVO.setTid(tid);

        // Then
        assertThat(basicBaseRfidBindingBatchVO.getTid()).isEqualTo(tid);
    }

    @Test
    void testEpcNotBlank() {
        // Given
        String epc = "";

        // When
        basicBaseRfidBindingBatchVO.setEpc(epc);

        // Then
        assertThat(basicBaseRfidBindingBatchVO.getEpc()).isNotBlank();
    }

    @Test
    void testTidNotBlank() {
        // Given
        String tid = "";

        // When
        basicBaseRfidBindingBatchVO.setTid(tid);

        // Then
        assertThat(basicBaseRfidBindingBatchVO.getTid()).isNotBlank();
    }

    @Test
    void testEpcSize() {
        // Given
        String epc = "a".repeat(101);

        // When
        basicBaseRfidBindingBatchVO.setEpc(epc);

        // Then
        assertThat(basicBaseRfidBindingBatchVO.getEpc().length()).isLessThanOrEqualTo(100);
    }

    @Test
    void testTidSize() {
        // Given
        String tid = "a".repeat(101);

        // When
        basicBaseRfidBindingBatchVO.setTid(tid);

        // Then
        assertThat(basicBaseRfidBindingBatchVO.getTid().length()).isLessThanOrEqualTo(100);
    }
}
