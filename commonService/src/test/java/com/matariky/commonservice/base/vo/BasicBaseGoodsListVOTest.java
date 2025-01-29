package com.matariky.commonservice.base.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicBaseGoodsListVOTest {

    @InjectMocks
    private BasicBaseGoodsListVO basicbasegoodslistvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetIndex() {
        // Given
        basicbasegoodslistvo.setIndex(5);

        // When
        int index = basicbasegoodslistvo.getIndex();

        // Then
        assertEquals(5, index);
    }

    @Test
    void testGetPerPage() {
        // Given
        basicbasegoodslistvo.setPerPage(10);

        // When
        int perPage = basicbasegoodslistvo.getPerPage();

        // Then
        assertEquals(10, perPage);
    }

    @Test
    void testGetGoodsName() {
        // Given
        basicbasegoodslistvo.setGoodsName("Test Goods");

        // When
        String goodsName = basicbasegoodslistvo.getGoodsName();

        // Then
        assertEquals("Test Goods", goodsName);
    }

    @Test
    void testGetGoodsCode() {
        // Given
        basicbasegoodslistvo.setGoodsCode("TG123");

        // When
        String goodsCode = basicbasegoodslistvo.getGoodsCode();

        // Then
        assertEquals("TG123", goodsCode);
    }

    @Test
    void testGetExtendFieldList() {
        // Given
        basicbasegoodslistvo.setExtendFieldList(Arrays.asList("Field1", "Field2"));

        // When
        List<String> extendFieldList = basicbasegoodslistvo.getExtendFieldList();

        // Then
        assertEquals(Arrays.asList("Field1", "Field2"), extendFieldList);
    }

    // Add more test methods for other methods in BasicBaseGoodsListVO
}
