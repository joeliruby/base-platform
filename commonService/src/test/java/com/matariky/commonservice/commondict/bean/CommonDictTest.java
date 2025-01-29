package com.matariky.commonservice.commondict.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CommonDictTest {

    @InjectMocks
    private CommonDict commondict;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAndSetId() {
        // Given
        Long expectedId = 1L;

        // When
        commondict.setId(expectedId);

        // Then
        assertThat(commondict.getId()).isEqualTo(expectedId);
    }

    @Test
    void testGetAndSetDictName() {
        // Given
        String expectedDictName = "TestDictName";

        // When
        commondict.setDictName(expectedDictName);

        // Then
        assertThat(commondict.getDictName()).isEqualTo(expectedDictName);
    }

    @Test
    void testGetAndSetDictTypeId() {
        // Given
        Long expectedDictTypeId = 2L;

        // When
        commondict.setDictTypeId(expectedDictTypeId);

        // Then
        assertThat(commondict.getDictTypeId()).isEqualTo(expectedDictTypeId);
    }

    @Test
    void testGetAndSetDictValue() {
        // Given
        String expectedDictValue = "TestDictValue";

        // When
        commondict.setDictValue(expectedDictValue);

        // Then
        assertThat(commondict.getDictValue()).isEqualTo(expectedDictValue);
    }

    @Test
    void testGetAndSetTenantId() {
        // Given
        String expectedTenantId = "TestTenantId";

        // When
        commondict.setTenantId(expectedTenantId);

        // Then
        assertThat(commondict.getTenantId()).isEqualTo(expectedTenantId);
    }

    @Test
    void testGetAndSetComment() {
        // Given
        String expectedComment = "TestComment";

        // When
        commondict.setComment(expectedComment);

        // Then
        assertThat(commondict.getComment()).isEqualTo(expectedComment);
    }

    @Test
    void testGetAndSetCreateTime() {
        // Given
        Long expectedCreateTime = 123456789L;

        // When
        commondict.setCreateTime(expectedCreateTime);

        // Then
        assertThat(commondict.getCreateTime()).isEqualTo(expectedCreateTime);
    }

    @Test
    void testGetAndSetUpdateTime() {
        // Given
        Long expectedUpdateTime = 987654321L;

        // When
        commondict.setUpdateTime(expectedUpdateTime);

        // Then
        assertThat(commondict.getUpdateTime()).isEqualTo(expectedUpdateTime);
    }

    @Test
    void testGetAndSetIsActive() {
        // Given
        Boolean expectedIsActive = true;

        // When
        commondict.setIsActive(expectedIsActive);

        // Then
        assertThat(commondict.getIsActive()).isEqualTo(expectedIsActive);
    }

    @Test
    void testGetAndSetCreatedBy() {
        // Given
        Long expectedCreatedBy = 3L;

        // When
        commondict.setCreatedBy(expectedCreatedBy);

        // Then
        assertThat(commondict.getCreatedBy()).isEqualTo(expectedCreatedBy);
    }

    @Test
    void testGetAndSetUpdatedBy() {
        // Given
        Long expectedUpdatedBy = 4L;

        // When
        commondict.setUpdatedBy(expectedUpdatedBy);

        // Then
        assertThat(commondict.getUpdatedBy()).isEqualTo(expectedUpdatedBy);
    }

    @Test
    void testGetAndSetDictKey() {
        // Given
        String expectedDictKey = "TestDictKey";

        // When
        commondict.setDictKey(expectedDictKey);

        // Then
        assertThat(commondict.getDictKey()).isEqualTo(expectedDictKey);
    }

    @Test
    void testGetAndSetDeleteTime() {
        // Given
        Long expectedDeleteTime = 123456789L;

        // When
        commondict.setDeleteTime(expectedDeleteTime);

        // Then
        assertThat(commondict.getDeleteTime()).isEqualTo(expectedDeleteTime);
    }
}
