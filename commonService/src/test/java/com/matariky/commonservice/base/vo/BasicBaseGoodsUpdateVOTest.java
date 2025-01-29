package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseGoodsUpdateVOTest {

    @InjectMocks
    private BasicBaseGoodsUpdateVO basicbasegoodsupdatevo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGoodsName() {
        // Given
        String goodsName = "Test Goods";
        basicbasegoodsupdatevo.setGoodsName(goodsName);

        // When
        String result = basicbasegoodsupdatevo.getGoodsName();

        // Then
        assertThat(result).isEqualTo(goodsName);
    }

    @Test
    void testGoodsImage() {
        // Given
        String goodsImage = "Test Image";
        basicbasegoodsupdatevo.setGoodsImage(goodsImage);

        // When
        String result = basicbasegoodsupdatevo.getGoodsImage();

        // Then
        assertThat(result).isEqualTo(goodsImage);
    }

    @Test
    void testGoodsDescribe() {
        // Given
        String goodsDescribe = "Test Description";
        basicbasegoodsupdatevo.setGoodsDescribe(goodsDescribe);

        // When
        String result = basicbasegoodsupdatevo.getGoodsDescribe();

        // Then
        assertThat(result).isEqualTo(goodsDescribe);
    }

    @Test
    void testId() {
        // Given
        Long id = 1L;
        basicbasegoodsupdatevo.setId(id);

        // When
        Long result = basicbasegoodsupdatevo.getId();

        // Then
        assertThat(result).isEqualTo(id);
    }
}
