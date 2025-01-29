package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DeviceTypeOptionTest {

    @InjectMocks
    private DeviceTypeOption devicetypeoption;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTypeId() {
        // Given
        Long expectedTypeId = 1L;
        devicetypeoption.setTypeId(expectedTypeId);

        // When
        Long actualTypeId = devicetypeoption.getTypeId();

        // Then
        assertThat(actualTypeId).isEqualTo(expectedTypeId);
    }

    @Test
    void testGetTypeCode() {
        // Given
        String expectedTypeCode = "TYPE_CODE";
        devicetypeoption.setTypeCode(expectedTypeCode);

        // When
        String actualTypeCode = devicetypeoption.getTypeCode();

        // Then
        assertThat(actualTypeCode).isEqualTo(expectedTypeCode);
    }

    @Test
    void testGetTypeName() {
        // Given
        String expectedTypeName = "TYPE_NAME";
        devicetypeoption.setTypeName(expectedTypeName);

        // When
        String actualTypeName = devicetypeoption.getTypeName();

        // Then
        assertThat(actualTypeName).isEqualTo(expectedTypeName);
    }

    @Test
    void testGetDeviceFactory() {
        // Given
        String expectedDeviceFactory = "DEVICE_FACTORY";
        devicetypeoption.setDeviceFactory(expectedDeviceFactory);

        // When
        String actualDeviceFactory = devicetypeoption.getDeviceFactory();

        // Then
        assertThat(actualDeviceFactory).isEqualTo(expectedDeviceFactory);
    }

    @Test
    void testGetDeviceModel() {
        // Given
        String expectedDeviceModel = "DEVICE_MODEL";
        devicetypeoption.setDeviceModel(expectedDeviceModel);

        // When
        String actualDeviceModel = devicetypeoption.getDeviceModel();

        // Then
        assertThat(actualDeviceModel).isEqualTo(expectedDeviceModel);
    }

    @Test
    void testGetLabel() {
        // Given
        String expectedLabel = "LABEL";
        devicetypeoption.setLabel(expectedLabel);

        // When
        String actualLabel = devicetypeoption.getLabel();

        // Then
        assertThat(actualLabel).isEqualTo(expectedLabel);
    }
}
