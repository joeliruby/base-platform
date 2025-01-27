package com.matariky.automation.bean;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class DbBeanTest {

    @InjectMocks
    private DbBean dbbean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMk() {
        // Given
        String expectedMk = "testMk";
        when(dbbean.getMk()).thenReturn(expectedMk);

        // When
        String actualMk = dbbean.getMk();

        // Then
        assertThat(actualMk).isEqualTo(expectedMk);
    }

    @Test
    void testSetMk() {
        // Given
        String mk = "testMk";

        // When
        dbbean.setMk(mk);

        // Then
        verify(dbbean).setMk(mk);
    }

    @Test
    void testGetZdname() {
        // Given
        String expectedZdname = "testZdname";
        when(dbbean.getZdname()).thenReturn(expectedZdname);

        // When
        String actualZdname = dbbean.getZdname();

        // Then
        assertThat(actualZdname).isEqualTo(expectedZdname);
    }

    @Test
    void testSetZdname() {
        // Given
        String zdname = "testZdname";

        // When
        dbbean.setZdname(zdname);

        // Then
        verify(dbbean).setZdname(zdname);
    }

    @Test
    void testGetCompages() {
        // Given
        String expectedCompages = "testCompages";
        when(dbbean.getCompages()).thenReturn(expectedCompages);

        // When
        String actualCompages = dbbean.getCompages();

        // Then
        assertThat(actualCompages).isEqualTo(expectedCompages);
    }

    @Test
    void testSetCompages() {
        // Given
        String compages = "testCompages";

        // When
        dbbean.setCompages(compages);

        // Then
        verify(dbbean).setCompages(compages);
    }
}
