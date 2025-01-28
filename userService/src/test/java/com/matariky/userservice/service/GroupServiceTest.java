package com.matariky.userservice.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.List;
import org.mockito.Mock;
import com.matariky.userservice.bean.Group;
import com.matariky.userservice.mapper.GroupMapper;

@SpringBootTest
public class GroupServiceTest {

    @InjectMocks
    private GroupService groupService;

    @Mock
    private GroupMapper groupMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchByGroupNamePrefix() {
        // Given
        String tenantId = "tenant1";
        String groupNamePrefix = "group";
        List<Group> expectedGroups = Arrays.asList(new Group(), new Group());
        when(groupMapper.searchByGroupNamePrefix(tenantId, groupNamePrefix)).thenReturn(expectedGroups);

        // When
        List<Group> actualGroups = groupService.searchByGroupNamePrefix(tenantId, groupNamePrefix);

        // Then
        assertThat(actualGroups).isEqualTo(expectedGroups);
        verify(groupMapper, times(1)).searchByGroupNamePrefix(tenantId, groupNamePrefix);
    }

    // Add more test methods for other methods in GroupService
}
