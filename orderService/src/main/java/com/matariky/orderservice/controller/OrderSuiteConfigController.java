package com.matariky.orderservice.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.constant.PermissionConstant;
import com.matariky.orderservice.bean.OrderSuiteConfig;
import com.matariky.orderservice.service.OrderSuiteConfigService;
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
public class OrderSuiteConfigController {

    @Value("${message.locale}")
    String locale;
    @Autowired
    private OrderSuiteConfigService orderSuiteConfigService;

    @Autowired
    private CommonDictService commonDictService;

    @Autowired
    private CommonDictTypeService commonDictTypeService;

     
    @RequestMapping("/orderSuiteConfig/list")
    public AjaxResult list(HttpServletRequest request, OrderSuiteConfig bean, @PathVariable("tenantId") String tenantId, @RequestParam("index") int pageIndex, @RequestParam("perPage") int perPage, @RequestHeader("Authorization") String jwt) {
        PageHelper.startPage(pageIndex, perPage);
        PageInfo<OrderSuiteConfig> page = new PageInfo<OrderSuiteConfig>(orderSuiteConfigService.getOrderSuiteConfigAll());
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

    @RequestMapping("/orderSuiteConfig/daclist")
    public AjaxResult daclist(HttpServletRequest request,
                          @RequestParam Map<String, Object> params,
                          @PathVariable("tenantId") String tenantId,
                          @RequestHeader("Authorization") String jwt) {
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
        List<OrderSuiteConfig> commonDictList = orderSuiteConfigService.getOrderSuiteConfigDAC(params, request);
        Long count = orderSuiteConfigService.getOrderSuiteConfigDACCount(params, request);
        PageInfo<OrderSuiteConfig> page = new PageInfo<OrderSuiteConfig>(commonDictList);
        page.setTotal(count);
        page.setPageSize(perPage);
        page.setPageNum(pageIndex);
        page.setPages(Integer.parseInt(String.valueOf(count % perPage == 0 ? count % perPage : count % perPage + 1)));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

     
    @RequestMapping(value = "/orderSuiteConfig", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody OrderSuiteConfig bean, HttpServletRequest request, HttpServletResponse response) {
        orderSuiteConfigService.createOrderSuiteConfigWithOrg(bean, request);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

     
    @RequestMapping(value = "/orderSuiteConfig", method = RequestMethod.PUT)
    public AjaxResult update(@RequestBody OrderSuiteConfig bean, HttpServletRequest request, HttpServletResponse response) {
        orderSuiteConfigService.updateOrderSuiteConfig(bean);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

     
    @RequestMapping(value = "/orderSuiteConfig", method = RequestMethod.DELETE)
    public AjaxResult del(String id, HttpServletRequest request, HttpServletResponse response) {
        orderSuiteConfigService.deleteById(Long.parseLong(id));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

     
    @RequestMapping(value = "/orderSuiteConfig/{orderSuiteConfigId}", method = RequestMethod.GET)
    public AjaxResult getOne(@PathVariable("/orderSuiteConfigId") Long id, HttpServletRequest request, HttpServletResponse response) {
        OrderSuiteConfig info = orderSuiteConfigService.selectById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, info);
    }

     
    @RequestMapping("/orderSuiteConfig/getList")
    public AjaxResult getList(HttpServletRequest request,
                              @RequestParam Map<String, Object> params,
                              @PathVariable("tenantId") String tenantId,
                              @RequestHeader("Authorization") String jwt) {
        OrderSuiteConfig orderSuiteConfig = new OrderSuiteConfig();
        orderSuiteConfig.setSuiteCode(params.get("suiteCode") != null ? params.get("suiteCode").toString() : "");
        List<OrderSuiteConfig> list = orderSuiteConfigService.getOrderSuiteConfigs(orderSuiteConfig);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, list);
    }


    @RequestMapping("/orderSuiteConfig/getPriceConfig")
    public AjaxResult getPriceConfig(HttpServletRequest request,
                                     @RequestParam Map<String, Object> params,
                                     @PathVariable("tenantId") String tenantId,
                                     @RequestHeader("Authorization") String jwt) {

        String result = orderSuiteConfigService.getOrderSuiteConfigByParams(params);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, result);

    }


    @RequestMapping("/orderSuiteConfig/getOrderSuiteConfig")
    public AjaxResult getOrderSuiteConfigByCode(HttpServletRequest request,
                                                @RequestParam Map<String, Object> params,
                                                @PathVariable("tenantId") String tenantId,
                                                @RequestHeader("Authorization") String jwt) {
        OrderSuiteConfig info = orderSuiteConfigService.getOrderSuiteConfigByCode(params);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, info);
    }
}
