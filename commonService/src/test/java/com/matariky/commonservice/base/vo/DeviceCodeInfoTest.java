package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DeviceCodeInfoTest {

    @InjectMocks
    private DeviceCodeInfo devicecodeinfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String typeName = "TypeName";
        String deviceCode = "DeviceCode";
        String deviceFactory = "DeviceFactory";
        String deviceModel = "DeviceModel";
        String label = "Label";

        // When
        devicecodeinfo.setId(id);
        devicecodeinfo.setTypeName(typeName);
        devicecodeinfo.setDeviceCode(deviceCode);
        devicecodeinfo.setDeviceFactory(deviceFactory);
        devicecodeinfo.setDeviceModel(deviceModel);
        devicecodeinfo.setLabel(label);

        // Then
        assertThat(devicecodeinfo.getId()).isEqualTo(id);
        assertThat(devicecodeinfo.getTypeName()).isEqualTo(typeName);
        assertThat(devicecodeinfo.getDeviceCode()).isEqualTo(deviceCode);
        assertThat(devicecodeinfo.getDeviceFactory()).isEqualTo(deviceFactory);
        assertThat(devicecodeinfo.getDeviceModel()).isEqualTo(deviceModel);
        assertThat(devicecodeinfo.getLabel()).isEqualTo(label);
    }

    @Test
    void testToString() {
        // Given
        devicecodeinfo.setId(1L);
        devicecodeinfo.setTypeName("TypeName");
        devicecodeinfo.setDeviceCode("DeviceCode");
        devicecodeinfo.setDeviceFactory("DeviceFactory");
        devicecodeinfo.setDeviceModel("DeviceModel");
        devicecodeinfo.setLabel("Label");

        // When
        String result = devicecodeinfo.toString();

        // Then
        assertThat(result).contains("DeviceCodeInfo");
        assertThat(result).contains("id=1");
        assertThat(result).contains("typeName=TypeName");
        assertThat(result).contains("deviceCode=DeviceCode");
        assertThat(result).contains("deviceFactory=DeviceFactory");
        assertThat(result).contains("deviceModel=DeviceModel");
        assertThat(result).contains("label=Label");
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        DeviceCodeInfo deviceCodeInfo1 = new DeviceCodeInfo();
        deviceCodeInfo1.setId(1L);
        deviceCodeInfo1.setTypeName("TypeName");
        deviceCodeInfo1.setDeviceCode("DeviceCode");
        deviceCodeInfo1.setDeviceFactory("DeviceFactory");
        deviceCodeInfo1.setDeviceModel("DeviceModel");
        deviceCodeInfo1.setLabel("Label");

        DeviceCodeInfo deviceCodeInfo2 = new DeviceCodeInfo();
        deviceCodeInfo2.setId(1L);
        deviceCodeInfo2.setTypeName("TypeName");
        deviceCodeInfo2.setDeviceCode("DeviceCode");
        deviceCodeInfo2.setDeviceFactory("DeviceFactory");
        deviceCodeInfo2.setDeviceModel("DeviceModel");
        deviceCodeInfo2.setLabel("Label");

        // When & Then
        assertThat(deviceCodeInfo1).isEqualTo(deviceCodeInfo2);
        assertThat(deviceCodeInfo1.hashCode()).isEqualTo(deviceCodeInfo2.hashCode());
    }
}
