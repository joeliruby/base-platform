package com.matariky.tdengine.meter.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.sql.Timestamp;

@SpringBootTest
public class MetersTest {

    @InjectMocks
    private Meters meters;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetTs() {
        // Given
        Timestamp expectedTs = new Timestamp(System.currentTimeMillis());

        // When
        meters.setTs(expectedTs);

        // Then
        assertThat(meters.getTs()).isEqualTo(expectedTs);
    }

    @Test
    void testSetAndGetCurrent() {
        // Given
        Float expectedCurrent = 10.5f;

        // When
        meters.setCurrent(expectedCurrent);

        // Then
        assertThat(meters.getCurrent()).isEqualTo(expectedCurrent);
    }

    @Test
    void testSetAndGetVoltage() {
        // Given
        Integer expectedVoltage = 220;

        // When
        meters.setVoltage(expectedVoltage);

        // Then
        assertThat(meters.getVoltage()).isEqualTo(expectedVoltage);
    }

    @Test
    void testSetAndGetPhase() {
        // Given
        Float expectedPhase = 1.5f;

        // When
        meters.setPhase(expectedPhase);

        // Then
        assertThat(meters.getPhase()).isEqualTo(expectedPhase);
    }

    @Test
    void testSetAndGetCode() {
        // Given
        String expectedCode = "ABC123";

        // When
        meters.setCode(expectedCode);

        // Then
        assertThat(meters.getCode()).isEqualTo(expectedCode);
    }
}
