package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
public class BasicBasePcVersionDelVOTest {

    @InjectMocks
    private BasicBasePcVersionDelVO basicbasepcversiondelvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIdsNotEmpty() {
        // Given
        basicbasepcversiondelvo.setIds(Arrays.asList(1L, 2L, 3L));

        // When
        boolean isEmpty = basicbasepcversiondelvo.getIds().isEmpty();

        // Then
        assertThat(isEmpty).isFalse();
    }

    @Test
    void testIdsEmpty() {
        // Given
        basicbasepcversiondelvo.setIds(Collections.emptyList());

        // When
        boolean isEmpty = basicbasepcversiondelvo.getIds().isEmpty();

        // Then
        assertThat(isEmpty).isTrue();
    }

    @Test
    void testIdsSize() {
        // Given
        basicbasepcversiondelvo.setIds(Arrays.asList(1L, 2L, 3L));

        // When
        int size = basicbasepcversiondelvo.getIds().size();

        // Then
        assertThat(size).isEqualTo(3);
    }

    @Test
    void testIdsContainsSpecificValue() {
        // Given
        basicbasepcversiondelvo.setIds(Arrays.asList(1L, 2L, 3L));

        // When
        boolean containsValue = basicbasepcversiondelvo.getIds().contains(2L);

        // Then
        assertThat(containsValue).isTrue();
    }

    // Add more test methods for other scenarios if needed
}
