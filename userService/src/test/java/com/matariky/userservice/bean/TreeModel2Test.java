package com.matariky.userservice.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TreeModel2Test {

    @InjectMocks
    private TreeModel2 treemodel2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        Long pid = 2L;
        String label = "Test Label";
        String code = "Test Code";
        String url = "http://test.com";
        Integer resourceType = 1;
        String applicationName = "Test App";
        String icon = "test-icon";
        boolean isActive = true;
        Long sortOrder = 1L;

        // When
        treemodel2.setId(id);
        treemodel2.setPid(pid);
        treemodel2.setLabel(label);
        treemodel2.setCode(code);
        treemodel2.setUrl(url);
        treemodel2.setResourceType(resourceType);
        treemodel2.setApplicationName(applicationName);
        treemodel2.setIcon(icon);
        treemodel2.setActive(isActive);
        treemodel2.setSortOrder(sortOrder);

        // Then
        assertThat(treemodel2.getId()).isEqualTo(id);
        assertThat(treemodel2.getPid()).isEqualTo(pid);
        assertThat(treemodel2.getLabel()).isEqualTo(label);
        assertThat(treemodel2.getCode()).isEqualTo(code);
        assertThat(treemodel2.getUrl()).isEqualTo(url);
        assertThat(treemodel2.getResourceType()).isEqualTo(resourceType);
        assertThat(treemodel2.getApplicationName()).isEqualTo(applicationName);
        assertThat(treemodel2.getIcon()).isEqualTo(icon);
        assertThat(treemodel2.isActive()).isEqualTo(isActive);
        assertThat(treemodel2.getSortOrder()).isEqualTo(sortOrder);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        TreeModel2 treeModel1 = new TreeModel2();
        treeModel1.setId(1L);
        TreeModel2 treeModel2 = new TreeModel2();
        treeModel2.setId(1L);

        // When & Then
        assertThat(treeModel1).isEqualTo(treeModel2);
        assertThat(treeModel1.hashCode()).isEqualTo(treeModel2.hashCode());
    }

    @Test
    void testToString() {
        // Given
        treemodel2.setId(1L);
        treemodel2.setLabel("Test Label");

        // When
        String toString = treemodel2.toString();

        // Then
        assertThat(toString).contains("id=1");
        assertThat(toString).contains("label=Test Label");
    }

    // Add more test methods for other methods in TreeModel2
}
