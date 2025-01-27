package com.matariky.automation.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class DbStaticTest {

    @InjectMocks
    private DbStatic dbstatic;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDbDriveInitialization() {
        // Given
        // Initialize your test data and mocks here

        // When
        String driver = DbStatic.DB_DRIVE.get("MYSQL");

        // Then
        assertThat(driver).isEqualTo("com.mysql.jdbc.Driver");
    }

    @Test
    void testDbBeanOdbcInitialization() {
        // Given
        // Initialize your test data and mocks here

        // When
        String varcharType = DbStatic.DB_BEAN_ODBC.get("VARCHAR");
        String intType = DbStatic.DB_BEAN_ODBC.get("INT");

        // Then
        assertThat(varcharType).isEqualTo("java.lang.String");
        assertThat(intType).isEqualTo("java.lang.Integer");
    }

    @Test
    void testDbBeanOdbcContainsKey() {
        // Given
        // Initialize your test data and mocks here

        // When
        boolean containsVarchar = DbStatic.DB_BEAN_ODBC.containsKey("VARCHAR");
        boolean containsNonExistent = DbStatic.DB_BEAN_ODBC.containsKey("NON_EXISTENT");

        // Then
        assertThat(containsVarchar).isTrue();
        assertThat(containsNonExistent).isFalse();
    }

    // Add more test methods for other methods in DbStatic
}
