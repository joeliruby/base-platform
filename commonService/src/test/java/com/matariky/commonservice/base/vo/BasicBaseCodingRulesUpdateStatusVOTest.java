package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseCodingRulesUpdateStatusVOTest {

    @InjectMocks
    private BasicBaseCodingRulesUpdateStatusVO basicbasecodingrulesupdatestatusvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetId() {
        // Given
        Long expectedId = 1L;

        // When
        basicbasecodingrulesupdatestatusvo.setId(expectedId);

        // Then
        assertThat(basicbasecodingrulesupdatestatusvo.getId()).isEqualTo(expectedId);
    }

    @Test
    void testSetStatus() {
        // Given
        Integer expectedStatus = 1;

        // When
        basicbasecodingrulesupdatestatusvo.setStatus(expectedStatus);

        // Then
        assertThat(basicbasecodingrulesupdatestatusvo.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        basicbasecodingrulesupdatestatusvo.setId(expectedId);

        // When
        Long actualId = basicbasecodingrulesupdatestatusvo.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetStatus() {
        // Given
        Integer expectedStatus = 1;
        basicbasecodingrulesupdatestatusvo.setStatus(expectedStatus);

        // When
        Integer actualStatus = basicbasecodingrulesupdatestatusvo.getStatus();

        // Then
        assertThat(actualStatus).isEqualTo(expectedStatus);
    }
}
