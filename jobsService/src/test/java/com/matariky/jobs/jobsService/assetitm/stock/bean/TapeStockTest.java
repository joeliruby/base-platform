package com.matariky.jobs.jobsService.assetitm.stock.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TapeStockTest {

    @InjectMocks
    private TapeStock tapestock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        tapestock.setId(1L);
        tapestock.setOperatorOrgCode("ORG123");
        tapestock.setOperatorSelfOrgCode("SELF123");
        tapestock.setTenantId("TENANT123");
        tapestock.setLocationId("LOC123");
        tapestock.setLibraryId(2L);
        tapestock.setQuantity(100);
        tapestock.setCreatedBy(3L);
        tapestock.setUpdatedBy(4L);
        tapestock.setCreateTime(1627849200000L);
        tapestock.setUpdateTime(1627849200000L);
        tapestock.setDeleteTime(1627849200000L);

        assertEquals(1L, tapestock.getId());
        assertEquals("ORG123", tapestock.getOperatorOrgCode());
        assertEquals("SELF123", tapestock.getOperatorSelfOrgCode());
        assertEquals("TENANT123", tapestock.getTenantId());
        assertEquals("LOC123", tapestock.getLocationId());
        assertEquals(2L, tapestock.getLibraryId());
        assertEquals(100, tapestock.getQuantity());
        assertEquals(3L, tapestock.getCreatedBy());
        assertEquals(4L, tapestock.getUpdatedBy());
        assertEquals(1627849200000L, tapestock.getCreateTime());
        assertEquals(1627849200000L, tapestock.getUpdateTime());
        assertEquals(1627849200000L, tapestock.getDeleteTime());
    }

    // Add more test methods for other methods in TapeStock
}
