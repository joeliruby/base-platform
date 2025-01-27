package com.matariky.userservice.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.matariky.annotation.RequirePermission;
import com.matariky.annotation.UserLoginToken;
import com.matariky.annotation.VerifyTenantId;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.commonservice.upload.utils.Result;
import com.matariky.constant.PermissionConstant;
import com.matariky.constant.RedisKey;
import com.matariky.exception.QslException;
import com.matariky.redis.RedisUtils;
import com.matariky.userservice.bean.Permission;
import com.matariky.userservice.bean.TreeModel;
import com.matariky.userservice.bean.User;
import com.matariky.userservice.bean.UserApplication;
import com.matariky.userservice.bean.UserGroup;
import com.matariky.userservice.bean.UserRole;
import com.matariky.userservice.bean.UserTenant;
import com.matariky.userservice.dto.PermissionInfoVO;
import com.matariky.userservice.mapper.PermissionMapper;
import com.matariky.userservice.service.PermissionService;
import com.matariky.userservice.service.UserApplicationService;
import com.matariky.userservice.service.UserGroupService;
import com.matariky.userservice.service.UserRoleService;
import com.matariky.userservice.service.UserService;
import com.matariky.userservice.service.UserTenantService;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.ConstantUtil;
import com.matariky.utils.FileUtil;
import com.matariky.utils.StringUtils;
import com.matariky.utils.TokenUtils;
import com.matariky.utils.TreeUtils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ConcurrentHashSet;
import cn.hutool.core.util.ArrayUtil;
import io.jsonwebtoken.lang.Collections;

@RestController
@RequestMapping("api/v1/tenant/{tenantId}")
@Component
public class PermissionController {
    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    PermissionService permissionService;

    @Autowired
    UserService userService;

    @Autowired
    UserApplicationService applicationService;

    @Autowired
    UserGroupService userGroupService;

    @Autowired
    UserRoleService userRoleService;

    @Value("${message.locale}")
    String locale;

    @Autowired
    CommonDictService commonDictService;

    @Autowired
    CommonDictTypeService commonDictTypeService;

    @Autowired
    UserTenantService tenantService;

    @Autowired
    private RedisUtils redisUtils;

    @UserLoginToken
    @GetMapping("/permission/")
    @RequirePermission
    @VerifyTenantId
    public Object searchUserByName(@PathVariable("tenantId") String tenantId, @RequestParam("index") int pageIndex,
            @RequestParam("perPage") int perPage, @RequestHeader("Authorization") String jwt) {
        PageHelper.startPage(pageIndex, perPage);
        Page<Permission> page = permissionMapper.getPermissionByTenantId(tenantId);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

    @PutMapping("/permission/{permissionId}/{dataPermissionFlag}")
    public Object setDataPermission(HttpServletRequest request, @PathVariable("tenantId") String tenantId,
            @PathVariable("permissionId") Long permissionId,
            @PathVariable("dataPermissionFlag") String dataPermissionFlag, @RequestHeader("Authorization") String jwt) {
        Permission perm = permissionService.selectById(permissionId);
        if (perm == null) {
            throw new QslException(MessageKey.RESOURCE_NOT_EXIST);
        }
        if (!ConstantUtil.getAllConstantValuesByCategory(PermissionConstant.class, "COMMON_DATA_ACCESS")
                .contains(dataPermissionFlag)) {
            throw new QslException(MessageKey.INVALID_COMMON_DATA_ACCESS_FLAG);
        }

        CommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(
                TokenUtils.extractTenantIdFromHttpReqeust(request), PermissionConstant.DATA_ACCESS_PERMISSION);
        if (commonDictType == null) {
            throw new QslException(MessageKey.COMMONDICTTYPE_NOT_EXIST);
        }
        CommonDict dict = commonDictService.getCommonDictByIdTenantIdAndDictType("dp" + permissionId, tenantId,
                commonDictType.getId());
        if (dict == null) {
            dict = new CommonDict();
            dict.setDictTypeId(commonDictType.getId());
            dict.setDictKey("dp" + permissionId.toString());
            dict.setDictValue(dataPermissionFlag.toString());
            dict.setTenantId(TokenUtils.extractTenantIdFromHttpReqeust(request));
            Boolean flag = commonDictService.insert(dict);
            if (flag) {
                return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
            } else {
                throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
            }
        } else {
            dict.setDictValue(dataPermissionFlag.toString());
            commonDictService.updateValueByKeyAndTenantId(commonDictType.getId().toString(), dict.getDictKey(),
                    dict.getDictValue(), tenantId);
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
        }

    }

    @UserLoginToken
    @VerifyTenantId
    @GetMapping("/application/{applicationId}/permission/{permissionName}")
    public Object searchPermissionByNamePrefix(@PathVariable("tenantId") String tenantId,
            @RequestHeader("Authorization") String jwt, @PathVariable("applicationId") Long applicationId,
            @PathVariable("permissionName") String permissionName) {
        UserApplication application = applicationService.selectById(applicationId);
        if (application == null) {
            throw new QslException(MessageKey.APPLICATION_NOT_EXIST);
        }
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS,
                permissionService.findPermissionsByPermissionNamePrefix(tenantId, applicationId, permissionName));
    }

