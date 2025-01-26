package com.matariky.jobs.jobsService.util;

import com.matariky.jobs.jobsService.job.base.BaseJob;

/**
 * <p>
 * 定时 Task 反射工具类
 * </p>
 *
 * @package: com.matariky.jobs.jobsService.util
 * @description: 定时 Task 反射工具类
 * @version: V1.0
 */
public class JobUtil {
    /**
     * 根据全类名获取Job实例
     *
     * @param classname Job全类名
     * @return {@link BaseJob} 实例
     * @throws Exception 泛型获取异常
     */
    public static BaseJob getClass(String classname) throws Exception {
        Class<?> clazz = Class.forName(classname);
        return (BaseJob) clazz.getDeclaredConstructor(clazz).newInstance((Object[])null);
    }
}
