package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CodingRulesOptionInfoTest {

    @InjectMocks
    private CodingRulesOptionInfo codingrulesoptioninfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        codingrulesoptioninfo.setId(expectedId);

        // When
        Long actualId = codingrulesoptioninfo.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetRulesName() {
        // Given
        String expectedRulesName = "Test Rule";
        codingrulesoptioninfo.setRulesName(expectedRulesName);

        // When
        String actualRulesName = codingrulesoptioninfo.getRulesName();

        // Then
        assertThat(actualRulesName).isEqualTo(expectedRulesName);
    }

    @Test
    void testGetLabel() {
        // Given
        String expectedLabel = "Test Label";
        codingrulesoptioninfo.setLabel(expectedLabel);

        // When
        String actualLabel = codingrulesoptioninfo.getLabel();

        // Then
        assertThat(actualLabel).isEqualTo(expectedLabel);
    }

    @Test
    void testSetId() {
        // Given
        Long expectedId = 2L;

        // When
        codingrulesoptioninfo.setId(expectedId);

        // Then
        assertThat(codingrulesoptioninfo.getId()).isEqualTo(expectedId);
    }

    @Test
    void testSetRulesName() {
        // Given
        String expectedRulesName = "New Rule";

        // When
        codingrulesoptioninfo.setRulesName(expectedRulesName);

        // Then
        assertThat(codingrulesoptioninfo.getRulesName()).isEqualTo(expectedRulesName);
    }

    @Test
    void testSetLabel() {
        // Given
        String expectedLabel = "New Label";

        // When
        codingrulesoptioninfo.setLabel(expectedLabel);

        // Then
        assertThat(codingrulesoptioninfo.getLabel()).isEqualTo(expectedLabel);
    }
}
