package com.matariky.processor.config;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matariky.processor.constants.ProcessorConstants;
import com.matariky.processor.utils.ProcessorUtils;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpProcessor implements Processor {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void process(Exchange exchange) throws Exception {
        Message camelMessage = exchange.getIn();
        String inJson = camelMessage.getBody(String.class);
        Map<String, String> httpRequest = objectMapper.readValue(inJson, Map.class);
        String operation = httpRequest.get(ProcessorConstants.OPERATION);

        switch (operation) {
            case ProcessorConstants.GET_FILE_NAMES:
                String env = httpRequest.get(ProcessorConstants.ENV);
                String fileDir = ProcessorUtils.getProperty(env, ProcessorConstants.FILE_DIR);
                File dir = new File(fileDir);
                String[] fileNames = dir.list();
                List<Map<String, String>> fileNamesList = new ArrayList();
                for (String fileName : fileNames) {
                    if (fileName.endsWith(".xml")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("fileName", fileName);
                        fileNamesList.add(map);
                    }
                }
                JSONObject object = new JSONObject();
                object.put("data", JSONArray.parseArray(JSON.toJSONString(fileNamesList)));
                object.put("code", 200);
                exchange.getOut().setBody(object);
                break;
            case ProcessorConstants.UPDATE_FILE_CONTENT:
                String fileNameUpdate = httpRequest.get(ProcessorConstants.FILE_NAME);
                String envUpdate = httpRequest.get(ProcessorConstants.ENV);
                String updateFileAddress = ProcessorUtils.getProperty(envUpdate, ProcessorConstants.FILE_DIR) + File.separator + fileNameUpdate;
                String updateContent = httpRequest.get(ProcessorConstants.FILE_CONTENT);
                Files.write(Paths.get(updateFileAddress), updateContent.getBytes());
                JSONObject o = new JSONObject();
                o.put("data", "success");
                o.put("code", 200);
                exchange.getOut().setBody(o);
                break;
            case ProcessorConstants.GET_FILE_CONTENT:
                String fileName = httpRequest.get(ProcessorConstants.FILE_NAME);
                String env1 = httpRequest.get(ProcessorConstants.ENV);
                String fileAddress = ProcessorUtils.getProperty(env1, ProcessorConstants.FILE_DIR) + File.separator + fileName;
                String content = new String(Files.readAllBytes(Paths.get(fileAddress)));
                JSONObject obj = new JSONObject();
                obj.put("data", content);
                obj.put("code", 200);
                exchange.getOut().setBody(obj);
                break;
            default:
                break;
        }
    }


}
