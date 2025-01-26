package com.matariky.clickhouse.logs.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.matariky.clickhouse.logs.entity.ServiceLog;
import com.matariky.clickhouse.logs.mapper.ServiceLogMapper;
import com.matariky.clickhouse.logs.service.IServiceLogService;
import com.matariky.clickhouse.logs.vo.ServiceLogExcelVO;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.mapper.CommonDictMapper;
import com.matariky.commonservice.commondict.mapper.CommonDictTypeMapper;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;
import com.matariky.utils.BeanUtils;
import com.matariky.utils.StringUtils;
import com.matariky.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceLogServiceImpl extends ServiceImpl<ServiceLogMapper, ServiceLog> implements IServiceLogService {
    @Autowired
    private ServiceLogMapper logsMapper;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CommonDictTypeMapper commonDictTypeMapper;
    @Autowired
    private CommonDictMapper commonDictMapper;

    public Page<ServiceLog> getTracesAll(ServiceLog logs, Integer index, Integer perPage) {
        if (index != null && perPage != null) {
            Integer offSet = (index - 1) * perPage;
            logs.setOffSet(offSet);
        }
        logs.setPerPage(perPage);
        Page<ServiceLog> page = logsMapper.getAppTracesAll(logs);
        page.getResult().stream().forEach(item -> {
            if (item.getDurationNano() != null) {
                BigDecimal dur = item.getDurationNano().divide(new BigDecimal("1000000000"), 4, BigDecimal.ROUND_HALF_UP);
                item.setDurationNano(dur);
            }
        });
        return page;
    }

    @Override
    public Long getAppTracesCount(ServiceLog logs) {
        return logsMapper.getAppTracesCount(logs);
    }

    @Override
    public void export(ServiceLog logs) {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        Long count = logsMapper.getAppTracesCount(logs);
        CommonDictType dictType = commonDictTypeMapper.selectOne(Wrappers.lambdaQuery(CommonDictType.class)
                .eq(CommonDictType::getDictTypeKey, TokenUtils.extractLocaleForRequest(request) + "_EXPORT_DATA_MAX_ROW")
                .eq(CommonDictType::getIsActive, 1)
                .eq(CommonDictType::getTenantId, tenantId));
        CommonDict maxRow = commonDictMapper.selectOne(Wrappers.lambdaQuery(CommonDict.class)
                .eq(CommonDict::getDictTypeId, dictType.getId())
                .eq(CommonDict::getIsActive, 1)
                .eq(CommonDict::getTenantId, tenantId)
                .eq(CommonDict::getDictKey, "max_row")
                .eq(CommonDict::getDeleteTime, 0));
        if (count > Long.valueOf(maxRow.getDictValue())) {
            throw new QslException(MessageKey.EXPORT_DATA_LENG_OUT_SIZE);
        }
        Page<ServiceLog> page = this.getTracesAll(logs, null, null);
        List<ServiceLogExcelVO> newList = page.getResult().stream().map(item -> {
            ServiceLogExcelVO vo = new ServiceLogExcelVO();
            BeanUtils.copyProperties(item, vo);
            if ("[]".equals(vo.getErrorMessage())) {
                vo.setErrorMessage(StringUtils.EMPTY);
            }
            if (item.getHasError() != null) {
                if (item.getHasError()) {
                    vo.setHasError("是");
                } else {
                    vo.setHasError("否");
                }
            }
            if (StringUtils.isNotBlank(item.getTimestamp())) {
                vo.setTimestamp(item.getTimestamp().substring(0, 19));
            }
            return vo;
        }).collect(Collectors.toList());
        response.setContentType("application/vnd.ms-excel; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(System.currentTimeMillis() + ".xlsx", "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            EasyExcel.write(outputStream, ServiceLogExcelVO.class)
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet("服务日志")
                    .doWrite(newList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
