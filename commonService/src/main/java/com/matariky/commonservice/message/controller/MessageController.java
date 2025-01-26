package com.matariky.commonservice.message.controller;

import com.matariky.annotation.SourcePermission;
import com.matariky.commonservice.message.param.QueryMessageParam;
import com.matariky.commonservice.message.service.MessageService;
import com.matariky.model.PrimaryParam;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 消息Controller
 * @author: bo.chen
 * @create: 2023/10/19 15:16
 **/
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * @Description: 分页获取消息
     * @Author: bo.chen
     * @Date: 2023/10/19 15:18
     * @param params
     * @return java.lang.Object
     **/
    @SourcePermission("533304969136181248")
    @RequestMapping("/list")
    public Object getList(QueryMessageParam params) {
    	return  new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,messageService.getPage(params));
    }

    /**
     * @Description: 设置消息已读
     * @Author: bo.chen
     * @Date: 2023/11/16 10:37
     * @param param
     * @return java.lang.Object
     **/
    @RequestMapping("/setRead")
    public Object setRead(PrimaryParam param) {
        messageService.setRead(param);
        return  new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,null);
    }
}
