package com.matariky.userservice.controller;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.matariky.annotation.PassToken;
import com.matariky.annotation.RequirePermission;
import com.matariky.annotation.UserLoginToken;
import com.matariky.annotation.VerifyTenantId;
import com.matariky.bo.Customer;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.loginlog.bean.CommonLoginLog;
import com.matariky.commonservice.loginlog.service.ICommonLoginLogService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.commonservice.upload.utils.Result;
import com.matariky.constant.RedisKey;
import com.matariky.constant.TokenConstant;
import com.matariky.exception.QslException;
import com.matariky.orderservice.bean.OrderInfo;
import com.matariky.orderservice.mapper.OrderInfoMapper;
import com.matariky.orderservice.service.OrderInfoService;
import com.matariky.redis.RedisUtils;
import com.matariky.userservice.bean.User;
import com.matariky.userservice.bean.UserApplication;
import com.matariky.userservice.bean.UserOrganization;
import com.matariky.userservice.bean.UserTenant;
import com.matariky.userservice.mapper.UserTenantMapper;
import com.matariky.userservice.service.*;
import com.matariky.utils.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
@Component
public class LoginController {
    @Value("${message.locale}")
    String locale;

    @Value("${verify}")
    String verificationCode;

    @Value("${keycloak.url}")
    String keycloakUrl;

    @Value("${keycloak.client_id}")
    String keycloakClientId;

    @Value("${keycloak.secret}")
    String keycloakSecret;

    @Value("${keycloak.grant_type}")
    String keycloakGrantType;

    @Value("${keycloak.realm}")
    String keycloakRealm;


    @Autowired
    CommonDictService commonDictService;


    @Autowired
    UserApplicationService userApplicationService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    CaptchaService captchaService;

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    UserOrganizationService userOrganizationService;

    @Autowired
    ICommonLoginLogService commonLoginLogService;

    @Autowired
    UserApplicationService applicationService;

    @Autowired
    UserTenantService tenantService;

    @Autowired
    OrderInfoService orderInfoService;

