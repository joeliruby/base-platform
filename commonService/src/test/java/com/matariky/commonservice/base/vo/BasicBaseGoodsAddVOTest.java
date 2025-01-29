package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseGoodsAddVOTest {

    @InjectMocks
    private BasicBaseGoodsAddVO basicbasegoodsaddvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGoodsCode() {
        // Given
        String expectedGoodsCode = "12345";
        basicbasegoodsaddvo.setGoodsCode(expectedGoodsCode);

        // When
        String actualGoodsCode = basicbasegoodsaddvo.getGoodsCode();

        // Then
        assertThat(actualGoodsCode).isEqualTo(expectedGoodsCode);
    }

    @Test
    void testGoodsName() {
        // Given
        String expectedGoodsName = "Test Item";
        basicbasegoodsaddvo.setGoodsName(expectedGoodsName);

        // When
        String actualGoodsName = basicbasegoodsaddvo.getGoodsName();

        // Then
        assertThat(actualGoodsName).isEqualTo(expectedGoodsName);
    }

    @Test
    void testGoodsImage() {
        // Given
        String expectedGoodsImage = "image.png";
        basicbasegoodsaddvo.setGoodsImage(expectedGoodsImage);

        // When
        String actualGoodsImage = basicbasegoodsaddvo.getGoodsImage();

        // Then
        assertThat(actualGoodsImage).isEqualTo(expectedGoodsImage);
    }

    @Test
    void testGoodsDescribe() {
        // Given
        String expectedGoodsDescribe = "This is a test item description.";
        basicbasegoodsaddvo.setGoodsDescribe(expectedGoodsDescribe);

        // When
        String actualGoodsDescribe = basicbasegoodsaddvo.getGoodsDescribe();

        // Then
        assertThat(actualGoodsDescribe).isEqualTo(expectedGoodsDescribe);
    }

    // Add more test methods for other methods in BasicBaseGoodsAddVO if needed
}
