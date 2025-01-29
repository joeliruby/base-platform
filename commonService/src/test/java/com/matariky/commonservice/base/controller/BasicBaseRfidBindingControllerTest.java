package com.matariky.commonservice.base.controller;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import com.matariky.commonservice.base.service.BasicBaseRfidBindingService;
import com.matariky.commonservice.base.vo.*;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import java.util.Collections;

@SpringBootTest
public class BasicBaseRfidBindingControllerTest {

    @InjectMocks
    private BasicBaseRfidBindingController basicBaseRfidBindingController;

    @Mock
    private BasicBaseRfidBindingService basicBaseRfidBindingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        BasicBaseRfidBindingListVO listVO = new BasicBaseRfidBindingListVO();
        when(basicBaseRfidBindingService.getBasicBaseRfidBindingAll(listVO)).thenReturn(Collections.emptyList());

        // When
        AjaxResult result = basicBaseRfidBindingController.list(listVO);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        assertThat(result.get(AjaxResult.DATA_TAG)).isNotNull();
    }

    @Test
    void testSave() {
        // Given
        BasicBaseRfidBindingAddVO addVO = new BasicBaseRfidBindingAddVO();

        // When
        AjaxResult result = basicBaseRfidBindingController.save(addVO);

        // Then
        verify(basicBaseRfidBindingService, times(1)).createBasicBaseRfidBindingWithOrg(addVO);
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testSaveBatch() {
        // Given
        BasicBaseRfidBindingBatchAddVO addVO = new BasicBaseRfidBindingBatchAddVO();

        // When
        AjaxResult result = basicBaseRfidBindingController.save(addVO);

        // Then
        verify(basicBaseRfidBindingService, times(1)).createBasicBaseRfidBindingWithOrgBatch(addVO);
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseRfidBindingUpdateVO updateVO = new BasicBaseRfidBindingUpdateVO();

        // When
        AjaxResult result = basicBaseRfidBindingController.update(updateVO);

        // Then
        verify(basicBaseRfidBindingService, times(1)).updateBasicBaseRfidBinding(updateVO);
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";

        // When
        AjaxResult result = basicBaseRfidBindingController.del(id);

        // Then
        verify(basicBaseRfidBindingService, times(1)).deleteById(Long.parseLong(id));
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testGetRfidCode() {
        // Given
        Long expectedCode = 123L;
        when(basicBaseRfidBindingService.getRfidCode()).thenReturn(expectedCode);

        // When
        AjaxResult result = basicBaseRfidBindingController.getRfidCode();

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        assertThat(result.get(AjaxResult.DATA_TAG)).isEqualTo(expectedCode);
    }
}