    @Autowired
    RedisUtils redisUtil;
    private static final String BASE_CHECK_CODES = "qwertyuiplkjhgfdsazxcvbnmQWERTYUPLKJHGFDSAZXCVBNM1234567890";
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    /**
     * 后台 Generation 图形验证码 ：有效
     *
     * @param response
     * @param key
     */
    @GetMapping(value = "/randomImage")
    public Object randomImage(HttpServletResponse response, String uuid) {
        Result<String> res = new Result<String>();
        try {

            String code = RandomUtil.randomString(BASE_CHECK_CODES, 4);

            String lowerCaseCode = code.toLowerCase();

            String realKey = MD5Util.MD5Encode(lowerCaseCode + uuid, "utf-8");

            redisUtils.set(realKey, lowerCaseCode, 60);

            String base64 = captchaService.generate(code);
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, base64);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), AjaxResult.FAILURE, null);
    }


    @PostMapping("/renewal/application/{applicationId}")
    @PassToken
    public Object renew(@RequestHeader("Authorization") String token, @PathVariable("applicationId") Long applicationId, @RequestParam(name = "locale", required = false) String locale) {
        //执行到此token已经经过验证有效，AuthenticationInterceptor
        String userId = TokenUtils.extractUserIdFromToken(token);
        String tenantId = TokenUtils.extractTenantIdFromToken(token);
        String extractedLocale = TokenUtils.extractLocaleFromToken(token);
        Long extractedApplicationId = TokenUtils.extractApplicatoinIdFromToken(token);

        if (applicationId == null || applicationId == 0) {
            throw new QslException(MessageKey.EMPTY_APPLICATION_ID);
        }
        UserApplication application = applicationService.selectById(applicationId);
        Long activityTimeout = application.getActivityTimeout() * 1000000L;
        Long lastActiveityTime = redisUtils.get(RedisKey.USER_LAST_ACTIVE_TIME_PREFIX + "_" + userId);
        if (lastActiveityTime != null && lastActiveityTime + activityTimeout > System.currentTimeMillis()) {
            throw new QslException(MessageKey.LOGIN_TIMED_OUT);
        }
        // if(System.currentTimeMillis()+activityTimeout)
        User userForBase = null;

        if (locale != null && !locale.equals(extractedLocale)) {
            userForBase = userService.findUserById(userId);
            userForBase.setLocale(locale);
            userService.updateById(userForBase);
        }

        if (!extractedApplicationId.equals(applicationId)) {//切换应用
            //设置切换至 Tenant 为当前应用
            Map<String, String> orgCodes = organizationService.getOrgIdByUserId(application.getTenantId(), userId);

            userForBase = userService.findUserById(userId);
            if (orgCodes != null) {
                userForBase.setDepartmentOrganizationCode(orgCodes.get("organization_code"));
                userForBase.setSelfOrganizationCode(orgCodes.get("self_organization_code"));
            }
            userForBase.setTenantId(application.getTenantId());
            userForBase.setApplicationId(applicationId);
            tokenService.updateLoginInfo(userForBase);
            createLoginLog(userForBase, commonDictService.getServiceMessage(locale + "_SERVICE_CONSTANT_MESSAGE", "APPLICATION_SWITCHED", true, tenantId));
        } else {//当前应用token续订
            userForBase = userService.findUserById(userId);
            tokenService.updateLoginInfo(userForBase);
            createLoginLog(userForBase, commonDictService.getServiceMessage(locale + "_SERVICE_CONSTANT_MESSAGE", "TOKEN_RENEWED", true, tenantId));
        }
        Map<String, String> tokenMap;
        try {
            if (!StringUtils.isEmpty(locale))
                userForBase.setLocale(locale);
            tokenMap = tokenService.getToken(userForBase);
        } catch (QslException e) {
            JSONObject jo = JSONObject.parseObject(e.getMessage());
            return AjaxResult.error(HttpStatus.INTERNAL_SERVER_ERROR.toString(), jo.getString("message"));
//            return new ResponseEntity<JSONObject>(jo, HttpStatus.BAD_REQUEST);
        }
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, tokenMap);
//        return tokenMap;

    }


    //	    @UserLoginToken
    @RequestMapping(value = "/tenant/{tenantId}/user/", method = RequestMethod.GET)
    @RequirePermission
    @VerifyTenantId
    public Object searchUserByName(@PathVariable("tenantId") String tenantId, @RequestParam("filter") String userNamePrefix, @RequestHeader("Authorization") String jwt) {
        List<User> userList = userService.searchByUserNamePrefix(tenantId, userNamePrefix);
        List<Map<String, Object>> formatedUserList = new ArrayList<Map<String, Object>>();
        for (User user : userList) {
            Map<String, Object> userMap = new HashMap<String, Object>();
            UserOrganization org = userOrganizationService.getOrganizationByCode(user.getDepartmentOrganizationCode(), tenantId);
            userMap.put("orgName", org != null ? "(" + org.getOrganizationName() + ")" : "");
            userMap.put("value", user.getId().toString());
            userMap.put("id", user.getId().toString());
            userMap.put("fullName", user.getRealName());
            userMap.put("firstName", user.getRealName());
            userMap.put("lastName", user.getRealName());
            formatedUserList.add(userMap);
        }
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("start", 0);
        returnMap.put("size", returnMap.size());
        returnMap.put("total", returnMap.size());
        returnMap.put("data", formatedUserList);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, returnMap);
    }

    @RequestMapping(value = "/tenant/{tenantId}/user/{id}", method = RequestMethod.GET)
    @Cacheable(key = "'user-'+#userId", value = "users")
    public Object getUserById(@PathVariable("id") String userId) {
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, userService.findUserById(userId));
    }

    //退出
    @PostMapping("/logout/user/{userId}")
    @PassToken
    @UserLoginToken
    public Object logout(@PathVariable("userId") String userId, HttpServletRequest request) {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        if(tenantId==null){
            throw new QslException(MessageKey.KEYCLOAK_TOKEN_NOT_EXIST);
        }
        User user = userService.findUserById(userId);
        if (user == null) {
            throw new QslException(MessageKey.USER_NOT_EXIST);
        }
        redisUtils.set(TokenConstant.LAST_ACCESS_TIME + "_" + user.getId(), System.currentTimeMillis());
        createLoginLog(user, commonDictService.getServiceMessage(locale + "_SERVICE_CONSTANT_MESSAGE", "LOGOUT_SUCCESS", true, tenantId));
        redisUtils.unCacheKeycloakToken(user.getId());
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
//        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }


    @GetMapping("/IOTUserInfo/{userName}")
    public Object iotUserByUsername(HttpServletRequest request, @PathVariable("userName") String userName) {
        String basicAuthStr = request.getHeader("authorization");
        byte[] decodedBytes = Base64.getDecoder().decode(basicAuthStr.substring(6));
        String decodedString = new String(decodedBytes);
        String uName = decodedString.split(":")[0];
        User userForBase = userService.findByUsername(uName);
        String pwd = decodedString.split(":")[1];
        if (!userForBase.getPazzword().equals(EncryptionUtils.getHash3(pwd, "SHA"))) {
            throw new QslException(MessageKey.WRONG_PASSWORD);
        }
        User loginUser = userService.findByUsername(userName);
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("username", loginUser.getLoginName());
        userMap.put("gender", loginUser.getGender());
        userMap.put("email", loginUser.getEmail());
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, userMap);
    }

    @GetMapping("/IOTUserInfo/{userName}/credentials")
    public Object iotCreentialsByUsername(HttpServletRequest request, @PathVariable("userName") String userName) {
        String basicAuthStr = request.getHeader("authorization");
        byte[] decodedBytes = Base64.getDecoder().decode(basicAuthStr.substring(6));
        String decodedString = new String(decodedBytes);
        String uName = decodedString.split(":")[0];
        User userForBase = userService.findByUsername(uName);
        String pwd = decodedString.split(":")[1];
        if (!userForBase.getPazzword().equals(EncryptionUtils.getHash3(pwd, "SHA"))) {
            throw new QslException(MessageKey.WRONG_PASSWORD);
        }
        User loginUser = userService.findByUsername(userName);
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("type", "password");
        userMap.put("algorithm", "SHA");
        userMap.put("salt", "");
        userMap.put("iterations", 1);
        userMap.put("value", loginUser.getPazzword());
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, userMap);
    }

    @GetMapping("/IOTUserInfo")
    public Object iOTUserInfo(HttpServletRequest request) {
        String basicAuthStr = request.getHeader("authorization");
        byte[] decodedBytes = Base64.getDecoder().decode(basicAuthStr.substring(6));
        String decodedString = new String(decodedBytes);
        String uName = decodedString.split(":")[0];
        User userForBase = userService.findByUsername(uName);
        String pwd = decodedString.split(":")[1];
        if (!userForBase.getPazzword().equals(EncryptionUtils.getHash3(pwd, "SHA"))) {
            throw new QslException(MessageKey.WRONG_PASSWORD);
        }
        String tenantId = userForBase.getTenantId();
        List<Map> mapList = userService.getIOTUsers(tenantId);
        List<Customer> customerList = new ArrayList<Customer>();
        for (Map mp : mapList) {
            Customer cust = new Customer();
            cust.setUsername((String) mp.get("username"));
            cust.setGender(mp.get("gender").toString());
            cust.setEmail((String) mp.get("email"));
            if (mp.get("roles") != null) {

                cust.setRoles(Arrays.asList(((String) mp.get("roles")).split(",")));
            }
            customerList.add(cust);
        }
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, customerList);
    }


    //登录
    @PostMapping("/login")
