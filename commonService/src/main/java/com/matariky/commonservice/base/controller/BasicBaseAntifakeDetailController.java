package com.matariky.commonservice.base.controller;

import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.bean.BasicBaseAntifakeDetail;
import com.matariky.commonservice.base.service.BasicBaseAntifakeDetailService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controller
 *
 * @author AUTOMATION
 */
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
@Api(value = "Anti -counterfeiting certification Detail", tags = "Anti -counterfeiting certification Detail")
public class BasicBaseAntifakeDetailController {

    @Value("${message.locale}")
    String locale;
    @Autowired
    private BasicBaseAntifakeDetailService basicBaseAntifakeDetailService;


    @ApiOperation("Pagination")
    @GetMapping("/basicBaseAntifakeDetail/list")
    public AjaxResult list(BasicBaseAntifakeDetail bean,
                           @ApiParam(value = "Page Index", required = true) @RequestParam("index") int pageIndex,
                           @ApiParam(value = "Page Size", required = true) @RequestParam("perPage") int perPage) {
        if (StringUtils.isNotEmpty(bean.getValidationTimeStart())) {
            bean.setValidationTimeEnd(bean.getValidationTimeStart() + " 00:00:00");
        }
        if (StringUtils.isNotEmpty(bean.getValidationTimeEnd())) {
            bean.setValidationTimeEnd(bean.getValidationTimeEnd() + " 23:59:59");
        }
        PageInfo<BasicBaseAntifakeDetail> page = new PageInfo(basicBaseAntifakeDetailService.getBasicBaseAntifakeDetailAll(bean, pageIndex, perPage));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }


    @ApiOperation(" Save ")
    @PostMapping(value = "/basicBaseAntifakeDetail")
    public AjaxResult save(@RequestBody BasicBaseAntifakeDetail bean) {
        try {
            basicBaseAntifakeDetailService.createBasicBaseAntifakeDetailWithOrg(bean);
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
        } catch (Exception e) {
            throw new QslException(e.getMessage());
        }
    }

    @ApiOperation("Update")
    @PutMapping(value = "/basicBaseAntifakeDetail")
    public AjaxResult update(@RequestBody BasicBaseAntifakeDetail bean) {
        try {
            basicBaseAntifakeDetailService.updateBasicBaseAntifakeDetail(bean);
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
        } catch (Exception e) {
            throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
        }
    }

    @ApiOperation("Delete ")
    @DeleteMapping(value = "/basicBaseAntifakeDetail")
    public AjaxResult del(Long id) {
        try {
            basicBaseAntifakeDetailService.delBasicBaseAntifakeDetailById(id);
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
        } catch (Exception e) {
            throw new QslException(e.getMessage());
        }
    }

    @ApiOperation("  Detail ")
    @GetMapping(value = "/basicBaseAntifakeDetail/{basicBaseAntifakeDetailId}")
    public AjaxResult getOne(@PathVariable("/basicBaseAntifakeDetailId") Long id) {
        BasicBaseAntifakeDetail info = basicBaseAntifakeDetailService.getBasicBaseAntifakeDetailById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, info);
    }


}
