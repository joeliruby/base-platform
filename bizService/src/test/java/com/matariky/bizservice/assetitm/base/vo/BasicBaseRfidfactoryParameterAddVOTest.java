package com.matariky.bizservice.assetitm.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseRfidfactoryParameterAddVOTest {

    @InjectMocks
    private BasicBaseRfidfactoryParameterAddVO basicbaserfidfactoryparameteraddvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        Long rfidfactoryId = 2L;
        String type = "QR Code";
        String parameterName = "Parameter Name";
        String parameterContent = "Parameter Content";
        String tenantId = "TenantId";
        String remark = "Remark";
        Long createdBy = 3L;
        Long updatedBy = 4L;
        Long createTime = 5L;
        Long updateTime = 6L;
        Long deleteTime = 7L;

        // When
        basicbaserfidfactoryparameteraddvo.setId(id);
        basicbaserfidfactoryparameteraddvo.setRfidfactoryId(rfidfactoryId);
        basicbaserfidfactoryparameteraddvo.setType(type);
        basicbaserfidfactoryparameteraddvo.setParameterName(parameterName);
        basicbaserfidfactoryparameteraddvo.setParameterContent(parameterContent);
        basicbaserfidfactoryparameteraddvo.setTenantId(tenantId);
        basicbaserfidfactoryparameteraddvo.setRemark(remark);
        basicbaserfidfactoryparameteraddvo.setCreatedBy(createdBy);
        basicbaserfidfactoryparameteraddvo.setUpdatedBy(updatedBy);
        basicbaserfidfactoryparameteraddvo.setCreateTime(createTime);
        basicbaserfidfactoryparameteraddvo.setUpdateTime(updateTime);
        basicbaserfidfactoryparameteraddvo.setDeleteTime(deleteTime);

        // Then
        assertThat(basicbaserfidfactoryparameteraddvo.getId()).isEqualTo(id);
        assertThat(basicbaserfidfactoryparameteraddvo.getRfidfactoryId()).isEqualTo(rfidfactoryId);
        assertThat(basicbaserfidfactoryparameteraddvo.getType()).isEqualTo(type);
        assertThat(basicbaserfidfactoryparameteraddvo.getParameterName()).isEqualTo(parameterName);
        assertThat(basicbaserfidfactoryparameteraddvo.getParameterContent()).isEqualTo(parameterContent);
        assertThat(basicbaserfidfactoryparameteraddvo.getTenantId()).isEqualTo(tenantId);
        assertThat(basicbaserfidfactoryparameteraddvo.getRemark()).isEqualTo(remark);
        assertThat(basicbaserfidfactoryparameteraddvo.getCreatedBy()).isEqualTo(createdBy);
        assertThat(basicbaserfidfactoryparameteraddvo.getUpdatedBy()).isEqualTo(updatedBy);
        assertThat(basicbaserfidfactoryparameteraddvo.getCreateTime()).isEqualTo(createTime);
        assertThat(basicbaserfidfactoryparameteraddvo.getUpdateTime()).isEqualTo(updateTime);
        assertThat(basicbaserfidfactoryparameteraddvo.getDeleteTime()).isEqualTo(deleteTime);
    }

    @Test
    void testToString() {
        // Given
        basicbaserfidfactoryparameteraddvo.setId(1L);
        basicbaserfidfactoryparameteraddvo.setRfidfactoryId(2L);
        basicbaserfidfactoryparameteraddvo.setType("QR Code");
        basicbaserfidfactoryparameteraddvo.setParameterName("Parameter Name");
        basicbaserfidfactoryparameteraddvo.setParameterContent("Parameter Content");

        // When
        String result = basicbaserfidfactoryparameteraddvo.toString();

        // Then
        assertThat(result).contains("1", "2", "QR Code", "Parameter Name", "Parameter Content");
    }

    // Add more test methods for other methods in BasicBaseRfidfactoryParameterAddVO
}
