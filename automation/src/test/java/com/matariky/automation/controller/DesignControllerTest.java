package com.matariky.automation.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mockito.Mock;
import org.springframework.ui.Model;
import org.springframework.data.domain.Pageable;
import com.matariky.automation.bean.DataBaseBean;
import com.matariky.automation.bean.DbBean;
import com.matariky.automation.bean.RequestData;
import com.matariky.automation.utils.CheckedSession;
import com.matariky.automation.utils.LoginDb;
import com.matariky.automation.utils.WordUtil;

@SpringBootTest
public class DesignControllerTest {

    @InjectMocks
    private DesignController designController;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Model model;

    @Mock
    private Pageable pageable;

    @Mock
    private DbBean dbBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdmin() {
        // When
        String viewName = designController.admin(model, pageable);

        // Then
        assertEquals("design/index", viewName);
    }

    @Test
    void testIndexWithError() {
        // Given
        when(dbBean.getZdname()).thenReturn(null);

        // When
        RequestData result = designController.index(request, dbBean);

        // Then
        assertEquals("ERROR", result.getCode());
        assertEquals("Error reading database Info", result.getMsg());
    }

    @Test
    void testIndexWithSuccess() throws Exception {
        // Given
        when(dbBean.getZdname()).thenReturn("table1@ctable1,table2@ctable2");
        when(CheckedSession.check(request)).thenReturn("sessionKey");
        List<DataBaseBean> dbList = new ArrayList<>();
        DataBaseBean dbBean1 = new DataBaseBean();
        dbBean1.setTablename("table1");
        dbBean1.setCtablename("ctable1");
        dbBean1.setList(new ArrayList<>());
        dbList.add(dbBean1);
        DataBaseBean dbBean2 = new DataBaseBean();
        dbBean2.setTablename("table2");
        dbBean2.setCtablename("ctable2");
        dbBean2.setList(new ArrayList<>());
        dbList.add(dbBean2);
        doNothing().when(LoginDb.class);
        LoginDb.colseDb();
        doNothing().when(WordUtil.class);
        WordUtil.createWord(anyMap(), anyString(), anyString(), anyString());

        // When
        RequestData result = designController.index(request, dbBean);

        // Then
        assertEquals("SUCCESS", result.getCode());
        assertEquals("EXPORT SUCCESS！", result.getMsg());
    }

    @Test
    void testDownload() {
        // Given
        String key = "testKey";

        // When
        designController.download(key, response);

        // Then
        // Verify that the WordUtil.downLoad method was called
        verify(WordUtil.class);
        WordUtil.downLoad(eq(key + ".doc"), anyString(), eq(response));
    }
}
