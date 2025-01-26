package com.matariky.commonservice.base.controller;

import com.matariky.commonservice.base.service.BasicBaseDeviceUpgradeService;
import com.matariky.commonservice.base.vo.*;
import com.matariky.utils.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controller
 *
 * @author AUTOMATION
 */
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
@Api(value = " Device 升级", tags = " Device 升级")
public class BasicBaseDeviceUpgradeController {

    @Value("${message.locale}")
    String locale;
    @Autowired
    private BasicBaseDeviceUpgradeService basicBaseDeviceupgradeService;


    @ApiOperation(value = "升级列表")
    @GetMapping("basicBaseDeviceupgrade/list")
    public AjaxResult list(UpgradeListVO vo) {
        List<BasicBaseDeviceUpgradeResVO> list = basicBaseDeviceupgradeService.list(vo);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, list);
    }


    @ApiOperation(value = "升级 Device ")
    @PostMapping(value = "/basicBaseDeviceupgrade")
    public AjaxResult save(@RequestBody @Validated BasicBaseDeviceUpgradeAddDTO addDTO) {
        basicBaseDeviceupgradeService.createBasicBaseDeviceupgradeWithOrg(addDTO);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation(value = "获取已经升级过包的 Device 列表")
    @GetMapping(value = "/upgradeDeviceList/{packageId}")
    public AjaxResult upgradeDeviceList(@PathVariable Long packageId) {
        List<UpgradeDeviceVO> list = basicBaseDeviceupgradeService.upgradeDeviceList(packageId);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, list);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/upgradeDeviceList")
    public AjaxResult del(DeviceUpgradeListVO vo) {
        basicBaseDeviceupgradeService.delete(vo);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }
}
