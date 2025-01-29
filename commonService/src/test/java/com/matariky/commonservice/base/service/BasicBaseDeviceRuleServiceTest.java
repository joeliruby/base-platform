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
import com.matariky.commonservice.base.vo.BasicBaseDeviceRuleAddVO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceRuleDetailAddVO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceRuleUpdateVO;
import com.matariky.exception.QslException;
import com.matariky.utils.TokenUtils;
import org.mockito.Mock;
import javax.servlet.http.HttpServletRequest;

@SpringBootTest
public class BasicBaseDeviceRuleServiceTest {

    @InjectMocks
    private BasicBaseDeviceRuleService basicbasedeviceruleservice;

    @Mock
    private BasicBaseDeviceRuleMapper basicBaseDeviceruleMapper;

    @Mock
    private BasicBaseDeviceRuleDetailMapper basicBaseDeviceruleDetailMapper;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBasicBaseDeviceruleWithOrg() {
        // Given
        BasicBaseDeviceRuleAddVO addVO = new BasicBaseDeviceRuleAddVO();
        String jwt = "test-jwt";
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenant-id");
        when(TokenUtils.extractUserIdFromToken(jwt)).thenReturn("1");
        when(TokenUtils.extractOrgCode(request)).thenReturn("org-code");
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("self-org-code");
        when(basicBaseDeviceruleMapper.selectOne(any())).thenReturn(null);

        // When
        Long result = basicbasedeviceruleservice.createBasicBaseDeviceruleWithOrg(addVO, jwt);

        // Then
        assertNotNull(result);
        verify(basicBaseDeviceruleMapper, times(1)).createBasicBaseDevicerule(any(BasicBaseDeviceRule.class));
    }

    @Test
    void testUpdateBasicBaseDevicerule() {
        // Given
        BasicBaseDeviceRuleUpdateVO updateVO = new BasicBaseDeviceRuleUpdateVO();
        updateVO.setId(1L);
        updateVO.setIsRecordLog(true);
        String jwt = "test-jwt";
        when(TokenUtils.extractUserIdFromToken(jwt)).thenReturn("1");

        // When
        int result = basicbasedeviceruleservice.updateBasicBaseDevicerule(updateVO, jwt);

        // Then
        assertEquals(1, result);
        verify(basicBaseDeviceruleMapper, times(1)).updateById(any(BasicBaseDeviceRule.class));
    }

    @Test
    void testCreateBasicBaseDeviceruleDetailWithOrg() {
        // Given
        BasicBaseDeviceRuleDetailAddVO addVO = new BasicBaseDeviceRuleDetailAddVO();
        String jwt = "test-jwt";
        when(TokenUtils.extractUserIdFromToken(jwt)).thenReturn("1");
        when(TokenUtils.extractOrgCode(request)).thenReturn("org-code");
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("self-org-code");
        when(basicBaseDeviceruleDetailMapper.selectCount(any())).thenReturn(0L);

        // When
        int result = basicbasedeviceruleservice.createBasicBaseDeviceruleDetailWithOrg(addVO, jwt);

        // Then
        assertEquals(1, result);
        verify(basicBaseDeviceruleDetailMapper, times(1))
                .createBasicBaseDeviceruleDetail(any(BasicBaseDeviceRuleDetail.class));
    }

    @Test
    void testCreateBasicBaseDeviceruleDetailWithOrgThrowsException() {
        // Given
        BasicBaseDeviceRuleDetailAddVO addVO = new BasicBaseDeviceRuleDetailAddVO();
        String jwt = "test-jwt";
        when(basicBaseDeviceruleDetailMapper.selectCount(any())).thenReturn(1L);

        // When / Then
        assertThrows(QslException.class, () -> {
            basicbasedeviceruleservice.createBasicBaseDeviceruleDetailWithOrg(addVO, jwt);
        });
    }

    // Add more test methods for other methods in BasicBaseDeviceRuleService
}
