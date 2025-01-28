package com.matariky.jobs.jobsService.assetitm.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TapeRackTest {

    @InjectMocks
    private TapeRack taperack;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String operatorOrgCode = "ORG123";
        String rackName = "Rack A";

        // When
        taperack.setId(id);
        taperack.setOperatorOrgCode(operatorOrgCode);
        taperack.setRackName(rackName);

        // Then
        assertThat(taperack.getId()).isEqualTo(id);
        assertThat(taperack.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
        assertThat(taperack.getRackName()).isEqualTo(rackName);
    }

    @Test
    void testIsValid() {
        // Given
        String isValid = "Y";

        // When
        taperack.setIsVaild(isValid);

        // Then
        assertThat(taperack.getIsVaild()).isEqualTo(isValid);
    }

    @Test
    void testStatus() {
        // Given
        String status = "Active";

        // When
        taperack.setStatus(status);

        // Then
        assertThat(taperack.getStatus()).isEqualTo(status);
    }

    @Test
    void testRackSpecifications() {
        // Given
        Double length = 10.0;
        Double width = 5.0;
        Double height = 2.0;

        // When
        taperack.setRackSpecLength(length);
        taperack.setRackSpecWidth(width);
        taperack.setRackSpecHeight(height);

        // Then
        assertThat(taperack.getRackSpecLength()).isEqualTo(length);
        assertThat(taperack.getRackSpecWidth()).isEqualTo(width);
        assertThat(taperack.getRackSpecHeight()).isEqualTo(height);
    }

    // Add more test methods for other fields and methods in TapeRack
}
