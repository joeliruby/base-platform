package com.matariky.jobs.jobsService.assetitm.inventory.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TapeInventorySubtaskTest {

    @InjectMocks
    private TapeInventorySubtask tapeInventorySubtask;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        tapeInventorySubtask.setId(1L);

        // When
        Long id = tapeInventorySubtask.getId();

        // Then
        assertEquals(1L, id);
    }

    @Test
    void testSetId() {
        // Given
        Long id = 2L;

        // When
        tapeInventorySubtask.setId(id);

        // Then
        assertEquals(id, tapeInventorySubtask.getId());
    }

    @Test
    void testGetTaskId() {
        // Given
        tapeInventorySubtask.setTaskId(10L);

        // When
        Long taskId = tapeInventorySubtask.getTaskId();

        // Then
        assertEquals(10L, taskId);
    }

    @Test
    void testSetTaskId() {
        // Given
        Long taskId = 20L;

        // When
        tapeInventorySubtask.setTaskId(taskId);

        // Then
        assertEquals(taskId, tapeInventorySubtask.getTaskId());
    }

    @Test
    void testGetQuantity() {
        // Given
        tapeInventorySubtask.setQuantity(100);

        // When
        Integer quantity = tapeInventorySubtask.getQuantity();

        // Then
        assertEquals(100, quantity);
    }

    @Test
    void testSetQuantity() {
        // Given
        Integer quantity = 200;

        // When
        tapeInventorySubtask.setQuantity(quantity);

        // Then
        assertEquals(quantity, tapeInventorySubtask.getQuantity());
    }

    @Test
    void testGetActualQuantity() {
        // Given
        tapeInventorySubtask.setActualQuantity(150);

        // When
        Integer actualQuantity = tapeInventorySubtask.getActualQuantity();

        // Then
        assertEquals(150, actualQuantity);
    }

    @Test
    void testSetActualQuantity() {
        // Given
        Integer actualQuantity = 250;

        // When
        tapeInventorySubtask.setActualQuantity(actualQuantity);

        // Then
        assertEquals(actualQuantity, tapeInventorySubtask.getActualQuantity());
    }

    @Test
    void testIsDiscrepancy() {
        // Given
        tapeInventorySubtask.setIsDiscrepancy(true);

        // When
        Boolean isDiscrepancy = tapeInventorySubtask.getIsDiscrepancy();

        // Then
        assertTrue(isDiscrepancy);
    }

    @Test
    void testSetIsDiscrepancy() {
        // Given
        Boolean isDiscrepancy = false;

        // When
        tapeInventorySubtask.setIsDiscrepancy(isDiscrepancy);

        // Then
        assertFalse(tapeInventorySubtask.getIsDiscrepancy());
    }

    @Test
    void testGetStatus() {
        // Given
        tapeInventorySubtask.setStatus(1);

        // When
        Integer status = tapeInventorySubtask.getStatus();

        // Then
        assertEquals(1, status);
    }

    @Test
    void testSetStatus() {
        // Given
        Integer status = 2;

        // When
        tapeInventorySubtask.setStatus(status);

        // Then
        assertEquals(status, tapeInventorySubtask.getStatus());
    }

    @Test
    void testGetRemark() {
        // Given
        tapeInventorySubtask.setRemark("Test Remark");

        // When
        String remark = tapeInventorySubtask.getRemark();

        // Then
        assertEquals("Test Remark", remark);
    }

    @Test
    void testSetRemark() {
        // Given
        String remark = "New Remark";

        // When
        tapeInventorySubtask.setRemark(remark);

        // Then
        assertEquals(remark, tapeInventorySubtask.getRemark());
    }

    @Test
    void testGetCreatedBy() {
        // Given
        tapeInventorySubtask.setCreatedBy(1L);

        // When
        Long createdBy = tapeInventorySubtask.getCreatedBy();

        // Then
        assertEquals(1L, createdBy);
    }

    @Test
    void testSetCreatedBy() {
        // Given
        Long createdBy = 2L;

        // When
        tapeInventorySubtask.setCreatedBy(createdBy);

        // Then
        assertEquals(createdBy, tapeInventorySubtask.getCreatedBy());
    }

    @Test
    void testGetUpdatedBy() {
        // Given
        tapeInventorySubtask.setUpdatedBy(1L);

        // When
        Long updatedBy = tapeInventorySubtask.getUpdatedBy();

        // Then
        assertEquals(1L, updatedBy);
    }

    @Test
    void testSetUpdatedBy() {
        // Given
        Long updatedBy = 2L;

        // When
        tapeInventorySubtask.setUpdatedBy(updatedBy);

        // Then
        assertEquals(updatedBy, tapeInventorySubtask.getUpdatedBy());
    }

    @Test
    void testGetCreateTime() {
        // Given
        tapeInventorySubtask.setCreateTime(1000L);

        // When
        Long createTime = tapeInventorySubtask.getCreateTime();

        // Then
        assertEquals(1000L, createTime);
    }

    @Test
    void testSetCreateTime() {
        // Given
        Long createTime = 2000L;

        // When
        tapeInventorySubtask.setCreateTime(createTime);

        // Then
        assertEquals(createTime, tapeInventorySubtask.getCreateTime());
    }

    @Test
    void testGetUpdateTime() {
        // Given
        tapeInventorySubtask.setUpdateTime(3000L);

        // When
        Long updateTime = tapeInventorySubtask.getUpdateTime();

        // Then
        assertEquals(3000L, updateTime);
    }

    @Test
    void testSetUpdateTime() {
        // Given
        Long updateTime = 4000L;

        // When
        tapeInventorySubtask.setUpdateTime(updateTime);

        // Then
        assertEquals(updateTime, tapeInventorySubtask.getUpdateTime());
    }

    @Test
    void testGetDeleteTime() {
        // Given
        tapeInventorySubtask.setDeleteTime(5000L);

        // When
        Long deleteTime = tapeInventorySubtask.getDeleteTime();

        // Then
        assertEquals(5000L, deleteTime);
    }

    @Test
    void testSetDeleteTime() {
        // Given
        Long deleteTime = 6000L;

        // When
        tapeInventorySubtask.setDeleteTime(deleteTime);

        // Then
        assertEquals(deleteTime, tapeInventorySubtask.getDeleteTime());
    }
}
