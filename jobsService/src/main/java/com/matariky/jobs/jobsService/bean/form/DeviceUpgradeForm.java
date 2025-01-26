package com.matariky.jobs.jobsService.bean.form;


import lombok.Data;

@Data
public class DeviceUpgradeForm {

    private Long packageId;

    private String cronExpression;

    private String jobClassName;

    private String jobGroupName;

    private Long deviceId;

    private Long upgradeTime;
}
