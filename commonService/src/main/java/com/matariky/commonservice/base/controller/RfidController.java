package com.matariky.commonservice.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.matariky.commonservice.base.service.RfidService;
import com.matariky.commonservice.base.vo.RfidTagInfo;
import com.matariky.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/rfid")
public class RfidController {


    @Autowired
    private RfidService rfidService;

    /**
     * 接收 Label  Data 
     *
     * @param object
     * @return
     */
    @PostMapping(value = "/stock/inout/insert")
    public void receiveRfidData(@RequestBody JSONObject object) {
        rfidService.receiveRfidData(object);
    }


    /**
     *   Retrieve Label  Data 
     *
     * @param
     * @return
     */
    @GetMapping(value = "/data")
    public AjaxResult getReceiveRfidData(@RequestParam(defaultValue = "true") Boolean isOne) {
        RfidTagInfo receiveRfidData = rfidService.getReceiveRfidData(isOne);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, receiveRfidData);
    }

}
