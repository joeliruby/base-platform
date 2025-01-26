package com.matariky.commonservice.base.controller;

import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.service.BasicBaseRfidBindingService;
import com.matariky.commonservice.base.vo.*;
import com.matariky.utils.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
@Api(value = " Label 绑定 Information ", tags = " Label 绑定 Information ")
public class BasicBaseRfidBindingController {

    @Value("${message.locale}")
    String locale;

    @Autowired
    private BasicBaseRfidBindingService basicBaseRfidBindingService;


    /**
     * Pagination 
     *
     * @param listVO
     * @return
     */
    @RequestMapping("/basicBaseRfidBinding/list")
    public AjaxResult list(BasicBaseRfidBindingListVO listVO) {
        PageInfo<BasicBaseRfidBindingInfoVO> page = new PageInfo(basicBaseRfidBindingService.getBasicBaseRfidBindingAll(listVO));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

    @ApiOperation("New-单个绑定")
    @PostMapping(value = "/basicBaseRfidBinding")
    public AjaxResult save(@RequestBody @Validated BasicBaseRfidBindingAddVO addVO) {
        basicBaseRfidBindingService.createBasicBaseRfidBindingWithOrg(addVO);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation("New-多个相同 Item 绑定")
    @PostMapping(value = "/basicBaseRfidBinding/batch")
    public AjaxResult save(@RequestBody @Validated BasicBaseRfidBindingBatchAddVO addVO) {
        basicBaseRfidBindingService.createBasicBaseRfidBindingWithOrgBatch(addVO);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation("编辑")
    @PutMapping(value = "/basicBaseRfidBinding")
    public AjaxResult update(@RequestBody @Validated BasicBaseRfidBindingUpdateVO updateVO) {
        basicBaseRfidBindingService.updateBasicBaseRfidBinding(updateVO);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/basicBaseRfidBinding/{id}")
    public AjaxResult del(@PathVariable String id) {
        basicBaseRfidBindingService.deleteById(Long.parseLong(id));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }


    @ApiOperation("获取 Label 自增编码")
    @GetMapping(value = "/code")
    public AjaxResult getRfidCode() {
        Long rfidCode = basicBaseRfidBindingService.getRfidCode();
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS,rfidCode);
    }

}
