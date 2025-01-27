package com.matariky.commonservice.base.controller;

import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.bean.BasicBaseDeviceType;
import com.matariky.commonservice.base.service.BasicBaseDeviceTypeService;
import com.matariky.commonservice.base.vo.*;
import com.matariky.utils.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
@Api(value = " Device   Base Type ", tags = " Device   Base Type ")
public class BasicBaseDeviceTypeController {

    @Autowired
    private BasicBaseDeviceTypeService basicBaseDevicetypeService;

    @ApiOperation(value = "Pagination", response = BasicBaseDeviceType.class)
    @GetMapping("/basicBaseDevicetype/list")
    public AjaxResult list(BasicBaseDeviceTypeListVO vo) {
        PageInfo<BasicBaseDeviceTypeInfoVO> page = new PageInfo(basicBaseDevicetypeService.getBasicBaseDevicetypeAll(vo));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

    @ApiOperation(value = "New")
    @PostMapping(value = "/basicBaseDevicetype")
    public AjaxResult save(@RequestBody @Validated BasicBaseDeviceTypeAddVO addVO, @RequestHeader("Authorization") String jwt) {
        basicBaseDevicetypeService.createBasicBaseDevicetypeWithOrg(addVO, jwt);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation(value = "Update")
    @PutMapping(value = "/basicBaseDevicetype")
    public AjaxResult update(@RequestBody BasicBaseDeviceTypeUpdateVO updateVO, @RequestHeader("Authorization") String jwt) {
        basicBaseDevicetypeService.updateBasicBaseDevicetype(updateVO, jwt);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation(value = "Update Status ")
    @PutMapping(value = "/basicBaseDevicetype/status")
    public AjaxResult update(@RequestBody @Validated BasicBaseDevicetypeStatusVO vo) {
        basicBaseDevicetypeService.updateBasicBaseDevicetypeStatus(vo);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }


    @ApiOperation(value = "Delete ")
    @DeleteMapping(value = "/basicBaseDevicetype/{id}")
    public AjaxResult del(@PathVariable Long id) {
        basicBaseDevicetypeService.delBasicBaseDevicetypeById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation(value = "Query Detail By ID")
    @GetMapping(value = "/basicBaseDevicetype/{id}")
    public AjaxResult getOne(@PathVariable Long id) {
        BasicBaseDeviceTypeInfo type = basicBaseDevicetypeService.getBasicBaseDevicetypeById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, type);
    }


    @ApiOperation(value = " Device Type   Drop Down Box")
    @GetMapping(value = "/basicBaseDevicetype/option")
    public AjaxResult optionList() {
        List<DeviceTypeOption> list = basicBaseDevicetypeService.getOptionList();
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, list);
    }

}
