package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBaseAntifakeDetail;
import com.matariky.commonservice.base.mapper.BasicBaseAntifakeDetailMapper;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.utils.TokenUtils;
import org.mockito.Mock;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SpringBootTest
public class BasicBaseAntifakeDetailServiceTest {

    @InjectMocks
    private BasicBaseAntifakeDetailService basicBaseAntifakeDetailService;

    @Mock
    private BasicBaseAntifakeDetailMapper basicBaseAntifakeDetailMapper;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private CommonDictTypeService commonDictTypeService;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseAntifakeDetailAll() {
        // Given
        BasicBaseAntifakeDetail bean = new BasicBaseAntifakeDetail();
        Integer pageIndex = 1;
        Integer perPage = 10;
        String hid = "someHeaderId";
        String tenantId = "someTenantId";
        CommonDictType commonDictType = new CommonDictType();
        commonDictType.setId(1L);
        CommonDict dict = new CommonDict();
        dict.setDictValue("someDictValue");

        when(request.getHeader("id")).thenReturn(hid);
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn(tenantId);
        when(commonDictTypeService.getDictTypeByKey(tenantId, "DATA_ACCESS_PERMISSION")).thenReturn(commonDictType);
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), eq(tenantId), eq(1L)))
                .thenReturn(dict);
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(TokenUtils.extractOrgCode(request)).thenReturn("orgCode");

        // When
        List<BasicBaseAntifakeDetail> result = basicBaseAntifakeDetailService.getBasicBaseAntifakeDetailAll(bean,
                pageIndex, perPage);

        // Then
        assertNotNull(result);
        verify(basicBaseAntifakeDetailMapper).getBasicBaseAntifakeDetailAll(bean);
    }

    @Test
    void testCreateBasicBaseAntifakeDetailWithOrg() {
        // Given
        BasicBaseAntifakeDetail bean = new BasicBaseAntifakeDetail();
        when(TokenUtils.extractOrgCode(request)).thenReturn("orgCode");
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(basicBaseAntifakeDetailMapper.createBasicBaseAntifakeDetail(bean)).thenReturn(1);

        // When
        int result = basicBaseAntifakeDetailService.createBasicBaseAntifakeDetailWithOrg(bean);

        // Then
        assertEquals(1, result);
        verify(basicBaseAntifakeDetailMapper).createBasicBaseAntifakeDetail(bean);
    }

    @Test
    void testUpdateBasicBaseAntifakeDetail() {
        // Given
        BasicBaseAntifakeDetail bean = new BasicBaseAntifakeDetail();
        when(basicBaseAntifakeDetailMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicBaseAntifakeDetailService.updateBasicBaseAntifakeDetail(bean);

        // Then
        assertEquals(1, result);
        verify(basicBaseAntifakeDetailMapper).updateById(bean);
    }

    @Test
    void testDelBasicBaseAntifakeDetailById() {
        // Given
        Long id = 1L;
        when(basicBaseAntifakeDetailMapper.delBasicBaseAntifakeDetailById(id)).thenReturn(1);

        // When
        int result = basicBaseAntifakeDetailService.delBasicBaseAntifakeDetailById(id);

        // Then
        assertEquals(1, result);
        verify(basicBaseAntifakeDetailMapper).delBasicBaseAntifakeDetailById(id);
    }

    @Test
    void testGetBasicBaseAntifakeDetailById() {
        // Given
        Long id = 1L;
        BasicBaseAntifakeDetail detail = new BasicBaseAntifakeDetail();
        when(basicBaseAntifakeDetailMapper.getBasicBaseAntifakeDetailById(id)).thenReturn(detail);

        // When
        BasicBaseAntifakeDetail result = basicBaseAntifakeDetailService.getBasicBaseAntifakeDetailById(id);

        // Then
        assertNotNull(result);
        verify(basicBaseAntifakeDetailMapper).getBasicBaseAntifakeDetailById(id);
    }
}
