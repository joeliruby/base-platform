package com.matariky.jobs.jobsService.dbbackup;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.matariky.commonservice.backup.service.SystemMysqlBackupsService;

@SpringBootTest
public class DBBackupJobTest {

    @InjectMocks
    private DBBackupJob dbbackupjob;

    @Mock
    private SystemMysqlBackupsService systemMysqlBackupsService;

    @Mock
    private JobExecutionContext jobExecutionContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() throws JobExecutionException {
        doNothing().when(systemMysqlBackupsService).mysqlBackups();

        dbbackupjob.execute(jobExecutionContext);

        verify(systemMysqlBackupsService, times(1)).mysqlBackups();
    }

    // Add more test methods for other methods in DBBackupJob
}
