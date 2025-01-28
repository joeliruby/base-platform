package com.matariky.userservice.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TreeModelTest {

    @InjectMocks
    private TreeModel treemodel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        treemodel = new TreeModel();
    }

    @Test
    void testSetAndGetId() {
        // Given
        Long id = 1L;

        // When
        treemodel.setId(id);

        // Then
        assertThat(treemodel.getId()).isEqualTo(id);
    }

    @Test
    void testSetAndGetPid() {
        // Given
        Long pid = 2L;

        // When
        treemodel.setPid(pid);

        // Then
        assertThat(treemodel.getPid()).isEqualTo(pid);
    }

    @Test
    void testSetAndGetName() {
        // Given
        String name = "Test Name";

        // When
        treemodel.setName(name);

        // Then
        assertThat(treemodel.getName()).isEqualTo(name);
    }

    @Test
    void testSetAndGetCode() {
        // Given
        String code = "Test Code";

        // When
        treemodel.setCode(code);

        // Then
        assertThat(treemodel.getCode()).isEqualTo(code);
    }

    @Test
    void testSetAndGetInSuite() {
        // Given
        Long inSuite = 3L;

        // When
        treemodel.setInSuite(inSuite);

        // Then
        assertThat(treemodel.getInSuite()).isEqualTo(inSuite);
    }

    @Test
    void testSetAndGetDisabled() {
        // Given
        boolean disabled = true;

        // When
        treemodel.setDisabled(disabled);

        // Then
        assertThat(treemodel.isDisabled()).isEqualTo(disabled);
    }

    @Test
    void testSetAndGetUserGroupId() {
        // Given
        Long userGroupId = 4L;

        // When
        treemodel.setUserGroupId(userGroupId);

        // Then
        assertThat(treemodel.getUserGroupId()).isEqualTo(userGroupId);
    }

    @Test
    void testSetAndGetUrl() {
        // Given
        String url = "http://example.com";

        // When
        treemodel.setUrl(url);

        // Then
        assertThat(treemodel.getUrl()).isEqualTo(url);
    }

    @Test
    void testSetAndGetResourceType() {
        // Given
        Integer resourceType = 5;

        // When
        treemodel.setResourceType(resourceType);

        // Then
        assertThat(treemodel.getResourceType()).isEqualTo(resourceType);
    }

    @Test
    void testSetAndGetApplicationName() {
        // Given
        String applicationName = "Test App";

        // When
        treemodel.setApplicationName(applicationName);

        // Then
        assertThat(treemodel.getApplicationName()).isEqualTo(applicationName);
    }

    @Test
    void testSetAndGetIcon() {
        // Given
        String icon = "test-icon";

        // When
        treemodel.setIcon(icon);

        // Then
        assertThat(treemodel.getIcon()).isEqualTo(icon);
    }

    @Test
    void testSetAndGetIsActive() {
        // Given
        boolean isActive = true;

        // When
        treemodel.setActive(isActive);

        // Then
        assertThat(treemodel.isActive()).isEqualTo(isActive);
    }

    @Test
    void testSetAndGetSortOrder() {
        // Given
        Long sortOrder = 6L;

        // When
        treemodel.setSortOrder(sortOrder);

        // Then
        assertThat(treemodel.getSortOrder()).isEqualTo(sortOrder);
    }

    @Test
    void testSetAndGetIsCheck() {
        // Given
        boolean isCheck = true;

        // When
        treemodel.setCheck(isCheck);

        // Then
        assertThat(treemodel.isCheck()).isEqualTo(isCheck);
    }
}
