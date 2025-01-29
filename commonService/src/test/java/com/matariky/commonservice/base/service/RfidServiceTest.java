package com.matariky.commonservice.base.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.matariky.commonservice.base.vo.RfidTagInfo;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class RfidServiceTest {

    @InjectMocks
    private RfidService rfidService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReceiveRfidData() {
        // Given
        JSONObject data = new JSONObject();
        JSONObject data1 = new JSONObject();
        data1.put("epcList", "[{\"epc\":\"123\",\"tid\":\"456\"}]");
        data.put("data", data1);

        // When
        rfidService.receiveRfidData(data);

        // Then
        RfidTagInfo result = rfidService.getReceiveRfidData(true);
        assertThat(result.getEpc()).isEqualTo("123");
        assertThat(result.getTagId()).isEqualTo("456");
    }

    @Test
    void testGetReceiveRfidDataSingle() {
        // Given
        JSONArray array = new JSONArray();
        Map<String, String> map = new HashMap<>();
        map.put("epc", "123");
        map.put("tid", "456");
        array.add(map);
        rfidService.receiveRfidData(
                new JSONObject().fluentPut("data", new JSONObject().fluentPut("epcList", array.toJSONString())));

        // When
        RfidTagInfo result = rfidService.getReceiveRfidData(true);

        // Then
        assertThat(result.getEpc()).isEqualTo("123");
        assertThat(result.getTagId()).isEqualTo("456");
    }

    @Test
    void testGetReceiveRfidDataMultiple() {
        // Given
        JSONArray array = new JSONArray();
        Map<String, String> map1 = new HashMap<>();
        map1.put("epc", "123");
        map1.put("tid", "456");
        Map<String, String> map2 = new HashMap<>();
        map2.put("epc", "789");
        map2.put("tid", "012");
        array.add(map1);
        array.add(map2);
        rfidService.receiveRfidData(
                new JSONObject().fluentPut("data", new JSONObject().fluentPut("epcList", array.toJSONString())));

        // When
        RfidTagInfo result = rfidService.getReceiveRfidData(false);

        // Then
        assertThat(result.getEpcAndTidList()).hasSize(2);
        assertThat(result.getEpcListStr()).isEqualTo("123,789");
    }
}
