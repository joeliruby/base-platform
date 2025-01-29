package com.matariky.commonservice.base.mapper;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.util.Collections;
import java.util.List;
import org.mockito.Mock;
import com.matariky.commonservice.base.bean.BasicBaseCodingRules;
import com.matariky.commonservice.base.vo.BasicBaseCodingRulesInfoVO;
import com.matariky.commonservice.base.vo.BasicBaseCodingRulesListVO;
import com.matariky.commonservice.base.vo.CodingRulesOptionInfo;
import com.matariky.model.QueryDataIsolation;

@SpringBootTest
public class BasicBaseCodingRulesMapperTest {

    @InjectMocks
    private BasicBaseCodingRulesMapper basicBaseCodingRulesMapper;

    @Mock
    private BasicBaseCodingRulesListVO basicBaseCodingRulesListVO;

    @Mock
    private BasicBaseCodingRules basicBaseCodingRules;

    @Mock
    private QueryDataIsolation queryDataIsolation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseCodingrulesAll() {
        // Given
        List<BasicBaseCodingRulesInfoVO> expectedList = Collections.emptyList();
        when(basicBaseCodingRulesMapper.getBasicBaseCodingrulesAll(basicBaseCodingRulesListVO))
                .thenReturn(expectedList);

        // When
        List<BasicBaseCodingRulesInfoVO> result = basicBaseCodingRulesMapper
                .getBasicBaseCodingrulesAll(basicBaseCodingRulesListVO);

        // Then
        assertThat(result).isEqualTo(expectedList);
    }

    @Test
    void testDelBasicBaseCodingrulesById() {
        // Given
        Long id = 1L;
        when(basicBaseCodingRulesMapper.delBasicBaseCodingrulesById(id)).thenReturn(1);

        // When
        int result = basicBaseCodingRulesMapper.delBasicBaseCodingrulesById(id);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testCreateBasicBaseCodingrules() {
        // Given
        when(basicBaseCodingRulesMapper.createBasicBaseCodingrules(basicBaseCodingRules)).thenReturn(1);

        // When
        int result = basicBaseCodingRulesMapper.createBasicBaseCodingrules(basicBaseCodingRules);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testGetOptionList() {
        // Given
        List<CodingRulesOptionInfo> expectedList = Collections.emptyList();
        when(basicBaseCodingRulesMapper.getOptionList(queryDataIsolation)).thenReturn(expectedList);

        // When
        List<CodingRulesOptionInfo> result = basicBaseCodingRulesMapper.getOptionList(queryDataIsolation);

        // Then
        assertThat(result).isEqualTo(expectedList);
    }

    @Test
    void testSelectRuleCountFromrfidfactory() {
        // Given
        Long id = 1L;
        when(basicBaseCodingRulesMapper.selectRuleCountFromrfidfactory(id)).thenReturn(1);

        // When
        Integer result = basicBaseCodingRulesMapper.selectRuleCountFromrfidfactory(id);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testSelectRuleCountFromrrfidprint() {
        // Given
        Long id = 1L;
        when(basicBaseCodingRulesMapper.selectRuleCountFromrrfidprint(id)).thenReturn(1);

        // When
        Integer result = basicBaseCodingRulesMapper.selectRuleCountFromrrfidprint(id);

        // Then
        assertThat(result).isEqualTo(1);
    }
}
