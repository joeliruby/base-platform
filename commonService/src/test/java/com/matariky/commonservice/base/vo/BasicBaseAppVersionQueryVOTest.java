package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseAppVersionQueryVOTest {

    @InjectMocks
    private BasicBaseAppVersionQueryVO basicbaseappversionqueryvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetIndex() {
        // Given
        basicbaseappversionqueryvo.setIndex(1);

        // When
        Integer index = basicbaseappversionqueryvo.getIndex();

        // Then
        assertThat(index).isEqualTo(1);
    }

    @Test
    void testGetPerPage() {
        // Given
        basicbaseappversionqueryvo.setPerPage(10);

        // When
        Integer perPage = basicbaseappversionqueryvo.getPerPage();

        // Then
        assertThat(perPage).isEqualTo(10);
    }

    @Test
    void testGetVersionNo() {
        // Given
        basicbaseappversionqueryvo.setVersionNo("1.0.0");

        // When
        String versionNo = basicbaseappversionqueryvo.getVersionNo();

        // Then
        assertThat(versionNo).isEqualTo("1.0.0");
    }

    @Test
    void testSetIndex() {
        // Given
        Integer index = 2;

        // When
        basicbaseappversionqueryvo.setIndex(index);

        // Then
        assertThat(basicbaseappversionqueryvo.getIndex()).isEqualTo(index);
    }

    @Test
    void testSetPerPage() {
        // Given
        Integer perPage = 20;

        // When
        basicbaseappversionqueryvo.setPerPage(perPage);

        // Then
        assertThat(basicbaseappversionqueryvo.getPerPage()).isEqualTo(perPage);
    }

    @Test
    void testSetVersionNo() {
        // Given
        String versionNo = "2.0.0";

        // When
        basicbaseappversionqueryvo.setVersionNo(versionNo);

        // Then
        assertThat(basicbaseappversionqueryvo.getVersionNo()).isEqualTo(versionNo);
    }
}
