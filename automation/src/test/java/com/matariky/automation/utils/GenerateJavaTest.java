package com.matariky.automation.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.automation.bean.DbBean;

@SpringBootTest
public class GenerateJavaTest {

    @InjectMocks
    private GenerateJava generatejava;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateJavaCode() {
        // Given
        DbBean model = mock(DbBean.class);
        when(model.getZdname()).thenReturn("id--INTEGER,name--VARCHAR");
        when(model.getCompages()).thenReturn("com.matariky");
        when(model.getTablename()).thenReturn("test_table");

        // When
        String result = GenerateJava.GenerateJavaCode(model);

        // Then
        assertNotNull(result);
        assertTrue(result.contains("public class TestTable"));
    }

    @Test
    void testGetFileSetMethon() {
        // Given
        DbBean model = mock(DbBean.class);
        when(model.getZdname()).thenReturn("id--INTEGER,name--VARCHAR");

        // When
        String result = GenerateJava.get_fileSetMethon(model);

        // Then
        assertNotNull(result);
        assertTrue(result.contains("public void setId"));
        assertTrue(result.contains("public void setName"));
    }

    @Test
    void testGetFileGetMethon() {
        // Given
        DbBean model = mock(DbBean.class);
        when(model.getZdname()).thenReturn("id--INTEGER,name--VARCHAR");

        // When
        String result = GenerateJava.get_fileGetMethon(model);

        // Then
        assertNotNull(result);
        assertTrue(result.contains("public Integer getId"));
        assertTrue(result.contains("public String getName"));
    }

    @Test
    void testGetFileAttribute() {
        // Given
        DbBean model = mock(DbBean.class);
        when(model.getZdname()).thenReturn("id--INTEGER,name--VARCHAR");

        // When
        String result = GenerateJava.get_fileattribute(model);

        // Then
        assertNotNull(result);
        assertTrue(result.contains("private Integer id"));
        assertTrue(result.contains("private String name"));
    }

    @Test
    void testGetFileTop() {
        // Given
        DbBean model = mock(DbBean.class);
        when(model.getZdname()).thenReturn("id--INTEGER,name--VARCHAR");
        when(model.getCompages()).thenReturn("com.matariky");
        when(model.getTablename()).thenReturn("test_table");

        // When
        String result = GenerateJava.get_filetop(model);

        // Then
        assertNotNull(result);
        assertTrue(result.contains("package com.matariky.bean;"));
        assertTrue(result.contains("public class TestTable"));
    }
}
