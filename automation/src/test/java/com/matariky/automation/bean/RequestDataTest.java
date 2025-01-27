package com.matariky.automation.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class RequestDataTest {

    @Mock
    private RequestData requestData;

    @InjectMocks
    private RequestData requestDataInjected;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetMsg() {
        // Given
        String msg = "Test Message";

        // When
        requestDataInjected.setMsg(msg);

        // Then
        assertEquals(msg, requestDataInjected.getMsg());
    }

    @Test
    void testSetAndGetCode() {
        // Given
        String code = "200";

        // When
        requestDataInjected.setCode(code);

        // Then
        assertEquals(code, requestDataInjected.getCode());
    }

    @Test
    void testSetAndGetObjData() {
        // Given
        Object objData = new Object();

        // When
        requestDataInjected.setObjData(objData);

        // Then
        assertEquals(objData, requestDataInjected.getObjData());
    }

    @Test
    void testSetAndGetListData() {
        // Given
        List<String> listData = List.of("item1", "item2");

        // When
        requestDataInjected.setListData(listData);

        // Then
        assertEquals(listData, requestDataInjected.getListData());
    }

    @Test
    void testSetAndGetKey() {
        // Given
        String key = "TestKey";

        // When
        requestDataInjected.setKey(key);

        // Then
        assertEquals(key, requestDataInjected.getKey());
    }
}
