package com.matariky.automation.bean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LogsTest {

    @InjectMocks
    private Logs logs;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        when(logs.getId()).thenReturn(1);

        // When
        int id = logs.getId();

        // Then
        assertEquals(1, id);
    }

    @Test
    void testSetId() {
        // Given
        logs.setId(1);

        // When
        int id = logs.getId();

        // Then
        assertEquals(1, id);
    }

    @Test
    void testGetIp() {
        // Given
        when(logs.getIp()).thenReturn("127.0.0.1");

        // When
        String ip = logs.getIp();

        // Then
        assertEquals("127.0.0.1", ip);
    }

    @Test
    void testSetIp() {
        // Given
        logs.setIp("127.0.0.1");

        // When
        String ip = logs.getIp();

        // Then
        assertEquals("127.0.0.1", ip);
    }

    @Test
    void testGetDb() {
        // Given
        when(logs.getDb()).thenReturn("testDb");

        // When
        String db = logs.getDb();

        // Then
        assertEquals("testDb", db);
    }

    @Test
    void testSetDb() {
        // Given
        logs.setDb("testDb");

        // When
        String db = logs.getDb();

        // Then
        assertEquals("testDb", db);
    }

    @Test
    void testGetPageurl() {
        // Given
        when(logs.getPageurl()).thenReturn("http://example.com");

        // When
        String pageurl = logs.getPageurl();

        // Then
        assertEquals("http://example.com", pageurl);
    }

    @Test
    void testSetPageurl() {
        // Given
        logs.setPageurl("http://example.com");

        // When
        String pageurl = logs.getPageurl();

        // Then
        assertEquals("http://example.com", pageurl);
    }

    @Test
    void testGetDownurl() {
        // Given
        when(logs.getDownurl()).thenReturn("http://example.com/download");

        // When
        String downurl = logs.getDownurl();

        // Then
        assertEquals("http://example.com/download", downurl);
    }

    @Test
    void testSetDownurl() {
        // Given
        logs.setDownurl("http://example.com/download");

        // When
        String downurl = logs.getDownurl();

        // Then
        assertEquals("http://example.com/download", downurl);
    }

    @Test
    void testGetTablename() {
        // Given
        when(logs.getTablename()).thenReturn("testTable");

        // When
        String tablename = logs.getTablename();

        // Then
        assertEquals("testTable", tablename);
    }

    @Test
    void testSetTablename() {
        // Given
        logs.setTablename("testTable");

        // When
        String tablename = logs.getTablename();

        // Then
        assertEquals("testTable", tablename);
    }

    @Test
    void testGetDbname() {
        // Given
        when(logs.getDbname()).thenReturn("testDbName");

        // When
        String dbname = logs.getDbname();

        // Then
        assertEquals("testDbName", dbname);
    }

    @Test
    void testSetDbname() {
        // Given
        logs.setDbname("testDbName");

        // When
        String dbname = logs.getDbname();

        // Then
        assertEquals("testDbName", dbname);
    }

    @Test
    void testGetDbip() {
        // Given
        when(logs.getDbip()).thenReturn("192.168.1.1");

        // When
        String dbip = logs.getDbip();

        // Then
        assertEquals("192.168.1.1", dbip);
    }

    @Test
    void testSetDbip() {
        // Given
        logs.setDbip("192.168.1.1");

        // When
        String dbip = logs.getDbip();

        // Then
        assertEquals("192.168.1.1", dbip);
    }

    @Test
    void testGetDbusername() {
        // Given
        when(logs.getDbusername()).thenReturn("testUser");

        // When
        String dbusername = logs.getDbusername();

        // Then
        assertEquals("testUser", dbusername);
    }

    @Test
    void testSetDbusername() {
        // Given
        logs.setDbusername("testUser");

        // When
        String dbusername = logs.getDbusername();

        // Then
        assertEquals("testUser", dbusername);
    }

    @Test
    void testGetDbpassword() {
        // Given
        when(logs.getDbpassword()).thenReturn("testPassword");

        // When
        String dbpassword = logs.getDbpassword();

        // Then
        assertEquals("testPassword", dbpassword);
    }

    @Test
    void testSetDbpassword() {
        // Given
        logs.setDbpassword("testPassword");

        // When
        String dbpassword = logs.getDbpassword();

        // Then
        assertEquals("testPassword", dbpassword);
    }
}
