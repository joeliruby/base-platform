package com.matariky.commonservice.log.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicLogBusinessTest {

    @InjectMocks
    private BasicLogBusiness basiclogbusiness;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetId() {
        // Given
        Long expectedId = 1L;

        // When
        basiclogbusiness.setId(expectedId);

        // Then
        assertEquals(expectedId, basiclogbusiness.getId());
    }

    @Test
    void testSetAndGetSystemVersion() {
        // Given
        String expectedSystemVersion = "1.0";

        // When
        basiclogbusiness.setSystemVersion(expectedSystemVersion);

        // Then
        assertEquals(expectedSystemVersion, basiclogbusiness.getSystemVersion());
    }

    @Test
    void testSetAndGetAccessTerminals() {
        // Given
        String expectedAccessTerminals = "Terminal1";

        // When
        basiclogbusiness.setAccessTerminals(expectedAccessTerminals);

        // Then
        assertEquals(expectedAccessTerminals, basiclogbusiness.getAccessTerminals());
    }

    @Test
    void testSetAndGetAccessModule() {
        // Given
        String expectedAccessModule = "Module1";

        // When
        basiclogbusiness.setAccessModule(expectedAccessModule);

        // Then
        assertEquals(expectedAccessModule, basiclogbusiness.getAccessModule());
    }

    @Test
    void testSetAndGetAccessUrl() {
        // Given
        String expectedAccessUrl = "http://example.com";

        // When
        basiclogbusiness.setAccessUrl(expectedAccessUrl);

        // Then
        assertEquals(expectedAccessUrl, basiclogbusiness.getAccessUrl());
    }

    @Test
    void testSetAndGetAccessInterface() {
        // Given
        String expectedAccessInterface = "Interface1";

        // When
        basiclogbusiness.setAccessInterface(expectedAccessInterface);

        // Then
        assertEquals(expectedAccessInterface, basiclogbusiness.getAccessInterface());
    }

    @Test
    void testSetAndGetRequestParameters() {
        // Given
        String expectedRequestParameters = "param1=value1";

        // When
        basiclogbusiness.setRequestParameters(expectedRequestParameters);

        // Then
        assertEquals(expectedRequestParameters, basiclogbusiness.getRequestParameters());
    }

    @Test
    void testSetAndGetBusinessOperateAction() {
        // Given
        String expectedBusinessOperateAction = "Action1";

        // When
        basiclogbusiness.setBusinessOperateAction(expectedBusinessOperateAction);

        // Then
        assertEquals(expectedBusinessOperateAction, basiclogbusiness.getBusinessOperateAction());
    }

    @Test
    void testSetAndGetOperateContent() {
        // Given
        String expectedOperateContent = "Content1";

        // When
        basiclogbusiness.setOperateContent(expectedOperateContent);

        // Then
        assertEquals(expectedOperateContent, basiclogbusiness.getOperateContent());
    }

    @Test
    void testSetAndGetReturnContent() {
        // Given
        String expectedReturnContent = "Return1";

        // When
        basiclogbusiness.setReturnContent(expectedReturnContent);

        // Then
        assertEquals(expectedReturnContent, basiclogbusiness.getReturnContent());
    }

    @Test
    void testSetAndGetBusinessTime() {
        // Given
        Long expectedBusinessTime = 123456789L;

        // When
        basiclogbusiness.setBusinessTime(expectedBusinessTime);

        // Then
        assertEquals(expectedBusinessTime, basiclogbusiness.getBusinessTime());
    }

    @Test
    void testSetAndGetAccessAccount() {
        // Given
        String expectedAccessAccount = "Account1";

        // When
        basiclogbusiness.setAccessAccount(expectedAccessAccount);

        // Then
        assertEquals(expectedAccessAccount, basiclogbusiness.getAccessAccount());
    }

}