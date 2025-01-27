package com.matariky.jobs.jobsService.util;

import com.matariky.jobs.jobsService.job.base.BaseJob;

/**
 * <p>
 * Scheduled Task reflection utility class
 * </p>
 *
 * @package: com.matariky.jobs.jobsService.util
 * @description: Scheduled Task reflection utility class
 * @version: V1.0
 */
public class JobUtil {
    /**
     * Retrieve Job instance based on full class name
     *
     * @param classname Full class name of the Job
     * @return {@link BaseJob} instance
     * @throws Exception Generic retrieval exception
     */
    public static BaseJob getClass(String classname) throws Exception {
        Class<?> clazz = Class.forName(classname);
        return (BaseJob) clazz.getDeclaredConstructor(clazz).newInstance((Object[]) null);
    }
}
