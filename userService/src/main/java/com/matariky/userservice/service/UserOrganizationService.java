package com.matariky.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.userservice.bean.TreeModel;
import com.matariky.userservice.bean.UserGroup;
import com.matariky.userservice.bean.UserOrganization;
import com.matariky.userservice.mapper.UserGroupMapper;
import com.matariky.userservice.mapper.UserOrganizationMapper;
import com.matariky.utils.DateUtil;
import com.matariky.utils.OrgCodeUtil;
import com.matariky.utils.TreeUtils;

import cn.hutool.core.collection.CollUtil;

/**
 * Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
public class UserOrganizationService extends BaseServiceImpl<UserOrganizationMapper, UserOrganization> {

    @Autowired
    private UserOrganizationMapper userOrganizationMapper;
    @Autowired
    private UserGroupMapper userGroupMapper;

    // Query All
    public Page<UserOrganization> getUserOrganizationAll() {
        return userOrganizationMapper.getUserOrganizationAll();
    }

    // Query 所有 Quantity
    public int getUserOrganizationAllCount() {
        return userOrganizationMapper.getUserOrganizationAllCount();
    }

    // New Method
    public int createUserOrganization(UserOrganization bean) {
        return userOrganizationMapper.createUserOrganization(bean);
    }

    // Update Method
    public int updateUserOrganization(UserOrganization bean) {
        if (bean.getParentId() != null && bean.getUserGroupId() != null) {
            List<UserOrganization> subOrgList = userOrganizationMapper
                    .selectList(Wrappers.lambdaQuery(UserOrganization.class)
                            .eq(UserOrganization::getParentId, bean.getParentId())
                            .eq(UserOrganization::getDeleteTime, 0)
                            .ne(UserOrganization::getId, bean.getId()));
            List<UserOrganization> sameGroupIdList = subOrgList.stream()
                    .filter(item -> item.getUserGroupId() != null
                            && item.getUserGroupId().equals(bean.getUserGroupId()))
                    .collect(Collectors.toList());
            if (!sameGroupIdList.isEmpty()) {
                throw new QslException(MessageKey.USER_GROUP_NOT_REPEAT);
            }
        }
        if (bean.getUserGroupId() != null) {
            UserGroup userGroup = userGroupMapper.selectById(bean.getUserGroupId());
            bean.setOrganizationName(userGroup.getGroupName());
        }
        return userOrganizationMapper.updateUserOrganization(bean);
    }

    // Delete Method
    public int delUserOrganizationById(int id) {
        return userOrganizationMapper.delUserOrganizationById(id);
    }

    public UserOrganization getUserOrganizationById(String id) {
        return userOrganizationMapper.getUserOrganizationById(id);
    }

    public UserOrganization selectByOrgCode(String orgCode) {
        return userOrganizationMapper.selectByOrgCode(orgCode);
    }

    public List<TreeModel> getOrganizationTree(String tenantId) {

        return TreeUtils.build(userOrganizationMapper.getOrganizationList(tenantId));
    }

    public Long[] getChildrenOrganization(String organizationCode, String tenantId) {

        return userOrganizationMapper.getChildrenOrganization(organizationCode, tenantId);
    }

    public List<UserOrganization> selectByMap(Map<String, Object> map) {

        return userOrganizationMapper.selectByMap(map);
    }

    public void saveUserOrganization(UserOrganization bean) {
        if (bean.getParentId() != null && bean.getUserGroupId() != null) {
            List<UserOrganization> subOrgList = userOrganizationMapper
                    .selectList(Wrappers.lambdaQuery(UserOrganization.class)
                            .eq(UserOrganization::getParentId, bean.getParentId())
                            .eq(UserOrganization::getDeleteTime, 0));
            List<UserOrganization> sameGroupIdList = subOrgList.stream()
                    .filter(item -> item.getUserGroupId() != null
                            && item.getUserGroupId().equals(bean.getUserGroupId()))
                    .collect(Collectors.toList());
            if (!sameGroupIdList.isEmpty()) {
                throw new QslException(MessageKey.USER_GROUP_NOT_REPEAT);
            }
        }
        // Create Time
        bean.setCreateTime(DateUtil.getCurrentDateAndTime().getTime());
        if (bean.getUserGroupId() != null) {
            UserGroup userGroup = userGroupMapper.selectById(bean.getUserGroupId());
            bean.setOrganizationName(userGroup.getGroupName());
        }
        userOrganizationMapper.insert(bean);

        // Configuration organize Code
        UserOrganization parentOrg = userOrganizationMapper.selectById(bean.getParentId());
        String codeString = OrgCodeUtil.generateOrgCodeFromOrgBean(parentOrg.getOrganizationCode(), bean.getId(),
                bean.getOrgType());

        bean.setOrganizationCode(codeString);

        userOrganizationMapper.updateOrganizationCodeById(bean.getId(), codeString);
    }

    public String getParentcodeByParentId(Long parentId, String tenantId) {
        return userOrganizationMapper.getParentcodeByParentId(parentId, tenantId);
    }

    public int getCountByParentId(Long parentId, String tenantId) {
        return userOrganizationMapper.getCountByParentId(parentId, tenantId);
    }

    public void delete(Long id, String tenantId) {

        // Delete
        userOrganizationMapper.updateDeleteTimeById(id, tenantId);

    }

    public String selectNameById(Long parentId) {
        // TODO Auto-generated method stub
        return userOrganizationMapper.selectNameById(parentId);
    }

    /**
     * 根据pid ,构建树节点
     */
    public List<JSONObject> build(Map<Long, JSONObject> treeNodes) {
        List<JSONObject> result = new ArrayList<>();

        for (JSONObject node : treeNodes.values()) {
            JSONObject parent = null;
            if (node.get("pid") != null) {
                Long pid = Long.parseLong(node.get("pid").toString());
                parent = treeNodes.get(pid);
            }
            String id = node.get("id").toString();
            if (parent != null && !id.equals(parent.get("id").toString())) {
                JSONArray children = (JSONArray) parent.get("children");
                if (children == null) {
                    children = new JSONArray();
                }
                children.add(node);
                parent.put("children", children);
                continue;
            }
            result.add(node);
        }
        return result;
    }

    public List<TreeModel> queryTreeNode(String tenantId) {
        List<TreeModel> organizationList = userOrganizationMapper.getOrganizationList(tenantId);
        return TreeUtils.build(organizationList);
    }

    // 通过 Code Query 上级组织也包含自己这一层
    public List<UserOrganization> getParentOrgListByCode(String code, String tenantId) {
        List<UserOrganization> list = new ArrayList<>();
        // 自己本身的组织
        UserOrganization organizationByCode = userOrganizationMapper.getOrganizationByCode(code, tenantId);
        if (organizationByCode != null) {
            list.add(organizationByCode);
        }

        List<String> aList = new ArrayList<>();

        List<String> codeList = getCodeList(code, aList);

        if (CollUtil.isNotEmpty(codeList)) {
            for (String string : codeList) {
                UserOrganization entity = userOrganizationMapper.getOrganizationByCode(string, tenantId);
                if (entity != null) {
                    list.add(entity);
                }
            }
        }
        return list;
    }

    public static List<String> getCodeList(String code, List<String> codeList) {

        if (code.contains("_")) {
            int lastIndexOf = code.lastIndexOf("_");
            if (lastIndexOf == -1) {
                return codeList;
            } else {
                String substring = code.substring(0, lastIndexOf);
                codeList.add(substring);
                getCodeList(substring, codeList);
            }
        }

        return codeList;
    }

    public UserOrganization getOrganizationByCode(String code, String tenantId) {
        return userOrganizationMapper.getOrganizationByCode(code, tenantId);
    }

    public List<TreeModel> queryTreeListView(String tenantId, String code) {
        return TreeUtils.build(userOrganizationMapper.queryTreeListView(tenantId, code));
    }

    public UserOrganization selectTopOrganization(String tenantId) {
        return userOrganizationMapper.selectTopOrganization(tenantId);
    }

    public String getOrgNamesByCode(String[] codes) {

        return userOrganizationMapper.getOrgNamesByCode(codes);
    }

    public List<TreeModel> getOrganizationTreeWithInd(String tenantId) {
        List<TreeModel> treeModelList = TreeUtils.build(userOrganizationMapper.getOrganizationListWithInd(tenantId));

        return treeModelList;
    }

    public UserOrganization getUserSelfOrganization(Long id) {
        return userOrganizationMapper.getUserSelfOrganization(id);
    }

}
