package com.matariky.commonservice.area.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CommonAreaTest {

    @InjectMocks
    private CommonArea commonarea;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        commonarea.setId(expectedId);

        // When
        Long actualId = commonarea.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetAreaCode() {
        // Given
        String expectedAreaCode = "AC123";
        commonarea.setAreaCode(expectedAreaCode);

        // When
        String actualAreaCode = commonarea.getAreaCode();

        // Then
        assertThat(actualAreaCode).isEqualTo(expectedAreaCode);
    }

    @Test
    void testGetAreaName() {
        // Given
        String expectedAreaName = "Test Area";
        commonarea.setAreaName(expectedAreaName);

        // When
        String actualAreaName = commonarea.getAreaName();

        // Then
        assertThat(actualAreaName).isEqualTo(expectedAreaName);
    }

    @Test
    void testGetLevel() {
        // Given
        Integer expectedLevel = 2;
        commonarea.setLevel(expectedLevel);

        // When
        Integer actualLevel = commonarea.getLevel();

        // Then
        assertThat(actualLevel).isEqualTo(expectedLevel);
    }

    @Test
    void testGetCityCode() {
        // Given
        String expectedCityCode = "CC456";
        commonarea.setCityCode(expectedCityCode);

        // When
        String actualCityCode = commonarea.getCityCode();

        // Then
        assertThat(actualCityCode).isEqualTo(expectedCityCode);
    }

    @Test
    void testGetCenter() {
        // Given
        String expectedCenter = "Center1";
        commonarea.setCenter(expectedCenter);

        // When
        String actualCenter = commonarea.getCenter();

        // Then
        assertThat(actualCenter).isEqualTo(expectedCenter);
    }

    @Test
    void testGetParentId() {
        // Given
        Long expectedParentId = 10L;
        commonarea.setParentId(expectedParentId);

        // When
        Long actualParentId = commonarea.getParentId();

        // Then
        assertThat(actualParentId).isEqualTo(expectedParentId);
    }

    @Test
    void testGetRemark() {
        // Given
        String expectedRemark = "This is a remark";
        commonarea.setRemark(expectedRemark);

        // When
        String actualRemark = commonarea.getRemark();

        // Then
        assertThat(actualRemark).isEqualTo(expectedRemark);
    }
}
