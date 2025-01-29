package com.matariky.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PrimaryParamTest {

    @InjectMocks
    private PrimaryParam primaryparam;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPrimaryParamNotNull() {
        assertNotNull(primaryparam);
    }

    @Test
    void testPrimaryParamId() {
        primaryparam.setId(1L);
        assertEquals(1L, primaryparam.getId());
    }

    @Test
    void testPrimaryParamEqualsAndHashCode() {
        PrimaryParam param1 = new PrimaryParam();
        param1.setId(1L);
        PrimaryParam param2 = new PrimaryParam();
        param2.setId(1L);

        assertThat(param1).isEqualTo(param2);
        assertThat(param1.hashCode()).isEqualTo(param2.hashCode());
    }

    @Test
    void testPrimaryParamNotEquals() {
        PrimaryParam param1 = new PrimaryParam();
        param1.setId(1L);
        PrimaryParam param2 = new PrimaryParam();
        param2.setId(2L);

        assertThat(param1).isNotEqualTo(param2);
    }
}
