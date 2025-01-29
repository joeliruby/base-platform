package com.matariky.commonservice.base.controller;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import com.alibaba.fastjson.JSONObject;
import com.matariky.commonservice.base.service.RfidService;
import com.matariky.commonservice.base.vo.RfidTagInfo;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class RfidControllerTest {

    @InjectMocks
    private RfidController rfidController;

    @Mock
    private RfidService rfidService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReceiveRfidData() {
        // Given
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "value");

        // When
        rfidController.receiveRfidData(jsonObject);

        // Then
        verify(rfidService, times(1)).receiveRfidData(jsonObject);
    }

    @Test
    void testGetReceiveRfidData() {
        // Given
        RfidTagInfo rfidTagInfo = new RfidTagInfo();
        when(rfidService.getReceiveRfidData(true)).thenReturn(rfidTagInfo);

        // When
        AjaxResult result = rfidController.getReceiveRfidData(true);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.DATA_TAG)).isEqualTo(rfidTagInfo);
        verify(rfidService, times(1)).getReceiveRfidData(true);
    }

    // Add more test methods for other methods in RfidController if needed
}
