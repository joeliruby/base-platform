package com.matariky.jobs.jobsService.assetitm.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TapeLabelTest {

    @InjectMocks
    private TapeLabel tapelabel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String tenantId = "tenantId";
        String operatorOrgCode = "operatorOrgCode";
        String operatorSelfOrgCode = "operatorSelfOrgCode";
        String barcode = "barcode";
        String epc = "epc";
        String tid = "tid";
        Long createdBy = 2L;
        Long updatedBy = 3L;
        Long createTime = 4L;
        Long updateTime = 5L;
        Long deleteTime = 6L;

        // When
        tapelabel.setId(id);
        tapelabel.setTenantId(tenantId);
        tapelabel.setOperatorOrgCode(operatorOrgCode);
        tapelabel.setOperatorSelfOrgCode(operatorSelfOrgCode);
        tapelabel.setBarcode(barcode);
        tapelabel.setEpc(epc);
        tapelabel.setTid(tid);
        tapelabel.setCreatedBy(createdBy);
        tapelabel.setUpdatedBy(updatedBy);
        tapelabel.setCreateTime(createTime);
        tapelabel.setUpdateTime(updateTime);
        tapelabel.setDeleteTime(deleteTime);

        // Then
        assertThat(tapelabel.getId()).isEqualTo(id);
        assertThat(tapelabel.getTenantId()).isEqualTo(tenantId);
        assertThat(tapelabel.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
        assertThat(tapelabel.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
        assertThat(tapelabel.getBarcode()).isEqualTo(barcode);
        assertThat(tapelabel.getEpc()).isEqualTo(epc);
        assertThat(tapelabel.getTid()).isEqualTo(tid);
        assertThat(tapelabel.getCreatedBy()).isEqualTo(createdBy);
        assertThat(tapelabel.getUpdatedBy()).isEqualTo(updatedBy);
        assertThat(tapelabel.getCreateTime()).isEqualTo(createTime);
        assertThat(tapelabel.getUpdateTime()).isEqualTo(updateTime);
        assertThat(tapelabel.getDeleteTime()).isEqualTo(deleteTime);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        TapeLabel tapeLabel1 = new TapeLabel();
        tapeLabel1.setId(1L);
        TapeLabel tapeLabel2 = new TapeLabel();
        tapeLabel2.setId(1L);

        // When & Then
        assertThat(tapeLabel1).isEqualTo(tapeLabel2);
        assertThat(tapeLabel1.hashCode()).isEqualTo(tapeLabel2.hashCode());

        tapeLabel2.setId(2L);
        assertThat(tapeLabel1).isNotEqualTo(tapeLabel2);
        assertThat(tapeLabel1.hashCode()).isNotEqualTo(tapeLabel2.hashCode());
    }

    @Test
    void testToString() {
        // Given
        tapelabel.setId(1L);
        tapelabel.setTenantId("tenantId");

        // When
        String toString = tapelabel.toString();

        // Then
        assertThat(toString).contains("id=1");
        assertThat(toString).contains("tenantId=tenantId");
    }
}
