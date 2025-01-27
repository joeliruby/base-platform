package com.matarikyr.processor.constants;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.MockitoAnnotations.initMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.processor.constants.TracerConstants;

@SpringBootTest
public class TracerConstantsTest {

    @InjectMocks
    private TracerConstants tracerconstants;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testHasErrorConstant() {
        assertEquals("hasError", TracerConstants.HASERROR);
    }

    @Test
    void testAppStartConstant() {
        assertEquals("appStart", TracerConstants.APPSTART);
    }

    @Test
    void testAppStopConstant() {
        assertEquals("appStop", TracerConstants.APPSTOP);
    }

    @Test
    void testAppNameConstant() {
        assertEquals("serviceName", TracerConstants.APPNAME);
    }

    @Test
    void testOperationNameConstant() {
        assertEquals("name", TracerConstants.OPERATIONNAME);
    }

    @Test
    void testErrorConstant() {
        assertEquals("error", TracerConstants.ERROR);
    }

    @Test
    void testSuccessConstant() {
        assertEquals("success", TracerConstants.SUCCESS);
    }

    @Test
    void testFailureConstant() {
        assertEquals("failure", TracerConstants.FAILURE);
    }

    @Test
    void testTracerConstant() {
        assertEquals("java-tracer", TracerConstants.TRACER);
    }

    @Test
    void testTracerVersionConstant() {
        assertEquals("1.0.0", TracerConstants.TRACERVERSION);
    }

    @Test
    void testDeviceCodeConstant() {
        assertEquals("deviceCode", TracerConstants.DEVICECODE);
    }

    @Test
    void testDeviceUpgradeContentConstant() {
        assertEquals("DEVICE_UPGRADE_CONTENT", TracerConstants.DEVICE_UPGRADE_CONTENT);
    }

    @Test
    void testEventTimeConstant() {
        assertEquals("businessTime", TracerConstants.EVENT_TIME);
    }

    @Test
    void testKarafConstant() {
        assertEquals("karaf", TracerConstants.KARAF);
    }

    @Test
    void testDateFormatConstant() {
        assertEquals("yyyy-MM-dd HH:mm:ss", TracerConstants.DATEFORMAT);
    }

    @Test
    void testOriginalMessageConstant() {
        assertEquals("orignalMessage", TracerConstants.ORIGINALMESSAGE);
    }

    @Test
    void testDeviceTypeConstant() {
        assertEquals("deviceType", TracerConstants.DEVICETYPE);
    }

    @Test
    void testOriginalMessageCorrectedConstant() {
        assertEquals("originalMessage", TracerConstants.ORININALMESSAGE);
    }

    @Test
    void testOperationConstant() {
        assertEquals("operation", TracerConstants.OPERATION);
    }

    @Test
    void testDeviceUpdateConstant() {
        assertEquals("deviceUpgrade", TracerConstants.DEVICEUPDATE);
    }

    @Test
    void testTagsConstant() {
        assertEquals("tags", TracerConstants.TAGS);
    }

    @Test
    void testHeartbeatConstant() {
        assertEquals("heartbeat", TracerConstants.HEARTBEAT);
    }

    @Test
    void testIpConstant() {
        assertEquals("ip", TracerConstants.IP);
    }

    @Test
    void testMacInConstant() {
        assertEquals("mac", TracerConstants.MACIN);
    }

    @Test
    void testMacOutConstant() {
        assertEquals("client-uuid", TracerConstants.MACOUT);
    }

    @Test
    void testMqttTopicConstant() {
        assertEquals("CamelMqttTopic", TracerConstants.MQTTTOPIC);
    }

    @Test
    void testSoftwareVersionConstant() {
        assertEquals("softwareVersion", TracerConstants.SOFTWAREVERSION);
    }

    @Test
    void testTimeSynchConstant() {
        assertEquals("timeSynch", TracerConstants.TIMESYNCH);
    }

    @Test
    void testNextRedisKeyConstant() {
        assertEquals("nextRedisKey", TracerConstants.NEXTREDISKEY);
    }

    @Test
    void testDeviceUpdatedConstant() {
        assertEquals("deviceUpdated/", TracerConstants.DEVICEUPDATED);
    }

    @Test
    void testDeviceSynchedConstant() {
        assertEquals("deviceSynched/", TracerConstants.DEVICESYNCHED);
    }

    @Test
    void testTimestampConstant() {
        assertEquals("timestamp", TracerConstants.TIMESTAMP);
    }

    @Test
    void testHostnameConstant() {
        assertEquals("hostname", TracerConstants.HOSTNAME);
    }

    @Test
    void testTagInventoryEventConstant() {
        assertEquals("tagInventoryEvent", TracerConstants.TAGINTENTORYEVENT);
    }

    @Test
    void testTidConstant() {
        assertEquals("tid", TracerConstants.TID);
    }

    @Test
    void testEpcConstant() {
        assertEquals("epc", TracerConstants.EPC);
    }

    @Test
    void testAntennaPortConstant() {
        assertEquals("antennaPort", TracerConstants.ANTENNAPORT);
    }

    @Test
    void testKindConstant() {
        assertEquals("kind", TracerConstants.KIND);
    }

    @Test
    void testParentSpanIdConstant() {
        assertEquals("parentSpanID", TracerConstants.PARENTSPANID);
    }
}
