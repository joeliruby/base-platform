package com.matariky.commonservice.base.controller;

import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.bean.BasicBaseDevicecommand;
import com.matariky.commonservice.base.service.BasicBaseDevicecommandService;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.constant.PermissionConstant;
import com.matariky.exception.QslException;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.TokenUtils;
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
public class BasicBaseDevicecommandController {

    @Value("${message.locale}")
    String locale;
    @Autowired
    private BasicBaseDevicecommandService basicBaseDevicecommandService;

    @Autowired
    private CommonDictService commonDictService;

    @Autowired
    private CommonDictTypeService commonDictTypeService;

     
    @RequestMapping("/basicBaseDevicecommand/list")
    public Object list(BasicBaseDevicecommand bean) {
        List<BasicBaseDevicecommand> list = basicBaseDevicecommandService.getBasicBaseDevicecommandAll(bean);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, list);
    }

    @RequestMapping("/basicBaseDevicecommand/daclist")
    public Object daclist(HttpServletRequest request, @ApiIgnore @RequestParam Map<String, Object> params, @ApiParam(value = " Tenant ID", required = true) @PathVariable("tenantId") String tenantId, @ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt) {
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
        List<BasicBaseDevicecommand> commonDictList = basicBaseDevicecommandService.getBasicBaseDevicecommandDAC(params, request);
        Long count = basicBaseDevicecommandService.getBasicBaseDevicecommandDACCount(params, request);
        PageInfo<BasicBaseDevicecommand> page = new PageInfo<BasicBaseDevicecommand>(commonDictList);
        page.setTotal(count);
        page.setPageSize(perPage);
        page.setPageNum(pageIndex);
        page.setPages(Integer.parseInt(new Long(count % new Long(perPage) == 0 ? count % new Long(perPage) : count % new Long(perPage) + 1).toString()));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

     
    @RequestMapping(value = "/basicBaseDevicecommand", method = RequestMethod.POST)
    public Object save(@RequestBody BasicBaseDevicecommand bean, HttpServletRequest request, HttpServletResponse response) {
        try {
            int success = basicBaseDevicecommandService.createBasicBaseDevicecommandWithOrg(bean, request);
            if (success > 0) {
                return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
            } else {
                throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
        }
    }

     
    @RequestMapping(value = "/basicBaseDevicecommand", method = RequestMethod.PUT)
    public Object update(@RequestBody BasicBaseDevicecommand bean, HttpServletRequest request, HttpServletResponse response) {
        try {
            int success = basicBaseDevicecommandService.updateBasicBaseDevicecommand(bean);
            if (success > 0) {
                return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
            } else {
                throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
        }
    }

     
    @RequestMapping(value = "/basicBaseDevicecommand", method = RequestMethod.DELETE)
    public Object del(String id, HttpServletRequest request, HttpServletResponse response) {
        try {
            Boolean success = basicBaseDevicecommandService.deleteById(Long.parseLong(id));
            if (success) {
                return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
            } else {
                throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
        }
    }

     
    @RequestMapping(value = "/basicBaseDevicecommand/{basicBaseDevicecommandId}", method = RequestMethod.GET)
    public Object getOne(@PathVariable("/basicBaseDevicecommandId") Long id, HttpServletRequest request, HttpServletResponse response) {
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, basicBaseDevicecommandService.selectById(id));
    }


}
