package com.matariky.automation.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import org.mockito.Mock;
import com.matariky.automation.bean.DataBaseBean;
import com.matariky.automation.bean.DataBaseTableBean;
import com.matariky.automation.bean.Db;

@SpringBootTest
public class LoginDbTest {

    @InjectMocks
    private LoginDb logindb;

    @Mock
    private Connection mockConnection;

    @Mock
    private Statement mockStatement;

    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin_DB_mysql_Table_TYPE() throws Exception {
        // Given
        Db db = new Db();
        db.setTablename("test_table");
        db.setDbname("test_db");

        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("COLUMN_NAME")).thenReturn("id");
        when(mockResultSet.getString("IS_NULLABLE")).thenReturn("NO");
        when(mockResultSet.getString("COLUMN_TYPE")).thenReturn("int(11)");
        when(mockResultSet.getString("COLUMN_COMMENT")).thenReturn("Primary Key");
        when(mockResultSet.getString("DATA_TYPE")).thenReturn("int");
        when(mockResultSet.getString("COLUMN_KEY")).thenReturn("PRI");

        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockConnection.createStatement()).thenReturn(mockStatement);

        // When
        List<DataBaseTableBean> result = logindb.login_DB_mysql_Table_TYPE(db);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("id", result.get(0).getName());
    }

    @Test
    void testLogin_DB_Mysql_TABLE() throws Exception {
        // Given
        Db db = new Db();
        db.setDbname("test_db");

        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("table_name")).thenReturn("test_table");
        when(mockResultSet.getString("TABLE_COMMENT")).thenReturn("Test Table");

        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockConnection.createStatement()).thenReturn(mockStatement);

        // When
        List<DataBaseBean> result = logindb.login_DB_Mysql_TABLE(db);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("test_table", result.get(0).getTablename());
    }

    @Test
    void testColseDb() throws Exception {
        // Given
        doNothing().when(mockResultSet).close();
        doNothing().when(mockStatement).close();
        doNothing().when(mockConnection).close();

        // When
        logindb.colseDb();

        // Then
        verify(mockResultSet, times(1)).close();
        verify(mockStatement, times(1)).close();
        verify(mockConnection, times(1)).close();
    }
}
