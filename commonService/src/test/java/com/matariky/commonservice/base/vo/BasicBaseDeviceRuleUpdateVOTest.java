package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDeviceRuleUpdateVOTest {

    @InjectMocks
    private BasicBaseDeviceRuleUpdateVO basicbasedeviceruleupdatevo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIdNotNull() {
        // Given
        basicbasedeviceruleupdatevo.setId(1L);

        // When
        Long id = basicbasedeviceruleupdatevo.getId();

        // Then
        assertThat(id).isNotNull();
        assertThat(id).isEqualTo(1L);
    }

    @Test
    void testIsRecordLogNotNull() {
        // Given
        basicbasedeviceruleupdatevo.setIsRecordLog(true);

        // When
        Boolean isRecordLog = basicbasedeviceruleupdatevo.getIsRecordLog();

        // Then
        assertThat(isRecordLog).isNotNull();
        assertThat(isRecordLog).isTrue();
    }

    @Test
    void testSetId() {
        // Given
        Long expectedId = 2L;

        // When
        basicbasedeviceruleupdatevo.setId(expectedId);

        // Then
        assertThat(basicbasedeviceruleupdatevo.getId()).isEqualTo(expectedId);
    }

    @Test
    void testSetIsRecordLog() {
        // Given
        Boolean expectedIsRecordLog = false;

        // When
        basicbasedeviceruleupdatevo.setIsRecordLog(expectedIsRecordLog);

        // Then
        assertThat(basicbasedeviceruleupdatevo.getIsRecordLog()).isEqualTo(expectedIsRecordLog);
    }

    // Add more test methods for other methods in BasicBaseDeviceRuleUpdateVO if
    // needed
}
