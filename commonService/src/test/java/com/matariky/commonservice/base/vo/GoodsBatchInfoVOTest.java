package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class GoodsBatchInfoVOTest {

    @InjectMocks
    private GoodsBatchInfoVO goodsbatchinfovo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        Long goodsId = 2L;
        String goodsName = "Test Goods";
        String goodsCode = "TG123";
        String goodsImage = "image.png";
        String tid = "TID123";
        String epc = "EPC123";
        Long createTime = System.currentTimeMillis();

        // When
        goodsbatchinfovo.setId(id);
        goodsbatchinfovo.setGoodsId(goodsId);
        goodsbatchinfovo.setGoodsName(goodsName);
        goodsbatchinfovo.setGoodsCode(goodsCode);
        goodsbatchinfovo.setGoodsImage(goodsImage);
        goodsbatchinfovo.setTid(tid);
        goodsbatchinfovo.setEpc(epc);
        goodsbatchinfovo.setCreateTime(createTime);

        // Then
        assertThat(goodsbatchinfovo.getId()).isEqualTo(id);
        assertThat(goodsbatchinfovo.getGoodsId()).isEqualTo(goodsId);
        assertThat(goodsbatchinfovo.getGoodsName()).isEqualTo(goodsName);
        assertThat(goodsbatchinfovo.getGoodsCode()).isEqualTo(goodsCode);
        assertThat(goodsbatchinfovo.getGoodsImage()).isEqualTo(goodsImage);
        assertThat(goodsbatchinfovo.getTid()).isEqualTo(tid);
        assertThat(goodsbatchinfovo.getEpc()).isEqualTo(epc);
        assertThat(goodsbatchinfovo.getCreateTime()).isEqualTo(createTime);
    }

    @Test
    void testToString() {
        // Given
        goodsbatchinfovo.setId(1L);
        goodsbatchinfovo.setGoodsId(2L);
        goodsbatchinfovo.setGoodsName("Test Goods");
        goodsbatchinfovo.setGoodsCode("TG123");
        goodsbatchinfovo.setGoodsImage("image.png");
        goodsbatchinfovo.setTid("TID123");
        goodsbatchinfovo.setEpc("EPC123");
        goodsbatchinfovo.setCreateTime(System.currentTimeMillis());

        // When
        String toString = goodsbatchinfovo.toString();

        // Then
        assertThat(toString).contains("GoodsBatchInfoVO");
        assertThat(toString).contains("id=1");
        assertThat(toString).contains("goodsId=2");
        assertThat(toString).contains("goodsName=Test Goods");
        assertThat(toString).contains("goodsCode=TG123");
        assertThat(toString).contains("goodsImage=image.png");
        assertThat(toString).contains("tid=TID123");
        assertThat(toString).contains("epc=EPC123");
    }

    // Add more test methods for other methods in GoodsBatchInfoVO if needed
}
