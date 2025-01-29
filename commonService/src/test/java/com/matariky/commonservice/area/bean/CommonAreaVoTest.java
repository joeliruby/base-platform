package com.matariky.commonservice.area.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CommonAreaVoTest {

    @InjectMocks
    private CommonAreaVo commonareavo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetChildrenNo() {
        // Given
        Integer expectedChildrenNo = 5;
        commonareavo.setChildrenNo(expectedChildrenNo);

        // When
        Integer actualChildrenNo = commonareavo.getChildrenNo();

        // Then
        assertThat(actualChildrenNo).isEqualTo(expectedChildrenNo);
    }

    @Test
    void testSetChildrenNo() {
        // Given
        Integer expectedChildrenNo = 10;

        // When
        commonareavo.setChildrenNo(expectedChildrenNo);

        // Then
        assertThat(commonareavo.getChildrenNo()).isEqualTo(expectedChildrenNo);
    }

    // Add more test methods for other methods in CommonAreaVo if needed
}
