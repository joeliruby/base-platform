package com.matariky.commonservice.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseGoodsTest {

    @InjectMocks
    private BasicBaseGoods basicBaseGoods;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String goodsCode = "G001";
        String goodsName = "Test Goods";
        String goodsImage = "image.jpg";
        String goodsDescribe = "Description";
        String remark = "Remark";
        Long createTime = System.currentTimeMillis();
        Long updateTime = System.currentTimeMillis();
        Long deleteTime = System.currentTimeMillis();
        Long createBy = 1L;
        Long updateBy = 2L;
        String operatorOrgCode = "ORG001";
        String operatorSelfOrgCode = "SELF001";
        String tenantId = "TENANT001";

        // When
        basicBaseGoods.setId(id);
        basicBaseGoods.setGoodsCode(goodsCode);
        basicBaseGoods.setGoodsName(goodsName);
        basicBaseGoods.setGoodsImage(goodsImage);
        basicBaseGoods.setGoodsDescribe(goodsDescribe);
        basicBaseGoods.setRemark(remark);
        basicBaseGoods.setCreateTime(createTime);
        basicBaseGoods.setUpdateTime(updateTime);
        basicBaseGoods.setDeleteTime(deleteTime);
        basicBaseGoods.setCreateBy(createBy);
        basicBaseGoods.setUpdateBy(updateBy);
        basicBaseGoods.setOperatorOrgCode(operatorOrgCode);
        basicBaseGoods.setOperatorSelfOrgCode(operatorSelfOrgCode);
        basicBaseGoods.setTenantId(tenantId);

        // Then
        assertThat(basicBaseGoods.getId()).isEqualTo(id);
        assertThat(basicBaseGoods.getGoodsCode()).isEqualTo(goodsCode);
        assertThat(basicBaseGoods.getGoodsName()).isEqualTo(goodsName);
        assertThat(basicBaseGoods.getGoodsImage()).isEqualTo(goodsImage);
        assertThat(basicBaseGoods.getGoodsDescribe()).isEqualTo(goodsDescribe);
        assertThat(basicBaseGoods.getRemark()).isEqualTo(remark);
        assertThat(basicBaseGoods.getCreateTime()).isEqualTo(createTime);
        assertThat(basicBaseGoods.getUpdateTime()).isEqualTo(updateTime);
        assertThat(basicBaseGoods.getDeleteTime()).isEqualTo(deleteTime);
        assertThat(basicBaseGoods.getCreateBy()).isEqualTo(createBy);
        assertThat(basicBaseGoods.getUpdateBy()).isEqualTo(updateBy);
        assertThat(basicBaseGoods.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
        assertThat(basicBaseGoods.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
        assertThat(basicBaseGoods.getTenantId()).isEqualTo(tenantId);
    }

    @Test
    void testToString() {
        // Given
        basicBaseGoods.setId(1L);
        basicBaseGoods.setGoodsCode("G001");
        basicBaseGoods.setGoodsName("Test Goods");

        // When
        String result = basicBaseGoods.toString();

        // Then
        assertThat(result).contains("id=1");
        assertThat(result).contains("goodsCode=G001");
        assertThat(result).contains("goodsName=Test Goods");
    }

    // Add more test methods for other methods in BasicBaseGoods
}
