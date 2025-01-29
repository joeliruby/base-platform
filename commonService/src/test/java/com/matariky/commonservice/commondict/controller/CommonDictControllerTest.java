package com.matariky.commonservice.commondict.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class CommonDictControllerTest {

    @InjectMocks
    private CommonDictController commondictcontroller;

    @Mock
    private CommonDictService commonDictService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        Map<String, Object> params = new HashMap<>();
        params.put("index", "1");
        params.put("perPage", "20");
        String tenantId = "tenant1";
        String jwt = "someJwtToken";

        // When
        AjaxResult result = commondictcontroller.list(null, params, tenantId, jwt);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testEdit() {
        // Given
        Long id = 1L;

        // When
        AjaxResult result = (AjaxResult) commondictcontroller.edit(null, id);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testInitialList() {
        // Given
        String tenantId = "tenant1";

        // When
        AjaxResult result = (AjaxResult) commondictcontroller.initialList(tenantId);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testSave() {
        // Given
        CommonDict bean = new CommonDict();
        bean.setIsActive(true);
        bean.setTenantId("tenant1");

        // When
        AjaxResult result = (AjaxResult) commondictcontroller.save(bean, null, null);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testUpdate() {
        // Given
        CommonDict bean = new CommonDict();
        bean.setIsActive(true);
        bean.setTenantId("tenant1");

        // When
        AjaxResult result = (AjaxResult) commondictcontroller.update(bean, null, null);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";

        // When
        AjaxResult result = (AjaxResult) commondictcontroller.del(id, null, null);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testDictListByTypeKey() {
        // Given
        String tenantId = "tenant1";
        String typeKey = "typeKey";
        Long isActive = 1L;
        Long deleteTime = 0L;

        // When
        AjaxResult result = (AjaxResult) commondictcontroller.dictListByTypeKey(tenantId, typeKey, isActive,
                deleteTime);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }
}
