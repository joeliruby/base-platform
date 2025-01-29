package com.matariky.userservice.controller;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;
import com.matariky.userservice.bean.Tenant;
import com.matariky.userservice.bean.TreeModel;
import com.matariky.userservice.bean.UserGroup;
import com.matariky.userservice.bean.UserOrganization;
import com.matariky.userservice.mapper.UserOrganizationMapper;
import com.matariky.userservice.service.TenantService;
import com.matariky.userservice.service.UserGroupService;
import com.matariky.userservice.service.UserOrganizationService;
import com.matariky.userservice.service.UserService;
import com.matariky.utils.AjaxResult;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author AUTOMATION
 */
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
public class UserOrganizationController {

    @Autowired
    private UserOrganizationService userOrganizationService;
    @Autowired
    private UserOrganizationMapper userOrganizationMapper;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private UserService userService;

    @Value("${message.locale}")
    String locale;

    @Autowired
    CommonDictService commonDictService;

    @Autowired
    UserGroupService groupService;

    @RequestMapping("/userOrganization/list")
    public AjaxResult list(HttpServletRequest request, UserOrganization bean, @PathVariable("tenantId") String tenantId,
            @RequestParam("index") int pageIndex, @RequestParam("perPage") int perPage,
            @RequestHeader("Authorization") String jwt) {
        PageHelper.startPage(pageIndex, perPage);
        Page<UserOrganization> page = userOrganizationService.getUserOrganizationAll();
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

    @RequestMapping(value = "/userOrganization/edit", method = RequestMethod.GET)
    public AjaxResult edit(HttpServletRequest request, @RequestParam String id,
            @PathVariable("tenantId") String tenantId) {
        UserOrganization bean = userOrganizationService.getUserOrganizationById(id);
        Tenant selectBytenantCode = tenantService.selectBytenantCode(bean.getTenantId());
        if (selectBytenantCode != null) {
            bean.setTenantName(selectBytenantCode.getTenantName());
        }

        String parentName = userOrganizationService.selectNameById(bean.getParentId());

        bean.setParentName(parentName);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, bean);
    }

