package com.matariky.commonservice.base.controller;

import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.bean.BasicBaseGoods;
import com.matariky.commonservice.base.service.BasicBaseGoodsService;
import com.matariky.commonservice.base.vo.BasicBaseGoodsListVO;
import com.matariky.commonservice.base.vo.GoodsOptionInfo;
import com.matariky.utils.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
@Api(value = "基础 Item  Information ", tags = "基础 Item  Information ")
public class BasicBaseGoodsController {

    @Autowired
    private BasicBaseGoodsService basicBaseGoodsService;

    @ApiOperation(value = "Pagination", response = BasicBaseGoods.class)
    @GetMapping("/basicBaseGoods/list")
    public AjaxResult list(BasicBaseGoodsListVO vo) {
        PageInfo<Map> pageInfo = basicBaseGoodsService.getBasicBaseGoodsAll(vo);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, pageInfo);
    }

    @ApiOperation(value = "New")
    @PostMapping(value = "/basicBaseGoods")
    public AjaxResult save(@Validated @RequestBody HashMap addVO) {
        basicBaseGoodsService.createBasicBaseGoodsWithOrg(addVO);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }


    @ApiOperation(value = "Update")
    @PutMapping(value = "/basicBaseGoods")
    public AjaxResult update(@Validated @RequestBody HashMap updateVO) {
        basicBaseGoodsService.updateBasicBaseGoods(updateVO);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/basicBaseGoods")
    public AjaxResult del(@RequestParam("id") Long id, @RequestParam(value = "extendId", required = false) Long extendId) {
        basicBaseGoodsService.delBasicBaseGoodsById(id, extendId);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation(value = "Query Detail By ID")
    @GetMapping(value = "/basicBaseGoods")
    public AjaxResult getOne(@RequestParam("id") Long id, @RequestParam(value = "extendId", required = false) Long extendId) {
        Map<String, Object> map = basicBaseGoodsService.getBasicBaseGoodsById(id, extendId);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, map);
    }


    @ApiOperation(value = " Item 下拉选")
    @GetMapping("/basicBaseGoods/option")
    public AjaxResult list() {
        List<GoodsOptionInfo> list = basicBaseGoodsService.optionList();
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, list);
    }

}
