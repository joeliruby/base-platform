package com.matariky.userservice.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.loginlog.service.ICommonLoginLogService;
import com.matariky.orderservice.mapper.OrderInfoMapper;
import com.matariky.orderservice.service.OrderInfoService;
import com.matariky.redis.RedisUtils;
import com.matariky.userservice.bean.User;
import com.matariky.userservice.service.CaptchaService;
import com.matariky.userservice.service.OrganizationService;
import com.matariky.userservice.service.TokenService;
import com.matariky.userservice.service.UserApplicationService;
import com.matariky.userservice.service.UserOrganizationService;
import com.matariky.userservice.service.UserService;
import com.matariky.userservice.service.UserTenantService;

@SpringBootTest
public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private UserApplicationService userApplicationService;

    @Mock
    private RedisUtils redisUtils;

    @Mock
    private CaptchaService captchaService;

    @Mock
    private UserService userService;

    @Mock
    private TokenService tokenService;

    @Mock
    private OrganizationService organizationService;

    @Mock
    private UserOrganizationService userOrganizationService;

    @Mock
    private ICommonLoginLogService commonLoginLogService;

    @Mock
    private UserTenantService tenantService;

    @Mock
    private OrderInfoService orderInfoService;

    @Mock
    private OrderInfoMapper orderInfoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRandomImage() {
        // Given
        HttpServletResponse response = mock(HttpServletResponse.class);
        String uuid = "test-uuid";

        // When
        Object result = loginController.randomImage(response, uuid);

        // Then
        assertNotNull(result);
    }

    @Test
    void testRenew() {
        // Given
        String token = "test-token";
        Long applicationId = 1L;
        String locale = "en";

        // When
        Object result = loginController.renew(token, applicationId, locale);

        // Then
        assertNotNull(result);
    }

    @Test
    void testSearchUserByName() {
        // Given
        String tenantId = "test-tenant";
        String userNamePrefix = "test-user";
        String jwt = "test-jwt";

        // When
        Object result = loginController.searchUserByName(tenantId, userNamePrefix, jwt);

        // Then
        assertNotNull(result);
    }

    @Test
    void testGetUserById() {
        // Given
        String userId = "test-user-id";

        // When
        Object result = loginController.getUserById(userId);

        // Then
        assertNotNull(result);
    }

    @Test
    void testLogout() {
        // Given
        String userId = "test-user-id";
        HttpServletRequest request = mock(HttpServletRequest.class);

        // When
        Object result = loginController.logout(userId, request);

        // Then
        assertNotNull(result);
    }

    @Test
    void testIotUserByUsername() {
        // Given
        HttpServletRequest request = mock(HttpServletRequest.class);
        String userName = "test-user";

        // When
        Object result = loginController.iotUserByUsername(request, userName);

        // Then
        assertNotNull(result);
    }

    @Test
    void testIotCreentialsByUsername() {
        // Given
        HttpServletRequest request = mock(HttpServletRequest.class);
        String userName = "test-user";

        // When
        Object result = loginController.iotCreentialsByUsername(request, userName);

        // Then
        assertNotNull(result);
    }

    @Test
    void testIOTUserInfo() {
        // Given
        HttpServletRequest request = mock(HttpServletRequest.class);

        // When
        Object result = loginController.iOTUserInfo(request);

        // Then
        assertNotNull(result);
    }

    @Test
    void testLogin() {
        // Given
        User user = new User();
        user.setLoginName("test-user");

        // When
        Object result = loginController.login(user);

        // Then
        assertNotNull(result);
    }

    @Test
    void testUserApplicationList() {
        // Given
        HttpServletRequest request = mock(HttpServletRequest.class);
        String loginName = "test-user";
        Integer applicationType = 1;

        // When
        Object result = loginController.userApplicationList(request, loginName, applicationType);

        // Then
        assertNotNull(result);
    }
}
