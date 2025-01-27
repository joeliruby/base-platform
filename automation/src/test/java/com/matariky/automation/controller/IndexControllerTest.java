package com.matariky.automation.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.springframework.ui.Model;
import org.springframework.data.domain.Pageable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.matariky.automation.bean.Db;
import com.matariky.automation.bean.DbBean;
import com.matariky.automation.bean.RequestData;
import java.io.IOException;

@SpringBootTest
public class IndexControllerTest {

    @InjectMocks
    private IndexController indexcontroller;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Model model;

    @Mock
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIndex() {
        // Given
        when(pageable.getPageNumber()).thenReturn(1);
        when(pageable.getPageSize()).thenReturn(10);

        // When
        String viewName = indexcontroller.index(request, model, pageable);

        // Then
        assertEquals("logs/index", viewName);
        verify(model).addAttribute("pag", 1);
        verify(model).addAttribute("pags", 10);
    }

    @Test
    void testAdmin() {
        // When
        String viewName = indexcontroller.admin(model, pageable);

        // Then
        assertEquals("administrator/index", viewName);
    }

    @Test
    void testLoginDB() {
        // Given
        Db db = new Db();
        RequestData expectedResponse = new RequestData();
        expectedResponse.setCode("SUCCESS");
        expectedResponse.setMsg("SUCCESS READING TABLE！");

        // When
        RequestData response = indexcontroller.loginDB(db);

        // Then
        assertEquals(expectedResponse.getCode(), response.getCode());
        assertEquals(expectedResponse.getMsg(), response.getMsg());
    }

    @Test
    void testLoginDBBytable() {
        // Given
        Db db = new Db();
        RequestData expectedResponse = new RequestData();
        expectedResponse.setCode("SUCCESS");
        expectedResponse.setMsg("SUCCESS READING TABLE ATTRIBUTES！");

        // When
        RequestData response = indexcontroller.loginDBBytable(db);

        // Then
        assertEquals(expectedResponse.getCode(), response.getCode());
        assertEquals(expectedResponse.getMsg(), response.getMsg());
    }

    @Test
    void testDownloadZip() throws IOException {
        // Given
        String key = "testKey";

        // When
        indexcontroller.loginDBBytable(key, response);

        // Then
        verify(response).setContentType("application/zip");
    }

    @Test
    void testExprot() {
        // Given
        DbBean dbBean = new DbBean();
        RequestData expectedResponse = new RequestData();
        expectedResponse.setCode("SUCCESS");

        // When
        RequestData response = indexcontroller.index(request, dbBean);

        // Then
        assertEquals(expectedResponse.getCode(), response.getCode());
    }
}
