package com.matariky.constant;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TracerConstantsTest {

    @InjectMocks
    private TracerConstants tracerconstants;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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

}