    @GetMapping("/application/{applicationId}/permission/url/")
    public Object getDataPermissionLevelByAlias(@PathVariable("tenantId") String tenantId,
            @RequestHeader("Authorization") String jwt, @PathVariable("applicationId") Long applicationId,
            @RequestParam("url") String url) {
        UserApplication application = applicationService.selectById(applicationId);
        if (application == null) {
            throw new QslException(MessageKey.APPLICATION_NOT_EXIST);
        }
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<Permission>();
        queryWrapper.eq("url", url);
        queryWrapper.eq("is_active", 1);
        List<Permission> permissionList = permissionService.selectList(queryWrapper);
        if (Collections.isEmpty(permissionList)) {
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
        } else
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS,
                    permissionList.get(0).getResourceAttribute());
    }

    /*
     * @UserLoginToken
     *
     * @VerifyTenantId
     */
    @RequestMapping(value = "/permission/user/{userId}", method = RequestMethod.POST)
    public AjaxResult assignPermissionToUser(
            @PathVariable("tenantId") String tenantId,
            @RequestHeader("Authorization") String jwt,
            @RequestBody Map<String, Object> map,
            @PathVariable("userId") Long userId,
            @RequestParam("applicationId") String applicationIds,
            HttpServletRequest request) {

        // Non -administrators cannot allocate the Code Rule menu permission Tenant 1,
        // not limited, because he is the largest administrator
        String permissionsStr = map.get("permissions").toString();
        List<String> permissionsIdList = Arrays.stream(permissionsStr.split(",")).collect(Collectors.toList());
        String backTenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        if (!backTenantId.equals("1")) {
            String backUserId = TokenUtils.extractUserIdFromHttpReqeust(request);
            Integer adminTenantCount = userService.isAdmin(Long.valueOf(backUserId));
            if (adminTenantCount == 0) {
                List<Permission> permissionsList = permissionMapper
                        .selectList(Wrappers.lambdaQuery(Permission.class).in(Permission::getId, permissionsIdList));
                List<String> permissionsNameList = permissionsList.stream().map(Permission::getPermissionName)
                        .collect(Collectors.toList());
                if (permissionsNameList.contains(" Code  Rule  Configuration ")) {
                    throw new QslException(MessageKey.CODINGRULES_NOT_ALLOCATION);
                }
            }
        }

        if (!StringUtil.isEmpty(applicationIds)) {
            for (String applicationId : applicationIds.split(",")) {
                UserTenant tenant = tenantService.selectByTenantCode(tenantId);
                if (applicationService.countUserApplicationTenant(userId, applicationId, tenant.getId()) == 0)
                    applicationService.insertUserApplicationRelation(userId, Long.parseLong(applicationId),
                            tenant.getId());
            }
        }

        User user = userService.selectById(userId);
        String permissions = map.get("permissions").toString();
        if (user == null)
            throw new QslException(MessageKey.USER_NOT_EXIST);
        if (StringUtil.isEmpty(permissions)) {
            throw new QslException(MessageKey.EMPTY_PERMISSION_ID);
        }
        // 先Delete 中间关系 user_r_user_permission
        permissionService.deleteRUserPermission(userId);
        for (String permId : permissions.split(",")) {
            permissionService.createResourceAllocationToUser(userId, Long.parseLong(permId));
        }
        JSONObject obj = commonDictService.getServiceMessage(locale + "_SERVICE_CONSTANT_MESSAGE",
                "RESOURCE_ASSIGNED_TO_USER", true, tenantId);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, obj);
    }

    // Role authorization
    @PostMapping("/permission/role/{roleId}")
    public Object assignPermissionToRole(
            @PathVariable("tenantId") String tenantId,
            @RequestHeader("Authorization") String jwt,
            @RequestBody Map<String, Object> map,
            @PathVariable("roleId") Long roleId, HttpServletRequest request) {

        UserRole userRole = userRoleService.selectById(roleId);
        String permissions = map.get("permissions").toString();
        if (userRole == null) {
            throw new QslException(MessageKey.ROLE_NOT_EXIST);

        }
        if (StringUtil.isEmpty(permissions)) {
            throw new QslException(MessageKey.EMPTY_PERMISSION_ID);
        }
        // First delete intermediate relationship user_r_role_permission
        Set<String> permissionIds = new HashSet<String>();
        permissionIds.addAll(Arrays.asList(permissions.split(",")));
        permissionService.deleteRRolePermission(roleId);
        for (String permId : permissionIds) {
            permissionService.createResourceAllocationToRole(roleId, Long.parseLong(permId));
        }
        return new AjaxResult(HttpStatus.OK.value(), commonDictService
                .getServiceMessage(locale + "_SERVICE_CONSTANT_MESSAGE", "RESOURCE_ASSIGNED_TO_ROLE", true, tenantId)
                .getString("message"));
    }

    @PostMapping("/permission/userGroup/{groupId}")
    public Object assignPermissionToGroup(@PathVariable("tenantId") String tenantId,
            @RequestBody Map<String, String> params,
            HttpServletRequest httpServletRequest) {
        String groupId = params.get("groupId");
        String permissions = params.get("permissions");
        UserGroup group = userGroupService.selectById(groupId);

        if (group == null) {
            throw new QslException(MessageKey.GROUP_NOT_EXIST);

        }

        if (StringUtil.isEmpty(permissions)) {
            throw new QslException(MessageKey.EMPTY_PERMISSION_ID);
        }

        // DeletePermission before grouping Binding
        userGroupService.deletePreviousGroupPermissionBound(groupId);

        for (String permId : permissions.split(",")) {
            permissionService.createResourceAllocationToGroup(Long.parseLong(groupId), Long.parseLong(permId));
        }
        return new AjaxResult(HttpStatus.OK.value(), commonDictService
                .getServiceMessage(locale + "_SERVICE_CONSTANT_MESSAGE", "RESOURCE_ASSIGNED_TO_GROUP", true, tenantId)
                .getString("message"));
    }

    @GetMapping("/userInfo")
    public Object getUserInfo(@RequestHeader("Authorization") String jwt) {
        if (StringUtils.isEmpty(jwt)) {
            return null;
        }
        Long applicationId = TokenUtils.extractApplicatoinIdFromToken(jwt);
        String userId = TokenUtils.extractUserIdFromToken(jwt);
        String tenantId = TokenUtils.extractTenantIdFromToken(jwt);
        String tenantName = TokenUtils.extractTenantNameFromToken(jwt);

        Set<Permission> permSet = permissionService.getPermissionTreeByTenantIdApplicationIdUserId(tenantId,
                applicationId, Long.parseLong(userId));
        Set<Long> origPermIdSet = new HashSet<Long>();
        for (Permission perm : permSet) {
            if (perm.getOrigId() != null) {
                origPermIdSet.add(perm.getOrigId());
            }
        }
        if (Collections.isEmpty(permSet)) {
            throw new QslException(MessageKey.PERMISSION_NOT_ASSIGNED);
        }
        Map<String, Object> userInfo = new HashMap<String, Object>();
        userInfo.put("applicationId", applicationId);
        userInfo.put("userId", userId);
        userInfo.put("tenantId", tenantId);
        userInfo.put("permissions", origPermIdSet);
        userInfo.put("tenantName", tenantName);

        User user = userService.selectById(userId);
        userInfo.put("userName", user.getLoginName());
        userInfo.put("userImage", user.getUserImg());
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, userInfo);
    }

    @GetMapping("/permissionTree/application/{applicationId}/user/{userId}")
    public Object getPermissionTree(@PathVariable("tenantId") String tenantId,
            @RequestHeader("Authorization") String jwt,
            @PathVariable("applicationId") Long applicationId,
            @PathVariable("userId") Long userId) {
        Map<Long, JSONObject> permissionTreeMap = new TreeMap<Long, JSONObject>();
        // Configuration Root node
        JSONObject root = new JSONObject();
        root.put("id", 0L);
        permissionTreeMap.put(0l, root);
        // All nodes enter MAp
        UserTenant tenant = tenantService.selectBytenantCode(tenantId);

        Set<Permission> permSet1 = new HashSet<>();
        Set<Permission> permSet2 = new HashSet<>();
        Set<Long> origIdSet = new HashSet<>();
        Set<Permission> permSet = new HashSet<>();
        HashMap<Long, Permission> permMap = new HashMap<>();
        if (tenant != null) {
            permSet1 = permissionService.selectPermissionByTenantId(tenant.getId().toString());
            if (CollectionUtils.isEmpty(permSet1) || tenant.getId() == 1l) {//// The top tenant has no orders
                permSet1 = permissionService.getPermissionTreeByTenantIdApplicationIdUserId(tenantId, applicationId,
                        userId);
            }
            if (CollectionUtils.isEmpty(permSet2)) {
                permSet2 = permissionService.getPermissionTreeByTenantIdApplicationIdUserId(tenantId, applicationId,
                        userId);
            }
        }

        // Remove the resources not in the token
        List<String> permsFromToken = TokenUtils.extractPermissionIdsFromToken(jwt);
        for (Permission per : permSet1) {
            if (permsFromToken.contains(per.getId().toString())) {
                permMap.put(per.getId(), per);
            }
        }
        for (Permission per : permSet2) {
            origIdSet.add(per.getOrigId());
        }
        // Remove the resources not in the app
        for (Permission per : permMap.values()) {
            if (!origIdSet.contains(per.getId())) {
                permMap.remove(per.getId());
            }
        }
        permSet.addAll(permMap.values());
        permSet = permSet1;
        String locale = TokenUtils.extractLocaleFromToken(jwt);
        // Ownership in Map Key: Permanent ID VALUE: Permanent Object jsonobject

        for (Permission perm : permSet) {
            perm.setId(perm.getOrigId());
            if ("CN".equals(locale)) {
                String pName = (String) redisUtils.hGet(RedisKey.MENU_NAMES + "CN", perm.getOrigId() + "");
                if (pName != null)
                    perm.setPermissionName(pName);
            }
            JSONObject permTreeNode = extractJSONObject(perm);
            if (perm.getId() != null) {
                permissionTreeMap.put(perm.getId(), permTreeNode);
            }
        }

        // Build a resource tree
        for (Long permId : permissionTreeMap.keySet()) {
            // mapAll nodes are back to the root node, including root
            traceRoot(permId, permissionTreeMap);

        }
        Object tree = permissionTreeMap.get(0L).get("children");
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, tree);

        // return tree;
    }

    // Role authorization back
    @RequestMapping(value = "/permission/and/application/all/role/{roleId}", method = RequestMethod.GET)
    public Object getPermissionAndApplicationByRole(
            HttpServletRequest request,
            @PathVariable("roleId") Long roleId,
            @PathVariable("tenantId") String tenantId) {
        String tenantIdData = "";
        String[] tenantStr = tenantId.split("_");
        if (tenantStr != null && tenantStr.length > 1) {
            tenantIdData = tenantStr[1];
        }
        // This tenantApp
        List<Map<String, Object>> appByTenantId = userGroupService.getAppByTenantId(tenantId);
        for (Map<String, Object> map : appByTenantId) {
            Long applicationId = Long.parseLong(map.get("id").toString());

            // checkId: [],
            List<Long> list = new ArrayList<Long>();

            // First query character and resource list Wether Data
            list = userRoleService.getPermissionIdByRoleId(roleId);

            if (CollUtil.isNotEmpty(list)) {
                map.put("checkId", list);
                map.put("hasCheck", true);
            } else {
                map.put("hasCheck", false);
            }
            if (StringUtils.isNotEmpty(tenantId) && !tenantId.equals("1")) {
                Map<String, Object> params = new HashMap<>();
                params.put("applicationId", applicationId);
                params.put("tenantId", tenantId);
                params.put("orderTenantId", tenantIdData);
                List<Permission> permissions = permissionService.getOrderSuitePermissionByParam(params);
                List<TreeModel> treeList = new ArrayList<>();
                if (CollUtil.isNotEmpty(permissions)) {
                    for (Permission permission : permissions) {

                        TreeModel t = new TreeModel();
                        BeanUtil.copyProperties(permission, t);
                        t.setCode(permission.getCreatedBy() == null ? "" : permission.getCreatedBy().toString());

                        t.setName(permission.getPermissionName());
                        t.setPid(permission.getParentId());
                        treeList.add(t);
                    }

                }

                List<TreeModel> build;
                if (CollUtil.isNotEmpty(treeList)) {
                    build = TreeUtils.build(treeList);
                } else {
                    build = new ArrayList<>();
                }

                if (CollUtil.isNotEmpty(build)) {
                    map.put("tree", build);
                } else {
                    map.put("hasCheck", false);
                }
            } else {
                QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("tenant_id", tenantId).eq("application_id", applicationId);
                List<TreeModel> treeList = permissionService.selectTreeList(queryWrapper);
                List<TreeModel> build;
                if (CollUtil.isNotEmpty(treeList)) {
                    build = TreeUtils.build(treeList);
                } else {
                    build = new ArrayList<>();
                }

                if (CollUtil.isNotEmpty(build)) {
                    map.put("tree", build);
                } else {
                    map.put("hasCheck", false);
                }
            }

        }

        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, appByTenantId);
    }

    // Grouping authorization back
    @RequestMapping(value = "/permission/and/application/all/group/{groupId}", method = RequestMethod.GET)
    public Object getPermissionAndApplicationByGroup(
            HttpServletRequest request,
            @PathVariable("groupId") Long groupId,
            @PathVariable("tenantId") String tenantId) {
        String tenantIdData = "";
        String[] tenantStr = tenantId.split("_");
        if (tenantStr != null && tenantStr.length > 1) {
            tenantIdData = tenantStr[1];
        }
        // The app under this tenant
        List<Map<String, Object>> appByTenantId = userGroupService.getAppByTenantId(tenantId);
        UserGroup grp = userGroupService.selectById(groupId);
        for (Map<String, Object> map : appByTenantId) {
            Long applicationId = Long.parseLong(map.get("id").toString());

            // checkId: [],
            List<Long> list = new ArrayList<Long>();
            map.put("group_name", grp.getGroupName());
            // First query grouping and resource list Wether has DATA
            list = userGroupService.getPermissionIdByGroupId(groupId);

            if (CollUtil.isNotEmpty(list)) {
                map.put("checkId", list);
                map.put("hasCheck", true);
            } else {
                map.put("hasCheck", false);
            }
            List<Permission> permissions = new ArrayList<>();
            if (StringUtils.isNotEmpty(tenantId) && !tenantId.equals("1")) {
                Map<String, Object> params = new HashMap<>();
                params.put("applicationId", applicationId);
                params.put("tenantId", tenantId);
                params.put("orderTenantId", tenantIdData);
                permissions = permissionService.getOrderSuitePermissionByParam(params);

                List<TreeModel> treeList = new ArrayList<>();
                if (CollUtil.isNotEmpty(permissions)) {
                    for (Permission permission : permissions) {

                        TreeModel t = new TreeModel();
                        BeanUtil.copyProperties(permission, t);
                        t.setCode(permission.getCreatedBy() == null ? "" : permission.getCreatedBy().toString());

                        t.setName(permission.getPermissionName());
                        t.setPid(permission.getParentId());
                        treeList.add(t);
                    }

                }

                List<TreeModel> build;
                if (CollUtil.isNotEmpty(treeList)) {
                    build = TreeUtils.build(treeList);
                } else {
                    build = new ArrayList<>();
                }
                if (CollUtil.isNotEmpty(build)) {
                    map.put("tree", build);
                } else {
                    map.put("hasCheck", false);
                }
            } else {
                List<TreeModel> treeList = permissionService.getTreeByApplicationId(tenantId, applicationId);
                List<TreeModel> build;
                if (CollUtil.isNotEmpty(treeList)) {
                    build = TreeUtils.build(treeList);
                } else {
                    build = new ArrayList<>();
                }
                if (CollUtil.isNotEmpty(build)) {
                    map.put("tree", build);
                } else {
                    map.put("hasCheck", false);
                }
            }

        }

        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, appByTenantId);
    }

    // Retrieve Tenant App Related resource drop -down box
    @RequestMapping(value = "/permission/and/application/all/user/{userId}", method = RequestMethod.GET)
    public Object getPermissionAndApplicationByAll(
            HttpServletRequest request,
            @PathVariable("userId") Long userId,
            @PathVariable("tenantId") String tenantId) {

        String tenantIdData = "";
        String[] tenantStr = tenantId.split("_");
        if (tenantStr != null && tenantStr.length > 1) {
            tenantIdData = tenantStr[1];
        }
        // Find personal belongingApp user_id tenant_id
        List<Map<String, Object>> listMaps = userService.getApplicationByUser(userId, tenantIdData);
        StringBuilder appIdSB = new StringBuilder();

        for (Map<String, Object> map : listMaps) {
            Long applicationId = Long.parseLong(map.get("id").toString());
            appIdSB.append(applicationId).append(",");

        }

        // The app under this tenant
        List<Map<String, Object>> appByTenantId = userGroupService.getAppByTenantId(tenantId);
        String string = appIdSB.toString();
        for (Map<String, Object> map : appByTenantId) {
            Long applicationId = Long.parseLong(map.get("id").toString());
            if (string.contains(applicationId.toString())) {
                map.put("hasCheck", true);
            }
            // checkId: [],
            List<Long> list = new ArrayList<Long>();
            List<Long> permissionIds = new ArrayList<Long>();
            // First Query User and Resource Table WETHER Data
            list = userService.getPermissionIdByUserIdR(userId);

            QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("tenant_id", tenantId)
                    .eq("application_id", applicationId);
            List<Permission> permissionList = permissionService.selectList(queryWrapper);

            if (CollUtil.isNotEmpty(permissionList)) {
                for (Permission permission : permissionList) {
                    permissionIds.add(permission.getId());
                }
            } else {
                map.put("checkId", permissionIds);
            }

            if (CollUtil.isEmpty(list) && CollUtil.isNotEmpty(permissionIds)) {
                list = new ArrayList<Long>();
                List<TreeModel> noTreeList = userService.getPermissionByUserNoTree(userId, tenantId, applicationId);
                if (CollUtil.isNotEmpty(noTreeList)) {
                    for (TreeModel model : noTreeList) {
                        list.add(model.getId());
                    }
                    // You also need to screen for the WETHER with this resource under this APP
                }
                list.retainAll(permissionIds);
                map.put("checkId", list);
            }

            // You also need to screen for the WETHER with this resource under this APP
            if (CollUtil.isNotEmpty(list) && CollUtil.isNotEmpty(permissionIds)) {
                list.retainAll(permissionIds);
                map.put("checkId", list);
            }
            List<TreeModel> _treeList = new ArrayList<>();

            List<Permission> permissions = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNotEmpty(tenantId) && !tenantId.equals("1")) {
                params.put("applicationId", applicationId);
                params.put("tenantId", tenantId);
                params.put("orderTenantId", tenantIdData);
                permissions = permissionMapper.getOrderSuitePermissionByUserId(params);
                if (CollectionUtils.isNotEmpty(permissions)) {
                    permissions.stream().forEach(item -> {
                        TreeModel treeModel = new TreeModel();
                        treeModel.setId(item.getId());
                        treeModel.setName(item.getPermissionName());
                        treeModel.setPid(item.getParentId());
                        _treeList.add(treeModel);
                    });
                }

                List<TreeModel> treeList = TreeUtils.build(_treeList);

                if (CollUtil.isNotEmpty(treeList)) {

                    map.put("tree", treeList);
                } else {
                    map.put("hasCheck", false);
                }
            } else {
                List<TreeModel> treeList = permissionService.selectTreeList(
                        new QueryWrapper<Permission>().eq("tenant_id", tenantId).eq("application_id", applicationId));

                if (CollUtil.isNotEmpty(treeList)) {

                    map.put("tree", treeList);
                } else {
                    map.put("hasCheck", false);
                }
            }

        }
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, appByTenantId);
    }

    @UserLoginToken
    @GetMapping("/dataPermissionTree/application/{applicationId}/")
    @RequirePermission
    @VerifyTenantId
    public Object getDataPermissionTree(@PathVariable("tenantId") String tenantId,
            @RequestHeader("Authorization") String jwt, @PathVariable("applicationId") Long applicationId) {
        Map<Long, JSONObject> permissionTreeMap = new ConcurrentHashMap<Long, JSONObject>();
        // Configuration Root node
        JSONObject root = new JSONObject();
        root.put("id", 0L);
        permissionTreeMap.put(0l, root);
        // All nodes entermap
        Set<Permission> permSet = permissionService.getDataPermissionTreeByTenantIdApplicationId(tenantId,
                applicationId);
        for (Permission perm : permSet) {
            JSONObject permTreeNode = extractJSONObject(perm);
            permissionTreeMap.put(perm.getId(), permTreeNode);
        }

        // The resource itself is controlled by DATA permissions, and the parent -level
        // is not there. All superiors with the resource controlled by DATA permissions
        // are added to the MAP of the build tree
        for (Long permId : permissionTreeMap.keySet()) {
            pullAncestors(permissionTreeMap.get(permId), permissionTreeMap);

        }

        // Build a resource tree
        for (Long permId : permissionTreeMap.keySet()) {
            traceDPRoot(permId, permissionTreeMap);
        }

        Object tree = permissionTreeMap.get(0L).get("children");

        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, tree);
        // return tree;
    }

    @PostMapping(value = "/application/{applicationId}/permission")
    public Object createPermission(
            @RequestBody Permission permission,
            @RequestHeader("Authorization") String jwt,
            @PathVariable("applicationId") Long applicationId,
            @PathVariable("tenantId") String tenantId,
            HttpServletRequest httpServletRequest) {
        if (permission.getParentId() != 0l) {
            Permission perm = permissionService.selectById(permission.getParentId());
            if (perm == null) {
                throw new QslException(MessageKey.PARENT_RESOURCE_NOT_EXIST);
            }
            if (!PermissionConstant.RESOURCE_TYPE_MENU.equals(perm.getResourceType())
                    && !PermissionConstant.RESOURCE_TYPE_PAGE.equals(perm.getResourceType())) {
                throw new QslException(MessageKey.PARENT_RESOURCE_NOT_MENU);
            }
            if (!ConstantUtil.getAllConstantValuesByCategory(PermissionConstant.class, "RESOURCE_TYPE")
                    .contains(permission.getResourceType())) {
                throw new QslException(MessageKey.INVALID_RESOURCE_TYPE);
            }
            if (!ConstantUtil.getAllConstantValuesByCategory(PermissionConstant.class, "RESOURCE_ATTRIBUTE")
                    .contains(permission.getResourceAttribute())) {
                throw new QslException(MessageKey.INVALID_RESOURCE_ATTRIBUTE);
            }
        }
        if (permission.getResourceType().equals(PermissionConstant.RESOURCE_TYPE_RESTAPI)) {
            // Interface Authority
            Permission addP = new Permission();
            addP.setParentId(permission.getParentId());
            addP.setIsActive(true);
            addP.setResourceType(PermissionConstant.RESOURCE_TYPE_RESTAPI);
            addP.setId(Long.parseLong(permission.getParentId().toString() + "1"));
            addP.setPermissionName(permission.getPermissionName() + "New Interface");
            addP.setTenantId(tenantId);
            // Add App id
            addP.setApplicationId(applicationId);
            // Add the relationship between APP and resources
            addP.setCreateTime(System.currentTimeMillis());
            addP.setCreatedBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(
                    ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest())));
            addP.setDeleteTime(0l);
            Boolean success = permissionService.insert(addP);
            addP.setOrigId(addP.getId());
            permissionService.updateById(addP);
            // delete
            Permission deleteP = new Permission();
            deleteP.setTenantId(tenantId);
            deleteP.setIsActive(true);
            deleteP.setResourceType(PermissionConstant.RESOURCE_TYPE_RESTAPI);
            deleteP.setId(Long.parseLong(permission.getParentId().toString() + "2"));
            deleteP.setPermissionName(permission.getPermissionName() + "Delete  Interface");
            // Add App id
            deleteP.setApplicationId(applicationId);
            deleteP.setParentId(permission.getParentId());
            // Add the relationship between APP and resources
            deleteP.setCreateTime(System.currentTimeMillis());
            deleteP.setCreatedBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(
                    ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest())));
            deleteP.setDeleteTime(0l);
            success = permissionService.insert(deleteP);
            deleteP.setOrigId(deleteP.getId());
            permissionService.updateById(deleteP);
            // change
            Permission updateP = new Permission();
            updateP.setIsActive(true);
            updateP.setTenantId(tenantId);
            updateP.setResourceType(PermissionConstant.RESOURCE_TYPE_RESTAPI);
            updateP.setParentId(permission.getParentId());
            updateP.setPermissionName(permission.getPermissionName() + "  Update Interface");
            updateP.setId(Long.parseLong(permission.getParentId().toString() + "3"));
            // Add App id
            updateP.setApplicationId(applicationId);
            // Add the relationship between APP and resources
            updateP.setCreateTime(System.currentTimeMillis());
            updateP.setCreatedBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(
                    ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest())));
            updateP.setDeleteTime(0l);
            success = permissionService.insert(updateP);
            updateP.setOrigId(updateP.getId());
            permissionService.updateById(updateP);
            // check
            Permission getP = new Permission();
            getP.setIsActive(true);
            getP.setResourceType(PermissionConstant.RESOURCE_TYPE_RESTAPI);
            getP.setTenantId(tenantId);
            getP.setParentId(permission.getParentId());
            getP.setPermissionName(permission.getPermissionName() + "查看 Interface");
            getP.setId(Long.parseLong(permission.getParentId().toString() + "4"));
            // Add an app ID
            getP.setApplicationId(applicationId);
            // Add the relationship between APP and resources
            getP.setCreateTime(System.currentTimeMillis());
            getP.setCreatedBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(
                    ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest())));
            getP.setDeleteTime(0l);
            success = permissionService.insert(getP);
            getP.setOrigId(getP.getId());
            permissionService.updateById(getP);
            // batchImport
            Permission batchInP = new Permission();
            batchInP.setResourceType(PermissionConstant.RESOURCE_TYPE_RESTAPI);
            batchInP.setTenantId(tenantId);
            batchInP.setParentId(permission.getParentId());
            batchInP.setId(Long.parseLong(permission.getParentId().toString() + "5"));
            batchInP.setPermissionName(permission.getPermissionName() + "批量Import Interface");
            // Add App id
            batchInP.setApplicationId(applicationId);
            batchInP.setIsActive(true);
            // Add the relationship between APP and resources
            batchInP.setCreateTime(System.currentTimeMillis());
            batchInP.setCreatedBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(
                    ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest())));
            batchInP.setDeleteTime(0l);
            success = permissionService.insert(batchInP);
            batchInP.setOrigId(batchInP.getId());
            permissionService.updateById(batchInP);
            // batch Export
            Permission batchOutP = new Permission();
            batchOutP.setResourceType(PermissionConstant.RESOURCE_TYPE_RESTAPI);
            batchOutP.setTenantId(tenantId);
            batchOutP.setParentId(permission.getParentId());
            batchOutP.setPermissionName(permission.getPermissionName() + "批量 Export  Interface");
            // Add App id
            batchOutP.setApplicationId(applicationId);
            batchOutP.setIsActive(true);
            batchOutP.setId(Long.parseLong(permission.getParentId().toString() + "6"));
            // Add the relationship between APP and resources
            batchOutP.setCreateTime(System.currentTimeMillis());
            batchOutP.setCreatedBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(
                    ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest())));
            batchOutP.setDeleteTime(0l);
            success = permissionService.insert(batchOutP);
            batchOutP.setOrigId(batchOutP.getId());
            permissionService.updateById(batchOutP);
            if (success) {
                return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
            } else {
                throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
            }
        }
        try {
            permission.setTenantId(tenantId);
            // Add App id
            permission.setApplicationId(applicationId);
            // Add the relationship between APP and resources
            permission.setCreateTime(System.currentTimeMillis());
            permission.setCreatedBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(
                    ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest())));
            permission.setDeleteTime(0l);
            permission.setAccessType(1);
            Boolean success = permissionService.insert(permission);
            permission.setOrigId(permission.getId());
            permissionService.updateById(permission);

            if (success) {
                return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
            } else {
                throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
            }
        } catch (Exception e) {
            throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
        }
    }

    /**
     * Superior resource Data Source
     *
     * @param tenantId      Tenant id
     * @param applicationId App id
     * @return Result<List < TreeModel>>
     */
    @RequestMapping(value = "/permission/dropDownParent/{applicationId}", method = RequestMethod.GET)
    public Object dropDownParentPermission(
            @PathVariable("tenantId") String tenantId,
            @PathVariable("applicationId") Long applicationId) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("application_id", applicationId)
                .eq("tenant_id", tenantId);

        List<Permission> permissionList = permissionService.selectList(queryWrapper);
        List<TreeModel> treeModels = new ArrayList<>();
        List<TreeModel> build = new ArrayList<>();

        if (CollUtil.isEmpty(permissionList)) {
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, build);
        }

        for (Permission permission : permissionList) {

            TreeModel t = new TreeModel();
            BeanUtil.copyProperties(permission, t);

            t.setName(permission.getPermissionName());
            t.setPid(permission.getParentId());
            treeModels.add(t);
        }

        if (CollUtil.isNotEmpty(treeModels)) {
            build = TreeUtils.build(treeModels);
        }
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, build);
    }

    private void pullAncestors(JSONObject permission, Map<Long, JSONObject> permissionTreeMap) {
        if (permission.getLong("parentId") != null && permission.getLong("parentId") != 0) {
            Permission parentPermission = permissionService.selectById(permission.getLong("parentId"));
            JSONObject permJsonObject = extractJSONObject(parentPermission);
            permissionTreeMap.put(parentPermission.getId(), permJsonObject);
            pullAncestors(permJsonObject, permissionTreeMap);
        }

    }

    private void traceDPRoot(Long permId, Map<Long, JSONObject> permissionTreeMap) {
        if (permId.equals(0l))
            return;
        JSONObject jo = permissionTreeMap.get(permId);
        JSONObject parentJo = permissionTreeMap.get(Long.parseLong(jo.get("parentId").toString()));
        TreeMap<Long, JSONObject> children = toMap((Collection<JSONObject>) parentJo.get("children"));
        if (children == null) {
            children = new TreeMap<Long, JSONObject>();
            children.put(jo.getLong("id"), jo);
            parentJo.put("children", children.values());
        } else {
            children.put(jo.getLong("id"), jo);
            parentJo.replace("children", children.values());
        }
        Long parentId = Long.parseLong(parentJo.getLong("id").toString());
        permissionTreeMap.put(parentId, parentJo);
        if (!parentId.equals(0l)) {
            traceRoot(parentId, permissionTreeMap);
        }

    }

    private void traceRoot(Long permId, Map<Long, JSONObject> permissionTreeMap) {
        if (permId.equals(0l))// Root node 0 exit
            return;
        JSONObject jo = permissionTreeMap.get(permId);
        JSONObject parentJo = permissionTreeMap.get(Long.parseLong(jo.get("parentId").toString()));
        if (parentJo == null) {// No parent node exits
            return;
        }
        if (jo.getInteger("resourceType") != 2) {// 不The page element is added to Children
            TreeMap<Long, JSONObject> children = toMap((Collection<JSONObject>) parentJo.get("children"));
            if (children == null) {
                children = new TreeMap<Long, JSONObject>();
                children.put(jo.getLong("sortOrder"), jo);
                parentJo.put("children", children.values());
            } else {// children ,New
                children.put(jo.getLong("sortOrder"), jo);
                parentJo.replace("children", children.values());
            }

            Long parentId = Long.parseLong(parentJo.getLong("id").toString());
            permissionTreeMap.put(parentId, parentJo);
            if (!parentId.equals(0l)) {
                traceRoot(parentId, permissionTreeMap);
            }
        } else {
            JSONObject meta = (JSONObject) parentJo.get("meta");
            if (meta == null) {
                TreeMap<Long, JSONObject> button = new TreeMap<Long, JSONObject>();
                button.put(jo.getLong("id"), jo);
                JSONObject newMeta = new JSONObject();
                newMeta.put("button", button.values());
                parentJo.put("meta", newMeta);
            } else {
                TreeMap<Long, JSONObject> existingMetaMap = toMap((Collection<JSONObject>) meta.get("button"));
                if (existingMetaMap == null)
                    existingMetaMap = new TreeMap<Long, JSONObject>();
                existingMetaMap.put(jo.getLong("id"), jo);
                meta.replace("button", existingMetaMap.values());
                parentJo.replace("meta", meta);
            }

            Long parentId = Long.parseLong(parentJo.getLong("id").toString());
            permissionTreeMap.put(parentId, parentJo);
            if (!parentId.equals(0l)) {
                traceRoot(parentId, permissionTreeMap);
            }
        }

    }

    private TreeMap<Long, JSONObject> toMap(Collection<JSONObject> set) {
        if (set == null)
            return null;
        TreeMap<Long, JSONObject> children = new TreeMap<Long, JSONObject>();
        for (JSONObject jo : set) {
            if (!children.values().contains(jo))
                children.put(jo.getLong("sortOrder"), jo);
        }
        return children;
    }

    private JSONObject extractJSONObject(Permission perm) {
        JSONObject permTreeNode = new JSONObject();
        permTreeNode.put("id", perm.getId());
        permTreeNode.put("parentId", perm.getParentId());
        permTreeNode.put("resourceName", perm.getPermissionName());
        permTreeNode.put("url", perm.getUrl());
        permTreeNode.put("resourceType", perm.getResourceType());
        permTreeNode.put("accessType", perm.getAccessType());
        permTreeNode.put("icon", perm.getIcon());
        permTreeNode.put("resourceAttribute", perm.getResourceAttribute());
        permTreeNode.put("origId", perm.getOrigId());
        permTreeNode.put("sortOrder", perm.getSortOrder());
        return permTreeNode;
    }

    // Resource tree view all resources under this TENANT
    @GetMapping("/permissionTree/{disabled}/{applicationId}")
    public AjaxResult getPermissionTreeByTenantId(@PathVariable("tenantId") String tenantId,
            @PathVariable("applicationId") Long applicationId,
            @PathVariable("disabled") boolean disabled) {

        List<TreeModel> list = permissionService.getPermissionTreeByTenantId(tenantId, applicationId, null);
        if (CollectionUtils.isNotEmpty(list)) {
            if (disabled) {
                list.stream().forEach(item -> {
                    item.setDisabled(true);
                });
            } else {
                list.stream().forEach(item -> {
                    item.setDisabled(false);
                });
            }
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, TreeUtils.build(list));
        } else {
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, new ArrayList<>());
        }
    }

    // Resource tree view all resources under this TENANT
    @GetMapping("/order/permissionTree/{disabled}/{applicationId}")
    public Object getPermissionOrderTreeByTenantId(@PathVariable("tenantId") String tenantId,
            @PathVariable("applicationId") Long applicationId,
            @PathVariable("disabled") boolean disabled) {
        List<TreeModel> list = permissionService.getPermissionTreeByTenantId(tenantId, applicationId, 1L);
        if (CollectionUtils.isNotEmpty(list)) {
            if (disabled) {
                list.stream().forEach(item -> {
                    item.setDisabled(true);
                });
            } else {
                list.stream().forEach(item -> {
                    if (item.getInSuite() != null && item.getInSuite() == 1) {
                        item.setDisabled(true);
                        item.setCheck(true);
                    } else {
                        item.setDisabled(false);
                    }
                });
            }
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, TreeUtils.build(list, redisUtils, locale));
        } else {
            return null;
        }

    }

    // App Management resources Pagination
    @GetMapping("/permission/list/{applicationId}")
    public Object list(
            @PathVariable("tenantId") String tenantId,
            @PathVariable("applicationId") Long applicationId,
            @RequestParam Map<String, Object> map) {
        // To all resources about this app ID of query intermediate table
        String resourceType = null;
        if (map.containsKey("resourceType")) {
            resourceType = (String) map.get("resourceType");
        }
        String name = null;
        if (map.containsKey("name")) {
            name = (String) map.get("name");
        }

        //
        List<TreeModel> treeModelList = new ArrayList<>();

        Map<String, Object> params = new HashMap<>();
        params.put("orderTenantId", tenantId);
        if (!StringUtil.isEmpty(name)) {
            params.put("permissionName", name);
        }
        if (!StringUtil.isEmpty(resourceType)) {
            params.put("resourceType", resourceType);
        }
        List<Permission> selectList = null;
        if (!"1".equals(tenantId))
            selectList = permissionMapper.getOrderSuitePermissionByParam(params);
        else {
            QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(!StringUtil.isEmpty(resourceType), "resource_type", resourceType)
                    .eq(org.apache.commons.lang3.StringUtils.isNotEmpty(name), "permission_name", name)
                    .eq(applicationId != 0, "application_id", applicationId)
                    .eq(!StringUtil.isEmpty(tenantId), "tenant_id", tenantId)
                    .eq("delete_time", 0);
            if (!StringUtil.isEmpty(name)) {
                queryWrapper.eq("permission_name", name);
            }
            selectList = permissionMapper.selectList(queryWrapper);
        }
        if (CollUtil.isNotEmpty(selectList)) {
            for (Permission permission : selectList) {

                TreeModel t = new TreeModel();
                BeanUtil.copyProperties(permission, t);
                t.setCode(permission.getCreatedBy() == null ? "" : permission.getCreatedBy().toString());

                t.setName(permission.getPermissionName());
                t.setPid(permission.getParentId());
                treeModelList.add(t);
            }

        }

        List<TreeModel> build;
        if (CollUtil.isNotEmpty(treeModelList)) {
            build = TreeUtils.build(treeModelList);
        } else {
            build = new ArrayList<>();
        }
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, build);
    }

    // AppManagement resource UPDATE and viewing
    @RequestMapping(value = "/permission/edit", method = RequestMethod.GET)
    public Object edit(String id) {

        PermissionInfoVO bean = permissionService.getPermissionsById(id);

        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, bean);
    }

    // Update resource
    @RequestMapping(value = "/permission", method = RequestMethod.PUT)
    public Object update(@RequestBody Permission bean) {

        try {
            int success = permissionService.updatePermission(bean);
            if (success > 0) {
                return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, bean);
            } else {
                throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
        }
    }

    // Delete resource
    @RequestMapping(value = "/permission", method = RequestMethod.DELETE)
    public Object del(String id, HttpServletRequest request, HttpServletResponse response,
            @PathVariable("tenantId") String tenantId) {

        String[] split;
        if (id.contains(",")) {
            split = id.split(",");
        } else {
            split = new String[] { id };
        }

        // You can't delete for verification
        if (ArrayUtil.isNotEmpty(split)) {
            for (int i = 0; i < split.length; i++) {
                QueryWrapper<Permission> wrapper = new QueryWrapper<>();
                wrapper.eq("parent_id", split[i]);

                List<Permission> selectList = permissionService.selectList(wrapper);
                if (!CollUtil.isEmpty(selectList)) {
                    JSONObject serviceMessage = commonDictService.getServiceMessage(
                            locale + "_SERVICE_CONSTANT_MESSAGE", "THERE_ARE_SUBSETS_UNDER_RESOURCES", false, tenantId);
                    String message = serviceMessage.get("message") + "";
                    message = selectList.get(0).getPermissionName() + message;
                    serviceMessage.put("message", message);
                    QslException qslException = new QslException(MessageKey.THIS_RESOURCE_HAS_SUB_SETS,
                            selectList.get(0).getPermissionName());
                    throw qslException;
                }
            }
        }

        try {
            permissionService.updateDeleteTimeById(split);
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
        }
    }

    // DATA permissions Tenant ID resource ID under this resource
    @RequestMapping(value = "/permission/{permissionId}/data", method = RequestMethod.GET)
    public Object getDataPermissionById(
            @PathVariable("tenantId") String tenantId,
            @PathVariable("permissionId") String permissionId,
            HttpServletRequest request) {
        // Find the DICTIONARY group of Data permissions under TENANT
        CommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(tenantId,
                PermissionConstant.DATA_ACCESS_PERMISSION);
        if (commonDictType == null) {
            throw new QslException(MessageKey.COMMONDICTTYPE_NOT_EXIST);
        }
        // Then based on the Tenant id and resource ID and dictionary group ID Query
        // Dictionary Detail entity
        String value = commonDictService.getDictValueByTenantIdAndKeyAndDictTypeId(tenantId, "dp" + permissionId,
                commonDictType.getId().toString());
        if (StringUtil.isEmpty(value)) {
            // This resource does not have data permissions
            value = PermissionConstant.COMMON_DATA_ACCESS_PRIVATE;
        }
        JSONObject jo = new JSONObject();
        jo.put("code", 0);
        jo.put("message", value);
        jo.put("success", true);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, jo);
    }

    // Import
    @RequestMapping(value = "/permission/bulk", method = RequestMethod.POST)
    public Object userBulkUpload(
            @RequestParam("file") MultipartFile multipartfile,
            @RequestParam("nonce") String nonce,
            @RequestHeader("Authorization") String jwt) {
        String tenantId = TokenUtils.extractTenantIdFromToken(jwt);
        if (nonce == null) {
            throw new QslException(MessageKey.NONCE_NOT_EXIST);

        }
        File file = FileUtil.convertMultipartFileToFile(multipartfile);
        int successCount = 0, failureCount = 0, repeatCount = 0, processedCount = 0;
        List<Permission> existingPermissionList = new ArrayList<Permission>();
        List<Map<String, Object>> failedUserList = new ArrayList<Map<String, Object>>();
        InputStream is = null;
        HSSFWorkbook xssfWorkbook = null;
        try {
            is = new FileInputStream(file);
            // Get the excel workbook Object
            xssfWorkbook = new HSSFWorkbook(is);
            // Get Excel worksheet Object
            HSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            BigDecimal totalRows = new BigDecimal(xssfSheet.getLastRowNum());
            // Get the designated line of the excel worksheet Object
            for (int i = 1; i <= xssfSheet.getLastRowNum(); i++) {
                HSSFRow xssfRow = xssfSheet.getRow(i);
                // Get the cells specified by the excel work table
                Permission permission = null;
                try {
                    permission = excelRowToPermission(xssfRow, tenantId);
                } catch (Exception e) {
                    failureCount++;
                    processedCount++;
                    BigDecimal successPercentage = new BigDecimal(processedCount).divide(totalRows, 2,
                            BigDecimal.ROUND_HALF_UP);
                    redisUtils.set(nonce, successPercentage.multiply(new BigDecimal(100)).intValue());
                    failedUserList.add(rowToMap(xssfRow, e.getMessage()));
                    e.printStackTrace();
                    continue;
                }

                Permission existingPermission = permissionService
                        .selectByTenantIdAndPermissionName(permission.getTenantId(), permission.getPermissionName());
                if (existingPermission != null) {
                    existingPermissionList.add(permission);
                    repeatCount++;
                    processedCount++;
                    BigDecimal successPercentage = new BigDecimal(processedCount).divide(totalRows, 2,
                            BigDecimal.ROUND_HALF_UP);
                    redisUtils.set(nonce, successPercentage.multiply(new BigDecimal(100)).intValue());

                    continue;
                }
                permissionService.newPermissionDefaults(permission);
                boolean insertedStatus = permissionService.insert(permission);
                if (insertedStatus) {
                    successCount++;
                    processedCount++;
                    BigDecimal successPercentage = new BigDecimal(processedCount).divide(totalRows, 2,
                            BigDecimal.ROUND_HALF_UP);
                    redisUtils.set(nonce, successPercentage.multiply(new BigDecimal(100)).intValue());
                } else {
                    failureCount++;
                    processedCount++;
                    failedUserList.add(rowToMap(xssfRow,
                            commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
                                    MessageKey.ERROR_INSERTING_DATABASE, false, tenantId)));
                    BigDecimal successPercentage = new BigDecimal(processedCount).divide(totalRows, 2,
                            BigDecimal.ROUND_HALF_UP);
                    redisUtils.set(nonce, successPercentage.multiply(new BigDecimal(100)).intValue());
                }

            }
            xssfWorkbook.close();
            is.close();
        } catch (IOException e) {
            if (xssfWorkbook != null) {
                try {
                    xssfWorkbook.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            if (xssfWorkbook != null) {
                try {
                    xssfWorkbook.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("successCount", successCount);
        resultMap.put("failureCount", failureCount);
        resultMap.put("repeatCount", repeatCount);
        resultMap.put("existingUserList", existingPermissionList);
        resultMap.put("failedUserList", failedUserList);
        // Get the cell style
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, resultMap);

    }

    private Permission excelRowToPermission(HSSFRow xssfRow, String tenantId) throws Exception {
        Permission permission = new Permission();
        try {
            permission.setParentId(Long.parseLong(new Double(xssfRow.getCell(0).getNumericCellValue()).toString()));
        } catch (Exception e) {
            throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
                    "PARENTID_INVALID", false, tenantId));
        }
        try {
            permission.setPermissionName(xssfRow.getCell(1).getStringCellValue());
        } catch (Exception e) {
            throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
                    "INVALID_PERMISSION_NAME", false, tenantId));
        }
        try {
            permission.setTenantId(xssfRow.getCell(2).getStringCellValue());
        } catch (Exception e) {
            throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
                    "INVALID_TENANT_ID", false, tenantId));
        }
        try {
            permission.setResourceType(Integer
                    .parseInt((new Double(xssfRow.getCell(3).getNumericCellValue()).toString().substring(0, 1))));
        } catch (Exception e) {
            throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
                    "INVALID_RESOURCE_TYPE", false, tenantId));
        }
        try {
            permission.setIcon(xssfRow.getCell(4).getStringCellValue());
        } catch (Exception e) {
            throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
                    "INVALID_RESOURCE_ICON", false, tenantId));
        }
        try {
            permission.setIsActive("1".equals(xssfRow.getCell(5).getStringCellValue()));
        } catch (Exception e) {
            throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
                    "INVALID_RESOURCE_ICON", false, tenantId));
        }
        try {
            permission.setSortOrder(Long.parseLong(xssfRow.getCell(6).getStringCellValue()));
        } catch (Exception e) {
            throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
                    "INVALID_RESOURCE_STATUS", false, tenantId));
        }
        try {
            permission.setUrl(xssfRow.getCell(7).getStringCellValue());
        } catch (Exception e) {
            throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
                    "INVALID_RESOURCE_ORDER", false, tenantId));
        }
        try {
            permission.setUrl(xssfRow.getCell(8).getStringCellValue());
        } catch (Exception e) {
            throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
                    "INVALID_RESOURCE_URL", false, tenantId));
        }

        try {
            permission.setAccessType(Integer.parseInt(new Double(xssfRow.getCell(9).getNumericCellValue()).toString()));
        } catch (Exception e) {
            throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
                    "INVALID_RESOURCE_URL", false, tenantId));
        }
        try {
            permission.setResourceAttribute(
                    Integer.parseInt(new Double(xssfRow.getCell(10).getNumericCellValue()).toString()));
        } catch (Exception e) {
            throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
                    "INVALID_RESOURCE_URL", false, tenantId));
        }
        try {
            permission.setCreatedBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(
                    ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest())));
        } catch (Exception e) {
            throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
                    "INVALID_CREATOR", false, tenantId));
        }
        return permission;
    }

    private Map<String, Object> rowToMap(HSSFRow xssfRow, String errorMessage) {
        Map<String, Object> user = new HashMap<String, Object>();
        user.put("parentId", xssfRow.getCell(0).getNumericCellValue());
        user.put("permissionName", xssfRow.getCell(1).getStringCellValue());
        user.put("tenantId", String.valueOf(xssfRow.getCell(2).getNumericCellValue()));
        user.put("resourceType", String.valueOf(xssfRow.getCell(3).getNumericCellValue()));
        user.put("icon", xssfRow.getCell(4).getStringCellValue());
        user.put("isActive", String.valueOf(xssfRow.getCell(5).getNumericCellValue()));
        user.put("sortOrder", String.valueOf(xssfRow.getCell(6).getNumericCellValue()));
        user.put("url", xssfRow.getCell(7).getStringCellValue());
        user.put("accessType", xssfRow.getCell(8).getNumericCellValue());
        user.put("resourceAttribute", xssfRow.getCell(9).getNumericCellValue());
        user.put("createdBy", Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(
                ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest())));
        user.put("error", errorMessage);
        return user;
    }

}
