package com.matariky.user.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RoleModel;
import org.keycloak.storage.ReadOnlyException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRoleModelTest {

    @InjectMocks
    private UserRoleModel userRoleModel;

    @Mock
    private RealmModel realm;

    private static final String ROLE_NAME = "testRole";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userRoleModel = new UserRoleModel(ROLE_NAME, realm);
    }

    @Test
    void testGetName() {
        assertThat(userRoleModel.getName()).isEqualTo(ROLE_NAME);
    }

    @Test
    void testGetDescription() {
        assertThat(userRoleModel.getDescription()).isNull();
    }

    @Test
    void testSetDescriptionThrowsException() {
        assertThatThrownBy(() -> userRoleModel.setDescription("new description"))
                .isInstanceOf(ReadOnlyException.class)
                .hasMessage("role is read only");
    }

    @Test
    void testGetId() {
        assertThat(userRoleModel.getId()).isEqualTo(ROLE_NAME);
    }

    @Test
    void testSetNameThrowsException() {
        assertThatThrownBy(() -> userRoleModel.setName("newName"))
                .isInstanceOf(ReadOnlyException.class)
                .hasMessage("role is read only");
    }

    @Test
    void testIsComposite() {
        assertThat(userRoleModel.isComposite()).isFalse();
    }

    @Test
    void testAddCompositeRoleThrowsException() {
        RoleModel roleModel = mock(RoleModel.class);
        assertThatThrownBy(() -> userRoleModel.addCompositeRole(roleModel))
                .isInstanceOf(ReadOnlyException.class)
                .hasMessage("role is read only");
    }

    @Test
    void testRemoveCompositeRoleThrowsException() {
        RoleModel roleModel = mock(RoleModel.class);
        assertThatThrownBy(() -> userRoleModel.removeCompositeRole(roleModel))
                .isInstanceOf(ReadOnlyException.class)
                .hasMessage("role is read only");
    }

    @Test
    void testGetCompositesStream() {
        assertThat(userRoleModel.getCompositesStream(null, null, null)).isEmpty();
    }

    @Test
    void testIsClientRole() {
        assertThat(userRoleModel.isClientRole()).isFalse();
    }

    @Test
    void testGetContainerId() {
        when(realm.getId()).thenReturn("realmId");
        assertThat(userRoleModel.getContainerId()).isEqualTo("realmId");
    }

    @Test
    void testGetContainer() {
        assertThat(userRoleModel.getContainer()).isEqualTo(realm);
    }

    @Test
    void testHasRole() {
        RoleModel roleModel = mock(RoleModel.class);
        when(roleModel.getName()).thenReturn(ROLE_NAME);
        assertThat(userRoleModel.hasRole(roleModel)).isTrue();
    }

    @Test
    void testSetSingleAttributeThrowsException() {
        assertThatThrownBy(() -> userRoleModel.setSingleAttribute("key", "value"))
                .isInstanceOf(ReadOnlyException.class)
                .hasMessage("role is read only");
    }

    @Test
    void testSetAttributeThrowsException() {
        assertThatThrownBy(() -> userRoleModel.setAttribute("key", List.of("value")))
                .isInstanceOf(ReadOnlyException.class)
                .hasMessage("role is read only");
    }

    @Test
    void testRemoveAttributeThrowsException() {
        assertThatThrownBy(() -> userRoleModel.removeAttribute("key"))
                .isInstanceOf(ReadOnlyException.class)
                .hasMessage("role is read only");
    }

    @Test
    void testGetAttributeStream() {
        assertThat(userRoleModel.getAttributeStream("key")).isEmpty();
    }

    @Test
    void testGetAttributes() {
        assertThat(userRoleModel.getAttributes()).isEmpty();
    }
}
