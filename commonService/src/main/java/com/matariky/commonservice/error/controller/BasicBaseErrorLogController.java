package com.matariky.commonservice.error.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.error.bean.BasicBaseErrorLog;
import com.matariky.commonservice.error.service.BasicBaseErrorLogService;
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
public class BasicBaseErrorLogController {

    @Value("${message.locale}")
    String locale;
    @Autowired
    private BasicBaseErrorLogService basicBaseErrorLogService;

    @Autowired
    private CommonDictService commonDictService;

    @Autowired
    private CommonDictTypeService commonDictTypeService;

     
    @RequestMapping("/basicBaseErrorLog/list")
    public AjaxResult list(HttpServletRequest request, BasicBaseErrorLog bean, @ApiParam(value = " Tenant ID", required = true) @PathVariable("tenantId") String tenantId, @ApiParam(value = "Page Index", required = true) @RequestParam("index") int pageIndex, @ApiParam(value = "Page Size", required = true) @RequestParam("perPage") int perPage, @ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt) {
        PageHelper.startPage(pageIndex, perPage);
        PageInfo<BasicBaseErrorLog> page = new PageInfo(basicBaseErrorLogService.getBasicBaseErrorLogAll(bean));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }


    @ApiOperation(" Export ")
    @GetMapping("/basicBaseErrorLog/export")
    public AjaxResult export(BasicBaseErrorLog bean) {
        basicBaseErrorLogService.export(bean);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @RequestMapping("/basicBaseErrorLog/daclist")
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
        List<BasicBaseErrorLog> commonDictList = basicBaseErrorLogService.getBasicBaseErrorLogDAC(params, request);
        Long count = basicBaseErrorLogService.getBasicBaseErrorLogDACCount(params, request);
        PageInfo<BasicBaseErrorLog> page = new PageInfo<BasicBaseErrorLog>(commonDictList);
        page.setTotal(count);
        page.setPageSize(perPage);
        page.setPageNum(pageIndex);
        page.setPages(Integer.parseInt(new Long(count % new Long(perPage) == 0 ? count % new Long(perPage) : count % new Long(perPage) + 1).toString()));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

     
    @RequestMapping(value = "/basicBaseErrorLog", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody List<BasicBaseErrorLog> beanList, HttpServletRequest request, HttpServletResponse response) {
        basicBaseErrorLogService.createBasicBaseErrorLogWithOrg(beanList, request);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

     
    @RequestMapping(value = "/basicBaseErrorLog", method = RequestMethod.PUT)
    public AjaxResult update(@RequestBody BasicBaseErrorLog bean, HttpServletRequest request, HttpServletResponse response) {
        basicBaseErrorLogService.updateBasicBaseErrorLog(bean);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

     
    @RequestMapping(value = "/basicBaseErrorLog", method = RequestMethod.DELETE)
    public AjaxResult del(String id, HttpServletRequest request, HttpServletResponse response) {
        basicBaseErrorLogService.deleteById(Long.parseLong(id));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

     
    @RequestMapping(value = "/basicBaseErrorLog/{basicBaseErrorLogId}", method = RequestMethod.GET)
    public AjaxResult getOne(@PathVariable("/basicBaseErrorLogId") Long id, HttpServletRequest request, HttpServletResponse response) {
        BasicBaseErrorLog info = basicBaseErrorLogService.selectById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, info);
    }


}
