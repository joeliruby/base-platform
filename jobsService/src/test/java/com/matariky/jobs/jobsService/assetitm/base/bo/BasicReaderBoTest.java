package com.matariky.jobs.jobsService.assetitm.base.bo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicReaderBoTest {

    @InjectMocks
    private BasicReaderBo basicreaderbo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRackCode() {
        // Given
        String expectedRackCode = "RACK123";
        basicreaderbo.setRackCode(expectedRackCode);

        // When
        String actualRackCode = basicreaderbo.getRackCode();

        // Then
        assertThat(actualRackCode).isEqualTo(expectedRackCode);
    }

    @Test
    void testGetReaderCode() {
        // Given
        String expectedReaderCode = "READER123";
        basicreaderbo.setReaderCode(expectedReaderCode);

        // When
        String actualReaderCode = basicreaderbo.getReaderCode();

        // Then
        assertThat(actualReaderCode).isEqualTo(expectedReaderCode);
    }

    @Test
    void testSetRackCode() {
        // Given
        String expectedRackCode = "RACK123";

        // When
        basicreaderbo.setRackCode(expectedRackCode);

        // Then
        assertThat(basicreaderbo.getRackCode()).isEqualTo(expectedRackCode);
    }

    @Test
    void testSetReaderCode() {
        // Given
        String expectedReaderCode = "READER123";

        // When
        basicreaderbo.setReaderCode(expectedReaderCode);

        // Then
        assertThat(basicreaderbo.getReaderCode()).isEqualTo(expectedReaderCode);
    }
}
