package com.matariky.commonservice.backup.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;

@SpringBootTest
public class SystemMysqlBackupsTest {

    @InjectMocks
    private SystemMysqlBackups systemMysqlBackups;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String mysqlIp = "127.0.0.1";
        String mysqlPort = "3306";
        String databaseName = "test_db";
        String mysqlCmd = "backup_cmd";
        String mysqlBackCmd = "restore_cmd";
        String backupsPath = "/backup/path";
        String backupsName = "backup.sql";
        Integer operation = 1;
        Integer status = 1;
        Date recoveryTime = new Date();
        Date createTime = new Date();

        // When
        systemMysqlBackups.setId(id);
        systemMysqlBackups.setMysqlIp(mysqlIp);
        systemMysqlBackups.setMysqlPort(mysqlPort);
        systemMysqlBackups.setDatabaseName(databaseName);
        systemMysqlBackups.setMysqlCmd(mysqlCmd);
        systemMysqlBackups.setMysqlBackCmd(mysqlBackCmd);
        systemMysqlBackups.setBackupsPath(backupsPath);
        systemMysqlBackups.setBackupsName(backupsName);
        systemMysqlBackups.setOperation(operation);
        systemMysqlBackups.setStatus(status);
        systemMysqlBackups.setRecoveryTime(recoveryTime);
        systemMysqlBackups.setCreateTime(createTime);

        // Then
        assertThat(systemMysqlBackups.getId()).isEqualTo(id);
        assertThat(systemMysqlBackups.getMysqlIp()).isEqualTo(mysqlIp);
        assertThat(systemMysqlBackups.getMysqlPort()).isEqualTo(mysqlPort);
        assertThat(systemMysqlBackups.getDatabaseName()).isEqualTo(databaseName);
        assertThat(systemMysqlBackups.getMysqlCmd()).isEqualTo(mysqlCmd);
        assertThat(systemMysqlBackups.getMysqlBackCmd()).isEqualTo(mysqlBackCmd);
        assertThat(systemMysqlBackups.getBackupsPath()).isEqualTo(backupsPath);
        assertThat(systemMysqlBackups.getBackupsName()).isEqualTo(backupsName);
        assertThat(systemMysqlBackups.getOperation()).isEqualTo(operation);
        assertThat(systemMysqlBackups.getStatus()).isEqualTo(status);
        assertThat(systemMysqlBackups.getRecoveryTime()).isEqualTo(recoveryTime);
        assertThat(systemMysqlBackups.getCreateTime()).isEqualTo(createTime);
    }

    @Test
    void testToString() {
        // Given
        systemMysqlBackups.setId(1L);
        systemMysqlBackups.setMysqlIp("127.0.0.1");
        systemMysqlBackups.setMysqlPort("3306");
        systemMysqlBackups.setDatabaseName("test_db");
        systemMysqlBackups.setMysqlCmd("backup_cmd");
        systemMysqlBackups.setMysqlBackCmd("restore_cmd");
        systemMysqlBackups.setBackupsPath("/backup/path");
        systemMysqlBackups.setBackupsName("backup.sql");
        systemMysqlBackups.setOperation(1);
        systemMysqlBackups.setStatus(1);
        systemMysqlBackups.setRecoveryTime(new Date());
        systemMysqlBackups.setCreateTime(new Date());

        // When
        String result = systemMysqlBackups.toString();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).contains("SystemMysqlBackups");
    }

    // Add more test methods for other methods in SystemMysqlBackups
}
