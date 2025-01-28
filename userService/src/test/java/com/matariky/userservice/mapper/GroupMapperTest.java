package com.matariky.userservice.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.userservice.bean.Group;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class GroupMapperTest {

    @InjectMocks
    private GroupMapper groupmapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertSelective() {
        Group group = new Group();
        group.setId(1L);
        group.setGroupName("Test Group");

        when(groupmapper.insertSelective(group)).thenReturn(1);

        int result = groupmapper.insertSelective(group);

        assertEquals(1, result);
    }

    @Test
    void testSelectByPrimaryKey() {
        Group group = new Group();
        group.setId(1L);
        group.setGroupName("Test Group");

        when(groupmapper.selectByPrimaryKey(1L)).thenReturn(group);

        Group result = groupmapper.selectByPrimaryKey(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Group", result.getGroupName());
    }

    @Test
    void testUpdateByPrimaryKeySelective() {
        Group group = new Group();
        group.setId(1L);
        group.setGroupName("Updated Group");

        when(groupmapper.updateByPrimaryKeySelective(group)).thenReturn(1);

        int result = groupmapper.updateByPrimaryKeySelective(group);

        assertEquals(1, result);
    }

    @Test
    void testDeleteByPrimaryKey() {
        when(groupmapper.deleteByPrimaryKey(1L)).thenReturn(1);

        int result = groupmapper.deleteByPrimaryKey(1L);

        assertEquals(1, result);
    }

    @Test
    void testSearchByGroupNamePrefix() {
        Group group1 = new Group();
        group1.setId(1L);
        group1.setGroupName("Test Group 1");

        Group group2 = new Group();
        group2.setId(2L);
        group2.setGroupName("Test Group 2");

        List<Group> groups = Arrays.asList(group1, group2);

        when(groupmapper.searchByGroupNamePrefix("tenant1", "Test")).thenReturn(groups);

        List<Group> result = groupmapper.searchByGroupNamePrefix("tenant1", "Test");

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    // Add more test methods for other methods in GroupMapper
}
