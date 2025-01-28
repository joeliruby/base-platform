package com.matariky.bizservice.assetitm.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseRfidprintParameterAddVOTest {

    @InjectMocks
    private BasicBaseRfidprintParameterAddVO basicbaserfidprintparameteraddvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        Long printId = 2L;
        String type = "type";
        String parameterName = "parameterName";
        String parameterContent = "parameterContent";
        String tenantId = "tenantId";
        String remark = "remark";
        Long createdBy = 3L;
        Long updatedBy = 4L;
        Long createTime = 5L;
        Long updateTime = 6L;
        Long deleteTime = 7L;

        // When
        basicbaserfidprintparameteraddvo.setId(id);
        basicbaserfidprintparameteraddvo.setPrintId(printId);
        basicbaserfidprintparameteraddvo.setType(type);
        basicbaserfidprintparameteraddvo.setParameterName(parameterName);
        basicbaserfidprintparameteraddvo.setParameterContent(parameterContent);
        basicbaserfidprintparameteraddvo.setTenantId(tenantId);
        basicbaserfidprintparameteraddvo.setRemark(remark);
        basicbaserfidprintparameteraddvo.setCreatedBy(createdBy);
        basicbaserfidprintparameteraddvo.setUpdatedBy(updatedBy);
        basicbaserfidprintparameteraddvo.setCreateTime(createTime);
        basicbaserfidprintparameteraddvo.setUpdateTime(updateTime);
        basicbaserfidprintparameteraddvo.setDeleteTime(deleteTime);

        // Then
        assertThat(basicbaserfidprintparameteraddvo.getId()).isEqualTo(id);
        assertThat(basicbaserfidprintparameteraddvo.getPrintId()).isEqualTo(printId);
        assertThat(basicbaserfidprintparameteraddvo.getType()).isEqualTo(type);
        assertThat(basicbaserfidprintparameteraddvo.getParameterName()).isEqualTo(parameterName);
        assertThat(basicbaserfidprintparameteraddvo.getParameterContent()).isEqualTo(parameterContent);
        assertThat(basicbaserfidprintparameteraddvo.getTenantId()).isEqualTo(tenantId);
        assertThat(basicbaserfidprintparameteraddvo.getRemark()).isEqualTo(remark);
        assertThat(basicbaserfidprintparameteraddvo.getCreatedBy()).isEqualTo(createdBy);
        assertThat(basicbaserfidprintparameteraddvo.getUpdatedBy()).isEqualTo(updatedBy);
        assertThat(basicbaserfidprintparameteraddvo.getCreateTime()).isEqualTo(createTime);
        assertThat(basicbaserfidprintparameteraddvo.getUpdateTime()).isEqualTo(updateTime);
        assertThat(basicbaserfidprintparameteraddvo.getDeleteTime()).isEqualTo(deleteTime);
    }

    @Test
    void testToString() {
        // Given
        basicbaserfidprintparameteraddvo.setId(1L);
        basicbaserfidprintparameteraddvo.setPrintId(2L);
        basicbaserfidprintparameteraddvo.setType("type");
        basicbaserfidprintparameteraddvo.setParameterName("parameterName");
        basicbaserfidprintparameteraddvo.setParameterContent("parameterContent");
        basicbaserfidprintparameteraddvo.setTenantId("tenantId");
        basicbaserfidprintparameteraddvo.setRemark("remark");
        basicbaserfidprintparameteraddvo.setCreatedBy(3L);
        basicbaserfidprintparameteraddvo.setUpdatedBy(4L);
        basicbaserfidprintparameteraddvo.setCreateTime(5L);
        basicbaserfidprintparameteraddvo.setUpdateTime(6L);
        basicbaserfidprintparameteraddvo.setDeleteTime(7L);

        // When
        String result = basicbaserfidprintparameteraddvo.toString();

        // Then
        assertThat(result).contains("id=1");
        assertThat(result).contains("printId=2");
        assertThat(result).contains("type=type");
        assertThat(result).contains("parameterName=parameterName");
        assertThat(result).contains("parameterContent=parameterContent");
        assertThat(result).contains("tenantId=tenantId");
        assertThat(result).contains("remark=remark");
        assertThat(result).contains("createdBy=3");
        assertThat(result).contains("updatedBy=4");
        assertThat(result).contains("createTime=5");
        assertThat(result).contains("updateTime=6");
        assertThat(result).contains("deleteTime=7");
    }
}
