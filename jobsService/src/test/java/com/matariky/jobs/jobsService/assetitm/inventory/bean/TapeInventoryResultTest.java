package com.matariky.jobs.jobsService.assetitm.inventory.bean;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TapeInventoryResultTest {

    @InjectMocks
    private TapeInventoryResult tapeInventoryResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetId() {
        tapeInventoryResult.setId(1L);
        assertThat(tapeInventoryResult.getId()).isEqualTo(1L);
    }

    @Test
    void testSetAndGetTaskId() {
        tapeInventoryResult.setTaskId(2L);
        assertThat(tapeInventoryResult.getTaskId()).isEqualTo(2L);
    }

    @Test
    void testSetAndGetSubtaskId() {
        tapeInventoryResult.setSubtaskId(3L);
        assertThat(tapeInventoryResult.getSubtaskId()).isEqualTo(3L);
    }

    @Test
    void testSetAndGetEpc() {
        tapeInventoryResult.setEpc("epc123");
        assertThat(tapeInventoryResult.getEpc()).isEqualTo("epc123");
    }

    @Test
    void testSetAndGetTid() {
        tapeInventoryResult.setTid("tid123");
        assertThat(tapeInventoryResult.getTid()).isEqualTo("tid123");
    }

    @Test
    void testSetAndGetLabelId() {
        tapeInventoryResult.setLabelId(4L);
        assertThat(tapeInventoryResult.getLabelId()).isEqualTo(4L);
    }

    @Test
    void testSetAndGetLocationId() {
        tapeInventoryResult.setLocationId("loc123");
        assertThat(tapeInventoryResult.getLocationId()).isEqualTo("loc123");
    }

    @Test
    void testSetAndGetLibraryId() {
        tapeInventoryResult.setLibraryId(5L);
        assertThat(tapeInventoryResult.getLibraryId()).isEqualTo(5L);
    }

    @Test
    void testSetAndGetReaderCode() {
        tapeInventoryResult.setReaderCode("reader123");
        assertThat(tapeInventoryResult.getReaderCode()).isEqualTo("reader123");
    }

    @Test
    void testSetAndGetFailReason() {
        tapeInventoryResult.setFailReason("failReason");
        assertThat(tapeInventoryResult.getFailReason()).isEqualTo("failReason");
    }

    @Test
    void testSetAndGetResult() {
        tapeInventoryResult.setResult(1);
        assertThat(tapeInventoryResult.getResult()).isEqualTo(1);
    }

    @Test
    void testSetAndGetFilePaths() {
        tapeInventoryResult.setFilePaths(Arrays.asList("path1", "path2"));
        assertThat(tapeInventoryResult.getFilePaths()).containsExactly("path1", "path2");
    }

    @Test
    void testSetAndGetIsValid() {
        tapeInventoryResult.setIsValid(true);
        assertThat(tapeInventoryResult.getIsValid()).isTrue();
    }

    @Test
    void testSetAndGetCreatedBy() {
        tapeInventoryResult.setCreatedBy(6L);
        assertThat(tapeInventoryResult.getCreatedBy()).isEqualTo(6L);
    }

    @Test
    void testSetAndGetUpdatedBy() {
        tapeInventoryResult.setUpdatedBy(7L);
        assertThat(tapeInventoryResult.getUpdatedBy()).isEqualTo(7L);
    }

    @Test
    void testSetAndGetCreateTime() {
        tapeInventoryResult.setCreateTime(8L);
        assertThat(tapeInventoryResult.getCreateTime()).isEqualTo(8L);
    }

    @Test
    void testSetAndGetUpdateTime() {
        tapeInventoryResult.setUpdateTime(9L);
        assertThat(tapeInventoryResult.getUpdateTime()).isEqualTo(9L);
    }

    @Test
    void testSetAndGetDeleteTime() {
        tapeInventoryResult.setDeleteTime(10L);
        assertThat(tapeInventoryResult.getDeleteTime()).isEqualTo(10L);
    }
}
