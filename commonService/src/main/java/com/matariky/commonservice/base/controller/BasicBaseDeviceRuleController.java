package com.matariky.commonservice.base.controller;

import com.matariky.commonservice.base.bean.BasicBaseDeviceRuleDetail;
import com.matariky.commonservice.base.service.BasicBaseDeviceRuleDetailService;
import com.matariky.commonservice.base.service.BasicBaseDeviceRuleService;
import com.matariky.commonservice.base.vo.*;
import com.matariky.utils.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
@Api(value = " Device  Rule  Configuration ", tags = " Device  Rule  Configuration ")
public class BasicBaseDeviceRuleController {

    @Autowired
    private BasicBaseDeviceRuleService basicBaseDeviceruleService;
    @Autowired
    private BasicBaseDeviceRuleDetailService basicBaseDeviceruleDetailService;


    @ApiOperation(" Rule  Detail Pagination ")
    @RequestMapping("/basicBaseDeviceruleDetail/list")
    public AjaxResult list(@Validated BasicBaseDeviceRuleDetailListVO vo, @ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt) {
        BasicBaseDeviceRuleDetailInfo data = basicBaseDeviceruleDetailService.getBasicBaseDeviceruleDetailAll(vo, jwt);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, data);
    }

    @ApiOperation("New Rule  Configuration ")
    @PostMapping(value = "/basicBaseDevicerule")
    public AjaxResult save(@RequestBody @Validated BasicBaseDeviceRuleAddVO addVO, @ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt) {
        Long ruleId = basicBaseDeviceruleService.createBasicBaseDeviceruleWithOrg(addVO, jwt);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, ruleId);
    }

    @ApiOperation("  Update Rule  Configuration ")
    @PutMapping(value = "/basicBaseDevicerule")
    public AjaxResult update(@RequestBody @Validated BasicBaseDeviceRuleUpdateVO updateVO, @ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt) {
        basicBaseDeviceruleService.updateBasicBaseDevicerule(updateVO, jwt);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation("New Rule  Detail")
    @PostMapping(value = "/basicBaseDeviceruleDetail")
    public AjaxResult saveDetail(@RequestBody @Validated BasicBaseDeviceRuleDetailAddVO addVO, @ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt) {
        basicBaseDeviceruleService.createBasicBaseDeviceruleDetailWithOrg(addVO, jwt);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation("  Update Rule  Detail")
    @PutMapping(value = "/basicBaseDeviceruleDetail")
    public AjaxResult update(@RequestBody BasicBaseDeviceRuleDetailUpdateByIdVO updateVO, @ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt) {
        basicBaseDeviceruleDetailService.updateBasicBaseDeviceruleDetail(updateVO, jwt);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }


    @ApiOperation("Delete  Rule  Detail")
    @DeleteMapping(value = "/basicBaseDeviceruleDetail/{id}")
    public AjaxResult del(@PathVariable Long id) {
        basicBaseDeviceruleDetailService.delBasicBaseDeviceruleDetailById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation(value = "Query Detail By ID")
    @GetMapping(value = "/basicBaseDeviceruleDetail/{id}")
    public AjaxResult getOne(@PathVariable Long id) {
        BasicBaseDeviceRuleDetail info = basicBaseDeviceruleDetailService.selectById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, info);
    }

}
