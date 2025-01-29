package com.matariky.commonservice.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDevicecommandTest {

    @InjectMocks
    private BasicBaseDevicecommand basicbasedevicecommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String commandName = "Test Command";
        String protocolType = "HTTP";
        String commandContent = "Content";
        String remark = "Remark";
        Long createTime = System.currentTimeMillis();
        Long updateTime = System.currentTimeMillis();
        Long deleteTime = System.currentTimeMillis();
        Long createBy = 1L;
        Long updateBy = 1L;
        String operatorOrgCode = "OrgCode";
        String operatorSelfOrgCode = "SelfOrgCode";
        String tenantId = "TenantId";
        String md5 = "md5hash";

        // When
        basicbasedevicecommand.setId(id);
        basicbasedevicecommand.setCommandName(commandName);
        basicbasedevicecommand.setProtocolType(protocolType);
        basicbasedevicecommand.setCommandContent(commandContent);
        basicbasedevicecommand.setRemark(remark);
        basicbasedevicecommand.setCreateTime(createTime);
        basicbasedevicecommand.setUpdateTime(updateTime);
        basicbasedevicecommand.setDeleteTime(deleteTime);
        basicbasedevicecommand.setCreateBy(createBy);
        basicbasedevicecommand.setUpdateBy(updateBy);
        basicbasedevicecommand.setOperatorOrgCode(operatorOrgCode);
        basicbasedevicecommand.setOperatorSelfOrgCode(operatorSelfOrgCode);
        basicbasedevicecommand.setTenantId(tenantId);
        basicbasedevicecommand.setMd5(md5);

        // Then
        assertThat(basicbasedevicecommand.getId()).isEqualTo(id);
        assertThat(basicbasedevicecommand.getCommandName()).isEqualTo(commandName);
        assertThat(basicbasedevicecommand.getProtocolType()).isEqualTo(protocolType);
        assertThat(basicbasedevicecommand.getCommandContent()).isEqualTo(commandContent);
        assertThat(basicbasedevicecommand.getRemark()).isEqualTo(remark);
        assertThat(basicbasedevicecommand.getCreateTime()).isEqualTo(createTime);
        assertThat(basicbasedevicecommand.getUpdateTime()).isEqualTo(updateTime);
        assertThat(basicbasedevicecommand.getDeleteTime()).isEqualTo(deleteTime);
        assertThat(basicbasedevicecommand.getCreateBy()).isEqualTo(createBy);
        assertThat(basicbasedevicecommand.getUpdateBy()).isEqualTo(updateBy);
        assertThat(basicbasedevicecommand.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
        assertThat(basicbasedevicecommand.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
        assertThat(basicbasedevicecommand.getTenantId()).isEqualTo(tenantId);
        assertThat(basicbasedevicecommand.getMd5()).isEqualTo(md5);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        BasicBaseDevicecommand anotherCommand = new BasicBaseDevicecommand();
        anotherCommand.setId(1L);

        basicbasedevicecommand.setId(1L);

        // When & Then
        assertThat(basicbasedevicecommand).isEqualTo(anotherCommand);
        assertThat(basicbasedevicecommand.hashCode()).isEqualTo(anotherCommand.hashCode());
    }

    @Test
    void testToString() {
        // Given
        basicbasedevicecommand.setId(1L);
        basicbasedevicecommand.setCommandName("Test Command");

        // When
        String toString = basicbasedevicecommand.toString();

        // Then
        assertThat(toString).contains("id=1");
        assertThat(toString).contains("commandName=Test Command");
    }

    // Add more test methods for other methods in BasicBaseDevicecommand
}
