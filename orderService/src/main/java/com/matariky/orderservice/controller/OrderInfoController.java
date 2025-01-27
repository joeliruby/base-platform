package com.matariky.orderservice.controller;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.matariky.commonservice.minio.utils.MinioUtil;
import com.matariky.exception.QslException;
import com.matariky.orderservice.bean.OrderInfo;
import com.matariky.orderservice.param.QueryTapeOrderInfoParam;
import com.matariky.orderservice.service.OrderInfoService;
import com.matariky.orderservice.vo.OrderInfoAddVo;
import com.matariky.orderservice.vo.OrderInfoEditVo;
import com.matariky.orderservice.vo.OrderInfoOperateVo;
import com.matariky.orderservice.vo.OrderInfoPageVo;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.PageUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller
 *
 * @author AUTOMATION
 */
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
public class OrderInfoController {

    @Value("${message.locale}")
    String locale;
    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private MinioUtil minioUtil;

    @RequestMapping("/orderInfo/list")
    public AjaxResult list(HttpServletRequest request, OrderInfo bean, @PathVariable("tenantId") String tenantId,
            @RequestParam("index") int pageIndex, @RequestParam("perPage") int perPage,
            @RequestHeader("Authorization") String jwt) {
        PageHelper.startPage(pageIndex, perPage);
        PageInfo<OrderInfo> page = new PageInfo<OrderInfo>(orderInfoService.getOrderInfoAll());
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

    @RequestMapping("/orderInfo/daclist")
    public AjaxResult daclist(QueryTapeOrderInfoParam params,
            @PathVariable("tenantId") String tenantId) {
        params.setPage(Boolean.TRUE);
        if (StringUtil.isNotEmpty(tenantId) && !tenantId.equals("1")) {
            params.setOrderTenantId(tenantId);
        }
        List<OrderInfoPageVo> commonDictList = orderInfoService.getOrderInfoDAC(params);
        Long count = orderInfoService.getOrderInfoDACCount(params);
        PageInfo<OrderInfoPageVo> pageInfo = PageUtils.getPageInfo(commonDictList, count, params.getIndex(),
                params.getPageSize());
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, pageInfo);
    }

    @RequestMapping(value = "/orderInfo", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody OrderInfoAddVo bean,
            @PathVariable("tenantId") String tenantId,
            @RequestHeader("Authorization") String jwt,
            HttpServletRequest request,
            HttpServletResponse response) {
        try {
            orderInfoService.createOrderInfoWithOrg(bean, request, tenantId, jwt);
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
        } catch (Exception e) {
            throw new QslException(e.getMessage());
        }
    }

    @RequestMapping(value = "/orderInfo", method = RequestMethod.PUT)
    public AjaxResult update(@RequestBody OrderInfoEditVo bean,
            @PathVariable("tenantId") String tenantId,
            @RequestHeader("Authorization") String jwt,
            HttpServletRequest request,
            HttpServletResponse response) {
        try {
            orderInfoService.updateOrderInfo(bean, request, tenantId, jwt);
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
        } catch (Exception e) {
            throw new QslException(e.getMessage());
        }
    }

    @RequestMapping(value = "/orderInfo", method = RequestMethod.DELETE)
    public AjaxResult del(String id, HttpServletRequest request, HttpServletResponse response) {
        try {
            orderInfoService.delOrderInfoById(Long.parseLong(id));
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
        } catch (Exception e) {
            throw new QslException(e.getMessage());
        }
    }

    @RequestMapping(value = "/orderInfo/{orderInfoId}", method = RequestMethod.GET)
    public AjaxResult getOne(@PathVariable("orderInfoId") Long id, HttpServletRequest request,
            HttpServletResponse response) {
        OrderInfo info = orderInfoService.selectById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, info);
    }

    @RequestMapping(value = "/orderInfo/uploadfile", method = RequestMethod.POST)
    public AjaxResult fileupload2(List<MultipartFile> files) {
        List<String> filePaths = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(files)) {
            filePaths = files.stream().map(item -> {
                try {
                    String fileName = IdUtil.simpleUUID();
                    minioUtil.uploadFile(item.getInputStream(), "order", fileName);
                    return fileName;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }).collect(Collectors.toList());
        }
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, filePaths);
    }

    @RequestMapping(value = "/operateData", method = RequestMethod.PUT)
    public AjaxResult operateData(@RequestBody OrderInfoOperateVo bean,
            @PathVariable("tenantId") String tenantId,
            @RequestHeader("Authorization") String jwt,
            HttpServletRequest request,
            HttpServletResponse response) {
        try {
            bean.setRecord(true);
            orderInfoService.operateData(bean, request, tenantId, jwt);
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
        } catch (Exception e) {
            throw new QslException(e.getMessage());
        }
    }

    @RequestMapping(value = "/operateData/aborted", method = RequestMethod.PUT)
    public AjaxResult operateAbortedData(@RequestBody OrderInfoOperateVo bean,
            @PathVariable("tenantId") String tenantId,
            @RequestHeader("Authorization") String jwt,
            HttpServletRequest request,
            HttpServletResponse response) {
        try {
            bean.setRecord(false);
            orderInfoService.operateData(bean, request, tenantId, jwt);
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
        } catch (Exception e) {
            throw new QslException(e.getMessage());
        }
    }

    @RequestMapping("/orderInfo/getList/{orderTenantId}")
    public AjaxResult getList(HttpServletRequest request,
            @PathVariable("orderTenantId") String orderTenantId,
            @PathVariable("tenantId") String tenantId,
            @RequestHeader("Authorization") String jwt) {
        String[] str = orderTenantId.split("_");
        List<OrderInfo> orderTenantIdList = null;
        if (str != null && str.length > 1) {
            orderTenantIdList = orderInfoService.getOrderTenantIdList(str[1]);
        } else {
            orderTenantIdList = orderInfoService.getOrderTenantIdList(orderTenantId);
        }
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, orderTenantIdList);
    }

    @RequestMapping(value = "/orderInfo/getExpiringSoon", method = RequestMethod.GET)
    public AjaxResult getOrderInfoExpiringSoon(
            @PathVariable("tenantId") String tenantId) {
        boolean b = orderInfoService.getOrderInfoExpiringSoon(tenantId);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, b);
    }
}
