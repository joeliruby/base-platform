package com.matariky.jobs.jobsService.assetitm.base.job;

import com.matariky.commonservice.base.bean.BasicBaseDevice;
import com.matariky.commonservice.base.bean.BasicBaseDevicePackage;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceMapper;
import com.matariky.commonservice.base.mapper.BasicBaseDevicePackageMapper;
import com.matariky.commonservice.base.service.CommonService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.commonservice.upload.utils.SpringContextUtils;
import com.matariky.exception.QslException;
import com.matariky.jobs.jobsService.job.base.BaseJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Order expire JOB
 */
public class DeviceUpgradeJob implements BaseJob {

    private static final BasicBaseDevicePackageMapper basicBaseDevicePackageMapper = SpringContextUtils
            .getBean(BasicBaseDevicePackageMapper.class);
    private static final BasicBaseDeviceMapper basicBaseDeviceMapper = SpringContextUtils
            .getBean(BasicBaseDeviceMapper.class);
    private static final CommonService commonService = SpringContextUtils.getBean(CommonService.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("implement Device  Upgrade  Scheduled  Task ");
        Long packageId = Long.valueOf(context.getJobDetail().getJobDataMap().get("packageId").toString());
        Long deviceId = Long.valueOf(context.getJobDetail().getJobDataMap().get("deviceId").toString());
        BasicBaseDevicePackage devicePackage = basicBaseDevicePackageMapper.selectById(packageId);
        if (devicePackage == null) {
            throw new QslException(MessageKey.DEVICE_PACKAGE_NOT_EXIST);
        }
        BasicBaseDevice device = basicBaseDeviceMapper.selectById(deviceId);
        /** 向karaf发送 Upgrade Command **/
        commonService.deviceUpgrade(devicePackage.getMd5(), devicePackage.getPackageVersion(), device.getDeviceCode());
    }
}
