package com.matariky.userservice.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.userservice.bean.TreeModel;
import com.matariky.userservice.bean.User;
import com.matariky.userservice.bean.UserOrganization;
import com.matariky.userservice.bean.UserTenant;
import com.matariky.userservice.mapper.OrganizationMapper;
import com.matariky.userservice.mapper.UserGroupMapper;
import com.matariky.userservice.mapper.UserMapper;
import com.matariky.userservice.mapper.UserOrganizationMapper;
import com.matariky.userservice.mapper.UserRoleMapper;
import com.matariky.userservice.mapper.UserTenantMapper;
import com.matariky.utils.DateUtil;
import com.matariky.utils.EncryptionUtils;
import com.matariky.utils.OrgCodeUtil;
import com.matariky.utils.TokenUtils;
import com.matariky.utils.TreeUtils;
import com.matariky.utils.ValidatorUtils;

import cn.hutool.core.collection.CollUtil;
import jodd.util.StringUtil;

/**
 * @date 20:52
 */
@Service("UserService")
public class UserService extends BaseServiceImpl<UserMapper, User> implements BaseService<User> {

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrganizationMapper organizationMapper;

    @Autowired
    UserTenantMapper tenantMapper;

    @Autowired
    UserOrganizationMapper userOrganizationMapper;

    @Autowired
    UserRoleMapper roleMapper;

    @Autowired
    UserGroupMapper groupMapper;

    @Value("${admin.pazzword}")
    private String pazzword;

    @Value("${message.locale}")
    private String defaultLocale;

    public User findByUsername(String loginName) {
        return userMapper.selectByName(loginName);
    }

    public User findUserById(String userId) {

        return userMapper.selectByPrimaryKey(Long.valueOf(userId));
    }

    public int createUserAndTenantAndOrganization(Map<String, Object> map) {
        return userMapper.createUserAndTenantAndOrganization(map);
    }

    public List<User> searchByUserNamePrefix(String tenantId, String userNamePrefix) {

        return userMapper.searchByUserNamePrefix(tenantId, userNamePrefix);
    }

    public Page<User> getUserAll(Map<String, Object> map) {
        return userMapper.getUserAll(map);
    }

    public int createUser(User bean) {
        return userMapper.createUser(bean);
    }

    public int updateUser(User bean) {
        return userMapper.updateUser(bean);
    }

