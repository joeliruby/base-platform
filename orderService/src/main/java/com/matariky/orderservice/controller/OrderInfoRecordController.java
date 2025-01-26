package com.matariky.orderservice.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matariky.orderservice.bean.OrderInfoRecord;
import com.matariky.orderservice.service.OrderInfoRecordService;
import com.matariky.utils.AjaxResult;
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
public class OrderInfoRecordController {

    @Value("${message.locale}")
    String locale;
    @Autowired
    private OrderInfoRecordService orderInfoRecordService;


     
    @RequestMapping("/orderInfoRecord/list")
    public AjaxResult list(OrderInfoRecord bean, @RequestParam("index") int pageIndex, @RequestParam("perPage") int perPage, @RequestHeader("Authorization") String jwt) {
        PageHelper.startPage(pageIndex, perPage);
        PageInfo<OrderInfoRecord> page = new PageInfo<OrderInfoRecord>(orderInfoRecordService.getOrderInfoRecordAll());
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

    @RequestMapping("/orderInfoRecord/getList")
    public AjaxResult getList(HttpServletRequest request,
                              @RequestParam Map<String, Object> params,
                              @PathVariable("tenantId") String tenantId,
                              @RequestHeader("Authorization") String jwt) {
        List<OrderInfoRecord> commonDictList = orderInfoRecordService.getOrderInfoRecordDAC(params, request);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, commonDictList);
    }

     
    @RequestMapping(value = "/orderInfoRecord", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody OrderInfoRecord bean, HttpServletRequest request, HttpServletResponse response) {
        orderInfoRecordService.createOrderInfoRecordWithOrg(bean, request);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

     
    @RequestMapping(value = "/orderInfoRecord", method = RequestMethod.PUT)
    public AjaxResult update(@RequestBody OrderInfoRecord bean, HttpServletRequest request, HttpServletResponse response) {
        orderInfoRecordService.updateOrderInfoRecord(bean);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

     
    @RequestMapping(value = "/orderInfoRecord", method = RequestMethod.DELETE)
    public AjaxResult del(String id, HttpServletRequest request, HttpServletResponse response) {
        orderInfoRecordService.deleteById(Long.parseLong(id));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

     
    @RequestMapping(value = "/orderInfoRecord/{orderInfoRecordId}", method = RequestMethod.GET)
    public AjaxResult getOne(@PathVariable("/orderInfoRecordId") Long id, HttpServletRequest request, HttpServletResponse response) {
        OrderInfoRecord info = orderInfoRecordService.selectById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, info);
    }


}
