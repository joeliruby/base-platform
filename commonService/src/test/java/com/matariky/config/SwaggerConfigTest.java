package com.matariky.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootTest
public class SwaggerConfigTest {

    @InjectMocks
    private SwaggerConfig swaggerconfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBizApi() {
        // When
        Docket docket = swaggerconfig.createBizApi();

        // Then
        assertNotNull(docket);
        assertEquals("biz", docket.getGroupName());
    }

    @Test
    void testCreateCommonApi() {
        // When
        Docket docket = swaggerconfig.createCommonApi();

        // Then
        assertNotNull(docket);
        assertEquals("common", docket.getGroupName());
    }

    @Test
    void testCreateUserApi() {
        // When
        Docket docket = swaggerconfig.createUserApi();

        // Then
        assertNotNull(docket);
        assertEquals("user", docket.getGroupName());
    }

    @Test
    void testCreateOrderApi() {
        // When
        Docket docket = swaggerconfig.createOrderApi();

        // Then
        assertNotNull(docket);
        assertEquals("order", docket.getGroupName());
    }
}
