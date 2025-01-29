package com.matariky.tdengine.meter.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.util.Date;

@SpringBootTest
public class MetersVOTest {

    @InjectMocks
    private MetersVO metersvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetTs() {
        // Given
        Date date = new Date();

        // When
        metersvo.setTs(date);

        // Then
        assertThat(metersvo.getTs()).isEqualTo(date);
    }

    @Test
    void testSetAndGetCurrent() {
        // Given
        Float current = 10.5f;

        // When
        metersvo.setCurrent(current);

        // Then
        assertThat(metersvo.getCurrent()).isEqualTo(current);
    }

    @Test
    void testSetAndGetVoltage() {
        // Given
        Integer voltage = 220;

        // When
        metersvo.setVoltage(voltage);

        // Then
        assertThat(metersvo.getVoltage()).isEqualTo(voltage);
    }

    @Test
    void testSetAndGetPhase() {
        // Given
        Float phase = 1.5f;

        // When
        metersvo.setPhase(phase);

        // Then
        assertThat(metersvo.getPhase()).isEqualTo(phase);
    }

    @Test
    void testSetAndGetCode() {
        // Given
        String code = "ABC123";

        // When
        metersvo.setCode(code);

        // Then
        assertThat(metersvo.getCode()).isEqualTo(code);
    }
}
