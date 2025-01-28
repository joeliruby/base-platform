package com.matariky.jobs.jobsService.assetitm.inout.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TapeInoutTest {

    @InjectMocks
    private TapeInout tapeinout;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String operatorOrgCode = "ORG123";
        String operatorSelfOrgCode = "SELF123";
        String tenantId = "TENANT123";
        String serialNo = "SERIAL123";
        Long locationId = 2L;
        Long libraryId = 3L;
        Integer quantity = 10;
        Integer beforeQuantity = 5;
        Integer afterQuantity = 15;
        Integer type = 1;
        Integer status = 0;
        Integer application = 2;
        String operationIp = "192.168.1.1";
        String remark = "Test remark";
        Long createdBy = 4L;
        Long updatedBy = 5L;
        Long createTime = System.currentTimeMillis();
        Long updateTime = System.currentTimeMillis();
        Long deleteTime = System.currentTimeMillis();

        // When
        tapeinout.setId(id);
        tapeinout.setOperatorOrgCode(operatorOrgCode);
        tapeinout.setOperatorSelfOrgCode(operatorSelfOrgCode);
        tapeinout.setTenantId(tenantId);
        tapeinout.setSerialNo(serialNo);
        tapeinout.setLocationId(locationId);
        tapeinout.setLibraryId(libraryId);
        tapeinout.setQuantity(quantity);
        tapeinout.setBeforeQuantity(beforeQuantity);
        tapeinout.setAfterQuantity(afterQuantity);
        tapeinout.setType(type);
        tapeinout.setStatus(status);
        tapeinout.setApplication(application);
        tapeinout.setOperationIp(operationIp);
        tapeinout.setRemark(remark);
        tapeinout.setCreatedBy(createdBy);
        tapeinout.setUpdatedBy(updatedBy);
        tapeinout.setCreateTime(createTime);
        tapeinout.setUpdateTime(updateTime);
        tapeinout.setDeleteTime(deleteTime);

        // Then
        assertEquals(id, tapeinout.getId());
        assertEquals(operatorOrgCode, tapeinout.getOperatorOrgCode());
        assertEquals(operatorSelfOrgCode, tapeinout.getOperatorSelfOrgCode());
        assertEquals(tenantId, tapeinout.getTenantId());
        assertEquals(serialNo, tapeinout.getSerialNo());
        assertEquals(locationId, tapeinout.getLocationId());
        assertEquals(libraryId, tapeinout.getLibraryId());
        assertEquals(quantity, tapeinout.getQuantity());
        assertEquals(beforeQuantity, tapeinout.getBeforeQuantity());
        assertEquals(afterQuantity, tapeinout.getAfterQuantity());
        assertEquals(type, tapeinout.getType());
        assertEquals(status, tapeinout.getStatus());
        assertEquals(application, tapeinout.getApplication());
        assertEquals(operationIp, tapeinout.getOperationIp());
        assertEquals(remark, tapeinout.getRemark());
        assertEquals(createdBy, tapeinout.getCreatedBy());
        assertEquals(updatedBy, tapeinout.getUpdatedBy());
        assertEquals(createTime, tapeinout.getCreateTime());
        assertEquals(updateTime, tapeinout.getUpdateTime());
        assertEquals(deleteTime, tapeinout.getDeleteTime());
    }

    @Test
    void testToString() {
        // Given
        tapeinout.setId(1L);
        tapeinout.setOperatorOrgCode("ORG123");
        tapeinout.setOperatorSelfOrgCode("SELF123");
        tapeinout.setTenantId("TENANT123");
        tapeinout.setSerialNo("SERIAL123");
        tapeinout.setLocationId(2L);
        tapeinout.setLibraryId(3L);
        tapeinout.setQuantity(10);
        tapeinout.setBeforeQuantity(5);
        tapeinout.setAfterQuantity(15);
        tapeinout.setType(1);
        tapeinout.setStatus(0);
        tapeinout.setApplication(2);
        tapeinout.setOperationIp("192.168.1.1");
        tapeinout.setRemark("Test remark");
        tapeinout.setCreatedBy(4L);
        tapeinout.setUpdatedBy(5L);
        tapeinout.setCreateTime(System.currentTimeMillis());
        tapeinout.setUpdateTime(System.currentTimeMillis());
        tapeinout.setDeleteTime(System.currentTimeMillis());

        // When
        String result = tapeinout.toString();

        // Then
        assertNotNull(result);
        assertTrue(result.contains("TapeInout"));
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("operatorOrgCode=ORG123"));
        assertTrue(result.contains("operatorSelfOrgCode=SELF123"));
        assertTrue(result.contains("tenantId=TENANT123"));
        assertTrue(result.contains("serialNo=SERIAL123"));
        assertTrue(result.contains("locationId=2"));
        assertTrue(result.contains("libraryId=3"));
        assertTrue(result.contains("quantity=10"));
        assertTrue(result.contains("beforeQuantity=5"));
        assertTrue(result.contains("afterQuantity=15"));
        assertTrue(result.contains("type=1"));
        assertTrue(result.contains("status=0"));
        assertTrue(result.contains("application=2"));
        assertTrue(result.contains("operationIp=192.168.1.1"));
        assertTrue(result.contains("remark=Test remark"));
        assertTrue(result.contains("createdBy=4"));
        assertTrue(result.contains("updatedBy=5"));
    }
}
