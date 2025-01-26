package com.matariky.normalizers.deviceEvents.solid.SRE401X;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.matariky.processor.constants.TracerConstants;

/*
  	{
        "cmd":        "60005",
        "messageCode":        0,
        "data":        {
                "status":        0,
                "msg":        "update success"
        }
	}

 Device 升级topic 配置格式  update-mqtt/SRE401X/deviceCode123
  */

public class SolidSRE401XDeviceEventsNormlizer implements Processor{
	
	
	@Override
	public void process(Exchange exchange) throws Exception {
		Message camelMessage = exchange.getIn();
		String mqttTopic =(String)camelMessage.getHeader(TracerConstants.MQTTTOPIC);
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxdeviceEventNormalizer:mqtttopic:"+mqttTopic);
        String payloadOuter = camelMessage.getBody(String.class);
        if(StringUtils.isEmpty(payloadOuter))
        	return;
        JSONObject jo=JSONObject.parseObject(payloadOuter);
        if(!"60005".equals(jo.getString("cmd"))) {
        	throw new Exception();
        }
        JSONObject obj =jo.getJSONObject("data");
        //debug and get device type and device code from mqtt topic    temporarily hardcode
        String[] topicData=mqttTopic.split("/");
        String deviceCode=topicData[1];
        String deviceType=deviceCode.split("_")[0];
        obj.put(TracerConstants.DEVICETYPE, deviceType);
        obj.put(TracerConstants.DEVICECODE, deviceCode);
        obj.put(TracerConstants.ORININALMESSAGE,  payloadOuter);
        
        if("60005".equals(jo.getString("cmd")))//升级
        	obj.put(TracerConstants.OPERATION, TracerConstants.DEVICEUPDATE);
       
        String normalizedStr=JSON.toJSONString(obj);
        exchange.getIn().setHeader(TracerConstants.NEXTREDISKEY, TracerConstants.DEVICEUPDATED+deviceCode);
        exchange.getIn().setBody(normalizedStr);
	}

}
