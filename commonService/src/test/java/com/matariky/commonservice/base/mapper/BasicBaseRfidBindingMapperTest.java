package com.matariky.commonservice.base.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBaseRfidBinding;
import com.matariky.commonservice.base.vo.BasicBaseRfidBindingInfoVO;
import com.matariky.commonservice.base.vo.BasicBaseRfidBindingListVO;
import com.matariky.commonservice.base.vo.BatchInfoVO;
import com.matariky.commonservice.base.vo.GoodsBatchInfoVO;
import org.mockito.Mock;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBaseRfidBindingMapperTest {

    @InjectMocks
    private BasicBaseRfidBindingMapper basicBaseRfidBindingMapper;

    @Mock
    private BasicBaseRfidBindingMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseRfidBindingAll() {
        // Given
        BasicBaseRfidBindingListVO vo = new BasicBaseRfidBindingListVO();
        List<BasicBaseRfidBindingInfoVO> expectedList = Collections.singletonList(new BasicBaseRfidBindingInfoVO());
        when(mockMapper.getBasicBaseRfidBindingAll(vo)).thenReturn(expectedList);

        // When
        List<BasicBaseRfidBindingInfoVO> result = basicBaseRfidBindingMapper.getBasicBaseRfidBindingAll(vo);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicBaseRfidBinding() {
        // Given
        BasicBaseRfidBinding bean = new BasicBaseRfidBinding();
        when(mockMapper.createBasicBaseRfidBinding(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidBindingMapper.createBasicBaseRfidBinding(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGoodsBatchInfo() {
        // Given
        BatchInfoVO vo = new BatchInfoVO();
        List<GoodsBatchInfoVO> expectedList = Collections.singletonList(new GoodsBatchInfoVO());
        when(mockMapper.goodsBatchInfo(vo)).thenReturn(expectedList);

        // When
        List<GoodsBatchInfoVO> result = basicBaseRfidBindingMapper.goodsBatchInfo(vo);

        // Then
        assertEquals(expectedList, result);
    }
}
