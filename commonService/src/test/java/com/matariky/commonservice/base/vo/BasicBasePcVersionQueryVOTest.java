package com.matariky.commonservice.base.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicBasePcVersionQueryVOTest {

    @InjectMocks
    private BasicBasePcVersionQueryVO basicbasepcversionqueryvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetIndex() {
        // Given
        basicbasepcversionqueryvo.setIndex(1);

        // When
        Integer index = basicbasepcversionqueryvo.getIndex();

        // Then
        assertEquals(1, index);
    }

    @Test
    void testGetPerPage() {
        // Given
        basicbasepcversionqueryvo.setPerPage(10);

        // When
        Integer perPage = basicbasepcversionqueryvo.getPerPage();

        // Then
        assertEquals(10, perPage);
    }

    @Test
    void testGetVersionNo() {
        // Given
        basicbasepcversionqueryvo.setVersionNo("1.0.0");

        // When
        String versionNo = basicbasepcversionqueryvo.getVersionNo();

        // Then
        assertEquals("1.0.0", versionNo);
    }

    @Test
    void testSetIndex() {
        // Given
        Integer index = 2;

        // When
        basicbasepcversionqueryvo.setIndex(index);

        // Then
        assertEquals(index, basicbasepcversionqueryvo.getIndex());
    }

    @Test
    void testSetPerPage() {
        // Given
        Integer perPage = 20;

        // When
        basicbasepcversionqueryvo.setPerPage(perPage);

        // Then
        assertEquals(perPage, basicbasepcversionqueryvo.getPerPage());
    }

    @Test
    void testSetVersionNo() {
        // Given
        String versionNo = "2.0.0";

        // When
        basicbasepcversionqueryvo.setVersionNo(versionNo);

        // Then
        assertEquals(versionNo, basicbasepcversionqueryvo.getVersionNo());
    }
}
