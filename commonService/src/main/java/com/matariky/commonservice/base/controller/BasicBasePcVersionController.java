package com.matariky.commonservice.base.controller;

import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.bean.BasicBasePcVersion;
import com.matariky.commonservice.base.service.BasicBasePcVersionService;
import com.matariky.commonservice.base.vo.*;
import com.matariky.utils.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
@Api(value = "PC Version Information ", tags = "PC Version Information ")
public class BasicBasePcVersionController {

    @Autowired
    private BasicBasePcVersionService basicBasePcversionService;

    @ApiOperation("Pagination ")
    @RequestMapping("/basicBasePcversion/list")
    public AjaxResult list(BasicBasePcVersionQueryVO vo) {
        PageInfo<BasicBasePcVersionListVO> page = new PageInfo<>(
                basicBasePcversionService.getBasicBasePcversionAll(vo));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

    @ApiOperation("New")
    @PostMapping(value = "/basicBasePcversion")
    public AjaxResult save(@RequestBody @Validated BasicBasePcVersionAddVO addVO) {
        basicBasePcversionService.createBasicBasePcversionWithOrg(addVO);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation("  Update")
    @PutMapping(value = "/basicBasePcversion")
    public AjaxResult update(@RequestBody @Validated BasicBasePcVersionUpdateVO updateVO) {
        basicBasePcversionService.updateBasicBasePcversion(updateVO);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation("Delete ")
    @DeleteMapping(value = "/basicBasePcversion/{id}")
    public AjaxResult del(@PathVariable Long id) {
        basicBasePcversionService.delBasicBasePcversionById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation("批量Delete ")
    @DeleteMapping(value = "/basicBasePcversion")
    public AjaxResult del(@RequestBody @Validated BasicBasePcVersionDelVO vo) {
        basicBasePcversionService.delBasicBasePcversionByIds(vo.getIds());
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation(value = "Query Detail By ID")
    @GetMapping(value = "/basicBasePcversion/{id}")
    public AjaxResult getOne(@PathVariable Long id) {
        BasicBasePcVersion info = basicBasePcversionService.selectById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, info);
    }

}
