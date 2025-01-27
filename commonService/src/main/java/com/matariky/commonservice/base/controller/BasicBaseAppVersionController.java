package com.matariky.commonservice.base.controller;

import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.bean.BasicBaseAppVersion;
import com.matariky.commonservice.base.service.BasicBaseAppVersionService;
import com.matariky.commonservice.base.vo.*;
import com.matariky.utils.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller
 *
 * @author AUTOMATION
 */
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
@Api(value = "APP Version Information ", tags = "APP Version Information ")
public class BasicBaseAppVersionController {

    @Autowired
    private BasicBaseAppVersionService basicBaseAppversionService;

    /**
     * s
     * Pagination
     */
    @RequestMapping("/basicBaseAppversion/list")
    public AjaxResult list(BasicBaseAppVersionQueryVO vo) {
        PageInfo<BasicBaseAppVersionListVO> page = new PageInfo<>(
                basicBaseAppversionService.getBasicBaseAppversionAll(vo));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

    @ApiOperation("New")
    @PostMapping(value = "/basicBaseAppversion")
    public AjaxResult save(@Validated BasicBaseAppVersionAddVO addVO, @RequestParam("file") MultipartFile file) {
        basicBaseAppversionService.createBasicBaseAppversionWithOrg(addVO, file);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation("  Update")
    @PutMapping(value = "/basicBaseAppversion")
    public AjaxResult update(@Validated BasicBaseAppVersionUpdateVO updateVO,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        basicBaseAppversionService.updateBasicBaseAppversion(updateVO, file);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation("Delete ")
    @DeleteMapping(value = "/basicBaseAppversion/{id}")
    public AjaxResult del(@PathVariable Long id) {
        basicBaseAppversionService.delBasicBaseAppversionById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation(value = "Query Detail By ID")
    @GetMapping(value = "/basicBaseAppversion/{id}")
    public AjaxResult getOne(@PathVariable Long id) {
        BasicBaseAppVersion info = basicBaseAppversionService.selectById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, info);
    }

    @ApiOperation(value = " Query  Printer APP Version Information ")
    @GetMapping(value = "/basicBaseAppversion/getPrintApp")
    public AjaxResult getPrintApp() {
        return AjaxResult.success(basicBaseAppversionService.getBasicBasePrintApp());
    }

}
