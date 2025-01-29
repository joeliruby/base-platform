package com.matariky.commonservice.datasharing.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.List;
import org.mockito.Mock;
import com.matariky.commonservice.datasharing.bean.CommonSharingPool;
import com.matariky.commonservice.datasharing.mapper.CommonSharingPoolMapper;

@SpringBootTest
public class CommonSharingPoolServiceTest {

    @InjectMocks
    private CommonSharingPoolService commonSharingPoolService;

    @Mock
    private CommonSharingPoolMapper commonSharingPoolMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommonSharingPoolAll() {
        // Given
        List<CommonSharingPool> expectedList = Arrays.asList(new CommonSharingPool(), new CommonSharingPool());
        when(commonSharingPoolMapper.getCommonSharingPoolAll()).thenReturn(expectedList);

        // When
        List<CommonSharingPool> actualList = commonSharingPoolService.getCommonSharingPoolAll();

        // Then
        assertThat(actualList).isEqualTo(expectedList);
    }

    @Test
    void testGetCommonSharingPoolAllCount() {
        // Given
        int expectedCount = 5;
        when(commonSharingPoolMapper.getCommonSharingPoolAllCount()).thenReturn(expectedCount);

        // When
        int actualCount = commonSharingPoolService.getCommonSharingPoolAllCount();

        // Then
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    void testCreateCommonSharingPool() {
        // Given
        CommonSharingPool pool = new CommonSharingPool();
        when(commonSharingPoolMapper.createCommonSharingPool(pool)).thenReturn(1);

        // When
        int result = commonSharingPoolService.createCommonSharingPool(pool);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testUpdateCommonSharingPool() {
        // Given
        CommonSharingPool pool = new CommonSharingPool();
        when(commonSharingPoolMapper.updateCommonSharingPool(pool)).thenReturn(1);

        // When
        int result = commonSharingPoolService.updateCommonSharingPool(pool);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testDelCommonSharingPoolById() {
        // Given
        long id = 1L;
        when(commonSharingPoolMapper.delCommonSharingPoolById(id)).thenReturn(1);

        // When
        int result = commonSharingPoolService.delCommonSharingPoolById(id);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testGetCommonSharingPoolById() {
        // Given
        int id = 1;
        CommonSharingPool expectedPool = new CommonSharingPool();
        when(commonSharingPoolMapper.getCommonSharingPoolById(id)).thenReturn(expectedPool);

        // When
        CommonSharingPool actualPool = commonSharingPoolService.getCommonSharingPoolById(id);

        // Then
        assertThat(actualPool).isEqualTo(expectedPool);
    }

    @Test
    void testSelectByOrgCode() {
        // Given
        String orgCode = "ORG123";
        List<CommonSharingPool> expectedList = Arrays.asList(new CommonSharingPool(), new CommonSharingPool());
        when(commonSharingPoolMapper.selectByOrgCode(orgCode)).thenReturn(expectedList);

        // When
        List<CommonSharingPool> actualList = commonSharingPoolService.selectByOrgCode(orgCode);

        // Then
        assertThat(actualList).isEqualTo(expectedList);
    }

    @Test
    void testGetCommonSharingPoolByResourceId() {
        // Given
        Long resourceId = 1L;
        String tenantId = "TENANT123";
        List<CommonSharingPool> expectedList = Arrays.asList(new CommonSharingPool(), new CommonSharingPool());
        when(commonSharingPoolMapper.getCommonSharingPoolByResourceId(resourceId, tenantId)).thenReturn(expectedList);

        // When
        List<CommonSharingPool> actualList = commonSharingPoolService.getCommonSharingPoolByResourceId(resourceId,
                tenantId);

        // Then
        assertThat(actualList).isEqualTo(expectedList);
    }

    @Test
    void testGetOriginalOwnerOrgCodeList() {
        // Given
        String orgCode = "ORG123";
        String selfOrgCode = "SELF123";
        List<String> expectedList = Arrays.asList("ORG1", "ORG2");
        when(commonSharingPoolMapper.selectOriginalOwnerOrgCodes(orgCode, selfOrgCode)).thenReturn(expectedList);

        // When
        List<String> actualList = commonSharingPoolService.getOriginalOwnerOrgCodeList(orgCode, selfOrgCode);

        // Then
        assertThat(actualList).isEqualTo(expectedList);
    }
}
