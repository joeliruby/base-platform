package com.matariky.commonservice.base.controller;

import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.bean.BasicBaseCodingRules;
import com.matariky.commonservice.base.service.BasicBaseCodingRulesService;
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
@Api(value = "编码 Rule 设置", tags = "编码 Rule 设置")
public class BasicBaseCodingRulesController {

    @Autowired
    private BasicBaseCodingRulesService basicBaseCodingrulesService;

    @ApiOperation(value = "Pagination", response = BasicBaseCodingRulesInfoVO.class)
    @RequestMapping("/basicBaseCodingrules/list")
    public AjaxResult list(BasicBaseCodingRulesListVO vo) {
        PageInfo<BasicBaseCodingRulesInfoVO> page = new PageInfo(basicBaseCodingrulesService.getBasicBaseCodingrulesAll(vo));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

    @ApiOperation(value = "New")
    @PostMapping(value = "/basicBaseCodingrules")
    public AjaxResult save(@RequestBody @Validated BasicBaseCodingRulesAddVO addVO) {
        basicBaseCodingrulesService.createBasicBaseCodingrulesWithOrg(addVO);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }


    @ApiOperation(value = "获取 Rule 唯一编码")
    @GetMapping(value = "/basicBaseCodingrules/code")
    public AjaxResult getCodingrulesCode() {
        String codingrulesCode = basicBaseCodingrulesService.getCodingrulesCode();
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, codingrulesCode);
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "/basicBaseCodingrules")
    public AjaxResult update(@RequestBody @Validated BasicBaseCodingRulesUpdateVO updateVO) {
        basicBaseCodingrulesService.updateBasicBaseCodingrules(updateVO);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation(value = "修改 Status ")
    @PutMapping(value = "/basicBaseCodingrules/status")
    public AjaxResult update(@RequestBody @Validated BasicBaseCodingRulesUpdateStatusVO vo) {
        basicBaseCodingrulesService.updateBasicBaseCodingrulesStatus(vo);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/basicBaseCodingrules/{id}")
    public AjaxResult del(@PathVariable Long id) {
        basicBaseCodingrulesService.delBasicBaseCodingrulesById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);

    }

    @ApiOperation(value = "Query Detail By ID")
    @GetMapping(value = "/basicBaseCodingrules/{id}")
    public AjaxResult getOne(@PathVariable Long id) {
        BasicBaseCodingRules obj = basicBaseCodingrulesService.selectById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, obj);
    }

    @ApiOperation(value = "编码 Rule 下拉选")
    @GetMapping("/basicBaseCodingrules/option")
    public AjaxResult list() {
        List<CodingRulesOptionInfo> list = basicBaseCodingrulesService.optionList();
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, list);
    }

}
