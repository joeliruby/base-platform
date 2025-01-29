package com.matariky.commonservice.log.controller;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import com.matariky.commonservice.log.service.BasicLogBusinessService;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.log.bean.BasicLogBusiness;

@SpringBootTest
@AutoConfigureMockMvc
public class BasicLogBusinessControllerTest {

    @InjectMocks
    private BasicLogBusinessController basiclogbusinesscontroller;

    @Mock
    private BasicLogBusinessService basicLogBusinessService;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private CommonDictTypeService commonDictTypeService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() throws Exception {
        PageInfo<BasicLogBusiness> pageInfo = new PageInfo<>(Collections.emptyList());
        when(basicLogBusinessService.getBasicLogBusinessAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/tenant/1/basicLogBusiness/list")
                .param("index", "1")
                .param("perPage", "10")
                .header("Authorization", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.list").isEmpty());
    }

    @Test
    void testSave() throws Exception {
        BasicLogBusiness logBusiness = new BasicLogBusiness();
        when(basicLogBusinessService.createBasicLogBusinessWithOrg(any(BasicLogBusiness.class), any())).thenReturn(1);

        mockMvc.perform(post("/api/v1/tenant/1/basicLogBusiness")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(logBusiness)))
                .andExpect(status().isOk())
                .andExpect(content().string("SUCCESS"));
    }

    @Test
    void testUpdate() throws Exception {
        BasicLogBusiness logBusiness = new BasicLogBusiness();
        when(basicLogBusinessService.updateBasicLogBusiness(any(BasicLogBusiness.class))).thenReturn(1);

        mockMvc.perform(put("/api/v1/tenant/1/basicLogBusiness")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(logBusiness)))
                .andExpect(status().isOk())
                .andExpect(content().string("SUCCESS"));
    }

    @Test
    void testDelete() throws Exception {
        when(basicLogBusinessService.deleteById(any(Long.class))).thenReturn(true);

        mockMvc.perform(delete("/api/v1/tenant/1/basicLogBusiness")
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("SUCCESS"));
    }

    @Test
    void testGetOne() throws Exception {
        BasicLogBusiness logBusiness = new BasicLogBusiness();
        when(basicLogBusinessService.selectById(any(Long.class))).thenReturn(logBusiness);

        mockMvc.perform(get("/api/v1/tenant/1/basicLogBusiness/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(logBusiness.getId()));
    }
}
