package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseAntifakeDetailVOTest {

    @InjectMocks
    private BasicBaseAntifakeDetailVO basicbaseantifakedetailvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long validationTimeStart = 1627849200000L;
        Long validationTimeEnd = 1627852800000L;
        String goodsCode = "ABC123";
        String goodsName = "Sample Goods";

        // When
        basicbaseantifakedetailvo.setValidationTimeStart(validationTimeStart);
        basicbaseantifakedetailvo.setValidationTimeEnd(validationTimeEnd);
        basicbaseantifakedetailvo.setGoodsCode(goodsCode);
        basicbaseantifakedetailvo.setGoodsName(goodsName);

        // Then
        assertThat(basicbaseantifakedetailvo.getValidationTimeStart()).isEqualTo(validationTimeStart);
        assertThat(basicbaseantifakedetailvo.getValidationTimeEnd()).isEqualTo(validationTimeEnd);
        assertThat(basicbaseantifakedetailvo.getGoodsCode()).isEqualTo(goodsCode);
        assertThat(basicbaseantifakedetailvo.getGoodsName()).isEqualTo(goodsName);
    }

    @Test
    void testToString() {
        // Given
        basicbaseantifakedetailvo.setValidationTimeStart(1627849200000L);
        basicbaseantifakedetailvo.setValidationTimeEnd(1627852800000L);
        basicbaseantifakedetailvo.setGoodsCode("ABC123");
        basicbaseantifakedetailvo.setGoodsName("Sample Goods");

        // When
        String result = basicbaseantifakedetailvo.toString();

        // Then
        assertThat(result).contains("validationTimeStart=1627849200000");
        assertThat(result).contains("validationTimeEnd=1627852800000");
        assertThat(result).contains("goodsCode=ABC123");
        assertThat(result).contains("goodsName=Sample Goods");
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        BasicBaseAntifakeDetailVO vo1 = new BasicBaseAntifakeDetailVO();
        vo1.setValidationTimeStart(1627849200000L);
        vo1.setValidationTimeEnd(1627852800000L);
        vo1.setGoodsCode("ABC123");
        vo1.setGoodsName("Sample Goods");

        BasicBaseAntifakeDetailVO vo2 = new BasicBaseAntifakeDetailVO();
        vo2.setValidationTimeStart(1627849200000L);
        vo2.setValidationTimeEnd(1627852800000L);
        vo2.setGoodsCode("ABC123");
        vo2.setGoodsName("Sample Goods");

        // When & Then
        assertThat(vo1).isEqualTo(vo2);
        assertThat(vo1.hashCode()).isEqualTo(vo2.hashCode());
    }
}