    public int delUserById(int id) {
        return userMapper.delUserById(id);
    }

    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    public List<User> selectByMap(Map<String, Object> columnMap) {
        return userMapper.selectByMap(columnMap);
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(User bean) {
        // 校验Type
        ValidatorUtils.validateEntity(bean);

        // Password 加密

        newUserDefaults(bean);

        // tenant_id 默认 Tenant ID
        // organization_id 默认组织机构ID
        // self_organization_code User 自身组织机构 Code
        // department_organization_code User 所在部门组织机构 Code
        // Save User
        userMapper.insert(bean);

        // Save 角色 User 关系
        saveOrUpdateRole(bean.getId(), bean.getRoleIdList());

        // Save 分组关系
        saveOrUpdateGroup(bean.getId(), bean.getGroupIdList());

        // Save User 和 Tenant 的关联 和 Save 机构 User 关系 (两个中间表合在一起了)
        saveOrUpdateTenantAndOrganization(bean);

        // 组织机构 Code +ind+ User Primary Key
        String selfOrganizationCode = null;
        String departmentOrganizationCode = bean.getDepartmentOrganizationCode();
        if (departmentOrganizationCode.contains(",")) {// 多个组织

            String[] departmentOrgCodes = departmentOrganizationCode.split(",");

            for (int i = 0; i < departmentOrgCodes.length; i++) {

                // 还要 Add 到组织表 Code Rule 开头加 ind_
                UserOrganization organization = new UserOrganization();

                // Create 组织
                createOrgUser(departmentOrgCodes[i], organization, bean);

                String orgCode = null;

                // 逗号隔开用于 User 表冗余
                if (i == departmentOrgCodes.length - 1) {
                    selfOrganizationCode = OrgCodeUtil.generateSelfOrganizationCode(departmentOrgCodes[i],
                            bean.getId());
                } else {
                    selfOrganizationCode = OrgCodeUtil.generateSelfOrganizationCode(departmentOrgCodes[i], bean.getId())
                            + ",";
                }

                orgCode = OrgCodeUtil.generateSelfOrganizationCode(departmentOrgCodes[i], bean.getId());

                // Update组织 Code
                userOrganizationMapper.updateOrganizationCodeById(organization.getId(), orgCode.toString());
            }
        } else {// Single 组织

            UserOrganization organization = new UserOrganization();

            createOrgUser(departmentOrganizationCode, organization, bean);

            selfOrganizationCode = OrgCodeUtil.generateSelfOrganizationCode(departmentOrganizationCode, bean.getId());
            // Update组织 Code
            userOrganizationMapper.updateOrganizationCodeById(organization.getId(), selfOrganizationCode.toString());
        }

        // 然后去 Update User 表的 User 自身 Code
        if (!StringUtils.isEmpty(selfOrganizationCode))
            userMapper.updateSelfOrganizationCodeById(bean.getId(), selfOrganizationCode);

    }

    public void createOrgUser(String code, UserOrganization organization, User bean) {
        // Name
        organization.setOrganizationName(bean.getRealName());

        // Create Time
        organization.setCreateTime(DateUtil.getCurrentDateAndTime().getTime());

        // Tenant id
        organization.setTenantId(bean.getTenantId());

        // 联系人 Name
        organization.setLiaisonName(bean.getRealName());

        // 联系人电话号码
        organization.setLiaisonMobile(bean.getCellPhone());

        organization.setOrgType(4);// 人员Type

        // 上级组织id
        UserOrganization selectByOrgCode = userOrganizationMapper.selectByOrgCode(code);

        if (selectByOrgCode != null) {
            organization.setParentId(selectByOrgCode.getId());
        }
        organization.setOrganizationCode("0");
        userOrganizationMapper.insert(organization);

    }

    public void newUserDefaults(User user) {
        String hash3 = EncryptionUtils.getHash3(pazzword, "SHA");
        user.setPazzword(hash3);
        user.setCreateTime(DateUtil.getCurrentDateAndTime().getTime());
        user.setLoginCount(0);
        user.setCreateTime(System.currentTimeMillis());
        user.setCreatedBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(
                ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest())));
        user.setDeleteTime(0l);
        user.setIsActive(true);
        user.setLocale(defaultLocale);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(User bean) {
        bean.setUpdateTime(DateUtil.getCurrentDateAndTime().getTime());

        // 不改 Password

        // 组织机构 Code +ind+ User Primary Key
        String selfOrganizationCode = null;
        String departmentOrganizationCode = bean.getDepartmentOrganizationCode();

        // String selfOrgCode = bean.getSelfOrganizationCode();
        String selfOrgCode = OrgCodeUtil.generateSelfOrganizationCode(departmentOrganizationCode, bean.getId());

        if (!StringUtils.isEmpty(selfOrgCode) && selfOrgCode.contains(",")) {
            String[] split = selfOrgCode.split(",");

            for (int i = 0; i < split.length; i++) {
                String string = split[i];

                Map<String, Object> columnMap = new HashMap<>();

                columnMap.put("organization_code", string);
                userOrganizationMapper.deleteByMap(columnMap);

            }
        } else {
            Map<String, Object> columnMap = new HashMap<>();
            columnMap.put("organization_code", selfOrgCode);
            // userOrganizationMapper.deleteByMap(columnMap);
        }

        if (departmentOrganizationCode.contains(",")) {
            String[] departmentOrganizationCodes = departmentOrganizationCode.split(",");
            for (int i = 0; i < departmentOrganizationCodes.length; i++) {// 多个组织机构 Add

                UserOrganization organization = new UserOrganization();

                // Create 组织
                createOrgUser(departmentOrganizationCodes[i], organization, bean);

                //
                String orgCode = null;

                if (i == departmentOrganizationCodes.length - 1) {
                    selfOrganizationCode = OrgCodeUtil.generateSelfOrganizationCode(departmentOrganizationCodes[i],
                            bean.getId());
                } else {
                    selfOrganizationCode = OrgCodeUtil.generateSelfOrganizationCode(departmentOrganizationCodes[i],
                            bean.getId()) + ",";
                }

                orgCode = OrgCodeUtil.generateSelfOrganizationCode(departmentOrganizationCodes[i], bean.getId());

                // Update组织 Code
                userOrganizationMapper.updateOrganizationCodeById(organization.getId(), orgCode.toString());

            }
        } else {

            // UserOrganization organization=new UserOrganization();

            // createOrgUser(departmentOrganizationCode,organization,bean);

            selfOrganizationCode = OrgCodeUtil.generateSelfOrganizationCode(departmentOrganizationCode, bean.getId());
            // Update组织 Code

            UserOrganization parentUserOrgan = userOrganizationMapper.selectByOrgCode(departmentOrganizationCode);

            userOrganizationMapper.updateOrganizationCodeAndParentIdById(bean.getId(), selfOrganizationCode.toString(),
                    parentUserOrgan.getId());

        }

        bean.setSelfOrganizationCode("");
        // Update User
        userMapper.updateById(bean);

        if (!StringUtil.isEmpty(selfOrganizationCode))
            userMapper.updateSelfOrganizationCodeById(bean.getId(), selfOrganizationCode);

        // Save 角色 User 关系
        saveOrUpdateRole(bean.getId(), bean.getRoleIdList());

        // Save 分组关系
        saveOrUpdateGroup(bean.getId(), bean.getGroupIdList());

        // Save User 和 Tenant 的关联 和 Save 机构 User 关系 (两个中间表合在一起了)
        saveOrUpdateTenantAndOrganization(bean);
    }

