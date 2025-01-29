package com.matariky.commonservice.base.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBaseAntifake;
import com.matariky.commonservice.base.service.BasicBaseAntifakeService;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class BasicBaseAntifakeControllerTest {

    @InjectMocks
    private BasicBaseAntifakeController basicBaseAntifakeController;

    @Mock
    private BasicBaseAntifakeService basicBaseAntifakeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        BasicBaseAntifake bean = new BasicBaseAntifake();
        int pageIndex = 1;
        int perPage = 10;

        // When
        AjaxResult result = basicBaseAntifakeController.list(bean, pageIndex, perPage);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testSave() {
        // Given
        BasicBaseAntifake bean = new BasicBaseAntifake();

        // When
        AjaxResult result = basicBaseAntifakeController.save(bean);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseAntifake bean = new BasicBaseAntifake();

        // When
        AjaxResult result = basicBaseAntifakeController.update(bean);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";

        // When
        AjaxResult result = basicBaseAntifakeController.del(id);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;

        // When
        AjaxResult result = basicBaseAntifakeController.getOne(id);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    // Add more test methods for other methods in BasicBaseAntifakeController
}
