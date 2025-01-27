package com.matariky.commonservice.message.controller;

import com.matariky.annotation.SourcePermission;
import com.matariky.commonservice.message.param.QueryMessageParam;
import com.matariky.commonservice.message.service.MessageService;
import com.matariky.model.PrimaryParam;
import com.matariky.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tenant/{tenantId}/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @SourcePermission("533304969136181248")
    @RequestMapping("/list")
    public Object getList(QueryMessageParam params) {
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, messageService.getPage(params));
    }

    @RequestMapping("/setRead")
    public Object setRead(PrimaryParam param) {
        messageService.setRead(param);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
    }
}
