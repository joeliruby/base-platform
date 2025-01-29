package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TapeRfidCreateCNExeclReqVoTest {

    @InjectMocks
    private TapeRfidCreateCNExeclReqVo taperfidcreatecnexeclreqvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEpc() {
        // Given
        String expectedEpc = "testEpc";
        taperfidcreatecnexeclreqvo.setEpc(expectedEpc);

        // When
        String actualEpc = taperfidcreatecnexeclreqvo.getEpc();

        // Then
        assertThat(actualEpc).isEqualTo(expectedEpc);
    }

    @Test
    void testTid() {
        // Given
        String expectedTid = "testTid";
        taperfidcreatecnexeclreqvo.setTid(expectedTid);

        // When
        String actualTid = taperfidcreatecnexeclreqvo.getTid();

        // Then
        assertThat(actualTid).isEqualTo(expectedTid);
    }

    @Test
    void testOdContent() {
        // Given
        String expectedOdContent = "testOdContent";
        taperfidcreatecnexeclreqvo.setOdContent(expectedOdContent);

        // When
        String actualOdContent = taperfidcreatecnexeclreqvo.getOdContent();

        // Then
        assertThat(actualOdContent).isEqualTo(expectedOdContent);
    }

    @Test
    void testQrContent() {
        // Given
        String expectedQrContent = "testQrContent";
        taperfidcreatecnexeclreqvo.setQrContent(expectedQrContent);

        // When
        String actualQrContent = taperfidcreatecnexeclreqvo.getQrContent();

        // Then
        assertThat(actualQrContent).isEqualTo(expectedQrContent);
    }

    @Test
    void testGoodsCode() {
        // Given
        String expectedGoodsCode = "testGoodsCode";
        taperfidcreatecnexeclreqvo.setGoodsCode(expectedGoodsCode);

        // When
        String actualGoodsCode = taperfidcreatecnexeclreqvo.getGoodsCode();

        // Then
        assertThat(actualGoodsCode).isEqualTo(expectedGoodsCode);
    }

    @Test
    void testPassword() {
        // Given
        String expectedPassword = "testPassword";
        taperfidcreatecnexeclreqvo.setPassword(expectedPassword);

        // When
        String actualPassword = taperfidcreatecnexeclreqvo.getPassword();

        // Then
        assertThat(actualPassword).isEqualTo(expectedPassword);
    }

    @Test
    void testId() {
        // Given
        Long expectedId = 1L;
        taperfidcreatecnexeclreqvo.setId(expectedId);

        // When
        Long actualId = taperfidcreatecnexeclreqvo.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }
}
