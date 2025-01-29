package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class CommandVOTest {

    @InjectMocks
    private CommandVO commandvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommandId() {
        // Given
        Long expectedCommandId = 123L;
        commandvo.setCommandId(expectedCommandId);

        // When
        Long actualCommandId = commandvo.getCommandId();

        // Then
        assertThat(actualCommandId).isEqualTo(expectedCommandId);
    }

    @Test
    void testSetCommandId() {
        // Given
        Long expectedCommandId = 456L;

        // When
        commandvo.setCommandId(expectedCommandId);

        // Then
        assertThat(commandvo.getCommandId()).isEqualTo(expectedCommandId);
    }

    @Test
    void testGetPackageIds() {
        // Given
        List<Long> expectedPackageIds = Arrays.asList(1L, 2L, 3L);
        commandvo.setPackageIds(expectedPackageIds);

        // When
        List<Long> actualPackageIds = commandvo.getPackageIds();

        // Then
        assertThat(actualPackageIds).isEqualTo(expectedPackageIds);
    }

    @Test
    void testSetPackageIds() {
        // Given
        List<Long> expectedPackageIds = Arrays.asList(4L, 5L, 6L);

        // When
        commandvo.setPackageIds(expectedPackageIds);

        // Then
        assertThat(commandvo.getPackageIds()).isEqualTo(expectedPackageIds);
    }
}
