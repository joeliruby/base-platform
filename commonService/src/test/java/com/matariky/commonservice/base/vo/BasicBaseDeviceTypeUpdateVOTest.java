package com.matariky.commonservice.base.vo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicBaseDeviceTypeUpdateVOTest {

    @InjectMocks
    private BasicBaseDeviceTypeUpdateVO basicbasedevicetypeupdatevo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTypeKey() {
        // Given
        String typeKey = "someTypeKey";
        basicbasedevicetypeupdatevo.setTypeKey(typeKey);

        // When
        String result = basicbasedevicetypeupdatevo.getTypeKey();

        // Then
        assertThat(result).isEqualTo(typeKey);
    }

    @Test
    void testDeviceModel() {
        // Given
        String deviceModel = "someDeviceModel";
        basicbasedevicetypeupdatevo.setDeviceModel(deviceModel);

        // When
        String result = basicbasedevicetypeupdatevo.getDeviceModel();

        // Then
        assertThat(result).isEqualTo(deviceModel);
    }

    @Test
    void testDeviceFactory() {
        // Given
        String deviceFactory = "someDeviceFactory";
        basicbasedevicetypeupdatevo.setDeviceFactory(deviceFactory);

        // When
        String result = basicbasedevicetypeupdatevo.getDeviceFactory();

        // Then
        assertThat(result).isEqualTo(deviceFactory);
    }

    @Test
    void testDeviceDescribe() {
        // Given
        String deviceDescribe = "someDeviceDescribe";
        basicbasedevicetypeupdatevo.setDeviceDescribe(deviceDescribe);

        // When
        String result = basicbasedevicetypeupdatevo.getDeviceDescribe();

        // Then
        assertThat(result).isEqualTo(deviceDescribe);
    }

    @Test
    void testId() {
        // Given
        Long id = 123L;
        basicbasedevicetypeupdatevo.setId(id);

        // When
        Long result = basicbasedevicetypeupdatevo.getId();

        // Then
        assertThat(result).isEqualTo(id);
    }

    @Test
    void testProtocolType() {
        // Given
        String protocolType = "someProtocolType";
        basicbasedevicetypeupdatevo.setProtocolType(protocolType);

        // When
        String result = basicbasedevicetypeupdatevo.getProtocolType();

        // Then
        assertThat(result).isEqualTo(protocolType);
    }

    @Test
    void testIsAutoUpgrade() {
        // Given
        String isAutoUpgrade = "true";
        basicbasedevicetypeupdatevo.setIsAutoUpgrade(isAutoUpgrade);

        // When
        String result = basicbasedevicetypeupdatevo.getIsAutoUpgrade();

        // Then
        assertThat(result).isEqualTo(isAutoUpgrade);
    }

    @Test
    void testCommandList() {
        // Given
        List<CommandVO> commandList = List.of(new CommandVO(), new CommandVO());
        basicbasedevicetypeupdatevo.setCommandList(commandList);

        // When
        List<CommandVO> result = basicbasedevicetypeupdatevo.getCommandList();

        // Then
        assertThat(result).isEqualTo(commandList);
    }
}
