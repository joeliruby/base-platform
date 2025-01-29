package com.matariky.aop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.commondict.mapper.CommonDictMapper;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.datasharing.service.CommonSharingPoolService;
import com.matariky.constant.PermissionConstant;
import com.matariky.model.QueryDataIsolation;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class DataIsolationAspectTest {

    @InjectMocks
    private DataIsolationAspect dataIsolationAspect;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private CommonDictTypeService commonDictTypeService;

    @Mock
    private CommonSharingPoolService sharingPoolService;

    @Mock
    private CommonDictMapper commonDictMapper;

    @Mock
    private HttpServletRequest httpServletRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDataFilterWithQueryDataIsolation() throws Throwable {
        // Given
        QueryDataIsolation queryDataIsolation = new QueryDataIsolation();
        when(httpServletRequest.getHeader("id")).thenReturn("someId");
        when(TokenUtils.extractTenantIdFromHttpRequest(httpServletRequest)).thenReturn("tenantId");

        // When
        ProceedingJoinPoint proceedingJoinPoint = mock(ProceedingJoinPoint.class);
        when(proceedingJoinPoint.proceed()).thenReturn("proceeding result");
        dataIsolationAspect.dataFilter(proceedingJoinPoint);

        // Then
        assertNotNull(queryDataIsolation.getStrategyCode());
    }

    @Test
    void testGetStrategyCodeWithValidSourcePermission() {
        // Given
        String tenantId = "tenantId";
        org.aspectj.lang.Signature signature = mock(org.aspectj.lang.Signature.class);
        when(httpServletRequest.getHeader("id")).thenReturn("someId");
        when(commonDictMapper.getPermissionId(tenantId, "someId")).thenReturn(1L);

        // When
        String strategyCode = dataIsolationAspect.getStrategyCode(signature, tenantId);

        // Then
        assertEquals("expectedStrategyCode", strategyCode);
    }

    @Test
    void testSetQueryDataIsolationWithPrivateAccess() {
        // Given
        QueryDataIsolation queryDataIsolation = new QueryDataIsolation();
        String orgCode = "orgCode";
        String selfOrgCode = "selfOrgCode";

        // When
        dataIsolationAspect.setQueryDataIsolation(queryDataIsolation, orgCode, selfOrgCode);

        // Then
        assertEquals(PermissionConstant.COMMON_DATA_ACCESS_PRIVATE, queryDataIsolation.getStrategyCode());
    }

    @Test
    void testSetSharingOrgCodesWithNonEmptyOrgCodes() {
        // Given
        QueryDataIsolation queryDataIsolation = new QueryDataIsolation();
        String orgCode = "orgCode";
        String selfOrgCode = "selfOrgCode";
        when(sharingPoolService.getOriginalOwnerOrgCodeList(orgCode, selfOrgCode))
                .thenReturn(Arrays.asList("ind_1", "org_2"));

        // When
        dataIsolationAspect.setSharingOrgCodes(queryDataIsolation, orgCode, selfOrgCode);

        // Then
        assertFalse(queryDataIsolation.getSharingSelfOrgCodes().isEmpty());
        assertFalse(queryDataIsolation.getSharingOrgCodes().isEmpty());
    }

    // Add more test methods for other methods in DataIsolationAspect
}
