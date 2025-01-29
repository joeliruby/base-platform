package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ExtendedPageInfoTest {

    @InjectMocks
    private ExtendedPageInfo<String> extendedPageInfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCode() {
        // Given
        List<String> list = Arrays.asList("item1", "item2");
        String code = "testCode";
        extendedPageInfo = new ExtendedPageInfo<>(list, 5, code);

        // When
        String result = extendedPageInfo.getCode();

        // Then
        assertEquals(code, result);
    }

    @Test
    void testSetCode() {
        // Given
        List<String> list = Arrays.asList("item1", "item2");
        String code = "initialCode";
        extendedPageInfo = new ExtendedPageInfo<>(list, 5, code);
        String newCode = "newCode";

        // When
        extendedPageInfo.setCode(newCode);

        // Then
        assertEquals(newCode, extendedPageInfo.getCode());
    }

    // Add more test methods for other methods in ExtendedPageInfo
}
