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
public class BasicBaseDevicecommandVOTest {

    @InjectMocks
    private BasicBaseDevicecommandVO basicbasedevicecommandvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCommandId() {
        // Given
        Long expectedCommandId = 123L;
        basicbasedevicecommandvo.setCommandId(expectedCommandId);

        // When
        Long actualCommandId = basicbasedevicecommandvo.getCommandId();

        // Then
        assertThat(actualCommandId).isEqualTo(expectedCommandId);
    }

    @Test
    void testIsAll() {
        // Given
        Boolean expectedIsAll = true;
        basicbasedevicecommandvo.setIsAll(expectedIsAll);

        // When
        Boolean actualIsAll = basicbasedevicecommandvo.getIsAll();

        // Then
        assertThat(actualIsAll).isEqualTo(expectedIsAll);
    }

    @Test
    void testPackageIds() {
        // Given
        PackageInfoVO packageInfoVO = new PackageInfoVO();
        List<PackageInfoVO> expectedPackageIds = Collections.singletonList(packageInfoVO);
        basicbasedevicecommandvo.setPackageIds(expectedPackageIds);

        // When
        List<PackageInfoVO> actualPackageIds = basicbasedevicecommandvo.getPackageIds();

        // Then
        assertThat(actualPackageIds).isEqualTo(expectedPackageIds);
    }

    // Add more test methods for other methods in BasicBaseDevicecommandVO
}
