package com.matariky.jobs.jobsService.assetitm.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TapeRfidPrintParameterTaskTest {

    @InjectMocks
    private TapeRfidPrintParameterTask taperfidprintparametertask;

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
        String remark = "remark";
        Long createTime = 123456789L;
        Long updateTime = 987654321L;
        Long deleteTime = 111111111L;
        Long createBy = 222222222L;
        Long updateBy = 333333333L;
        String operatorOrgCode = "operatorOrgCode";
        String operatorSelfOrgCode = "operatorSelfOrgCode";

        // When
        taperfidprintparametertask.setId(id);
        taperfidprintparametertask.setPrintId(printId);
        taperfidprintparametertask.setType(type);
        taperfidprintparametertask.setParameterName(parameterName);
        taperfidprintparametertask.setParameterContent(parameterContent);
        taperfidprintparametertask.setRemark(remark);
        taperfidprintparametertask.setCreateTime(createTime);
        taperfidprintparametertask.setUpdateTime(updateTime);
        taperfidprintparametertask.setDeleteTime(deleteTime);
        taperfidprintparametertask.setCreateBy(createBy);
        taperfidprintparametertask.setUpdateBy(updateBy);
        taperfidprintparametertask.setOperatorOrgCode(operatorOrgCode);
        taperfidprintparametertask.setOperatorSelfOrgCode(operatorSelfOrgCode);

        // Then
        assertThat(taperfidprintparametertask.getId()).isEqualTo(id);
        assertThat(taperfidprintparametertask.getPrintId()).isEqualTo(printId);
        assertThat(taperfidprintparametertask.getType()).isEqualTo(type);
        assertThat(taperfidprintparametertask.getParameterName()).isEqualTo(parameterName);
        assertThat(taperfidprintparametertask.getParameterContent()).isEqualTo(parameterContent);
        assertThat(taperfidprintparametertask.getRemark()).isEqualTo(remark);
        assertThat(taperfidprintparametertask.getCreateTime()).isEqualTo(createTime);
        assertThat(taperfidprintparametertask.getUpdateTime()).isEqualTo(updateTime);
        assertThat(taperfidprintparametertask.getDeleteTime()).isEqualTo(deleteTime);
        assertThat(taperfidprintparametertask.getCreateBy()).isEqualTo(createBy);
        assertThat(taperfidprintparametertask.getUpdateBy()).isEqualTo(updateBy);
        assertThat(taperfidprintparametertask.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
        assertThat(taperfidprintparametertask.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
    }

    @Test
    void testToString() {
        // Given
        taperfidprintparametertask.setId(1L);
        taperfidprintparametertask.setPrintId(2L);
        taperfidprintparametertask.setType("type");
        taperfidprintparametertask.setParameterName("parameterName");
        taperfidprintparametertask.setParameterContent("parameterContent");
        taperfidprintparametertask.setRemark("remark");
        taperfidprintparametertask.setCreateTime(123456789L);
        taperfidprintparametertask.setUpdateTime(987654321L);
        taperfidprintparametertask.setDeleteTime(111111111L);
        taperfidprintparametertask.setCreateBy(222222222L);
        taperfidprintparametertask.setUpdateBy(333333333L);
        taperfidprintparametertask.setOperatorOrgCode("operatorOrgCode");
        taperfidprintparametertask.setOperatorSelfOrgCode("operatorSelfOrgCode");

        // When
        String result = taperfidprintparametertask.toString();

        // Then
        assertThat(result).contains("TapeRfidPrintParameterTask");
        assertThat(result).contains("id=1");
        assertThat(result).contains("printId=2");
        assertThat(result).contains("type=type");
        assertThat(result).contains("parameterName=parameterName");
        assertThat(result).contains("parameterContent=parameterContent");
        assertThat(result).contains("remark=remark");
        assertThat(result).contains("createTime=123456789");
        assertThat(result).contains("updateTime=987654321");
        assertThat(result).contains("deleteTime=111111111");
        assertThat(result).contains("createBy=222222222");
        assertThat(result).contains("updateBy=333333333");
        assertThat(result).contains("operatorOrgCode=operatorOrgCode");
        assertThat(result).contains("operatorSelfOrgCode=operatorSelfOrgCode");
    }

    // Add more test methods for other methods in TapeRfidPrintParameterTask
}
