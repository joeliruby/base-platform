package com.matariky.commonservice.base.vo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicBaseAppVersionDelVOTest {

    @InjectMocks
    private BasicBaseAppVersionDelVO basicbaseappversiondelvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIdsNotEmpty() {
        // Given
        basicbaseappversiondelvo.setIds(Arrays.asList(1L, 2L, 3L));

        // When
        List<Long> ids = basicbaseappversiondelvo.getIds();

        // Then
        assertThat(ids).isNotEmpty();
        assertThat(ids).containsExactly(1L, 2L, 3L);
    }

    @Test
    void testIdsEmpty() {
        // Given
        basicbaseappversiondelvo.setIds(Collections.emptyList());

        // When
        List<Long> ids = basicbaseappversiondelvo.getIds();

        // Then
        assertThat(ids).isEmpty();
    }

    @Test
    void testIdsNull() {
        // Given
        basicbaseappversiondelvo.setIds(null);

        // When
        List<Long> ids = basicbaseappversiondelvo.getIds();

        // Then
        assertThat(ids).isNull();
    }

    // Add more test methods for other scenarios if needed
}
