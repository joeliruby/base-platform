package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.mapper.BasicBasePcVersionMapper;
import com.matariky.commonservice.base.vo.BasicBasePcVersionAddVO;
import com.matariky.commonservice.base.vo.BasicBasePcVersionListVO;
import com.matariky.commonservice.base.vo.BasicBasePcVersionQueryVO;
import com.matariky.commonservice.base.vo.BasicBasePcVersionUpdateVO;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.utils.TokenUtils;
import org.mockito.Mock;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBasePcVersionServiceTest {

    @InjectMocks
    private BasicBasePcVersionService basicbasepcversionservice;

    @Mock
    private BasicBasePcVersionMapper basicBasePcversionMapper;

    @Mock
    private HttpServletRequest request;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private CommonDictTypeService commonDictTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBasePcversionAll() {
        // Given
        BasicBasePcVersionQueryVO vo = new BasicBasePcVersionQueryVO();
        when(request.getHeader("id")).thenReturn("12345");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(TokenUtils.extractOrgCode(request)).thenReturn("orgCode");
        CommonDictType commonDictType = new CommonDictType();
        commonDictType.setId(1L);
        when(commonDictTypeService.getDictTypeByKey(anyString(), anyString())).thenReturn(commonDictType);
        CommonDict dict = new CommonDict();
        dict.setDictValue("dictValue");
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), anyString(), anyLong()))
                .thenReturn(dict);
        when(basicBasePcversionMapper.getBasicBasePcversionAll(any())).thenReturn(Collections.emptyList());

        // When
        List<BasicBasePcVersionListVO> result = basicbasepcversionservice.getBasicBasePcversionAll(vo);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateBasicBasePcversionWithOrg() {
        // Given
        BasicBasePcVersionAddVO addVO = new BasicBasePcVersionAddVO();
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(TokenUtils.extractUserIdFromHttpReqeust(request)).thenReturn("1");
        when(TokenUtils.extractOrgCode(request)).thenReturn("orgCode");
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(basicBasePcversionMapper.createBasicBasePcversion(any())).thenReturn(1);

        // When
        int result = basicbasepcversionservice.createBasicBasePcversionWithOrg(addVO);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBasePcversion() {
        // Given
        BasicBasePcVersionUpdateVO updateVO = new BasicBasePcVersionUpdateVO();
        updateVO.setId(1L);
        when(TokenUtils.extractUserIdFromHttpReqeust(request)).thenReturn("1");
        when(basicBasePcversionMapper.update(any(), any())).thenReturn(1);

        // When
        int result = basicbasepcversionservice.updateBasicBasePcversion(updateVO);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBasePcversionById() {
        // Given
        when(basicBasePcversionMapper.delBasicBasePcversionById(anyLong())).thenReturn(1);

        // When
        int result = basicbasepcversionservice.delBasicBasePcversionById(1L);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBasePcversionByIds() {
        // Given
        List<Long> ids = Collections.singletonList(1L);
        when(basicBasePcversionMapper.update(any(), any())).thenReturn(1);

        // When
        int result = basicbasepcversionservice.delBasicBasePcversionByIds(ids);

        // Then
        assertEquals(1, result);
    }
}
