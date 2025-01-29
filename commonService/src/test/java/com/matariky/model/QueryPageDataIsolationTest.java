package com.matariky.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class QueryPageDataIsolationTest {

    @InjectMocks
    private QueryPageDataIsolation querypagedataisolation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetIndexWhenIndexIsNull() {
        // Given
        querypagedataisolation.setIndex(null);

        // When
        Integer result = querypagedataisolation.getIndex();

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testGetIndexWhenIndexIsNotNull() {
        // Given
        querypagedataisolation.setIndex(5);

        // When
        Integer result = querypagedataisolation.getIndex();

        // Then
        assertThat(result).isEqualTo(5);
    }

    @Test
    void testGetPerPageWhenPerPageIsNull() {
        // Given
        querypagedataisolation.setPerPage(null);

        // When
        Integer result = querypagedataisolation.getPerPage();

        // Then
        assertThat(result).isEqualTo(20);
    }

    @Test
    void testGetPerPageWhenPerPageIsNotNull() {
        // Given
        querypagedataisolation.setPerPage(10);

        // When
        Integer result = querypagedataisolation.getPerPage();

        // Then
        assertThat(result).isEqualTo(10);
    }

    @Test
    void testGetPageStart() {
        // Given
        querypagedataisolation.setIndex(3);
        querypagedataisolation.setPerPage(10);

        // When
        Integer result = querypagedataisolation.getPageStart();

        // Then
        assertThat(result).isEqualTo(20);
    }

    @Test
    void testGetPageSize() {
        // Given
        querypagedataisolation.setPerPage(15);

        // When
        Integer result = querypagedataisolation.getPageSize();

        // Then
        assertThat(result).isEqualTo(15);
    }
}
