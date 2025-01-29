package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBaseDeviceRule;
import com.matariky.commonservice.base.bean.BasicBaseDeviceRuleDetail;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceRuleDetailMapper;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceRuleMapper;
import com.matariky.commonservice.base.vo.BasicBaseDeviceRuleDetailInfo;
import com.matariky.commonservice.base.vo.BasicBaseDeviceRuleDetailListVO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceRuleDetailUpdateByIdVO;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.exception.QslException;
import com.matariky.utils.TokenUtils;
import org.mockito.Mock;
import javax.servlet.http.HttpServletRequest;

@SpringBootTest
public class BasicBaseDeviceRuleDetailServiceTest {

    @InjectMocks
    private BasicBaseDeviceRuleDetailService basicbasedeviceruledetailservice;

    @Mock
    private BasicBaseDeviceRuleDetailMapper basicBaseDeviceruleDetailMapper;

    @Mock
    private HttpServletRequest request;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private CommonDictTypeService commonDictTypeService;

    @Mock
    private BasicBaseDeviceRuleMapper basicBaseDeviceruleMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseDeviceruleDetailAll() {
        // Given
        BasicBaseDeviceRuleDetailListVO vo = new BasicBaseDeviceRuleDetailListVO();
        String jwt = "someJwtToken";
        when(request.getHeader("id")).thenReturn("someId");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(TokenUtils.extractOrgCode(request)).thenReturn("orgCode");
        when(commonDictTypeService.getDictTypeByKey(anyString(), anyString())).thenReturn(new CommonDictType());
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), anyString(), anyLong()))
                .thenReturn(new CommonDict());
        when(basicBaseDeviceruleMapper.selectOne(any())).thenReturn(new BasicBaseDeviceRule());

        // When
        BasicBaseDeviceRuleDetailInfo result = basicbasedeviceruledetailservice.getBasicBaseDeviceruleDetailAll(vo,
                jwt);

        // Then
        assertNotNull(result);
    }

    @Test
    void testUpdateBasicBaseDeviceruleDetail() {
        // Given
        BasicBaseDeviceRuleDetailUpdateByIdVO updateVO = new BasicBaseDeviceRuleDetailUpdateByIdVO();
        updateVO.setId(1L);
        String jwt = "someJwtToken";
        BasicBaseDeviceRuleDetail ruleDetail = new BasicBaseDeviceRuleDetail();
        when(basicBaseDeviceruleDetailMapper.selectById(updateVO.getId())).thenReturn(ruleDetail);
        when(basicBaseDeviceruleDetailMapper.selectCount(any())).thenReturn(0L);
        when(basicBaseDeviceruleDetailMapper.updateById(any())).thenReturn(1);

        // When
        int result = basicbasedeviceruledetailservice.updateBasicBaseDeviceruleDetail(updateVO, jwt);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseDeviceruleDetailById() {
        // Given
        Long id = 1L;
        when(basicBaseDeviceruleDetailMapper.delBasicBaseDeviceruleDetailById(id)).thenReturn(1);

        // When
        int result = basicbasedeviceruledetailservice.delBasicBaseDeviceruleDetailById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseDeviceruleDetailThrowsException() {
        // Given
        BasicBaseDeviceRuleDetailUpdateByIdVO updateVO = new BasicBaseDeviceRuleDetailUpdateByIdVO();
        updateVO.setId(1L);
        String jwt = "someJwtToken";
        when(basicBaseDeviceruleDetailMapper.selectById(updateVO.getId())).thenReturn(null);

        // When & Then
        assertThrows(QslException.class, () -> {
            basicbasedeviceruledetailservice.updateBasicBaseDeviceruleDetail(updateVO, jwt);
        });
    }
}
