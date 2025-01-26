package com.matariky.commonservice.device.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.device.bean.BasicBaseDeviceLog;
import com.matariky.commonservice.device.service.BasicBaseDeviceLogService;
import com.matariky.constant.PermissionConstant;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.TokenUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author AUTOMATION
 */
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
public class BasicBaseDeviceLogController {

    @Value("${message.locale}")
    String locale;
    @Autowired
    private BasicBaseDeviceLogService basicBaseDeviceLogService;

    @Autowired
    private CommonDictService commonDictService;

    @Autowired
    private CommonDictTypeService commonDictTypeService;

     
    @RequestMapping("/basicBaseDeviceLog/list")
    public AjaxResult list(HttpServletRequest request, BasicBaseDeviceLog bean, @ApiParam(value = " Tenant ID", required = true) @PathVariable("tenantId") String tenantId, @ApiParam(value = "Page Index", required = true) @RequestParam("index") int pageIndex, @ApiParam(value = "Page Size", required = true) @RequestParam("perPage") int perPage, @ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt) {
        PageHelper.startPage(pageIndex, perPage);
        PageInfo<BasicBaseDeviceLog> page = new PageInfo(basicBaseDeviceLogService.getBasicBaseDeviceLogAll(bean));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

    @ApiOperation(" Export ")
    @GetMapping("/basicBaseDeviceLog/export")
    public AjaxResult export(BasicBaseDeviceLog bean) {
        basicBaseDeviceLogService.export(bean);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @RequestMapping("/basicBaseDeviceLog/daclist")
    public AjaxResult daclist(HttpServletRequest request, @ApiIgnore @RequestParam Map<String, Object> params, @ApiParam(value = " Tenant ID", required = true) @PathVariable("tenantId") String tenantId, @ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt) {
        String hid = request.getHeader("id");
        String resourceIdDictKey = "dp" + hid.substring(0, hid.length() - 1);
        CommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(TokenUtils.extractTenantIdFromHttpReqeust(request), PermissionConstant.DATA_ACCESS_PERMISSION);
        CommonDict dict = commonDictService.getCommonDictByIdTenantIdAndDictType(resourceIdDictKey, tenantId, commonDictType.getId());
        if (dict == null) {
            params.put("strategyCode", PermissionConstant.COMMON_DATA_ACCESS_ALL);
        } else {
            params.put("strategyCode", dict.getDictValue());
        }
        int pageIndex = Integer.parseInt(params.get("index").toString());
        int perPage = Integer.parseInt(params.get("perPage").toString());
        params.put("tenantId", tenantId);
        params.put("pageStart", (pageIndex - 1) * perPage);
        params.put("pageSize", perPage);
        List<BasicBaseDeviceLog> commonDictList = basicBaseDeviceLogService.getBasicBaseDeviceLogDAC(params, request);
        Long count = basicBaseDeviceLogService.getBasicBaseDeviceLogDACCount(params, request);
        PageInfo<BasicBaseDeviceLog> page = new PageInfo<BasicBaseDeviceLog>(commonDictList);
        page.setTotal(count);
        page.setPageSize(perPage);
        page.setPageNum(pageIndex);
        page.setPages(Integer.parseInt(new Long(count % new Long(perPage) == 0 ? count % new Long(perPage) : count % new Long(perPage) + 1).toString()));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

     
    @RequestMapping(value = "/basicBaseDeviceLog", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody BasicBaseDeviceLog bean, HttpServletRequest request, HttpServletResponse response) {
        basicBaseDeviceLogService.createBasicBaseDeviceLogWithOrg(bean, request);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

     
    @RequestMapping(value = "/basicBaseDeviceLog", method = RequestMethod.PUT)
    public AjaxResult update(@RequestBody BasicBaseDeviceLog bean, HttpServletRequest request, HttpServletResponse response) {
        basicBaseDeviceLogService.updateBasicBaseDeviceLog(bean);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

     
    @RequestMapping(value = "/basicBaseDeviceLog", method = RequestMethod.DELETE)
    public AjaxResult del(String id, HttpServletRequest request, HttpServletResponse response) {
        basicBaseDeviceLogService.deleteById(Long.parseLong(id));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

     
    @RequestMapping(value = "/basicBaseDeviceLog/{basicBaseDeviceLogId}", method = RequestMethod.GET)
    public AjaxResult getOne(@PathVariable("/basicBaseDeviceLogId") Long id, HttpServletRequest request, HttpServletResponse response) {
        BasicBaseDeviceLog info = basicBaseDeviceLogService.selectById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, info);
    }


}
