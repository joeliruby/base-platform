package com.matariky.orderservice.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.constant.PermissionConstant;
import com.matariky.orderservice.bean.OrderSuitePermission;
import com.matariky.orderservice.service.OrderSuitePermissionService;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
public class OrderSuitePermissionController {

    @Value("${message.locale}")
    String locale;
    @Autowired
    private OrderSuitePermissionService orderSuitePermissionService;

    @Autowired
    private CommonDictService commonDictService;

    @Autowired
    private CommonDictTypeService commonDictTypeService;

     
    @RequestMapping("/orderSuitePermission/list")
    public AjaxResult list(HttpServletRequest request, OrderSuitePermission bean, @PathVariable("tenantId") String tenantId, @RequestParam("index") int pageIndex, @RequestParam("perPage") int perPage, @RequestHeader("Authorization") String jwt) {
        PageHelper.startPage(pageIndex, perPage);
        PageInfo<OrderSuitePermission> page = new PageInfo<OrderSuitePermission>(orderSuitePermissionService.getOrderSuitePermissionAll());
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

    @RequestMapping("/orderSuitePermission/daclist")
    public AjaxResult daclist(HttpServletRequest request, @RequestParam Map<String, Object> params, @PathVariable("tenantId") String tenantId, @RequestHeader("Authorization") String jwt) {
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
        List<OrderSuitePermission> commonDictList = orderSuitePermissionService.getOrderSuitePermissionDAC(params, request);
        Long count = orderSuitePermissionService.getOrderSuitePermissionDACCount(params, request);
        PageInfo<OrderSuitePermission> page = new PageInfo<OrderSuitePermission>(commonDictList);
        page.setTotal(count);
        page.setPageSize(perPage);
        page.setPageNum(pageIndex);
        page.setPages(Integer.parseInt(String.valueOf(count % perPage == 0 ? count % perPage : count % perPage + 1)));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

     
    @RequestMapping(value = "/orderSuitePermission", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody OrderSuitePermission bean, HttpServletRequest request, HttpServletResponse response) {
        orderSuitePermissionService.createOrderSuitePermissionWithOrg(bean, request);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

     
    @RequestMapping(value = "/orderSuitePermission", method = RequestMethod.PUT)
    public AjaxResult update(@RequestBody OrderSuitePermission bean, HttpServletRequest request, HttpServletResponse response) {
        orderSuitePermissionService.updateOrderSuitePermission(bean);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

     
    @RequestMapping(value = "/orderSuitePermission", method = RequestMethod.DELETE)
    public AjaxResult del(String id, HttpServletRequest request, HttpServletResponse response) {
        orderSuitePermissionService.deleteById(Long.parseLong(id));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

     
    @RequestMapping(value = "/orderSuitePermission/{orderSuitePermissionId}", method = RequestMethod.GET)
    public AjaxResult getOne(@PathVariable("/orderSuitePermissionId") Long id, HttpServletRequest request, HttpServletResponse response) {
        OrderSuitePermission info = orderSuitePermissionService.selectById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, info);
    }

     
    @RequestMapping("/orderSuitePermission/getList")
    public AjaxResult getList(HttpServletRequest request,
                              @RequestParam Map<String, Object> params,
                              @PathVariable("tenantId") String tenantId,
                              @RequestHeader("Authorization") String jwt) {
        OrderSuitePermission orderSuitePermission = new OrderSuitePermission();
        orderSuitePermission.setSuiteCode(params.get("suiteCode") != null ? params.get("suiteCode").toString() : "");
        List<OrderSuitePermission> list = orderSuitePermissionService.getOrderSuitePermissions(orderSuitePermission);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, list);
    }
}
