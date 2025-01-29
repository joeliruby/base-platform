package com.matariky.commonservice.base.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddExtendFieldVOTest {

    @InjectMocks
    private AddExtendFieldVO addextendfieldvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetName() {
        // Given
        String expectedName = "Test Form";
        addextendfieldvo.setName(expectedName);

        // When
        String actualName = addextendfieldvo.getName();

        // Then
        assertEquals(expectedName, actualName);
    }

    @Test
    void testSetName() {
        // Given
        String expectedName = "Test Form";

        // When
        addextendfieldvo.setName(expectedName);

        // Then
        assertEquals(expectedName, addextendfieldvo.getName());
    }

    @Test
    void testGetList() {
        // Given
        AddExtendFieldInfoVO fieldInfo = new AddExtendFieldInfoVO();
        List<AddExtendFieldInfoVO> expectedList = Collections.singletonList(fieldInfo);
        addextendfieldvo.setList(expectedList);

        // When
        List<AddExtendFieldInfoVO> actualList = addextendfieldvo.getList();

        // Then
        assertEquals(expectedList, actualList);
    }

    @Test
    void testSetList() {
        // Given
        AddExtendFieldInfoVO fieldInfo = new AddExtendFieldInfoVO();
        List<AddExtendFieldInfoVO> expectedList = Collections.singletonList(fieldInfo);

        // When
        addextendfieldvo.setList(expectedList);

        // Then
        assertEquals(expectedList, addextendfieldvo.getList());
    }

    @Test
    void testEmptyName() {
        // Given
        String expectedName = "";

        // When
        addextendfieldvo.setName(expectedName);

        // Then
        assertEquals(expectedName, addextendfieldvo.getName());
    }

    @Test
    void testNullName() {
        // Given
        String expectedName = null;

        // When
        addextendfieldvo.setName(expectedName);

        // Then
        assertNull(addextendfieldvo.getName());
    }

    @Test
    void testEmptyList() {
        // Given
        List<AddExtendFieldInfoVO> expectedList = Collections.emptyList();

        // When
        addextendfieldvo.setList(expectedList);

        // Then
        assertEquals(expectedList, addextendfieldvo.getList());
    }

    @Test
    void testNullList() {
        // Given
        List<AddExtendFieldInfoVO> expectedList = null;

        // When
        addextendfieldvo.setList(expectedList);

        // Then
        assertNull(addextendfieldvo.getList());
    }
}
