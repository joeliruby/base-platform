package com.matariky.automation.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class DbTest {

    @InjectMocks
    private Db db;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetDbtype() {
        // Given
        String dbtype = "MySQL";

        // When
        db.setDbtype(dbtype);

        // Then
        assertThat(db.getDbtype()).isEqualTo(dbtype);
    }

    @Test
    void testSetAndGetDbip() {
        // Given
        String dbip = "127.0.0.1";

        // When
        db.setDbip(dbip);

        // Then
        assertThat(db.getDbip()).isEqualTo(dbip);
    }

    @Test
    void testSetAndGetDbport() {
        // Given
        String dbport = "3306";

        // When
        db.setDbport(dbport);

        // Then
        assertThat(db.getDbport()).isEqualTo(dbport);
    }

    @Test
    void testSetAndGetDbusername() {
        // Given
        String dbusername = "root";

        // When
        db.setDbusername(dbusername);

        // Then
        assertThat(db.getDbusername()).isEqualTo(dbusername);
    }

    @Test
    void testSetAndGetDbpassword() {
        // Given
        String dbpassword = "password";

        // When
        db.setDbpassword(dbpassword);

        // Then
        assertThat(db.getDbpassword()).isEqualTo(dbpassword);
    }

    @Test
    void testSetAndGetDbname() {
        // Given
        String dbname = "testdb";

        // When
        db.setDbname(dbname);

        // Then
        assertThat(db.getDbname()).isEqualTo(dbname);
    }

    @Test
    void testSetAndGetTablename() {
        // Given
        String tablename = "testtable";

        // When
        db.setTablename(tablename);

        // Then
        assertThat(db.getTablename()).isEqualTo(tablename);
    }
}
