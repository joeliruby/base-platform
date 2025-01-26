package com.matariky.commonservice.base.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class ExcelService {

    @Autowired
    private BasicBaseDeviceTypeService basicBaseDevicetypeService;

    @Autowired
    private BasicBaseDeviceService basicBaseDeviceService;


}