    // New Subordinate
    @RequestMapping(value = "/userOrganization/create/parentId/{parentId}", method = RequestMethod.GET)
    public AjaxResult create(HttpServletRequest request,
            @PathVariable("tenantId") String tenantId,
            @PathVariable("parentId") Long parentId) {
        JSONObject jsonObject = new JSONObject();
        // Code Automatic Generation 01父 00x子

        jsonObject.put("code", "");
        jsonObject.put("tenantName", tenantService.selectBytenantCode(tenantId).getTenantName());
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, jsonObject);
    }

    @RequestMapping(value = "/userOrganization", method = RequestMethod.POST)
    public AjaxResult save(
            @RequestBody UserOrganization bean,
            @PathVariable("tenantId") String tenantId,
            HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> map = new HashMap<>();
        // 校验
        map.put("organization_name", bean.getOrganizationName());
        map.put("tenant_id", tenantId);
        map.put("parent_id", bean.getParentId());
        map.put("delete_time", 0);

        Long count = userOrganizationMapper.selectCount(Wrappers.lambdaQuery(UserOrganization.class)
                .eq(UserOrganization::getUserGroupId, bean.getUserGroupId())
                .eq(UserOrganization::getDeleteTime, 0));
        if (count > 0) {
            throw new QslException(MessageKey.USER_GROUP_ORGANIZATION_IS_EXIST);
        }

        // Determine the name weTher repeat
        List<UserOrganization> selectByMap = userOrganizationService.selectByMap(map);

        if (CollUtil.isNotEmpty(selectByMap)) {
            throw new QslException(MessageKey.ORGANIZATION_NAME_NULL);
        }

        // Institutional name cannot be empty
        if (StringUtil.isEmpty(bean.getOrganizationName())) {
            throw new QslException(MessageKey.ORGANIZATION_NAME_ERR);
        }

        // Institution Type cannot be empty
        if (bean.getOrgType() == null) {
            throw new QslException(MessageKey.ORGANIZATION_TYPE_ERR);
        }

        bean.setTenantId(tenantId);
        userOrganizationService.saveUserOrganization(bean);

        Map<String, Object> groupMap = new HashMap<>();
        groupMap.put("group_name", bean.getOrganizationName());
        groupMap.put("tenant_id", tenantId);
        groupMap.put("delete_time", 0);

        List<UserGroup> groupList = groupService.selectByMap(groupMap);

        if (!Collections.isEmpty(groupList)) {
            UserGroup grp = groupList.get(0);
            grp.setOrgCode(bean.getOrganizationCode());
            groupService.updateById(grp);
        }
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, bean);
    }

    @RequestMapping(value = "/userOrganization", method = RequestMethod.PUT)
    public AjaxResult update(@RequestBody UserOrganization bean, HttpServletRequest request,
            HttpServletResponse response, @PathVariable("tenantId") String tenantId) {
        // Institutional name cannot be empty
        if (StringUtil.isEmpty(bean.getOrganizationName())) {
            throw new QslException(MessageKey.ORGANIZATION_NAME_ERR);
        }
        // Institution Type cannot be empty
        if (bean.getOrgType() == null) {
            throw new QslException(MessageKey.ORGANIZATION_TYPE_ERR);
        }

        Long count = userOrganizationMapper.selectCount(Wrappers.lambdaQuery(UserOrganization.class)
                .eq(UserOrganization::getUserGroupId, bean.getUserGroupId())
                .ne(UserOrganization::getId, bean.getId())
                .eq(UserOrganization::getDeleteTime, 0));
        if (count > 0) {
            throw new QslException(MessageKey.USER_GROUP_ORGANIZATION_IS_EXIST);
        }

        userOrganizationService.updateUserOrganization(bean);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    @RequestMapping(value = "/userOrganization", method = RequestMethod.DELETE)
    public AjaxResult del(String id, @PathVariable("tenantId") String tenantId, HttpServletRequest request,
            HttpServletResponse response) {

        String code = userOrganizationService.getParentcodeByParentId(Long.parseLong(id), tenantId);

        // Judgment WETHER's sub -department
        Long[] organizationIds = userOrganizationService.getChildrenOrganization(code, tenantId);

        if (organizationIds != null && organizationIds.length > 1) {
            throw new QslException(MessageKey.ORGANIZATION_CHILDREN_ERR);

        }

        // Judgment department with Wether has a user
        int count = userService.getCountByOrganizationId(organizationIds, tenantId);
        if (count > 0) {

            throw new QslException(MessageKey.ORGANIZATION_USER_ERR);
        }

        userOrganizationService.delete(Long.parseLong(id), tenantId);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
    }

    /**
     * Query Data Find out the organization and respond to the front end in the DATA
     * format of the tree structure
     *
     * @return
     */
    @RequestMapping(value = "/userOrganization/treeList", method = RequestMethod.GET)
    public AjaxResult queryTreeList(@PathVariable("tenantId") String tenantId) {

        List<TreeModel> treeList = userOrganizationService.getOrganizationTree(tenantId);

        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, treeList);
    }

    /**
     * Query Data Find out the organization and respond to the front end in the DATA
     * format of the tree structure
     *
     * @return
     */
    @RequestMapping(value = "/userOrganization/indTree", method = RequestMethod.GET)
    public AjaxResult queryIndTree(@PathVariable("tenantId") String tenantId) {
        List<TreeModel> treeList = userOrganizationService.getOrganizationTreeWithInd(tenantId);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, treeList);
    }

    // Query Organization tree has no sub -nodes to remove children
    @RequestMapping(value = "/userOrganization/nochildren/treeList", method = RequestMethod.GET)
    public AjaxResult queryTreeNode(String tenantId) {

        List<TreeModel> treeList = userOrganizationService.queryTreeNode(tenantId);

        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, treeList);
    }

    // View organizational architecture
    @RequestMapping(value = "/userOrganization/view/treeList", method = RequestMethod.GET)
    public AjaxResult queryTreeListView(@PathVariable("tenantId") String tenantId,
            @RequestParam String code) {

        List<TreeModel> treeList = userOrganizationService.queryTreeListView(tenantId, code);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, treeList);
    }

    @GetMapping("/userOrganization/all")
    public AjaxResult selectUserAllByTenantId(@PathVariable("tenantId") String tenantId) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("tenant_id", tenantId);
        columnMap.put("delete_time", 0);
        List<UserOrganization> userList = userOrganizationService.selectByMap(columnMap);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, userList);
    }

}
