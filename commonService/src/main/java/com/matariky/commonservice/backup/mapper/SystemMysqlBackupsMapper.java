package com.matariky.commonservice.backup.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.matariky.commonservice.backup.bean.SystemMysqlBackups;


public interface SystemMysqlBackupsMapper extends BaseMapper<SystemMysqlBackups> {

    /**
     *  Query All Backups Data 
     */
    Page<SystemMysqlBackups> selectBackupsList();

    /**
     *  Query By ID 
     */
    SystemMysqlBackups selectListId(@Param("id") Long id);
}

