package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBasePcVersionUpdateVOTest {

    @InjectMocks
    private BasicBasePcVersionUpdateVO basicbasepcversionupdatevo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testVersionName() {
        // Given
        String versionName = "1.0.0";
        basicbasepcversionupdatevo.setVersionName(versionName);

        // When
        String result = basicbasepcversionupdatevo.getVersionName();

        // Then
        assertThat(result).isEqualTo(versionName);
    }

    @Test
    void testVersionNo() {
        // Given
        String versionNo = "100";
        basicbasepcversionupdatevo.setVersionNo(versionNo);

        // When
        String result = basicbasepcversionupdatevo.getVersionNo();

        // Then
        assertThat(result).isEqualTo(versionNo);
    }

    @Test
    void testVersionContent() {
        // Given
        String versionContent = "Initial release";
        basicbasepcversionupdatevo.setVersionContent(versionContent);

        // When
        String result = basicbasepcversionupdatevo.getVersionContent();

        // Then
        assertThat(result).isEqualTo(versionContent);
    }

    @Test
    void testRequirementDate() {
        // Given
        Long requirementDate = 1633036800000L;
        basicbasepcversionupdatevo.setRequirementDate(requirementDate);

        // When
        Long result = basicbasepcversionupdatevo.getRequirementDate();

        // Then
        assertThat(result).isEqualTo(requirementDate);
    }

    @Test
    void testMessageShutdownTime() {
        // Given
        String messageShutdownTime = "10:00 PM";
        basicbasepcversionupdatevo.setMessageShutdownTime(messageShutdownTime);

        // When
        String result = basicbasepcversionupdatevo.getMessageShutdownTime();

        // Then
        assertThat(result).isEqualTo(messageShutdownTime);
    }

    @Test
    void testId() {
        // Given
        Long id = 1L;
        basicbasepcversionupdatevo.setId(id);

        // When
        Long result = basicbasepcversionupdatevo.getId();

        // Then
        assertThat(result).isEqualTo(id);
    }
}