//	    @PassToken
    public Object login(@RequestBody User user) {
        User userForBase = userService.findByUsername(user.getLoginName());
        String tenantId = userForBase.getTenantId();
        if(tenantId.contains("_")){
            tenantId=tenantId.split("_")[1];
        }
        if(!"1".equals(tenantId)) {
            //登录用户订单已经终止，不能登录
            Long count = orderInfoMapper.selectCount(Wrappers.lambdaQuery(OrderInfo.class)
                    .eq(OrderInfo::getOrderTenantId, tenantId)
                    .eq(OrderInfo::getDeleteTime, 0)
                    .in(OrderInfo::getOrderStatus, 2,3));
            if (count > 0) {
                throw new QslException(MessageKey.USER_ORDER_END_NOT_LOGIN);
            }
        }
        if (userForBase == null) {
            throw new QslException(MessageKey.USER_NOT_EXIST);
        }

        if (!userForBase.getIsActive()) {
            throw new QslException(MessageKey.USER_NOT_ACTIVE);
        }

        if (user.getApplicationId() == null || user.getApplicationId() == 0) {
            throw new QslException(MessageKey.EMPTY_APPLICATION_ID);
        }
        boolean flag = true;
        if (user.getApplicationId() != null && user.getApplicationId() == 2) {
            user.setApplicationId(1L);
            flag = false;
        }

        UserApplication application = applicationService.selectById(user.getApplicationId());

        if (application == null) {
            throw new QslException(MessageKey.APPLICATION_NOT_EXIST);
        }

        if (application.getTenantId() == null || !application.getTenantId().equals(userForBase.getTenantId())) {
            throw new QslException(MessageKey.USER_APPLICATION_NOT_UNDER_SAME_TENANT);
        }
        if (user.getApplicationId() == null || flag) {
            if (application.getApplicationType() != null && Integer.valueOf(1).equals(application.getApplicationType())) {

                if ("true".equals(verificationCode)) {
                    String captcha = user.getCaptcha();
                    if (captcha == null) {
                        throw new QslException(MessageKey.INVALID_VERIFICATION_CODE);
                    }
                    String lowerCaseCaptcha = captcha.toLowerCase();
                    String realKey = MD5Util.MD5Encode(lowerCaseCaptcha + user.getUuid(), "utf-8");
                    Object checkCode = redisUtils.get(realKey);
                    //当进入登录页时，有一定几率出现验证码错误 #1714
                    if (checkCode == null || !checkCode.toString().equals(lowerCaseCaptcha)) {
                        throw new QslException(MessageKey.INVALID_VERIFICATION_CODE);
                    }
                }
            }
        }


        UserTenant tenant = tenantService.selectBytenantCode(userForBase.getTenantId());
        List<Map<String, Object>> appList = userService.getApplicationByUser(userForBase.getId(), tenant.getId());
        List<Map<String, Object>> list = appList.stream().filter(item -> ((Long) item.get("id")).equals((application.getId()))).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(list)) {
            throw new QslException(MessageKey.USER_DOES_NOT_HAVE_APPLICATION);
        }

        if (!tenant.getId().toString().equals("1")) {
//            List<OrderInfo> orderInfos = orderInfoService.getOrderTenantIdList(tenant.getId().toString());
//            if(CollectionUtils.isEmpty(orderInfos)){
//                throw new QslException(MessageKey.USER_LOGIN_ORDER_IS_NOT_EXIST);
//            }
        }

        //验证码 Wether 正确
        /*
         * boolean flag = captchaService.validate(user.getUuid(), user.getCaptcha());
         * if(!flag){ jsonObject.put("message","验证码不正确"); return jsonObject; }
         */

        if (!userForBase.getPazzword().equals(EncryptionUtils.getHash3(user.getPazzword(), "SHA"))) {
            createLoginLog(userForBase, commonDictService.getServiceMessage(locale + "_SERVICE_CONSTANT_MESSAGE", "LOGIN_FAIL", false, userForBase.getTenantId()));
            throw new QslException(MessageKey.WRONG_PASSWORD);

        } else {

//            Integer numberOfIOTRoles=userService.isIOTUser(userForBase.getId());
//			if(numberOfIOTRoles>0) {
//				String keycloakToken = KeycloakUtils.getToken(user.getLoginName(), user.getPazzword(), keycloakUrl,keycloakRealm, keycloakSecret, keycloakClientId, keycloakGrantType);
//				if(!StringUtils.isEmpty(keycloakToken)) {
//					redisUtil.cacheKeycloakToken(userForBase.getId(),keycloakToken);
//				}
//			}
            Map<String, String> tokenMap;
            try {
                userForBase.setApplicationId(application.getId());
                tokenMap = tokenService.getToken(userForBase);
            } catch (QslException e) {
                JSONObject jo = JSONObject.parseObject(e.getMessage());

                return new ResponseEntity<JSONObject>(jo, HttpStatus.BAD_REQUEST);
            }
            userForBase.setLoginTime(System.currentTimeMillis());
            userForBase.setLastLoginTime(System.currentTimeMillis());
            userForBase.setLoginCount(userForBase.getLoginCount() + 1);
            createLoginLog(userForBase, commonDictService.getServiceMessage(locale + "_SERVICE_CONSTANT_MESSAGE", "LOGIN_SUCCESS", true, userForBase.getTenantId()));
            tokenMap.put("code", HttpStatus.OK.toString());
            tokenMap.put("message", "登录成功");
            redisUtils.set(TokenConstant.LAST_ACCESS_TIME + "_" + userForBase.getId(), System.currentTimeMillis());
            userService.updateById(userForBase);
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, tokenMap);
        }

    }

    @RequestMapping(value = "/userApplications/user/{loginName}", method = RequestMethod.GET)
    @PassToken
    public Object userApplicationList(HttpServletRequest request, @PathVariable("loginName") String loginName, @RequestParam("applicationType") Integer applicationType) {
        User userForBase = userService.findByUsername(loginName);
        if (userForBase == null) {

            throw new QslException(MessageKey.USER_NOT_EXIST);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        UserTenant tenant = tenantService.selectByTenantCode(userForBase.getTenantId());
        if (tenant == null) {
            throw new QslException(MessageKey.USER_NOT_TENANTID_EXIST);
        }

        map.put("tenantId", tenant.getId());
        map.put("userId", userForBase.getId());
        map.put("applicationType", applicationType);
        List<UserApplication> uaList = userApplicationService.getUserApplicationAllWithUserId(map);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, uaList);

    }


    private void createLoginLog(User userForBase, JSONObject msg) {
        CommonLoginLog cll = new CommonLoginLog();
        cll.setAccountName(userForBase.getLoginName());
        cll.setIsSuccess(false);
        cll.setFailMessage(msg.toString());
        cll.setLoginTime(System.currentTimeMillis());
        cll.setRealName(userForBase.getRealName());
        cll.setTenantId(userForBase.getTenantId());
        cll.setUserId(userForBase.getId());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = IPUtil.getIPAddress(request);
        cll.setIp(ip);
        cll.setLoginAddress(IPUtil.getCityInfo(ip));
        cll.setClient(request.getHeader("user-agent"));
        String tenantName = TokenUtils.extractTenantNameFromHttpRequest(request);
        if (tenantName != null)
            cll.setTenantName(tenantName);
        else
            cll.setTenantName(tenantService.selectByTenantCode(userForBase.getTenantId()).getTenantName());
        commonLoginLogService.createCommonLoginLog(cll);
    }

}
