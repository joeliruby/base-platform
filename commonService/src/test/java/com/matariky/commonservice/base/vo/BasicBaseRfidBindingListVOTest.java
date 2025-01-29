package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseRfidBindingListVOTest {

    @InjectMocks
    private BasicBaseRfidBindingListVO basicBaseRfidBindingListVO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetIndex() {
        // Given
        basicBaseRfidBindingListVO.setIndex(1);

        // When
        Integer index = basicBaseRfidBindingListVO.getIndex();

        // Then
        assertThat(index).isEqualTo(1);
    }

    @Test
    void testGetPerPage() {
        // Given
        basicBaseRfidBindingListVO.setPerPage(10);

        // When
        Integer perPage = basicBaseRfidBindingListVO.getPerPage();

        // Then
        assertThat(perPage).isEqualTo(10);
    }

    @Test
    void testGetGoodsName() {
        // Given
        basicBaseRfidBindingListVO.setGoodsName("Test Goods");

        // When
        String goodsName = basicBaseRfidBindingListVO.getGoodsName();

        // Then
        assertThat(goodsName).isEqualTo("Test Goods");
    }

    @Test
    void testGetGoodsCode() {
        // Given
        basicBaseRfidBindingListVO.setGoodsCode("TG123");

        // When
        String goodsCode = basicBaseRfidBindingListVO.getGoodsCode();

        // Then
        assertThat(goodsCode).isEqualTo("TG123");
    }

    @Test
    void testGetGoodsId() {
        // Given
        basicBaseRfidBindingListVO.setGoodsId(123L);

        // When
        Long goodsId = basicBaseRfidBindingListVO.getGoodsId();

        // Then
        assertThat(goodsId).isEqualTo(123L);
    }

    // Add more test methods for other methods in BasicBaseRfidBindingListVO if
    // needed
}
