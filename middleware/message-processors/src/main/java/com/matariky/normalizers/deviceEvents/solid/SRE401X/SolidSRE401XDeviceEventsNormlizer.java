package com.matariky.normalizers.deviceEvents.solid.SRE401X;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.matariky.processor.constants.TracerConstants;

/**
 * SolidSRE401XDeviceEventsNormlizer is a processor that normalizes device
 * events
 * for Solid SRE401X devices. It processes incoming messages, extracts relevant
 * information, and prepares a normalized message for further processing.
 * 
 * <p>
 * This processor performs the following steps:
 * <ul>
 * <li>Extracts the MQTT topic and payload from the incoming message.</li>
 * <li>Parses the payload as a JSON object.</li>
 * <li>Checks if the command in the payload is "60005" (Upgrade command).</li>
 * <li>Extracts device type and device code from the MQTT topic.</li>
 * <li>Adds device type, device code, and original message to the JSON
 * object.</li>
 * <li>Sets the operation to "DEVICEUPDATE" if the command is "60005".</li>
 * <li>Serializes the JSON object to a string and sets it as the message
 * body.</li>
 * <li>Sets the next Redis key header for further processing.</li>
 * </ul>
 * 
 * <p>
 * If the payload is empty or the command is not "60005", the processor will
 * either return without processing or throw an exception.
 * 
 * @throws Exception if the command in the payload is not "60005".
 */
public class SolidSRE401XDeviceEventsNormlizer implements Processor {

        @Override
        public void process(Exchange exchange) throws Exception {
                Message camelMessage = exchange.getIn();
                String mqttTopic = (String) camelMessage.getHeader(TracerConstants.MQTTTOPIC);
                String payloadOuter = camelMessage.getBody(String.class);
                if (!StringUtils.hasLength(payloadOuter))
                        return;
                JSONObject jo = JSONObject.parseObject(payloadOuter);
                if (!"60005".equals(jo.getString("cmd"))) {
                        throw new Exception();
                }
                JSONObject obj = jo.getJSONObject("data");
                // debug and get device type and device code from mqtt topic temporarily
                // hardcode
                String[] topicData = mqttTopic.split("/");
                String deviceCode = topicData[1];
                String deviceType = deviceCode.split("_")[0];
                obj.put(TracerConstants.DEVICETYPE, deviceType);
                obj.put(TracerConstants.DEVICECODE, deviceCode);
                obj.put(TracerConstants.ORININALMESSAGE, payloadOuter);

                if ("60005".equals(jo.getString("cmd")))// Upgrade
                        obj.put(TracerConstants.OPERATION, TracerConstants.DEVICEUPDATE);

                String normalizedStr = JSON.toJSONString(obj);
                exchange.getIn().setHeader(TracerConstants.NEXTREDISKEY, TracerConstants.DEVICEUPDATED + deviceCode);
                exchange.getIn().setBody(normalizedStr);
        }

}
