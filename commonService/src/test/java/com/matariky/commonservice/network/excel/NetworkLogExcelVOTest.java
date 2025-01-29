package com.matariky.commonservice.network.excel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class NetworkLogExcelVOTest {

    @InjectMocks
    private NetworkLogExcelVO networklogexcelvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBusinessTime() {
        // Given
        Long expectedBusinessTime = 123456789L;
        networklogexcelvo.setBusinessTime(expectedBusinessTime);

        // When
        Long actualBusinessTime = networklogexcelvo.getBusinessTime();

        // Then
        assertThat(actualBusinessTime).isEqualTo(expectedBusinessTime);
    }

    @Test
    void testDeviceType() {
        // Given
        String expectedDeviceType = "Mobile";
        networklogexcelvo.setDeviceType(expectedDeviceType);

        // When
        String actualDeviceType = networklogexcelvo.getDeviceType();

        // Then
        assertThat(actualDeviceType).isEqualTo(expectedDeviceType);
    }

    @Test
    void testBusinessModule() {
        // Given
        String expectedBusinessModule = "ModuleA";
        networklogexcelvo.setBusinessModule(expectedBusinessModule);

        // When
        String actualBusinessModule = networklogexcelvo.getBusinessModule();

        // Then
        assertThat(actualBusinessModule).isEqualTo(expectedBusinessModule);
    }

    @Test
    void testSignalLevel() {
        // Given
        Integer expectedSignalLevel = 5;
        networklogexcelvo.setSignalLevel(expectedSignalLevel);

        // When
        Integer actualSignalLevel = networklogexcelvo.getSignalLevel();

        // Then
        assertThat(actualSignalLevel).isEqualTo(expectedSignalLevel);
    }

    @Test
    void testAccessAccount() {
        // Given
        String expectedAccessAccount = "user123";
        networklogexcelvo.setAccessAccount(expectedAccessAccount);

        // When
        String actualAccessAccount = networklogexcelvo.getAccessAccount();

        // Then
        assertThat(actualAccessAccount).isEqualTo(expectedAccessAccount);
    }

    @Test
    void testCreateTime() {
        // Given
        Long expectedCreateTime = 987654321L;
        networklogexcelvo.setCreateTime(expectedCreateTime);

        // When
        Long actualCreateTime = networklogexcelvo.getCreateTime();

        // Then
        assertThat(actualCreateTime).isEqualTo(expectedCreateTime);
    }
}
