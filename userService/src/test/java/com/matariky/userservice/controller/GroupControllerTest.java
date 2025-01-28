package com.matariky.userservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.matariky.userservice.bean.UserGroup;
import com.matariky.userservice.service.UserGroupService;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class GroupControllerTest {

    @InjectMocks
    private GroupController groupController;

    @Mock
    private UserGroupService groupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchGroupByNamePrefix() {
        // Given
        String tenantId = "tenant1";
        String groupNamePrefix = "group";
        UserGroup group1 = new UserGroup();
        group1.setId(1l);
        group1.setGroupName("group1");
        group1.setOrgCode("org1");
        UserGroup group2 = new UserGroup();
        // "2", "group2", "org2"
        group2.setId(2l);
        group2.setGroupName("group2");
        group2.setOrgCode("group2");
        List<UserGroup> groupList = Arrays.asList(group1, group2);
        when(groupService.searchByGroupNamePrefix(tenantId, groupNamePrefix)).thenReturn(groupList);

        // When
        AjaxResult result = groupController.searchGroupByNamePrefix(tenantId, groupNamePrefix, "jwt");

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        assertThat(result.get(AjaxResult.DATA_TAG)).isNotNull();
        assertThat(((List<?>) result.get(AjaxResult.DATA_TAG))).size().isEqualTo(2);
    }

    // Add more test methods for other methods in GroupController
}
