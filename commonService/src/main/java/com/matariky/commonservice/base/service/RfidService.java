package com.matariky.commonservice.base.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.matariky.commonservice.base.vo.EPCAndTIDVO;
import com.matariky.commonservice.base.vo.RfidTagInfo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
public class RfidService {

    private JSONArray array = new JSONArray();

    /**
     * 接收 Label Data
     *
     * @param data
     */
    public void receiveRfidData(JSONObject data) {
        JSONObject data1 = data.getJSONObject("data");
        String epcList = data1.getString("epcList");
        array = JSONArray.parseArray(epcList);
    }

    /**
     * 接收 Label Data
     *
     * @param isOne
     * @return
     */
    public RfidTagInfo getReceiveRfidData(Boolean isOne) {
        RfidTagInfo rfidTagInfo = new RfidTagInfo();
        /** Single Take the first one ecp and tid **/
        if (isOne) {
            if (!array.isEmpty()) {
                Map<String, String> map = (Map<String, String>) array.get(0);
                rfidTagInfo.setEpc(map.get("epc"));
                rfidTagInfo.setTagId(map.get("tid"));
                return rfidTagInfo;
            }
        } else {
            /** mutliple take multiple epc **/
            List<EPCAndTIDVO> epcList = array.stream().map(item -> {
                EPCAndTIDVO vo = new EPCAndTIDVO();
                vo.setTid(((Map<String, String>) item).get("tid"));
                vo.setEpc(((Map<String, String>) item).get("epc"));
                return vo;
            }).collect(Collectors.toList());

            List<EPCAndTIDVO> list = epcList.stream()
                    .collect(Collectors.collectingAndThen(
                            Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getEpc()))),
                            ArrayList::new));
            rfidTagInfo.setEpcAndTidList(list);
            String epcListStr = list.stream().map(EPCAndTIDVO::getEpc).collect(Collectors.joining(","));
            rfidTagInfo.setEpcListStr(epcListStr);
        }
        return rfidTagInfo;
    }

}
