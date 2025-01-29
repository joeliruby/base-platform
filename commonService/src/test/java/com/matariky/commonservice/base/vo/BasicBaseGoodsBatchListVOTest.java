package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseGoodsBatchListVOTest {

    @InjectMocks
    private BasicBaseGoodsBatchListVO basicbasegoodsbatchlistvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetIndex() {
        // Given
        int expectedIndex = 5;
        basicbasegoodsbatchlistvo.setIndex(expectedIndex);

        // When
        int actualIndex = basicbasegoodsbatchlistvo.getIndex();

        // Then
        assertThat(actualIndex).isEqualTo(expectedIndex);
    }

    @Test
    void testGetPerPage() {
        // Given
        int expectedPerPage = 10;
        basicbasegoodsbatchlistvo.setPerPage(expectedPerPage);

        // When
        int actualPerPage = basicbasegoodsbatchlistvo.getPerPage();

        // Then
        assertThat(actualPerPage).isEqualTo(expectedPerPage);
    }

    @Test
    void testGetGoodsId() {
        // Given
        Long expectedGoodsId = 123L;
        basicbasegoodsbatchlistvo.setGoodsId(expectedGoodsId);

        // When
        Long actualGoodsId = basicbasegoodsbatchlistvo.getGoodsId();

        // Then
        assertThat(actualGoodsId).isEqualTo(expectedGoodsId);
    }

    @Test
    void testGetBatchCode() {
        // Given
        String expectedBatchCode = "ABC123";
        basicbasegoodsbatchlistvo.setBatchCode(expectedBatchCode);

        // When
        String actualBatchCode = basicbasegoodsbatchlistvo.getBatchCode();

        // Then
        assertThat(actualBatchCode).isEqualTo(expectedBatchCode);
    }

    // Add more test methods for other methods in BasicBaseGoodsBatchListVO if
    // needed
}
