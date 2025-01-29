package com.matariky.commonservice.base.controller;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import com.matariky.commonservice.base.service.CommonService;
import com.matariky.commonservice.base.vo.AddExtendFieldDetailVO;
import com.matariky.commonservice.base.vo.AddExtendFieldVO;
import com.matariky.commonservice.base.vo.RfidTagInfo;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class CommonControllerTest {

    @InjectMocks
    private CommonController commonController;

    @Mock
    private CommonService commonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRfidCodes() {
        // Given
        RfidTagInfo rfidTagInfo = new RfidTagInfo();
        when(commonService.getRfidCodes()).thenReturn(rfidTagInfo);

        // When
        AjaxResult result = commonController.getRfidCodes();

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.DATA_TAG)).isEqualTo(rfidTagInfo);
    }

    @Test
    void testUploadImg() {
        // Given
        MultipartFile file = mock(MultipartFile.class);
        String bucket = "testBucket";
        String expectedResponse = "imageUrl";
        when(commonService.uploadImg(file, bucket)).thenReturn(expectedResponse);

        // When
        AjaxResult result = commonController.uploadImg(file, bucket);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.DATA_TAG)).isEqualTo(expectedResponse);
    }

    @Test
    void testAddExtendField() {
        // Given
        AddExtendFieldVO vo = new AddExtendFieldVO();

        // When
        AjaxResult result = commonController.addExtendField(vo);

        // Then
        verify(commonService, times(1)).addExtendField(vo);
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetExtendField() {
        // Given
        String name = "testName";
        List<AddExtendFieldDetailVO> expectedList = Collections.singletonList(new AddExtendFieldDetailVO());
        when(commonService.getExtendField(name)).thenReturn(expectedList);

        // When
        AjaxResult result = commonController.getExtendField(name);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.DATA_TAG)).isEqualTo(expectedList);
    }
}
