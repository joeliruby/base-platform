package com.matariky.commonservice.datasharing.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.matariky.commonservice.datasharing.bean.CommonSharingPool;
import com.matariky.commonservice.datasharing.service.CommonSharingPoolService;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class CommonSharingPoolControllerTest {

    @InjectMocks
    private CommonSharingPoolController commonSharingPoolController;

    @Mock
    private CommonSharingPoolService commonSharingPoolService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        String tenantId = "tenant1";
        int pageIndex = 1;
        int perPage = 10;
        String jwt = "jwtToken";
        Long resourceId = 1L;

        // When
        AjaxResult result = (AjaxResult) commonSharingPoolController.list(null, null, tenantId, pageIndex, perPage, jwt,
                resourceId);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        CommonSharingPool bean = new CommonSharingPool();
        when(commonSharingPoolService.updateCommonSharingPool(bean)).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) commonSharingPoolController.update(bean, null, null);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testDel() {
        // Given
        String id = "1";
        when(commonSharingPoolService.delCommonSharingPoolById(Long.parseLong(id))).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) commonSharingPoolController.del(id, null, null);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testGetOne() {
        // Given
        String organizationCode = "org1";

        // When
        AjaxResult result = (AjaxResult) commonSharingPoolController.getOne(organizationCode, null, null);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testGetAliases() {
        // When
        AjaxResult result = (AjaxResult) commonSharingPoolController.getAliases();

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }
}
