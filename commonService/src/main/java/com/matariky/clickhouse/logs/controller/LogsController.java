package com.matariky.clickhouse.logs.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.matariky.clickhouse.logs.entity.Logs;
import com.matariky.clickhouse.logs.service.ILogsService;
import com.matariky.utils.AjaxResult;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/traces/v1")
public class LogsController {

	@Autowired
	private ILogsService logsService;

	@ApiOperation("Pagination ")
    @GetMapping("/list")
    public AjaxResult list(HttpServletRequest request, Logs vo) {
		Integer perPage= Integer.parseInt(request.getParameter("perPage"));
		Integer index = Integer.parseInt(request.getParameter("index"));
		Long count = logsService.getAppTracesCount(vo);
        PageInfo<Logs> page = new PageInfo<>(logsService.getTracesAll(vo,index, perPage));
        page.setTotal(count);
        page.setPageNum(index);
        page.setPageSize(perPage);

        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }


    @ApiOperation(" Export ")
    @GetMapping("/export")
    public AjaxResult export(HttpServletRequest request,Logs logs) {
        Integer perPage= Integer.parseInt(request.getParameter("perPage"));
        Integer index = Integer.parseInt(request.getParameter("index"));
        logsService.export(logs,index,perPage);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

}
