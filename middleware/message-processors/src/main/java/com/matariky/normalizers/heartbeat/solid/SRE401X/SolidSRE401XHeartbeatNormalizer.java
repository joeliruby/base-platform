package com.matariky.normalizers.heartbeat.solid.SRE401X;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.matariky.processor.constants.TracerConstants;

/*
 {
        "cmd":        "60002",
        "messageCode":        3,
        "data":        {
                "deviceType":        "SRE401",
                "ip":        "10.0.0.141",
                "mac":        "00140d2f71ac",
                "deviceNo":        "solid001_2",
                "isWork":        1,
                "systemTime":        "1712734264577",
                "softVersion":        "YX-MQTT.DDC.1.7"
        }
}
 */

public class SolidSRE401XHeartbeatNormalizer implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		Message camelMessage = exchange.getIn();

        String payloadOuter = camelMessage.getBody(String.class);
        if(StringUtils.isEmpty(payloadOuter))
        	return;
        JSONObject jo=JSONObject.parseObject(payloadOuter);
        if(!"60002".equals(jo.getString("cmd"))) {
        	throw new Exception();
        }
        JSONObject obj =jo.getJSONObject("data");
        String deviceCode=obj.getString("deviceNo");
        obj.remove("deviceNo");
        obj.put(TracerConstants.DEVICETYPE, obj.getString("deviceType"));
        obj.put(TracerConstants.DEVICECODE, deviceCode);
        obj.put(TracerConstants.ORININALMESSAGE,  payloadOuter);
        obj.put(TracerConstants.OPERATION, TracerConstants.HEARTBEAT);
        String normalizedStr=JSON.toJSONString(obj);
        exchange.getIn().setBody(normalizedStr);
	}

}
