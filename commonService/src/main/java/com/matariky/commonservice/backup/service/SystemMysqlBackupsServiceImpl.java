package com.matariky.commonservice.backup.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.matariky.commonservice.backup.bean.SystemMysqlBackups;
import com.matariky.commonservice.backup.mapper.SystemMysqlBackupsMapper;
import com.matariky.commonservice.minio.utils.MinioUtil;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.constant.BackupConstant;
import com.matariky.exception.QslException;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SystemMysqlBackupsServiceImpl extends ServiceImpl<SystemMysqlBackupsMapper, SystemMysqlBackups> implements SystemMysqlBackupsService {

    @Resource
    private SystemMysqlBackupsMapper systemMysqlBackupsMapper;
    
    /**
     * Database Username
     */
    @Value("${spring.datasource.dynamic.datasource.master.username:''}")
    public String userName;

    /**
     * Database Password
     */
    @Value("${spring.datasource.dynamic.datasource.master.password:''}")
    public String password;

    /**
     * Database URL
     */
    @Value("${spring.datasource.dynamic.datasource.master.url:''}")
    private String url;

    @Value("${spring.datasource.minio-path:''}")
    private String minioPath;

    public Page<SystemMysqlBackups> selectBackupsList() {
        return systemMysqlBackupsMapper.selectBackupsList();
    }
    
    @Autowired
    MinioUtil minioUtil;

    public Object mysqlBackups() {
        
        SystemMysqlBackups smb = new SystemMysqlBackups();
        // Get IP
        final String ip = url.split(":")[2].substring(2);
        // Get Port Number
        final String port = url.split(":")[3].split("\\/")[0];
        // Get Database Name
        final String database_name = url.split("\\/")[3].split("\\?")[0];
        // Database File Name
        StringBuilder mysqlFileName = new StringBuilder()
                .append(database_name)
                .append("_")
                .append(DateUtil.format(new Date(), "yyyy-MM-dd-HH-mm-ss"))
                .append(BackupConstant.FILE_SUFFIX);
        // Backup Command
        StringBuilder cmd = new StringBuilder()
                .append("mysqldump ")
                .append("--no-tablespaces ")
                .append("-h")
                .append(ip)
                .append(" -u")
                .append(userName)
                .append(" -p")
                .append(password)
                .append(" -P ")
                .append(port)
                // Exclude MySQL Backup Table
                .append(" --ignore-table ")
                .append(database_name)
                .append(".common_mysql_backups ")
                .append(" --single-transaction --databases ")
                .append(database_name);
        
        log.error("Database backup command: {}", cmd);
        // Get Runtime Instance
        Process process = null;
        InputStream sqlFileStream = null;
        try {
            process = Runtime.getRuntime().exec(cmd.toString());
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            List<ByteArrayInputStream> baisList = new ArrayList<>();
            // Read Output
            String line;
            while ((line = reader.readLine()) != null) {
                baisList.add(new ByteArrayInputStream(line.getBytes()));
            }
            process.waitFor();
            sqlFileStream = new SequenceInputStream(Collections.enumeration(baisList));
            log.info("MySQL Database backup successful, backup file name: {}", mysqlFileName);
                
            // Backup information stored in database
            smb.setMysqlIp(ip);
            smb.setMysqlPort(port);
            smb.setBackupsName(String.valueOf(mysqlFileName));
            smb.setDatabaseName(database_name);
            smb.setMysqlCmd(String.valueOf(cmd));
            smb.setBackupsPath(minioPath);
            smb.setCreateTime(DateTime.now());
            smb.setStatus(1);
            smb.setOperation(0);
            systemMysqlBackupsMapper.insert(smb);
            minioUtil.createBucket("dbbackup");
            minioUtil.uploadFile(sqlFileStream, "dbbackup", mysqlFileName.toString());
            reader.close(); 
        } catch (Exception e) {
            e.printStackTrace();
            if (sqlFileStream != null) {
                try {
                    sqlFileStream.close();
                } catch (IOException e1) {
                    throw new QslException(MessageKey.NETWORK_DISRUPTION);
                }
            }
            throw new QslException(MessageKey.NETWORK_DISRUPTION);
        } finally {
            if (sqlFileStream != null) {
                try {
                    sqlFileStream.close();
                } catch (IOException e1) {
                    throw new QslException(MessageKey.NETWORK_DISRUPTION);
                }
            }
        }
        return smb;
    }

    @Override
    public SystemMysqlBackups selectListId(Long id) {
        return systemMysqlBackupsMapper.selectListId(id);
    }

    @Override
    public Object rollback(SystemMysqlBackups smb) {
        // Backup Path and File Name
        StringBuilder realFilePath = new StringBuilder().append(smb.getBackupsPath()).append(smb.getBackupsName());
        if (!FileUtil.exist(String.valueOf(realFilePath))) {
            throw new QslException(MessageKey.FILE_NOT_EXIST);
        }
        StringBuilder cmd = new StringBuilder()
                .append("mysql -h")
                .append(smb.getMysqlIp())
                .append(" -u")
                .append(userName)
                .append(" -p")
                .append(password)
                .append(" ")
                .append(smb.getDatabaseName())
                .append(" < ")
                .append(realFilePath);
        String[] command = new String[0];
        log.error("Database restore command: {}", cmd);
        String osName = System.getProperty("os.name").toLowerCase();
        if (BackupConstant.isSystem(osName)) {
            // Windows
            command = new String[]{"cmd", "/c", String.valueOf(cmd)};
        } else {
            // Linux
            command = new String[]{"/bin/sh", "-c", String.valueOf(cmd)};
        }
        // Restore command written to database
        smb.setMysqlBackCmd(String.valueOf(cmd));
        // Update operation count
        smb.setRecoveryTime(DateTime.now());
        smb.setOperation(smb.getOperation() + 1);
        // Get Runtime Instance
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            if (process.waitFor() == 0) {
                log.error("MySQL Database restore successful, restore file name: {}", realFilePath);
            } else {
                throw new QslException(MessageKey.NETWORK_DISRUPTION);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new QslException(MessageKey.NETWORK_DISRUPTION);
        }
        return smb;
    }
}


