package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseGoodsInfoVoTest {

    @InjectMocks
    private BasicBaseGoodsInfoVo basicbasegoodsinfovo;

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
        String goodsImage = "image.png";
        String goodsDescribe = "Description";
        String remark = "Remark";
        Long createTime = System.currentTimeMillis();
        Long updateTime = System.currentTimeMillis();
        Long deleteTime = System.currentTimeMillis();
        Long createBy = 1L;
        Long updateBy = 2L;
        String operatorOrgCode = "OrgCode";
        String operatorSelfOrgCode = "SelfOrgCode";
        String tenantId = "Tenant123";
        String realName = "Real Name";
        Boolean isUsedByOther = true;

        // When
        basicbasegoodsinfovo.setId(id);
        basicbasegoodsinfovo.setGoodsCode(goodsCode);
        basicbasegoodsinfovo.setGoodsName(goodsName);
        basicbasegoodsinfovo.setGoodsImage(goodsImage);
        basicbasegoodsinfovo.setGoodsDescribe(goodsDescribe);
        basicbasegoodsinfovo.setRemark(remark);
        basicbasegoodsinfovo.setCreateTime(createTime);
        basicbasegoodsinfovo.setUpdateTime(updateTime);
        basicbasegoodsinfovo.setDeleteTime(deleteTime);
        basicbasegoodsinfovo.setCreateBy(createBy);
        basicbasegoodsinfovo.setUpdateBy(updateBy);
        basicbasegoodsinfovo.setOperatorOrgCode(operatorOrgCode);
        basicbasegoodsinfovo.setOperatorSelfOrgCode(operatorSelfOrgCode);
        basicbasegoodsinfovo.setTenantId(tenantId);
        basicbasegoodsinfovo.setRealName(realName);
        basicbasegoodsinfovo.setIsUsedByOther(isUsedByOther);

        // Then
        assertThat(basicbasegoodsinfovo.getId()).isEqualTo(id);
        assertThat(basicbasegoodsinfovo.getGoodsCode()).isEqualTo(goodsCode);
        assertThat(basicbasegoodsinfovo.getGoodsName()).isEqualTo(goodsName);
        assertThat(basicbasegoodsinfovo.getGoodsImage()).isEqualTo(goodsImage);
        assertThat(basicbasegoodsinfovo.getGoodsDescribe()).isEqualTo(goodsDescribe);
        assertThat(basicbasegoodsinfovo.getRemark()).isEqualTo(remark);
        assertThat(basicbasegoodsinfovo.getCreateTime()).isEqualTo(createTime);
        assertThat(basicbasegoodsinfovo.getUpdateTime()).isEqualTo(updateTime);
        assertThat(basicbasegoodsinfovo.getDeleteTime()).isEqualTo(deleteTime);
        assertThat(basicbasegoodsinfovo.getCreateBy()).isEqualTo(createBy);
        assertThat(basicbasegoodsinfovo.getUpdateBy()).isEqualTo(updateBy);
        assertThat(basicbasegoodsinfovo.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
        assertThat(basicbasegoodsinfovo.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
        assertThat(basicbasegoodsinfovo.getTenantId()).isEqualTo(tenantId);
        assertThat(basicbasegoodsinfovo.getRealName()).isEqualTo(realName);
        assertThat(basicbasegoodsinfovo.getIsUsedByOther()).isEqualTo(isUsedByOther);
    }

    @Test
    void testDefaultValues() {
        // Given
        BasicBaseGoodsInfoVo vo = new BasicBaseGoodsInfoVo();

        // Then
        assertThat(vo.getIsUsedByOther()).isFalse();
    }

    // Add more test methods for other methods in BasicBaseGoodsInfoVo
}
