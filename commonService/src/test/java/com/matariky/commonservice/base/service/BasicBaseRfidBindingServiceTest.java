package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.base.bean.BasicBaseRfidBinding;
import com.matariky.commonservice.base.bean.BasicBaseRfidInfo;
import com.matariky.commonservice.base.mapper.BasicBaseRfidBindingMapper;
import com.matariky.commonservice.base.mapper.BasicBaseRfidInfoMapper;
import com.matariky.commonservice.base.vo.BasicBaseRfidBindingAddVO;
import com.matariky.commonservice.base.vo.BasicBaseRfidBindingInfoVO;
import com.matariky.commonservice.base.vo.BasicBaseRfidBindingListVO;
import com.matariky.commonservice.base.vo.BasicBaseRfidBindingUpdateVO;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class BasicBaseRfidBindingServiceTest {

    @InjectMocks
    private BasicBaseRfidBindingService basicBaseRfidBindingService;

    @Mock
    private BasicBaseRfidBindingMapper basicBaseRfidBindingMapper;

    @Mock
    private BasicBaseRfidInfoMapper basicBaseRfidInfoMapper;

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
    void testGetBasicBaseRfidBindingAll() {
        // Given
        BasicBaseRfidBindingListVO vo = new BasicBaseRfidBindingListVO();
        when(request.getHeader("id")).thenReturn("12345");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(TokenUtils.extractOrgCode(request)).thenReturn("orgCode");
        CommonDictType commonDictType = new CommonDictType();
        when(commonDictTypeService.getDictTypeByKey(anyString(), anyString())).thenReturn(commonDictType);
        CommonDict dict = new CommonDict();
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), anyString(), anyLong()))
                .thenReturn(dict);
        when(basicBaseRfidBindingMapper.getBasicBaseRfidBindingAll(any())).thenReturn(Collections.emptyList());

        // When
        List<BasicBaseRfidBindingInfoVO> result = basicBaseRfidBindingService.getBasicBaseRfidBindingAll(vo);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateBasicBaseRfidBindingWithOrg() {
        // Given
        BasicBaseRfidBindingAddVO addVO = new BasicBaseRfidBindingAddVO();
        addVO.setEpc("epc");
        addVO.setTid("tid");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        BasicBaseRfidInfo rfidInfo = new BasicBaseRfidInfo();
        when(basicBaseRfidInfoMapper.selectOne(any())).thenReturn(rfidInfo);
        when(basicBaseRfidBindingMapper.selectCount(any())).thenReturn(0L);
        when(TokenUtils.extractUserIdFromHttpReqeust(request)).thenReturn("1");
        when(TokenUtils.extractOrgCode(request)).thenReturn("orgCode");
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(basicBaseRfidBindingMapper.createBasicBaseRfidBinding(any())).thenReturn(1);

        // When
        int result = basicBaseRfidBindingService.createBasicBaseRfidBindingWithOrg(addVO);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseRfidBinding() {
        // Given
        BasicBaseRfidBindingUpdateVO updateVO = new BasicBaseRfidBindingUpdateVO();
        updateVO.setRfidId(1L);
        updateVO.setId(1L);
        when(basicBaseRfidBindingMapper.selectCount(any())).thenReturn(0L);
        when(TokenUtils.extractUserIdFromHttpReqeust(request)).thenReturn("1");
        when(basicBaseRfidBindingMapper.updateById(any())).thenReturn(1);

        // When
        int result = basicBaseRfidBindingService.updateBasicBaseRfidBinding(updateVO);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDeleteById() {
        // Given
        Long id = 1L;
        when(basicBaseRfidBindingMapper.update(any(), any())).thenReturn(1);

        // When
        Integer result = basicBaseRfidBindingService.deleteById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetRfidCode() {
        // Given
        BasicBaseRfidBinding binding = new BasicBaseRfidBinding();
        binding.setTagCode(1L);
        when(basicBaseRfidBindingMapper.selectOne(any())).thenReturn(binding);

        // When
        Long result = basicBaseRfidBindingService.getRfidCode();

        // Then
        assertEquals(2L, result);
    }
}
