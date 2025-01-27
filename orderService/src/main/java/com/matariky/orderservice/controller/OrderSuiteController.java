package com.matariky.orderservice.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matariky.annotation.SourcePermission;
import com.matariky.orderservice.bean.OrderSuite;
import com.matariky.orderservice.param.QueryTapeOrderSuiteParam;
import com.matariky.orderservice.service.OrderSuiteService;
import com.matariky.orderservice.vo.OrderSuiteAddVo;
import com.matariky.orderservice.vo.OrderSuiteEditVo;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.PageUtils;
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
public class OrderSuiteController {

    @Value("${message.locale}")
    String locale;
    @Autowired
    private OrderSuiteService orderSuiteService;

    @RequestMapping("/orderSuite/list")
    public AjaxResult list(HttpServletRequest request, OrderSuite bean, @PathVariable("tenantId") String tenantId,
            @RequestParam("index") int pageIndex, int perPage, @RequestHeader("Authorization") String jwt) {
        PageHelper.startPage(pageIndex, perPage);
        PageInfo<OrderSuite> page = new PageInfo<OrderSuite>(orderSuiteService.getOrderSuiteAll());
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

    @SourcePermission("4253911278913249855")
    @RequestMapping("/orderSuite/daclist")
    public AjaxResult daclist(QueryTapeOrderSuiteParam params) {
        params.setPage(Boolean.TRUE);
        List<OrderSuite> commonDictList = orderSuiteService.getOrderSuiteDAC(params);
        Long count = orderSuiteService.getOrderSuiteDACCount(params);
        PageInfo<OrderSuite> pageInfo = PageUtils.getPageInfo(commonDictList, count, params.getIndex(),
                params.getPageSize());
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, pageInfo);
    }

    @RequestMapping(value = "/orderSuite", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody OrderSuiteAddVo bean,
            @PathVariable("tenantId") String tenantId,
            @RequestHeader("Authorization") String jwt,
            HttpServletRequest request,
            HttpServletResponse response) {
        bean.setTenantId(tenantId);
        bean.setCreatedBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
        bean.setUpdateBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
        orderSuiteService.saveOrderSuite(bean, request);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @RequestMapping(value = "/orderSuite", method = RequestMethod.PUT)
    public AjaxResult update(@RequestBody OrderSuiteEditVo bean,
            @PathVariable("tenantId") String tenantId,
            @RequestHeader("Authorization") String jwt,
            HttpServletRequest request,
            HttpServletResponse response) {
        bean.setTenantId(tenantId);
        bean.setUpdateBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
        orderSuiteService.updateOrderSuite(bean, request);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @RequestMapping(value = "/orderSuite", method = RequestMethod.DELETE)
    public AjaxResult del(String id, HttpServletRequest request, HttpServletResponse response) {
        orderSuiteService.deleteById(Long.parseLong(id));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @RequestMapping(value = "/orderSuite/{orderSuiteId}", method = RequestMethod.GET)
    public AjaxResult getOne(@PathVariable("orderSuiteId") Long orderSuiteId, HttpServletRequest request,
            HttpServletResponse response) {
        OrderSuite info = orderSuiteService.selectById(orderSuiteId);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, info);
    }

    @RequestMapping("/orderSuite/getList")
    public AjaxResult getList(HttpServletRequest request,
            @RequestParam Map<String, Object> params,
            @PathVariable("tenantId") String tenantId,
            @RequestHeader("Authorization") String jwt) {
        String suiteStatus = null;
        if (params.get("suiteStatus") != null) {
            suiteStatus = params.get("suiteStatus").toString();
        }
        List<OrderSuite> list = orderSuiteService.getOrderSuiteList(tenantId, suiteStatus);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, list);
    }

    @RequestMapping(value = "/orderSuiteStatus", method = RequestMethod.PUT)
    public AjaxResult updateOrderSuiteStatus(@RequestBody OrderSuiteEditVo bean,
            @PathVariable("tenantId") String tenantId,
            @RequestHeader("Authorization") String jwt,
            HttpServletRequest request,
            HttpServletResponse response) {
        bean.setTenantId(tenantId);
        bean.setUpdateBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
        orderSuiteService.updateOrderSuiteStatus(bean.getId(), bean.getSuiteStatus());
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }
}
