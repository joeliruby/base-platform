package com.matariky.iservice.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class APIConstantsTest {

    @InjectMocks
    private APIConstants apiconstants;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostConstant() {
        assertThat(APIConstants.POST).isEqualTo("0");
    }

    @Test
    void testGetConstant() {
        assertThat(APIConstants.GET).isEqualTo("4");
    }

    @Test
    void testDeleteConstant() {
        assertThat(APIConstants.DELETE).isEqualTo("2");
    }

    @Test
    void testPutConstant() {
        assertThat(APIConstants.PUT).isEqualTo("3");
    }
}
