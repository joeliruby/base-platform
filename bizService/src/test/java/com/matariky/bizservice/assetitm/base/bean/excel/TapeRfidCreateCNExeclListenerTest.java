package com.matariky.bizservice.assetitm.base.bean.excel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.matariky.commonservice.base.mapper.BasicBaseRfidInfoMapper;
import com.matariky.commonservice.base.vo.TapeRfidCreateCNExeclReqVo;

@SpringBootTest
public class TapeRfidCreateCNExeclListenerTest {

    @InjectMocks
    private TapeRfidCreateCNExeclListener tapeRfidCreateCNExeclListener;

    @Mock
    private BasicBaseRfidInfoMapper basicBaseRfidInfoMapper;

    @Mock
    private HttpServletRequest request;

    private List<TapeRfidCreateCNExeclReqVo> lists;
    private StringBuffer errorMessage;
    private String jwt;
    private String tenantId;

    @BeforeEach
    void setUp() {
        lists = new ArrayList<>();
        errorMessage = new StringBuffer();
        jwt = "testJwt";
        tenantId = "testTenantId";
        tapeRfidCreateCNExeclListener = new TapeRfidCreateCNExeclListener(lists, errorMessage, basicBaseRfidInfoMapper,
                jwt, request, tenantId);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInvokeWithValidData() {
        // Given
        TapeRfidCreateCNExeclReqVo vo = new TapeRfidCreateCNExeclReqVo();
        AnalysisContext context = mock(AnalysisContext.class);
        ReadRowHolder rowHolder = mock(ReadRowHolder.class);
        when(context.readRowHolder()).thenReturn(rowHolder);
        when(rowHolder.getRowIndex()).thenReturn(0);

        // When
        tapeRfidCreateCNExeclListener.invoke(vo, context);

        // Then
        assertTrue(lists.contains(vo));
        assertEquals(0, errorMessage.length());
    }

    @Test
    void testInvokeWithInvalidData() {
        // Given
        TapeRfidCreateCNExeclReqVo vo = new TapeRfidCreateCNExeclReqVo();
        AnalysisContext context = mock(AnalysisContext.class);
        ReadRowHolder rowHolder = mock(ReadRowHolder.class);
        when(context.readRowHolder()).thenReturn(rowHolder);
        when(rowHolder.getRowIndex()).thenReturn(0);
        doReturn(new StringBuffer("error")).when(tapeRfidCreateCNExeclListener).validInfo(vo);

        // When
        tapeRfidCreateCNExeclListener.invoke(vo, context);

        // Then
        assertFalse(lists.contains(vo));
        assertTrue(errorMessage.length() > 0);
    }

    @Test
    void testDoAfterAllAnalysed() {
        // Given
        AnalysisContext context = mock(AnalysisContext.class);

        // When
        tapeRfidCreateCNExeclListener.doAfterAllAnalysed(context);

        // Then
        // No exception should be thrown
    }

    @Test
    void testValidInfo() {
        // Given
        TapeRfidCreateCNExeclReqVo vo = new TapeRfidCreateCNExeclReqVo();

        // When
        StringBuffer result = tapeRfidCreateCNExeclListener.validInfo(vo);

        // Then
        assertNotNull(result);
        assertEquals(0, result.length());
    }
}
