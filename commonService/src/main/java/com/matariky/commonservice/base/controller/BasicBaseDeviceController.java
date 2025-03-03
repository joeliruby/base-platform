package com.matariky.commonservice.base.controller;

import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.bean.BasicBaseDevice;
import com.matariky.commonservice.base.service.BasicBaseDeviceService;
import com.matariky.commonservice.base.vo.*;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.BeanUtils;
import com.matariky.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
@Api(value = " Device   Base  Information ", tags = " Device   Base  Information ")
public class BasicBaseDeviceController {

    @Autowired
    private BasicBaseDeviceService basicBaseDeviceService;

    /**
     * @param vo
     * @return
     */
    @ApiOperation("Pagination ")
    @RequestMapping("/basicBaseDevice/list")
    public AjaxResult list(BasicBaseDeviceListVO vo) {
        PageInfo<BasicBaseDeviceInfoVO> page = new PageInfo<>(basicBaseDeviceService.getBasicBaseDeviceAll(vo));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

    @ApiOperation("New")
    @PostMapping(value = "/basicBaseDevice")
    public AjaxResult save(@Validated @RequestBody BasicBaseDeviceAddVO addVO) {
        basicBaseDeviceService.createBasicBaseDeviceWithOrg(addVO);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation("  Update")
    @PutMapping(value = "/basicBaseDevice")
    public AjaxResult update(@Validated @RequestBody BasicBaseDeviceUpdateVO updateVO) {
        basicBaseDeviceService.updateBasicBaseDevice(updateVO);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation("Delete ")
    @DeleteMapping(value = "/basicBaseDevice/{id}")
    public AjaxResult del(@PathVariable Long id) {
        basicBaseDeviceService.delBasicBaseDeviceById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation("Download Import ")
    @GetMapping("/basicBaseDevice/downLoadTemplate")
    public AjaxResult downLoadTemplate() {
        basicBaseDeviceService.downLoadTemplate();
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation("Import Data ")
    @PostMapping("/basicBaseDevice/importData")
    public AjaxResult importData(@RequestParam("file") MultipartFile file) {
        basicBaseDeviceService.importData(file);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation(value = "Query Detail By ID")
    @GetMapping(value = "/basicBaseDevice/{id}")
    public AjaxResult getOne(@PathVariable Long id) {
        BasicBaseDevice basicBaseDevice = basicBaseDeviceService.selectById(id);
        if (StringUtils.isNotBlank(basicBaseDevice.getLatitude())
                && StringUtils.isNotBlank(basicBaseDevice.getLongitude())) {
            BasicBaseDeviceInfo info = new BasicBaseDeviceInfo();
            BeanUtils.copyProperties(basicBaseDevice, info);
            info.setGisAddress(info.getLongitude() + "," + info.getLatitude());
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, info);
        } else {
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, basicBaseDevice);
        }
    }

    @ApiOperation(" Device  Power   Drop Down Box")
    @GetMapping("/basicBaseDevice/dbmOption")
    public AjaxResult getDbmOption() {
        List<DbmVO> list = basicBaseDeviceService.getDbmOption();
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, list);
    }

    @ApiOperation(value = " Printer  Drop Down Box")
    @GetMapping("/basicBaseDevice/printOption")
    public AjaxResult list() {
        List<PrintOptionInfo> list = basicBaseDeviceService.getPrintOptionList();
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, list);
    }

    @ApiOperation(" Device  Code （ Device  Factory  / Device Type /  Device  Type ）  Drop Down Box")
    @GetMapping("/basicBaseDevice/codeOption")
    public AjaxResult getCodeOption(CodeOptionListVO vo) {
        List<DeviceCodeInfo> list = basicBaseDeviceService.getCodeOption(vo);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, list);
    }

}
