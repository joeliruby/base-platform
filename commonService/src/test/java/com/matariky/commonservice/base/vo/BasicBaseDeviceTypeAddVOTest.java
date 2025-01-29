package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDeviceTypeAddVOTest {

    @InjectMocks
    private BasicBaseDeviceTypeAddVO basicbasedevicetypeaddvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTypeKey() {
        // Given
        String typeKey = "testKey";
        basicbasedevicetypeaddvo.setTypeKey(typeKey);

        // When
        String result = basicbasedevicetypeaddvo.getTypeKey();

        // Then
        assertThat(result).isEqualTo(typeKey);
    }

    @Test
    void testDeviceModel() {
        // Given
        String deviceModel = "testModel";
        basicbasedevicetypeaddvo.setDeviceModel(deviceModel);

        // When
        String result = basicbasedevicetypeaddvo.getDeviceModel();

        // Then
        assertThat(result).isEqualTo(deviceModel);
    }

    @Test
    void testDeviceFactory() {
        // Given
        String deviceFactory = "testFactory";
        basicbasedevicetypeaddvo.setDeviceFactory(deviceFactory);

        // When
        String result = basicbasedevicetypeaddvo.getDeviceFactory();

        // Then
        assertThat(result).isEqualTo(deviceFactory);
    }

    @Test
    void testDeviceDescribe() {
        // Given
        String deviceDescribe = "testDescription";
        basicbasedevicetypeaddvo.setDeviceDescribe(deviceDescribe);

        // When
        String result = basicbasedevicetypeaddvo.getDeviceDescribe();

        // Then
        assertThat(result).isEqualTo(deviceDescribe);
    }

    @Test
    void testIsAutoUpgrade() {
        // Given
        String isAutoUpgrade = "true";
        basicbasedevicetypeaddvo.setIsAutoUpgrade(isAutoUpgrade);

        // When
        String result = basicbasedevicetypeaddvo.getIsAutoUpgrade();

        // Then
        assertThat(result).isEqualTo(isAutoUpgrade);
    }

    @Test
    void testProtocolType() {
        // Given
        String protocolType = "testProtocol";
        basicbasedevicetypeaddvo.setProtocolType(protocolType);

        // When
        String result = basicbasedevicetypeaddvo.getProtocolType();

        // Then
        assertThat(result).isEqualTo(protocolType);
    }
}
