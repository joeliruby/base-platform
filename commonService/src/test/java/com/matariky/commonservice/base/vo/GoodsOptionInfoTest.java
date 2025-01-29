package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class GoodsOptionInfoTest {

    @InjectMocks
    private GoodsOptionInfo goodsOptionInfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String goodsCode = "G123";
        String goodsName = "Goods Name";
        String label = "Display Text";

        // When
        goodsOptionInfo.setId(id);
        goodsOptionInfo.setGoodsCode(goodsCode);
        goodsOptionInfo.setGoodsName(goodsName);
        goodsOptionInfo.setLabel(label);

        // Then
        assertThat(goodsOptionInfo.getId()).isEqualTo(id);
        assertThat(goodsOptionInfo.getGoodsCode()).isEqualTo(goodsCode);
        assertThat(goodsOptionInfo.getGoodsName()).isEqualTo(goodsName);
        assertThat(goodsOptionInfo.getLabel()).isEqualTo(label);
    }

    @Test
    void testToString() {
        // Given
        goodsOptionInfo.setId(1L);
        goodsOptionInfo.setGoodsCode("G123");
        goodsOptionInfo.setGoodsName("Goods Name");
        goodsOptionInfo.setLabel("Display Text");

        // When
        String result = goodsOptionInfo.toString();

        // Then
        assertThat(result).contains("GoodsOptionInfo");
        assertThat(result).contains("id=1");
        assertThat(result).contains("goodsCode=G123");
        assertThat(result).contains("goodsName=Goods Name");
        assertThat(result).contains("label=Display Text");
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        GoodsOptionInfo goodsOptionInfo1 = new GoodsOptionInfo();
        goodsOptionInfo1.setId(1L);
        goodsOptionInfo1.setGoodsCode("G123");
        goodsOptionInfo1.setGoodsName("Goods Name");
        goodsOptionInfo1.setLabel("Display Text");

        GoodsOptionInfo goodsOptionInfo2 = new GoodsOptionInfo();
        goodsOptionInfo2.setId(1L);
        goodsOptionInfo2.setGoodsCode("G123");
        goodsOptionInfo2.setGoodsName("Goods Name");
        goodsOptionInfo2.setLabel("Display Text");

        // Then
        assertThat(goodsOptionInfo1).isEqualTo(goodsOptionInfo2);
        assertThat(goodsOptionInfo1.hashCode()).isEqualTo(goodsOptionInfo2.hashCode());
    }
}
