package com.matariky.clickhouse.logs.service.impl;

import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.DateUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.matariky.clickhouse.logs.entity.Logs;
import com.matariky.clickhouse.logs.mapper.LogsMapper;
import com.matariky.clickhouse.logs.service.ILogsService;
import com.matariky.clickhouse.logs.vo.LogsExcelVO;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.mapper.CommonDictMapper;
import com.matariky.commonservice.commondict.mapper.CommonDictTypeMapper;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;
import com.matariky.utils.BeanUtils;
import com.matariky.utils.TokenUtils;

@Service
public class LogsServiceImpl extends ServiceImpl<LogsMapper, Logs> implements ILogsService {

    @Autowired
    private LogsMapper logsMapper;
    @Autowired
    private HttpServletResponse response;

    @Autowired
    CommonDictTypeMapper commonDictTypeMapper;

    @Autowired
    CommonDictMapper commonDictMapper;

    @Autowired
    HttpServletRequest httpServletRequest;

    public Page<Logs> getTracesAll(Logs logs, Integer index, Integer perPage) {
        if (logs.getTimeStartLong() != null) {
            Date startD = new Date(logs.getTimeStartLong());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startD);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            startD = calendar.getTime();
            logs.setTimeStart(DateUtils.format(startD, DateUtils.DATE_FORMAT_19));
        }
        if (logs.getTimeEndLong() != null) {
            Date endD = new Date(logs.getTimeStartLong());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endD);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            endD = calendar.getTime();
            logs.setTimeEnd(DateUtils.format(endD, DateUtils.DATE_FORMAT_19));
        }

        Integer offSet = (index - 1) * perPage;
        logs.setOffSet(offSet);
        logs.setPerPage(perPage);
        Page<Logs> page = logsMapper.getAppTracesAll(logs);
        return page;
    }

    @Override
    public Long getAppTracesCount(Logs logs) {
        return logsMapper.getAppTracesCount(logs);
    }

    @Override
    public void export(Logs logs, Integer index, Integer perPage) {
        CommonDictType dictType = commonDictTypeMapper.selectOne(Wrappers.lambdaQuery(CommonDictType.class)
                .eq(CommonDictType::getDictTypeKey,
                        TokenUtils.extractLocaleForRequest(httpServletRequest) + "_EXPORT_DATA_MAX_ROW")
                .eq(CommonDictType::getIsActive, 1)
                .eq(CommonDictType::getTenantId, TokenUtils.extractTenantIdFromHttpRequest(httpServletRequest)));
        CommonDict maxRow = commonDictMapper.selectOne(Wrappers.lambdaQuery(CommonDict.class)
                .eq(CommonDict::getDictTypeId, dictType.getId())
                .eq(CommonDict::getIsActive, 1)
                .eq(CommonDict::getTenantId, TokenUtils.extractTenantIdFromHttpRequest(httpServletRequest))
                .eq(CommonDict::getDictKey, "max_row")
                .eq(CommonDict::getDeleteTime, 0));
        if (perPage > Long.valueOf(maxRow.getDictValue())) {
            throw new QslException(MessageKey.EXPORT_DATA_LENG_OUT_SIZE);
        }
        Page<Logs> page = getTracesAll(logs, index, perPage);
        List<LogsExcelVO> newList = page.getResult().stream().map(item -> {
            LogsExcelVO vo = new LogsExcelVO();
            BeanUtils.copyProperties(item, vo);
            vo.setTimestamp(item.getTimestamp().substring(0, 19));
            String hasError = vo.getHasError();
            if ("true".equals(hasError)) {
                vo.setHasError("是");
            } else if ("false".equals(hasError)) {
                vo.setHasError("否");
            }
            return vo;
        }).collect(Collectors.toList());

        response.setContentType("application/vnd.ms-excel; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(System.currentTimeMillis() + ".xlsx", "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            EasyExcel.write(outputStream, LogsExcelVO.class)
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet("运行 Log ")
                    .doWrite(newList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
