package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.bean.BasicBaseGoods;
import com.matariky.commonservice.base.mapper.BasicBaseCreaterfidFactoryMapper;
import com.matariky.commonservice.base.mapper.BasicBaseCreaterfidPrintMapper;
import com.matariky.commonservice.base.mapper.BasicBaseFormExtendMapper;
import com.matariky.commonservice.base.mapper.BasicBaseGoodsMapper;
import com.matariky.commonservice.base.mapper.BasicBaseRfidBindingMapper;
import com.matariky.commonservice.base.vo.BasicBaseGoodsListVO;
import com.matariky.commonservice.base.vo.GoodsOptionInfo;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class BasicBaseGoodsServiceTest {

    @InjectMocks
    private BasicBaseGoodsService basicBaseGoodsService;

    @Mock
    private BasicBaseGoodsMapper basicBaseGoodsMapper;

    @Mock
    private HttpServletRequest request;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private CommonDictTypeService commonDictTypeService;

    @Mock
    private BasicBaseCreaterfidFactoryMapper basicBaseCreaterfidFactoryMapper;

    @Mock
    private BasicBaseCreaterfidPrintMapper basicBaseCreaterfidPrintMapper;

    @Mock
    private BasicBaseFormExtendMapper basicBaseFormExtendMapper;

    @Mock
    private BasicBaseRfidBindingMapper basicBaseRfidBindingMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseGoodsAll() {
        // Given
        BasicBaseGoodsListVO vo = new BasicBaseGoodsListVO();
        when(request.getHeader("id")).thenReturn("12345");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(TokenUtils.extractOrgCode(request)).thenReturn("orgCode");

        CommonDictType commonDictType = new CommonDictType();
        when(commonDictTypeService.getDictTypeByKey(anyString(), anyString())).thenReturn(commonDictType);

        CommonDict dict = new CommonDict();
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), anyString(), anyLong()))
                .thenReturn(dict);

        // When
        PageInfo<Map<String, Object>> result = basicBaseGoodsService.getBasicBaseGoodsAll(vo);

        // Then
        assertNotNull(result);
    }

    @Test
    void testCreateBasicBaseGoodsWithOrg() {
        // Given
        HashMap<String, Object> addVO = new HashMap<>();
        addVO.put("goodsName", "Test Goods");
        addVO.put("goodsCode", "TG123");

        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(basicBaseGoodsMapper.selectCount(any())).thenReturn(0L);

        // When
        basicBaseGoodsService.createBasicBaseGoodsWithOrg(addVO);

        // Then
        verify(basicBaseGoodsMapper, times(1)).createBasicBaseGoods(any(BasicBaseGoods.class));
    }

    @Test
    void testUpdateBasicBaseGoods() {
        // Given
        HashMap<String, Object> updateVO = new HashMap<>();
        updateVO.put("id", 1L);
        updateVO.put("goodsName", "Updated Goods");
        updateVO.put("goodsCode", "UG123");

        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(basicBaseGoodsMapper.selectCount(any())).thenReturn(0L);

        // When
        basicBaseGoodsService.updateBasicBaseGoods(updateVO);

        // Then
        verify(basicBaseGoodsMapper, times(1)).updateById(any(BasicBaseGoods.class));
    }

    @Test
    void testDelBasicBaseGoodsById() {
        // Given
        Long id = 1L;
        Long extendId = 1L;

        when(basicBaseGoodsMapper.getGoodsCountFromRfidfactory(id)).thenReturn(0);
        when(basicBaseCreaterfidFactoryMapper.selectCount(any())).thenReturn(0L);
        when(basicBaseCreaterfidPrintMapper.selectCount(any())).thenReturn(0L);
        when(basicBaseRfidBindingMapper.selectCount(any())).thenReturn(0L);
        when(basicBaseGoodsMapper.getGoodsCountFromRfidPrint(id)).thenReturn(0);

        // When
        basicBaseGoodsService.delBasicBaseGoodsById(id, extendId);

        // Then
        verify(basicBaseGoodsMapper, times(1)).delBasicBaseGoodsById(id);
        verify(basicBaseFormExtendMapper, times(1)).delBasicBaseFormExtendById(extendId);
    }

    @Test
    void testOptionList() {
        // Given
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");

        // When
        List<GoodsOptionInfo> result = basicBaseGoodsService.optionList();

        // Then
        assertNotNull(result);
    }

    @Test
    void testGetBasicBaseGoodsById() {
        // Given
        Long id = 1L;
        Long extendId = 1L;

        BasicBaseGoods basicBaseGoods = new BasicBaseGoods();
        when(basicBaseGoodsMapper.getBasicBaseGoodsById(id)).thenReturn(basicBaseGoods);

        // When
        Map<String, Object> result = basicBaseGoodsService.getBasicBaseGoodsById(id, extendId);

        // Then
        assertNotNull(result);
    }
}
