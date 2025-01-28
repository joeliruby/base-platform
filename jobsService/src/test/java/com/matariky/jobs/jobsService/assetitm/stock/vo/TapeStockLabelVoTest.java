package com.matariky.jobs.jobsService.assetitm.stock.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TapeStockLabelVoTest {

    @InjectMocks
    private TapeStockLabelVo tapestocklabelvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLocationId() {
        // Given
        String locationId = "location123";
        tapestocklabelvo.setLocationId(locationId);

        // When
        String result = tapestocklabelvo.getLocationId();

        // Then
        assertThat(result).isEqualTo(locationId);
    }

    @Test
    void testLibraryId() {
        // Given
        Long libraryId = 123L;
        tapestocklabelvo.setLibraryId(libraryId);

        // When
        Long result = tapestocklabelvo.getLibraryId();

        // Then
        assertThat(result).isEqualTo(libraryId);
    }

    @Test
    void testEpc() {
        // Given
        String epc = "epc123";
        tapestocklabelvo.setEpc(epc);

        // When
        String result = tapestocklabelvo.getEpc();

        // Then
        assertThat(result).isEqualTo(epc);
    }

    @Test
    void testLabelId() {
        // Given
        Long labelId = 456L;
        tapestocklabelvo.setLabelId(labelId);

        // When
        Long result = tapestocklabelvo.getLabelId();

        // Then
        assertThat(result).isEqualTo(labelId);
    }

    // Add more test methods for other methods in TapeStockLabelVo if needed
}
