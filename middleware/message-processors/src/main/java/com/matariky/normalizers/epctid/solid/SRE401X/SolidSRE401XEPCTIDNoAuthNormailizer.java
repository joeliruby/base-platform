package com.matariky.normalizers.epctid.solid.SRE401X;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.matariky.processor.constants.TracerConstants;

/*
 {
        "cmd":  "60001",
        "messageCode":  880,
        "data": {
                "deviceType":   "SRE401",
                "ip":   "192.168.1.136",
                "mac":  "00142951ac04",
                "deviceNo":     "SRE801_1_gate1_1",
                "epcList":      [{
                                "data": "tid123222323",
                                "epc":  "49637E5DB753EE8485E64495",
                                "ant":  4,
                                "rssi": -71,
                                "time": "2023-07-27 19:53:28.34",
                                "direction":    0
                        }]
        }
}
 */

public class SolidSRE401XEPCTIDNoAuthNormailizer implements Processor{
	private static final SimpleDateFormat sdf = new SimpleDateFormat(TracerConstants.DATEFORMAT);
	@Override
	public void process(Exchange exchange) throws Exception {
		//solid格式已经和标准格式一样不需要做任何处理
		Message camelMessage = exchange.getIn();

        String payloadOuter = camelMessage.getBody(String.class);
        if(StringUtils.isEmpty(payloadOuter))
        	return;
        
        JSONObject obj=JSONObject.parseObject(payloadOuter);
        JSONObject outBound= new JSONObject();
        if(!"60001".equals(obj.getString("cmd"))) {
        	throw new Exception();
        }
        else {
        	outBound.put(TracerConstants.OPERATION, TracerConstants.TAGS);
        }
        //debug and get device type and device code from mqtt topic    temporarily hardcode
        JSONObject data=obj.getJSONObject("data");
        
        outBound.put(TracerConstants.DEVICETYPE, data.getString(TracerConstants.DEVICETYPE));
        outBound.put(TracerConstants.DEVICECODE, data.getString("deviceNo"));
        outBound.put(TracerConstants.ORIGINALMESSAGE, payloadOuter);
        outBound.put(TracerConstants.TIMESTAMP, sdf.format(new Date(System.currentTimeMillis())));
        outBound.put(TracerConstants.HOSTNAME, data.getString(TracerConstants.IP));
        JSONArray epcList=data.getJSONArray("epcList");
        JSONArray tagInventoryEvent = new JSONArray();
        for (int i = 0; i < epcList.size(); i++) {
        	
            JSONObject tag = epcList.getJSONObject(i);
            
            JSONObject noramlizedTag= new JSONObject();
            noramlizedTag.put(TracerConstants.TID, tag.getString("data"));
            noramlizedTag.put(TracerConstants.EPC, tag.getString("epc"));
            noramlizedTag.put(TracerConstants.ANTENNAPORT, tag.getString("ant"));
            tagInventoryEvent.add(noramlizedTag);
        }
        outBound.put(TracerConstants.TAGINTENTORYEVENT, tagInventoryEvent);
        String normalizedStr=JSON.toJSONString(outBound);
        exchange.getIn().setBody(normalizedStr);
	}

}
