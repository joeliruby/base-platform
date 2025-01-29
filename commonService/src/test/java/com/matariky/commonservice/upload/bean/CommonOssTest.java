package com.matariky.commonservice.upload.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CommonOssTest {

    @InjectMocks
    private CommonOss commonoss;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAndSetId() {
        // Given
        Long expectedId = 1L;

        // When
        commonoss.setId(expectedId);

        // Then
        assertThat(commonoss.getId()).isEqualTo(expectedId);
    }

    @Test
    void testGetAndSetUrl() {
        // Given
        String expectedUrl = "http://example.com";

        // When
        commonoss.setUrl(expectedUrl);

        // Then
        assertThat(commonoss.getUrl()).isEqualTo(expectedUrl);
    }

    @Test
    void testGetAndSetTenantId() {
        // Given
        String expectedTenantId = "tenant123";

        // When
        commonoss.setTenantId(expectedTenantId);

        // Then
        assertThat(commonoss.getTenantId()).isEqualTo(expectedTenantId);
    }

    @Test
    void testGetAndSetDomainName() {
        // Given
        String expectedDomainName = "example.com";

        // When
        commonoss.setDomainName(expectedDomainName);

        // Then
        assertThat(commonoss.getDomainName()).isEqualTo(expectedDomainName);
    }

    @Test
    void testGetAndSetOldName() {
        // Given
        String expectedOldName = "oldName";

        // When
        commonoss.setOldName(expectedOldName);

        // Then
        assertThat(commonoss.getOldName()).isEqualTo(expectedOldName);
    }

    @Test
    void testGetAndSetFileName() {
        // Given
        String expectedFileName = "fileName";

        // When
        commonoss.setFileName(expectedFileName);

        // Then
        assertThat(commonoss.getFileName()).isEqualTo(expectedFileName);
    }

    @Test
    void testGetAndSetExtension() {
        // Given
        String expectedExtension = "txt";

        // When
        commonoss.setExtension(expectedExtension);

        // Then
        assertThat(commonoss.getExtension()).isEqualTo(expectedExtension);
    }

    @Test
    void testGetAndSetSize() {
        // Given
        Integer expectedSize = 1024;

        // When
        commonoss.setSize(expectedSize);

        // Then
        assertThat(commonoss.getSize()).isEqualTo(expectedSize);
    }

    @Test
    void testGetAndSetContentType() {
        // Given
        String expectedContentType = "text/plain";

        // When
        commonoss.setContentType(expectedContentType);

        // Then
        assertThat(commonoss.getContentType()).isEqualTo(expectedContentType);
    }

    @Test
    void testGetAndSetServiceProvider() {
        // Given
        String expectedServiceProvider = "AWS";

        // When
        commonoss.setService_provider(expectedServiceProvider);

        // Then
        assertThat(commonoss.getService_provider()).isEqualTo(expectedServiceProvider);
    }

    @Test
    void testGetAndSetCreateTime() {
        // Given
        Long expectedCreateTime = System.currentTimeMillis();

        // When
        commonoss.setCreateTime(expectedCreateTime);

        // Then
        assertThat(commonoss.getCreateTime()).isEqualTo(expectedCreateTime);
    }

    @Test
    void testGetAndSetUpdateTime() {
        // Given
        Long expectedUpdateTime = System.currentTimeMillis();

        // When
        commonoss.setUpdateTime(expectedUpdateTime);

        // Then
        assertThat(commonoss.getUpdateTime()).isEqualTo(expectedUpdateTime);
    }

    @Test
    void testGetAndSetCreatedBy() {
        // Given
        Long expectedCreatedBy = 1L;

        // When
        commonoss.setCreatedBy(expectedCreatedBy);

        // Then
        assertThat(commonoss.getCreatedBy()).isEqualTo(expectedCreatedBy);
    }

    @Test
    void testGetAndSetUpdatedBy() {
        // Given
        Long expectedUpdatedBy = 2L;

        // When
        commonoss.setUpdatedBy(expectedUpdatedBy);

        // Then
        assertThat(commonoss.getUpdatedBy()).isEqualTo(expectedUpdatedBy);
    }
}
