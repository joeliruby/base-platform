package com.matariky.commonservice.area.controller;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.matariky.commonservice.area.bean.CommonArea;
import com.matariky.commonservice.area.bean.CommonAreaVo;
import com.matariky.commonservice.area.service.CommonAreaService;
import com.matariky.commonservice.commondict.service.CommonDictService;

@SpringBootTest
public class CommonAreaControllerTest {

    @InjectMocks
    private CommonAreaController commonAreaController;

    @Mock
    private CommonAreaService commonAreaService;

    @Mock
    private CommonDictService commonDictService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        Page<CommonArea> expectedPage = PageHelper.startPage(1, 10);
        when(commonAreaService.getCommonAreaAll()).thenReturn(expectedPage);

        // When
        Page<CommonArea> result = commonAreaController.list(null, null, "tenantId", 1, 10, "jwt");

        // Then
        assertThat(result).isEqualTo(expectedPage);
    }

    @Test
    void testSubNodes() {
        // Given
        Long nodeId = 1L;
        List<CommonAreaVo> expectedList = Arrays.asList(new CommonAreaVo());
        when(commonAreaService.subNodesById(nodeId)).thenReturn(expectedList);

        // When
        List<CommonAreaVo> result = commonAreaController.subNodes(null, "tenantId", nodeId);

        // Then
        assertThat(result).isEqualTo(expectedList);
    }

    @Test
    void testGetAreaByParentId() {
        // Given
        Long parentId = 1L;
        List<CommonArea> expectedList = Arrays.asList(new CommonArea());
        when(commonAreaService.getAreaByParentId(parentId)).thenReturn(expectedList);

        // When
        Object result = commonAreaController.getAreaByParentId(null, null, parentId, "tenantId");

        // Then
        assertThat(result).isEqualTo(expectedList);
    }

    @Test
    void testSave() {
        // Given
        CommonArea commonArea = new CommonArea();
        when(commonAreaService.insert(commonArea)).thenReturn(true);

        // When
        ResponseEntity<String> result = commonAreaController.save(commonArea, null, null);

        // Then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo("SUCCESS");
    }

    @Test
    void testUpdate() {
        // Given
        CommonArea commonArea = new CommonArea();
        when(commonAreaService.updateById(commonArea)).thenReturn(true);

        // When
        ResponseEntity<String> result = commonAreaController.update(commonArea, null, null);

        // Then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo("SUCCESS");
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";
        when(commonAreaService.deleteById(Long.parseLong(id))).thenReturn(true);

        // When
        ResponseEntity<String> result = commonAreaController.del(id, null, null);

        // Then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo("SUCCESS");
    }
}