    // Save User 和 Tenant 中间表 Data
    public void saveOrUpdateTenantAndOrganization(User bean) {
        // 先Delete 组户和 User 的关系
        deleteTenantAndOrganizationByUserIds(new Long[] { bean.getId() });

        List<String[]> organizationCodeList = bean.getOrganizationCodeList();

        if (CollUtil.isNotEmpty(organizationCodeList)) {
            for (int i = 0; i < organizationCodeList.size(); i++) {
                String[] codes = organizationCodeList.get(i);
                String last = codes[codes.length - 1];
                UserOrganization selectByOrgCode = userOrganizationMapper.getOrganizationByCode(last,
                        bean.getTenantId());

                Map<String, Object> map = new HashMap<>();

                map.put("user_id", bean.getId());// User id
                map.put("tenant_code", bean.getTenantId());// Tenant Code
                map.put("organization_id", selectByOrgCode == null ? null : selectByOrgCode.getId());// 组织机构ID
                map.put("organization_code", last);// 组织机构 Code
                UserTenant selectBytenantCode = tenantMapper.selectByTenantCode(bean.getTenantId());
                map.put("tenant_id", selectBytenantCode.getId());// Tenant ID
                map.put("self_organization_code", bean.getSelfOrganizationCode());// 个人组织机构 Code

                // 插入 Data
                userMapper.saveTenantAndOrganization(map);
            }

        }

    }

    // Save User 和组中间表的 Data
    public void saveOrUpdateGroup(Long userId, List<Long> groupIdList) {

        // 先Delete 组和 User 的关系
        deleteGroupByUserIds(new Long[] { userId });

        // User 没有 one 组的情况
        if (CollUtil.isEmpty(groupIdList)) {
            return;
        }

        // Save 组和 User 关系
        for (Long groupId : groupIdList) {
            userMapper.saveRGroupUser(userId, groupId);
        }
    }

    // Save User 和角色中间表的 Data
    public void saveOrUpdateRole(Long userId, List<Long> roleIdList) {

        // 先Delete 角色和 User 的关系
        deleteRoleByUserIds(new Long[] { userId });

        // User 没有 one 角色的情况
        if (CollUtil.isEmpty(roleIdList)) {
            return;
        }

        // Save 组和 User 关系
        for (Long roleId : roleIdList) {
            userMapper.saveRRoleUser(userId, roleId);
        }
    }

    public void deleteRoleByUserIds(Long[] userIds) {
        userMapper.deleteRoleByUserIds(userIds);
    }

    public void deleteGroupByUserIds(Long[] userIds) {
        userMapper.deleteGroupByUserIds(userIds);
    }

    private void deleteTenantAndOrganizationByUserIds(Long[] userIds) {
        userMapper.deleteTenantAndOrganizationByUserIds(userIds);
    }

    public List<Long> getRoleIdList(Long id) {

        return userMapper.getRoleIdList(id);
    }

    public List<Long> getGroupIdList(Long id) {
        return userMapper.getGroupIdList(id);
    }

    public void updateDeleteTimeById(String[] id) {
        //
        for (int i = 0; i < id.length; i++) {
            String string = id[i];
            User bean = userMapper.selectById(string);

            String selfOrgCode = bean.getSelfOrganizationCode();

            if (selfOrgCode.contains(",")) {
                String[] split = selfOrgCode.split(",");

                for (int j = 0; j < split.length; j++) {
                    String s = split[j];

                    Map<String, Object> columnMap = new HashMap<>();

                    columnMap.put("organization_code", s);
                    columnMap.put("tenant_id", bean.getTenantId());
                    userOrganizationMapper.deleteByMap(columnMap);// Delete 组织表
                }
            } else {
                Map<String, Object> columnMap = new HashMap<>();
                columnMap.put("organization_code", selfOrgCode);
                columnMap.put("tenant_id", bean.getTenantId());
                userOrganizationMapper.deleteByMap(columnMap);// Delete 组织表
            }
        }

        userMapper.updateDeleteTimeById(id);
    }

