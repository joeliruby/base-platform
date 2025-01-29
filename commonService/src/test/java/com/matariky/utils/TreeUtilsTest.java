package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.redis.RedisUtils;

@SpringBootTest
public class TreeUtilsTest {

    @InjectMocks
    private TreeUtils treeutils;

    @Mock
    private RedisUtils redisUtils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuildWithPid() {
        // Given
        List<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.add(new TreeNode());
        treeNodes.add(new TreeNode());
        treeNodes.add(new TreeNode());

        // When
        List<TreeNode> result = TreeUtils.build(treeNodes, 0L);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getChildren().size());
    }

    @Test
    void testBuildWithRedisUtils() {
        // Given
        List<TreeNode> treeNodes = new ArrayList<>();
        TreeNode tn1 = new TreeNode();
        tn1.setId(1L);
        tn1.setPid(0L);
        treeNodes.add(tn1);
        TreeNode tn2 = new TreeNode();
        tn2.setId(2L);
        tn2.setPid(1L);
        treeNodes.add(tn2);
        TreeNode tn3 = new TreeNode();
        tn3.setId(2L);
        tn3.setPid(1L);
        treeNodes.add(tn3);

        when(redisUtils.hGet(anyString(), anyString())).thenReturn("MenuName");

        // When
        List<TreeNode> result = TreeUtils.build(treeNodes, redisUtils, "en");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("MenuName", result.get(0).toString());
    }

    @Test
    void testBuildWithoutPid() {
        // Given
        List<TreeNode> treeNodes = new ArrayList<>();
        TreeNode tn1 = new TreeNode();
        tn1.setId(1L);
        tn1.setPid(0L);
        treeNodes.add(tn1);
        TreeNode tn2 = new TreeNode();
        tn2.setId(2L);
        tn2.setPid(1L);
        treeNodes.add(tn2);
        TreeNode tn3 = new TreeNode();
        tn3.setId(2L);
        tn3.setPid(1L);
        treeNodes.add(tn3);

        // When
        List<TreeNode> result = TreeUtils.build(treeNodes);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getChildren().size());
    }

    // Add more test methods for other methods in TreeUtils
}
