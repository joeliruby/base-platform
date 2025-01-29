package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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

import com.matariky.commonservice.base.bean.BasicBaseCodingRules;
import com.matariky.commonservice.base.mapper.BasicBaseCodingRulesMapper;
import com.matariky.commonservice.base.vo.BasicBaseCodingRulesAddVO;
import com.matariky.commonservice.base.vo.BasicBaseCodingRulesInfoVO;
import com.matariky.commonservice.base.vo.BasicBaseCodingRulesListVO;
import com.matariky.commonservice.base.vo.BasicBaseCodingRulesUpdateVO;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class BasicBaseCodingRulesServiceTest {

    @InjectMocks
    private BasicBaseCodingRulesService basicbasecodingrulesservice;

    @Mock
    private BasicBaseCodingRulesMapper basicBaseCodingrulesMapper;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseCodingrulesAll() {
        // Given
        BasicBaseCodingRulesListVO vo = new BasicBaseCodingRulesListVO();
        when(request.getHeader("id")).thenReturn("12345");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(basicBaseCodingrulesMapper.getBasicBaseCodingrulesAll(vo)).thenReturn(Collections.emptyList());

        // When
        List<BasicBaseCodingRulesInfoVO> result = basicbasecodingrulesservice.getBasicBaseCodingrulesAll(vo);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetCodingrulesCode() {
        // Given
        BasicBaseCodingRules basicBaseCodingRules = new BasicBaseCodingRules();
        basicBaseCodingRules.setRulesCode("R-00002");
        when(basicBaseCodingrulesMapper.selectOne(any())).thenReturn(basicBaseCodingRules);

        // When
        String code = basicbasecodingrulesservice.getCodingrulesCode();

        // Then
        assertEquals("R-00003", code);
    }

    @Test
    void testCreateBasicBaseCodingrulesWithOrg() {
        // Given
        BasicBaseCodingRulesAddVO addVO = new BasicBaseCodingRulesAddVO();
        addVO.setRulesName("Test Rule");
        addVO.setRulesCode("R-00001");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(basicBaseCodingrulesMapper.selectCount(any())).thenReturn(0L);
        when(basicBaseCodingrulesMapper.createBasicBaseCodingrules(any())).thenReturn(1);

        // When
        int result = basicbasecodingrulesservice.createBasicBaseCodingrulesWithOrg(addVO);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseCodingrules() {
        // Given
        BasicBaseCodingRulesUpdateVO updateVO = new BasicBaseCodingRulesUpdateVO();
        updateVO.setId(1L);
        updateVO.setRulesName("Updated Rule");
        when(basicBaseCodingrulesMapper.selectRuleCountFromrfidfactory(updateVO.getId())).thenReturn(0);
        when(basicBaseCodingrulesMapper.selectRuleCountFromrrfidprint(updateVO.getId())).thenReturn(0);
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(basicBaseCodingrulesMapper.selectCount(any())).thenReturn(0L);
        when(basicBaseCodingrulesMapper.updateById(any())).thenReturn(1);

        // When
        int result = basicbasecodingrulesservice.updateBasicBaseCodingrules(updateVO);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseCodingrulesById() {
        // Given
        Long id = 1L;
        when(basicBaseCodingrulesMapper.delBasicBaseCodingrulesById(id)).thenReturn(1);

        // When
        int result = basicbasecodingrulesservice.delBasicBaseCodingrulesById(id);

        // Then
        assertEquals(1, result);
    }
}
