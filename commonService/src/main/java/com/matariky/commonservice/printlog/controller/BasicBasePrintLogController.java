package com.matariky.commonservice.printlog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.printlog.bean.BasicBasePrintLog;
import com.matariky.commonservice.printlog.service.BasicBasePrintLogService;
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
public class BasicBasePrintLogController {

    @Value("${message.locale}")
    String locale;
    @Autowired
    private BasicBasePrintLogService basicBasePrintLogService;

    @Autowired
    private CommonDictService commonDictService;

    @Autowired
    private CommonDictTypeService commonDictTypeService;

    @RequestMapping("/basicBasePrintLog/list")
    public AjaxResult list(HttpServletRequest request, BasicBasePrintLog bean,
            @ApiParam(value = " Tenant ID", required = true) @PathVariable("tenantId") String tenantId,
            @ApiParam(value = "Page Index", required = true) @RequestParam("index") int pageIndex,
            @ApiParam(value = "Page Size", required = true) @RequestParam("perPage") int perPage,
            @ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt) {
        PageHelper.startPage(pageIndex, perPage);
        PageInfo<BasicBasePrintLog> page = new PageInfo<>(basicBasePrintLogService.getBasicBasePrintLogAll(bean));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

    @ApiOperation(" Export ")
    @GetMapping("/basicBasePrintLog/export")
    public AjaxResult export(BasicBasePrintLog bean) {
        basicBasePrintLogService.export(bean);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @RequestMapping("/basicBasePrintLog/daclist")
    public AjaxResult daclist(HttpServletRequest request, @ApiIgnore @RequestParam Map<String, Object> params,
            @ApiParam(value = " Tenant ID", required = true) @PathVariable("tenantId") String tenantId,
            @ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt) {
        String hid = request.getHeader("id");
        String resourceIdDictKey = "dp" + hid.substring(0, hid.length() - 1);
        CommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(
                TokenUtils.extractTenantIdFromHttpReqeust(request), PermissionConstant.DATA_ACCESS_PERMISSION);
        CommonDict dict = commonDictService.getCommonDictByIdTenantIdAndDictType(resourceIdDictKey, tenantId,
                commonDictType.getId());
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
        List<BasicBasePrintLog> commonDictList = basicBasePrintLogService.getBasicBasePrintLogDAC(params, request);
        Long count = basicBasePrintLogService.getBasicBasePrintLogDACCount(params, request);
        PageInfo<BasicBasePrintLog> page = new PageInfo<BasicBasePrintLog>(commonDictList);
        page.setTotal(count);
        page.setPageSize(perPage);
        page.setPageNum(pageIndex);
        page.setPages(Integer.parseInt(
                new Long(count % new Long(perPage) == 0 ? count % new Long(perPage) : count % new Long(perPage) + 1)
                        .toString()));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

    @RequestMapping(value = "/basicBasePrintLog", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody List<BasicBasePrintLog> beanList, HttpServletRequest request,
            HttpServletResponse response) {
        basicBasePrintLogService.createBasicBasePrintLogWithOrg(beanList, request);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @RequestMapping(value = "/basicBasePrintLog", method = RequestMethod.PUT)
    public AjaxResult update(@RequestBody BasicBasePrintLog bean, HttpServletRequest request,
            HttpServletResponse response) {
        basicBasePrintLogService.updateBasicBasePrintLog(bean);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @RequestMapping(value = "/basicBasePrintLog", method = RequestMethod.DELETE)
    public AjaxResult del(String id, HttpServletRequest request, HttpServletResponse response) {
        basicBasePrintLogService.deleteById(Long.parseLong(id));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @RequestMapping(value = "/basicBasePrintLog/{basicBasePrintLogId}", method = RequestMethod.GET)
    public AjaxResult getOne(@PathVariable("/basicBasePrintLogId") Long id, HttpServletRequest request,
            HttpServletResponse response) {
        BasicBasePrintLog info = basicBasePrintLogService.selectById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, info);
    }

}
