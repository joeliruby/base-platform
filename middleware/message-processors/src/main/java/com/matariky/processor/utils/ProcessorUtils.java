package com.matariky.processor.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ProcessorUtils {
    public static String getProperty(String env, String key) {

        try {
            InputStream inputStream = ProcessorUtils.class.getClassLoader().getResourceAsStream(env + ".properties");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            Map<String, String> propMap = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null && !line.isBlank()) {
                String[] kv = line.split("=");
                propMap.put(kv[0], kv[1]);
            }
            return propMap.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
