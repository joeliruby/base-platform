package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBasePcVersionAddVOTest {

    @InjectMocks
    private BasicBasePcVersionAddVO basicbasepcversionaddvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testVersionName() {
        // Given
        String versionName = "Version 1.0";
        basicbasepcversionaddvo.setVersionName(versionName);

        // When
        String result = basicbasepcversionaddvo.getVersionName();

        // Then
        assertThat(result).isEqualTo(versionName);
    }

    @Test
    void testVersionNo() {
        // Given
        String versionNo = "1.0.0";
        basicbasepcversionaddvo.setVersionNo(versionNo);

        // When
        String result = basicbasepcversionaddvo.getVersionNo();

        // Then
        assertThat(result).isEqualTo(versionNo);
    }

    @Test
    void testVersionContent() {
        // Given
        String versionContent = "Initial release";
        basicbasepcversionaddvo.setVersionContent(versionContent);

        // When
        String result = basicbasepcversionaddvo.getVersionContent();

        // Then
        assertThat(result).isEqualTo(versionContent);
    }

    @Test
    void testRequirementDate() {
        // Given
        Long requirementDate = 1633036800000L; // Example timestamp
        basicbasepcversionaddvo.setRequirementDate(requirementDate);

        // When
        Long result = basicbasepcversionaddvo.getRequirementDate();

        // Then
        assertThat(result).isEqualTo(requirementDate);
    }

    @Test
    void testMessageShutdownTime() {
        // Given
        String messageShutdownTime = "10:00 PM";
        basicbasepcversionaddvo.setMessageShutdownTime(messageShutdownTime);

        // When
        String result = basicbasepcversionaddvo.getMessageShutdownTime();

        // Then
        assertThat(result).isEqualTo(messageShutdownTime);
    }
}
