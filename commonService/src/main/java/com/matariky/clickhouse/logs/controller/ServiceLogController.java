package com.matariky.clickhouse.logs.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.excel.util.DateUtils;
import com.github.pagehelper.PageInfo;
import com.matariky.clickhouse.logs.entity.ServiceLog;
import com.matariky.clickhouse.logs.service.IServiceLogService;
import com.matariky.utils.AjaxResult;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/traces/services")
public class ServiceLogController {

    @Autowired
    private IServiceLogService logsService;

    @ApiOperation("Pagination ")
    @GetMapping("/list")
    public AjaxResult list(HttpServletRequest request, ServiceLog logs) {
        Integer perPage = Integer.parseInt(request.getParameter("perPage"));
        Integer index = Integer.parseInt(request.getParameter("index"));
        if (logs.getTimeStart() != null) {
            Date startD = new Date(Long.parseLong(logs.getTimeStart()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startD);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            logs.setTimeStart(DateUtils.format(calendar.getTime(), DateUtils.DATE_FORMAT_19));
       }
        
        if (logs.getTimeEnd() != null) {
            Date endD = new Date(Long.parseLong(logs.getTimeEnd()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endD);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            logs.setTimeEnd(DateUtils.format(calendar.getTime(), DateUtils.DATE_FORMAT_19));
            endD.setMinutes(59);
            endD.setSeconds(59);
            logs.setTimeEnd(DateUtils.format(endD, DateUtils.DATE_FORMAT_19));
        }
        Long count = logsService.getAppTracesCount(logs);
        PageInfo<ServiceLog> page = new PageInfo<>(logsService.getTracesAll(logs, index, perPage));
        page.setTotal(count);
        page.setPageNum(index);
        page.setPageSize(perPage);

        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

    @ApiOperation(" Export ")
    @GetMapping("/export")
    public AjaxResult export(ServiceLog logs) {
        if (logs.getTimeStart() != null) {
            Date startD = new Date(Long.parseLong(logs.getTimeStart()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startD);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            logs.setTimeStart(DateUtils.format(calendar.getTime(), DateUtils.DATE_FORMAT_19));
        }

        if (logs.getTimeEnd() != null) {
            Date endD = new Date(Long.parseLong(logs.getTimeEnd()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endD);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            endD = calendar.getTime();
            logs.setTimeEnd(DateUtils.format(endD, DateUtils.DATE_FORMAT_19));
        }
        logsService.export(logs);

        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

}
