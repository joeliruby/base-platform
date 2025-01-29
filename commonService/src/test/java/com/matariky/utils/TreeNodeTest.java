package com.matariky.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TreeNodeTest {

    @InjectMocks
    private TreeNode<Long> treeNode;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetId() {
        // Given
        Long expectedId = 1L;

        // When
        treeNode.setId(expectedId);

        // Then
        assertThat(treeNode.getId()).isEqualTo(expectedId);
    }

    @Test
    void testSetAndGetPid() {
        // Given
        Long expectedPid = 2L;

        // When
        treeNode.setPid(expectedPid);

        // Then
        assertThat(treeNode.getPid()).isEqualTo(expectedPid);
    }

    @Test
    void testSetAndGetChildren() {
        // Given
        List<Long> expectedChildren = new ArrayList<>();
        expectedChildren.add(1l);
        expectedChildren.add(2l);
        expectedChildren.add(3l);

        // When
        treeNode.setChildren(expectedChildren);

        // Then
        assertThat(treeNode.getChildren()).isEqualTo(expectedChildren);
    }

    @Test
    void testSetAndGetSortOrder() {
        // Given
        Long expectedSortOrder = 3L;

        // When
        treeNode.setSortOrder(expectedSortOrder);

        // Then
        assertThat(treeNode.getSortOrder()).isEqualTo(expectedSortOrder);
    }

    // Add more test methods for other methods in TreeNode if needed
}
