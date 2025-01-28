package com.matariky.jobs.jobsService.assetitm.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TapeRfidPrintDetailTaskTest {

    @InjectMocks
    private TapeRfidPrintDetailTask taperfidprintdetailtask;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        taperfidprintdetailtask.setId(1L);

        // When
        Long id = taperfidprintdetailtask.getId();

        // Then
        assertThat(id).isEqualTo(1L);
    }

    @Test
    void testSetId() {
        // Given
        Long id = 2L;

        // When
        taperfidprintdetailtask.setId(id);

        // Then
        assertThat(taperfidprintdetailtask.getId()).isEqualTo(id);
    }

    @Test
    void testGetPrintId() {
        // Given
        taperfidprintdetailtask.setPrintId(3L);

        // When
        Long printId = taperfidprintdetailtask.getPrintId();

        // Then
        assertThat(printId).isEqualTo(3L);
    }

    @Test
    void testSetPrintId() {
        // Given
        Long printId = 4L;

        // When
        taperfidprintdetailtask.setPrintId(printId);

        // Then
        assertThat(taperfidprintdetailtask.getPrintId()).isEqualTo(printId);
    }

    @Test
    void testGetEpc() {
        // Given
        taperfidprintdetailtask.setEpc("EPC123");

        // When
        String epc = taperfidprintdetailtask.getEpc();

        // Then
        assertThat(epc).isEqualTo("EPC123");
    }

    @Test
    void testSetEpc() {
        // Given
        String epc = "EPC456";

        // When
        taperfidprintdetailtask.setEpc(epc);

        // Then
        assertThat(taperfidprintdetailtask.getEpc()).isEqualTo(epc);
    }

    // Add more test methods for other getters and setters in
    // TapeRfidPrintDetailTask
}
