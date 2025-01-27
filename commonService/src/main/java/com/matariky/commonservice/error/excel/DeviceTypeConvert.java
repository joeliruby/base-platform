package com.matariky.commonservice.error.excel;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.mapper.CommonDictMapper;
import com.matariky.commonservice.commondict.mapper.CommonDictTypeMapper;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

public class DeviceTypeConvert implements Converter<String> {

    @Override
    public WriteCellData<?> convertToExcelData(String value, ExcelContentProperty contentProperty,
            GlobalConfiguration globalConfiguration) throws Exception {
        ApplicationContext applicationContext = SpringUtil.getApplicationContext();
        CommonDictTypeMapper commonDictTypeMapper = applicationContext.getBean("commonDictTypeMapper",
                CommonDictTypeMapper.class);
        CommonDictMapper commonDictMapper = applicationContext.getBean("commonDictMapper", CommonDictMapper.class);
        CommonDictType dictType = commonDictTypeMapper.selectOne(Wrappers.lambdaQuery(CommonDictType.class)
                .eq(CommonDictType::getDictTypeKey, "CN_DEVICE_TYPE_CODE")
                .eq(CommonDictType::getIsActive, 1));
        String result = "";
        if (dictType == null) {
            return new WriteCellData<>(result);
        }
        List<CommonDict> deviceTypeCode = commonDictMapper.selectList(Wrappers.lambdaQuery(CommonDict.class)
                .eq(CommonDict::getDictTypeId, dictType.getId())
                .eq(CommonDict::getIsActive, 1)
                .eq(CommonDict::getDeleteTime, 0));
        List<CommonDict> list = deviceTypeCode.stream().filter(item -> {
            return item.getDictValue().equals(value);
        }).collect(Collectors.toList());
        if (list.isEmpty()) {
            return new WriteCellData<>(result);
        }
        result = list.get(0).getDictName();
        return new WriteCellData<String>(result);
    }
}
