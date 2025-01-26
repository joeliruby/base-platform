package com.matariky.commonservice.backup.bean;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * ClassName: SystemMysqlBackups
 * Class Description: MySQL Backup Entity
 */
@Data
@TableName("common_mysql_backups")
public class SystemMysqlBackups {

    /**
     * Primary Key ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * MySQL Server IP Address
     */
    @TableField("mysql_ip")
    private String mysqlIp;

    /**
     * MySQL Server Port Number
     */
    @TableField("mysql_port")
    private String mysqlPort;

    /**
     * Database Name
     */
    @TableField("database_name")
    private String databaseName;

    /**
     * MySQL Backup Command
     */
    @TableField("mysql_cmd")
    private String mysqlCmd;

    /**
     * MySQL Restore Command
     */
    @TableField("mysql_back_cmd")
    private String mysqlBackCmd;

    /**
     * MySQL Backup Storage Path
     */
    @TableField("backups_path")
    private String backupsPath;

    /**
     * MySQL Backup File Name
     */
    @TableField("backups_name")
    private String backupsName;

    /**
     * Operation Count
     */
    @TableField("operation")
    private Integer operation;

    /**
     * Data Status
     */
    @TableField("status")
    private Integer status;

    /**
     * Recovery Time
     */
    @TableField("recovery_time")
    private Date recoveryTime;

    /**
     * Backup Time
     */
    @TableField("create_time")
    private Date createTime;
}

