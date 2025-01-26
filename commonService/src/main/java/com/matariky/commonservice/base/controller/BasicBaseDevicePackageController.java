package com.matariky.commonservice.base.controller;

import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.bean.BasicBaseDevicePackage;
import com.matariky.commonservice.base.service.BasicBaseDevicePackageService;
import com.matariky.commonservice.base.vo.BasicBaseDevicePackageAddVO;
import com.matariky.commonservice.base.vo.BasicBaseDevicePackageInfoVO;
import com.matariky.commonservice.base.vo.BasicBaseDevicePackageUpdateVO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceUpgradeListVO;
import com.matariky.utils.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value = " Device 固件包", tags = " Device 固件包")
public class BasicBaseDevicePackageController {


    @Autowired
    private BasicBaseDevicePackageService basicBaseDevicepackageService;


    @ApiOperation(value = "Pagination ")
    @GetMapping("/basicBaseDevicepackage/list")
    public AjaxResult list(BasicBaseDeviceUpgradeListVO vo) {
        PageInfo<BasicBaseDevicePackageInfoVO> page = new PageInfo(basicBaseDevicepackageService.getBasicBaseDevicepackageAll(vo));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }


    @ApiOperation(value = "New")
    @PostMapping(value = "/basicBaseDevicepackage")
    public AjaxResult save(@RequestParam("file") MultipartFile file, @Validated BasicBaseDevicePackageAddVO addVO) {
        basicBaseDevicepackageService.createBasicBaseDevicepackageWithOrg(addVO, file);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "/basicBaseDevicepackage")
    public AjaxResult update(@RequestParam(value = "file", required = false) MultipartFile file, @Validated BasicBaseDevicePackageUpdateVO updateVO,
                             @ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt) {
        basicBaseDevicepackageService.updateBasicBaseDevicepackage(updateVO, file, jwt);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/basicBaseDevicepackage/{id}")
    public AjaxResult del(@PathVariable Long id) {
        basicBaseDevicepackageService.delBasicBaseDevicepackageById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation(value = "Query Detail By ID")
    @GetMapping(value = "/basicBaseDevicepackage/{id}")
    public Object getOne(@PathVariable Long id) {
        BasicBaseDevicePackage info = basicBaseDevicepackageService.selectById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, info);
    }

}
