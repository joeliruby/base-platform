package com.matariky.commonservice.base.controller;

import com.matariky.commonservice.base.service.CommonService;
import com.matariky.commonservice.base.vo.AddExtendFieldDetailVO;
import com.matariky.commonservice.base.vo.AddExtendFieldVO;
import com.matariky.commonservice.base.vo.RfidTagInfo;
import com.matariky.utils.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/v1/tenant/{tenantId}/common")
@Api(value = "基础 Item  Information ", tags = "基础 Item  Information ")
public class CommonController {

    @Autowired
    private CommonService commonService;


    @ApiOperation(value = "获取读卡器 Label  Data ", response = RfidTagInfo.class)
    @GetMapping("/getRfidCodes")
    public AjaxResult getRfidCodes() {
        RfidTagInfo rfidCodes = commonService.getRfidCodes();
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, rfidCodes);
    }


    @ApiOperation(value = "Upload Image")
    @PutMapping(value = "/uploadimg")
    public AjaxResult uploadImg(@RequestParam MultipartFile file, @RequestParam String bucket) {
        String s = commonService.uploadImg(file, bucket);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, s);
    }

    @ApiOperation(value = "添加扩展字段")
    @PostMapping(value = "/extendField")
    public AjaxResult addExtendField(@RequestBody @Validated AddExtendFieldVO vo) {
        commonService.addExtendField(vo);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @ApiOperation(value = " Query 扩展字段")
    @GetMapping(value = "/extendField")
    public AjaxResult getExtendField(@RequestParam String name) {
        List<AddExtendFieldDetailVO> list = commonService.getExtendField(name);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, list);
    }


}
