package com.matariky.processor.tracer;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson2.JSONObject;
import com.matariky.processor.constants.TracerConstants;
import com.matariky.processor.utils.OpenTelemetryUtils;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;

public class OpentelemetryTracerProcessor implements Processor{
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat(TracerConstants.DATEFORMAT);
	
	
	private String tracerUrl;
	
	

	public String getTracerUrl() {
		return tracerUrl;
	}

	public void setTracerUrl(String tracerUrl) {
		OpenTelemetryUtils.init(TracerConstants.KARAF,tracerUrl);
		this.tracerUrl = tracerUrl;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		Message camelMessage = exchange.getIn();

        String payloadOuter = camelMessage.getBody(String.class);;
        if(StringUtils.isEmpty(payloadOuter))
        	return;
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxopentelmetry tracer");
        System.out.println(payloadOuter);
        
        JSONObject jo=JSONObject.parseObject(payloadOuter);
        String deviceCode=jo.getString(TracerConstants.DEVICECODE);
        String deviceType=jo.getString(TracerConstants.DEVICETYPE);
        String operation=jo.getString(TracerConstants.OPERATION);
        
        String softwareVersion=jo.getString(TracerConstants.SOFTWAREVERSION);//只有心跳有
        
        Tracer tracer = OpenTelemetryUtils.getTracer();
        // Create Span
        // Operation Name
        Span span = tracer.spanBuilder(operation).startSpan();
        span.setAttribute(TracerConstants.APPNAME,TracerConstants.KARAF);//  App  Name
        span.setAttribute(TracerConstants.HASERROR,"false");// Wether   There is Error 
        span.setAttribute(TracerConstants.OPERATIONNAME,operation);
        span.setAttribute(TracerConstants.DEVICETYPE,deviceType);
        span.setAttribute(TracerConstants.DEVICECODE,deviceCode);
        span.setAttribute(TracerConstants.IP,jo.getString(TracerConstants.IP));//ip
        span.setAttribute(TracerConstants.MACOUT, jo.getString(TracerConstants.MACIN));//mac
        span.setAttribute(TracerConstants.KIND, "4");//4 Device  Log 
        span.setAttribute(TracerConstants.PARENTSPANID, TracerConstants.KARAF);
        if(!StringUtils.isEmpty(softwareVersion)) {
       	 span.setAttribute(TracerConstants.TRACERVERSION, jo.getString(TracerConstants.TRACERVERSION));
        }
        try (Scope scope = span.makeCurrent()) {
            //   RetrievetraceId
            //   RetrievespanId
            //   Configuration Properties
        	if(0==jo.getIntValue("status")) {
        		 
                 //以下是具体 Error   Message 
                 Attributes eventAttributes = Attributes.of(
                         AttributeKey.stringKey(TracerConstants.SUCCESS), TracerConstants.SUCCESS,
                         AttributeKey.stringKey(TracerConstants.DEVICECODE),deviceCode,
                         AttributeKey.stringKey(TracerConstants.DEVICETYPE),deviceType,
                         AttributeKey.stringKey(TracerConstants.OPERATION), operation,
                         AttributeKey.stringKey(TracerConstants.ORIGINALMESSAGE), jo.getString(TracerConstants.ORIGINALMESSAGE),
                         AttributeKey.stringKey(TracerConstants.EVENT_TIME), sdf.format(new Date(System.currentTimeMillis())));
                 
                
                 //  Add  Event 
                 span.addEvent(operation, eventAttributes);
        	}
        	else {
        		 Attributes eventAttributes = Attributes.of(
                         AttributeKey.stringKey(TracerConstants.SUCCESS), TracerConstants.FAILURE,
                         AttributeKey.stringKey(TracerConstants.DEVICECODE),deviceCode,
                         AttributeKey.stringKey(TracerConstants.DEVICETYPE),deviceType,
                         AttributeKey.stringKey(TracerConstants.OPERATION), operation,
                         AttributeKey.stringKey(TracerConstants.ORIGINALMESSAGE), jo.getString(TracerConstants.ORIGINALMESSAGE),
                         AttributeKey.stringKey(TracerConstants.EVENT_TIME), sdf.format(new Date(System.currentTimeMillis())));

                 //  Add  Event 
        		 span.setAttribute(TracerConstants.HASERROR,"true");
                 span.addEvent(operation, eventAttributes);
        		
        	}
           


        } catch (Throwable t) {
        	Attributes eventAttributes = Attributes.of(
                    AttributeKey.stringKey(TracerConstants.SUCCESS), TracerConstants.FAILURE,
                    AttributeKey.stringKey(TracerConstants.DEVICECODE),deviceCode,
                    AttributeKey.stringKey(TracerConstants.DEVICETYPE),deviceType,
                    AttributeKey.stringKey(TracerConstants.OPERATION), operation,
                    AttributeKey.stringKey(TracerConstants.ORIGINALMESSAGE), jo.getString(TracerConstants.ORIGINALMESSAGE),
                    AttributeKey.stringKey(TracerConstants.EVENT_TIME), sdf.format(new Date(System.currentTimeMillis())));

        	span.setAttribute(TracerConstants.HASERROR,"true");
            //  Add  Event 
            span.addEvent(operation, eventAttributes);
        } finally {

           OpenTelemetryUtils.getExporter().flush();

           span.end();
        }
	}

}
