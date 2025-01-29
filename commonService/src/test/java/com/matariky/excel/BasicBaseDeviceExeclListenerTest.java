package com.matariky.excel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.excel.context.AnalysisContext;
import com.matariky.commonservice.base.bean.BasicBaseDevice;
import com.matariky.commonservice.base.bean.BasicBaseDeviceType;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceMapper;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceTypeMapper;
import com.matariky.commonservice.base.service.BasicBaseDeviceService;
import com.matariky.commonservice.base.vo.BasicBaseDeviceExeclVO;
import com.matariky.commonservice.base.vo.DbmVO;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;

@SpringBootTest
public class BasicBaseDeviceExeclListenerTest {

    @InjectMocks
    private BasicBaseDeviceExeclListener basicBaseDeviceExeclListener;

    @Mock
    private BasicBaseDeviceMapper basicBaseDeviceMapper;

    @Mock
    private BasicBaseDeviceTypeMapper basicBaseDeviceTypeMapper;

    @Mock
    private BasicBaseDeviceService basicBaseDeviceService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private AnalysisContext analysisContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInvokeWithValidData() {
        // Given
        BasicBaseDeviceExeclVO vo = new BasicBaseDeviceExeclVO();
        vo.setDeviceCode("validCode");
        vo.setDeviceDbm("validDbm");
        vo.setDeviceIp("192.168.1.1");
        vo.setDeviceMac("00:1A:2B:3C:4D:5E");
        vo.setLongitude("123.456");
        vo.setLatitude("78.910");
        vo.setInstallAddress("validAddress");
        vo.setTypeName("typeName(factory/model)");

        BasicBaseDeviceType deviceType = new BasicBaseDeviceType();
        deviceType.setId(1L);
        deviceType.setCurrentVersion("1.0");

        when(basicBaseDeviceTypeMapper.selectOne(any())).thenReturn(deviceType);
        when(basicBaseDeviceMapper.selectCount(any())).thenReturn(0L);
        when(basicBaseDeviceService.getDbmOption()).thenReturn(List.of(new DbmVO()));

        // When
        basicBaseDeviceExeclListener.invoke(vo, analysisContext);

        // Then
        verify(basicBaseDeviceMapper, times(1)).createBasicBaseDevice(any(BasicBaseDevice.class));
    }

    @Test
    void testInvokeWithInvalidDeviceCodeLength() {
        // Given
        BasicBaseDeviceExeclVO vo = new BasicBaseDeviceExeclVO();
        vo.setDeviceCode("a".repeat(201));

        // When & Then
        QslException exception = assertThrows(QslException.class, () -> {
            basicBaseDeviceExeclListener.invoke(vo, analysisContext);
        });
        assertEquals(MessageKey.DEVICE_CODE_LENGTH_CANNOT_EXCEED, exception.getMessage());
    }

    @Test
    void testInvokeWithInvalidDeviceDbmLength() {
        // Given
        BasicBaseDeviceExeclVO vo = new BasicBaseDeviceExeclVO();
        vo.setDeviceDbm("a".repeat(101));

        // When & Then
        QslException exception = assertThrows(QslException.class, () -> {
            basicBaseDeviceExeclListener.invoke(vo, analysisContext);
        });
        assertEquals(MessageKey.DEVICE_DBM_LENGTH_CANNOT_EXCEED, exception.getMessage());
    }

    @Test
    void testInvokeWithInvalidDeviceIpLength() {
        // Given
        BasicBaseDeviceExeclVO vo = new BasicBaseDeviceExeclVO();
        vo.setDeviceIp("a".repeat(51));

        // When & Then
        QslException exception = assertThrows(QslException.class, () -> {
            basicBaseDeviceExeclListener.invoke(vo, analysisContext);
        });
        assertEquals(MessageKey.DEVICE_IP_LENGTH_CANNOT_EXCEED, exception.getMessage());
    }

    @Test
    void testInvokeWithInvalidDeviceMacLength() {
        // Given
        BasicBaseDeviceExeclVO vo = new BasicBaseDeviceExeclVO();
        vo.setDeviceMac("a".repeat(201));

        // When & Then
        QslException exception = assertThrows(QslException.class, () -> {
            basicBaseDeviceExeclListener.invoke(vo, analysisContext);
        });
        assertEquals(MessageKey.DEVICE_MAX_LENGTH_CANNOT_EXCEED, exception.getMessage());
    }

    @Test
    void testInvokeWithInvalidLongitudeLength() {
        // Given
        BasicBaseDeviceExeclVO vo = new BasicBaseDeviceExeclVO();
        vo.setLongitude("a".repeat(101));

        // When & Then
        QslException exception = assertThrows(QslException.class, () -> {
            basicBaseDeviceExeclListener.invoke(vo, analysisContext);
        });
        assertEquals(MessageKey.DEVICE_LONGITUDE_LENGTH_CANNOT_EXCEED, exception.getMessage());
    }

    @Test
    void testInvokeWithInvalidLatitudeLength() {
        // Given
        BasicBaseDeviceExeclVO vo = new BasicBaseDeviceExeclVO();
        vo.setLatitude("a".repeat(101));

        // When & Then
        QslException exception = assertThrows(QslException.class, () -> {
            basicBaseDeviceExeclListener.invoke(vo, analysisContext);
        });
        assertEquals(MessageKey.DEVICE_LATITUDE_LENGTH_CANNOT_EXCEED, exception.getMessage());
    }

    @Test
    void testInvokeWithInvalidInstallAddressLength() {
        // Given
        BasicBaseDeviceExeclVO vo = new BasicBaseDeviceExeclVO();
        vo.setInstallAddress("a".repeat(201));

        // When & Then
        QslException exception = assertThrows(QslException.class, () -> {
            basicBaseDeviceExeclListener.invoke(vo, analysisContext);
        });
        assertEquals(MessageKey.DEVICE_INSTALL_ADDRESS_LENGTH_CANNOT_EXCEED, exception.getMessage());
    }

    @Test
    void testInvokeWithBlankTypeName() {
        // Given
        BasicBaseDeviceExeclVO vo = new BasicBaseDeviceExeclVO();
        vo.setTypeName("");

        // When & Then
        QslException exception = assertThrows(QslException.class, () -> {
            basicBaseDeviceExeclListener.invoke(vo, analysisContext);
        });
        assertEquals(MessageKey.XLSX_FILE_INCORRECT_FORMAT, exception.getMessage());
    }

    @Test
    void testDoAfterAllAnalysed() {
        // Given
        AnalysisContext context = mock(AnalysisContext.class);

        // When
        basicBaseDeviceExeclListener.doAfterAllAnalysed(context);

        // Then
        // No exception should be thrown
    }
}
