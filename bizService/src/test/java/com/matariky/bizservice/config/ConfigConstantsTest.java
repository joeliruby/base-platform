package com.matariky.bizservice.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ConfigConstantsTest {

    @InjectMocks
    private ConfigConstants configconstants;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCacheEnabled() {
        // Given
        String cacheEnabled = "true";

        // When
        configconstants.setCacheEnabled(cacheEnabled);

        // Then
        assertThat(ConfigConstants.isCacheEnabled()).isTrue();
    }

    @Test
    void testSimText() {
        // Given
        String simText = "txt,html,htm";

        // When
        configconstants.setSimText(simText);

        // Then
        assertThat(ConfigConstants.getSimText()).containsExactly("txt", "html", "htm");
    }

    @Test
    void testMedia() {
        // Given
        String media = "mp3,wav";

        // When
        configconstants.setMedia(media);

        // Then
        assertThat(ConfigConstants.getMedia()).containsExactly("mp3", "wav");
    }

    @Test
    void testOfficePreviewType() {
        // Given
        String officePreviewType = "pdf";

        // When
        configconstants.setOfficePreviewType(officePreviewType);

        // Then
        assertThat(ConfigConstants.getOfficePreviewType()).isEqualTo("pdf");
    }

    @Test
    void testFtpUsername() {
        // Given
        String ftpUsername = "user";

        // When
        configconstants.setFtpUsername(ftpUsername);

        // Then
        assertThat(ConfigConstants.getFtpUsername()).isEqualTo("user");
    }

    @Test
    void testFtpPassword() {
        // Given
        String ftpPassword = "password";

        // When
        configconstants.setFtpPassword(ftpPassword);

        // Then
        assertThat(ConfigConstants.getFtpPassword()).isEqualTo("password");
    }

    @Test
    void testFtpControlEncoding() {
        // Given
        String ftpControlEncoding = "ISO-8859-1";

        // When
        configconstants.setFtpControlEncoding(ftpControlEncoding);

        // Then
        assertThat(ConfigConstants.getFtpControlEncoding()).isEqualTo("ISO-8859-1");
    }

    @Test
    void testBaseUrl() {
        // Given
        String baseUrl = "http://example.com";

        // When
        configconstants.setBaseUrl(baseUrl);

        // Then
        assertThat(ConfigConstants.getBaseUrl()).isEqualTo("http://example.com");
    }

    @Test
    void testTrustHost() {
        // Given
        String trustHost = "example.com,another.com";

        // When
        configconstants.setTrustHost(trustHost);

        // Then
        assertThat(ConfigConstants.getTrustHostSet()).containsExactlyInAnyOrder("example.com", "another.com");
    }

    @Test
    void testPdfDownloadDisable() {
        // Given
        String pdfDownloadDisable = "false";

        // When
        configconstants.setPdfDownloadDisable(pdfDownloadDisable);

        // Then
        assertThat(ConfigConstants.getPdfDownloadDisable()).isEqualTo("false");
    }
}
