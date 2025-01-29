package com.matariky.commonservice.base.vo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicBaseDeviceUpgradeAddDTOTest {

    @InjectMocks
    private BasicBaseDeviceUpgradeAddDTO basicbasedeviceupgradeadddto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeviceUpgradeList() {
        // Given
        DeviceUpgradeVO deviceUpgradeVO = new DeviceUpgradeVO();
        basicbasedeviceupgradeadddto.setDeviceUpgradeList(Collections.singletonList(deviceUpgradeVO));

        // When
        List<DeviceUpgradeVO> result = basicbasedeviceupgradeadddto.getDeviceUpgradeList();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(deviceUpgradeVO);
    }

    @Test
    void testPackageId() {
        // Given
        Long packageId = 123L;
        basicbasedeviceupgradeadddto.setPackageId(packageId);

        // When
        Long result = basicbasedeviceupgradeadddto.getPackageId();

        // Then
        assertThat(result).isEqualTo(packageId);
    }

    @Test
    void testNoArgsConstructor() {
        // Given
        BasicBaseDeviceUpgradeAddDTO dto = new BasicBaseDeviceUpgradeAddDTO();

        // When
        // No action needed

        // Then
        assertThat(dto.getDeviceUpgradeList()).isNull();
        assertThat(dto.getPackageId()).isNull();
    }

    // Add more test methods for other methods in BasicBaseDeviceUpgradeAddDTO
}
