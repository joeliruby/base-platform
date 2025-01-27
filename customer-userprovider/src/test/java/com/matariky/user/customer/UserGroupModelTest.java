package com.matariky.user.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.models.ClientModel;
import org.keycloak.models.GroupModel;
import org.keycloak.models.RoleModel;
import org.keycloak.storage.ReadOnlyException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserGroupModelTest {

    @InjectMocks
    private UserGroupModel userGroupModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userGroupModel = new UserGroupModel("testGroup");
    }

    @Test
    void testGetId() {
        assertThat(userGroupModel.getId()).isEqualTo("testGroup");
    }

    @Test
    void testGetName() {
        assertThat(userGroupModel.getName()).isEqualTo("testGroup");
    }

    @Test
    void testSetNameThrowsReadOnlyException() {
        assertThatThrownBy(() -> userGroupModel.setName("newName"))
                .isInstanceOf(ReadOnlyException.class)
                .hasMessage("group is read only");
    }

    @Test
    void testSetSingleAttributeThrowsReadOnlyException() {
        assertThatThrownBy(() -> userGroupModel.setSingleAttribute("key", "value"))
                .isInstanceOf(ReadOnlyException.class)
                .hasMessage("group is read only");
    }

    @Test
    void testSetAttributeThrowsReadOnlyException() {
        assertThatThrownBy(() -> userGroupModel.setAttribute("key", List.of("value")))
                .isInstanceOf(ReadOnlyException.class)
                .hasMessage("group is read only");
    }

    @Test
    void testRemoveAttributeThrowsReadOnlyException() {
        assertThatThrownBy(() -> userGroupModel.removeAttribute("key"))
                .isInstanceOf(ReadOnlyException.class)
                .hasMessage("group is read only");
    }

    @Test
    void testGetFirstAttribute() {
        assertThat(userGroupModel.getFirstAttribute("key")).isNull();
    }

    @Test
    void testGetAttributeStream() {
        assertThat(userGroupModel.getAttributeStream("key")).isEmpty();
    }

    @Test
    void testGetAttributes() {
        assertThat(userGroupModel.getAttributes()).isEmpty();
    }

    @Test
    void testGetParent() {
        assertThat(userGroupModel.getParent()).isNull();
    }

    @Test
    void testGetParentId() {
        assertThat(userGroupModel.getParentId()).isNull();
    }

    @Test
    void testGetSubGroupsStream() {
        assertThat(userGroupModel.getSubGroupsStream()).isEmpty();
    }

    @Test
    void testSetParentThrowsReadOnlyException() {
        assertThatThrownBy(() -> userGroupModel.setParent(mock(GroupModel.class)))
                .isInstanceOf(ReadOnlyException.class)
                .hasMessage("group is read only");
    }

    @Test
    void testAddChildThrowsReadOnlyException() {
        assertThatThrownBy(() -> userGroupModel.addChild(mock(GroupModel.class)))
                .isInstanceOf(ReadOnlyException.class)
                .hasMessage("group is read only");
    }

    @Test
    void testRemoveChildThrowsReadOnlyException() {
        assertThatThrownBy(() -> userGroupModel.removeChild(mock(GroupModel.class)))
                .isInstanceOf(ReadOnlyException.class)
                .hasMessage("group is read only");
    }

    @Test
    void testGetRealmRoleMappingsStream() {
        assertThat(userGroupModel.getRealmRoleMappingsStream()).isEmpty();
    }

    @Test
    void testGetClientRoleMappingsStream() {
        assertThat(userGroupModel.getClientRoleMappingsStream(mock(ClientModel.class))).isEmpty();
    }

    @Test
    void testHasRole() {
        assertThat(userGroupModel.hasRole(mock(RoleModel.class))).isFalse();
    }

    @Test
    void testGrantRoleThrowsReadOnlyException() {
        assertThatThrownBy(() -> userGroupModel.grantRole(mock(RoleModel.class)))
                .isInstanceOf(ReadOnlyException.class)
                .hasMessage("group is read only");
    }

    @Test
    void testGetRoleMappingsStream() {
        assertThat(userGroupModel.getRoleMappingsStream()).isEmpty();
    }

    @Test
    void testDeleteRoleMappingThrowsReadOnlyException() {
        assertThatThrownBy(() -> userGroupModel.deleteRoleMapping(mock(RoleModel.class)))
                .isInstanceOf(ReadOnlyException.class)
                .hasMessage("group is read only");
    }
}
