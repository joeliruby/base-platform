package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseCodingRulesListVOTest {

    @InjectMocks
    private BasicBaseCodingRulesListVO basicbasecodingruleslistvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetIndex() {
        // Given
        Integer expectedIndex = 5;

        // When
        basicbasecodingruleslistvo.setIndex(expectedIndex);

        // Then
        assertThat(basicbasecodingruleslistvo.getIndex()).isEqualTo(expectedIndex);
    }

    @Test
    void testSetAndGetPerPage() {
        // Given
        Integer expectedPerPage = 10;

        // When
        basicbasecodingruleslistvo.setPerPage(expectedPerPage);

        // Then
        assertThat(basicbasecodingruleslistvo.getPerPage()).isEqualTo(expectedPerPage);
    }

    @Test
    void testSetAndGetRulesName() {
        // Given
        String expectedRulesName = "Test Rules";

        // When
        basicbasecodingruleslistvo.setRulesName(expectedRulesName);

        // Then
        assertThat(basicbasecodingruleslistvo.getRulesName()).isEqualTo(expectedRulesName);
    }

    @Test
    void testSetAndGetRulesType() {
        // Given
        Integer expectedRulesType = 1;

        // When
        basicbasecodingruleslistvo.setRulesType(expectedRulesType);

        // Then
        assertThat(basicbasecodingruleslistvo.getRulesType()).isEqualTo(expectedRulesType);
    }

    @Test
    void testSetAndGetStatus() {
        // Given
        Integer expectedStatus = 1;

        // When
        basicbasecodingruleslistvo.setStatus(expectedStatus);

        // Then
        assertThat(basicbasecodingruleslistvo.getStatus()).isEqualTo(expectedStatus);
    }

    // Add more test methods for other methods in BasicBaseCodingRulesListVO if
    // needed
}