    public List<Map<String, Object>> getApplicationByUser(Long id, Serializable tenantId) {
        return userMapper.getApplicationByUser(id, tenantId);
    }

    public List<TreeModel> getPermissionByUser(Long id, String tenantId, Long applicationId) {

        return TreeUtils.build(userMapper.getPermissionByUser(id, tenantId, applicationId));
    }

    public List<TreeModel> getPermissionByUserNoTree(Long id, String tenantId, Long applicationId) {

        return userMapper.getPermissionByUser(id, tenantId, applicationId);
    }

    public List<TreeModel> getPermissionByUserAndRoleAndGroup(Long id, String tenantId, Long applicationId) {

        return TreeUtils.build(userMapper.getPermissionByUserAndRoleAndGroup(id, tenantId, applicationId));
    }

    public List<TreeModel> getPermissionByUserAndRoleAndGroupNoTree(Long id, String tenantId, Long applicationId) {

        return userMapper.getPermissionByUserAndRoleAndGroup(id, tenantId, applicationId);
    }

    public int insertSelective(User user) {

        return userMapper.insertSelective(user);
    }

    public List<Map<String, Object>> getUserAllWithRoleAndGroup(Map<String, Object> map) {
        return userMapper.getUserAllWithRoleAndGroup(map);
    }

    public int getCountByOrganizationId(Long[] organizationIds, String tenantId) {

        return userMapper.getCountByOrganizationId(organizationIds, tenantId);
    }

    public void insertUserTenantRelation(Long id, String tenantCode, Long organizationId, String organizationCode,
            boolean b, Long tenantId, String selfOrganizationCode) {
        userMapper.insertUserTenantRelation(id, tenantCode, organizationId, organizationCode, b, tenantId,
                selfOrganizationCode, null);
    }

    public void updatePassword(Long userId, String newPassword) {
        userMapper.updatePassword(userId, newPassword);
    }

    public List<Map<String, Object>> getUserAllWithRoleAndGroupByIds(Map<String, String> map) {
        String[] ids = ((String) map.get("ids")).split(",");
        Long[] lids = new Long[ids.length];
        for (int i = 0; i < ids.length; i++) {
            lids[i] = Long.parseLong(ids[i]);
        }
        return userMapper.getUserAllWithRoleAndGroupByIds(lids);
    }

    public void forcedUpdate(User fetched) {
        userMapper.updateById(fetched);
        roleMapper.deleteByUserId(fetched.getId());
        groupMapper.deleteByUserId(fetched.getId());
        for (Long roleId : fetched.getRoleIdList()) {
            roleMapper.insertUserRoleRelation(fetched.getId(), roleId);
        }
        for (Long groupId : fetched.getGroupIdList()) {
            groupMapper.insertUserGroupRelation(fetched.getId(), groupId);
        }
    }

    public List<Long> getUsersByRoleId(Long id) {
        return groupMapper.getUsersByRoleId(id);
    }

    public void insertUserRoleRelation(Long userId, Long roleId) {
        roleMapper.insertUserRoleRelation(userId, roleId);
    }

    public Object getAdminByTenantCode(String tenantCode) {
        Long adminUserId = tenantMapper.getAdminUserIdByTenantCode(tenantCode);
        if (adminUserId == null || adminUserId == 0) {
            JSONObject jo = new JSONObject();
            jo.put("exist", false);
            return jo;
        }
        return selectById(adminUserId);
    }

    public void deleteUserTenantRelation(Long userId, String tenantCode, int isAdmin) {
        userMapper.deleteUserTenantRelation(userId, tenantCode, isAdmin);
    }

    public List<Long> getPermissionIdByUserIdR(Long userId) {

        return userMapper.getPermissionIdByUserIdR(userId);
    }

    public Integer isAdmin(Long id) {
        return userMapper.isAdmin(id);
    }

    public List<Map> getIOTUsers(String tenantId) {
        return userMapper.getIOTUsers(tenantId);
    }

    public Integer isIOTUser(Long id) {
        return userMapper.isIOTUser(id);
    }

    public List<String> getRoleNameList(Long id) {
        return userMapper.getRoleNameList(id);
    }

    public List<String> getGourpNameList(Long id) {
        return userMapper.getGourpNameList(id);
    }

    public List<String> getApplicationNameList(Long id) {
        return userMapper.getApplicationNameList(id);
    }

}
