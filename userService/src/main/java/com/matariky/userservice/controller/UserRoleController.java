package com.matariky.userservice.controller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;

import cn.hutool.core.collection.CollUtil;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;
import com.matariky.userservice.bean.TreeModel;
import com.matariky.userservice.bean.UserApplication;
import com.matariky.userservice.bean.UserRole;
import com.matariky.userservice.service.UserApplicationService;
import com.matariky.userservice.service.UserGroupService;
import com.matariky.userservice.service.UserRoleService;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.DateUtil;
import com.matariky.utils.TokenUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller
 *
 * @author AUTOMATION
 */
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @Value("${message.locale}")
    String locale;

    @Autowired
    CommonDictService commonDictService;

    @Autowired
    UserGroupService userGroupService;

    @Autowired
    UserApplicationService userApplicationService;


     
    @RequestMapping(value = "/userRole/list", method = RequestMethod.GET)
    public Object list(
            HttpServletRequest request,
            @RequestParam Map<String, Object> map,
            @PathVariable("tenantId") String tenantId
            /*@ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt*/) {
        //PageHelper.startPage(pageIndex, perPage);

        map.put("tenantId", TokenUtils.extractTenantIdFromHttpReqeust(request));
        int pageIndex = 1;
        int perPage = 20;
        if (map.containsKey("index")) {
            pageIndex = Integer.parseInt(map.get("index").toString());
        }

        if (map.containsKey("perPage")) {
            perPage = Integer.parseInt(map.get("perPage").toString());
        }
        PageHelper.startPage(pageIndex, perPage);

        String beginst = null;
        String endst = null;

        if (map.containsKey("begin")) {
            beginst = map.get("begin").toString();
        }

        if (map.containsKey("end")) {
            endst = map.get("end").toString();
        }

        //把 Time 转成longType 
        long begin = 0l, end = 0l;
        if (StringUtil.isNotEmpty(beginst)) {
            begin = DateUtil.string2Dateyyyymmdd(beginst).getTime();
        }
        if (StringUtil.isNotEmpty(endst)) {
            end = DateUtil.string2Dateyyyymmdd(endst).getTime();
        }

        if (begin != 0l && begin == end) {
            end = begin + 24 * 60 * 60 * 1000;
            map.put("begin", begin);
            map.put("end", end);
        }

        List<UserRole> userList = userRoleService.getUserRoleAll(map);

        PageInfo<UserRole> page = new PageInfo<UserRole>(userList);
        return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,page);

//        return page;
    }

    @RequestMapping(value = "/userRole/edit", method = RequestMethod.GET)
    public Object edit(HttpServletRequest request, String id) {

        UserRole bean = userRoleService.getUserRoleById(Long.parseLong(id));

        return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,bean);
    }

     
    @RequestMapping(value = "/userRole", method = RequestMethod.POST)
    public Object save(
            @RequestBody @Valid UserRole bean,
            @PathVariable("tenantId") String tenantId,
            HttpServletRequest request,
            HttpServletResponse response) {
        try {

            if (StringUtil.isEmpty(bean.getRoleName())) {
                throw new QslException(MessageKey.ROLE_NAME_NULL);
            }
            Map<String, Object> columnMap = new HashMap<>();
            columnMap.put("role_name", bean.getRoleName());
            columnMap.put("tenant_id", tenantId);
            columnMap.put("delete_time", 0);
            List<UserRole> userRoles = userRoleService.selectByMap(columnMap);
            if (CollUtil.isNotEmpty(userRoles)) {
                throw new QslException(MessageKey.ROLE_NAME_ERR);
            }
            //角色 Name保持唯一
            bean.setCreateTime(DateUtil.getCurrentDateAndTime().getTime());
            bean.setDeleteTime(Long.parseLong("0"));
            bean.setTenantId(tenantId);

            int success = userRoleService.createUserRole(bean);
            if (success > 0) {
            	return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,null);
//                return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
            } else {
                throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
        }
    }

     
    @RequestMapping(value = "/userRole", method = RequestMethod.PUT)
    public Object update(@RequestBody @Valid UserRole bean, HttpServletRequest request, HttpServletResponse response, @PathVariable("tenantId") String tenantId) {
        try {
            if (StringUtil.isEmpty(bean.getRoleName())) {
                throw new QslException(MessageKey.ROLE_NAME_NULL);
            }
            Map<String, Object> columnMap = new HashMap<>();
            columnMap.put("role_name", bean.getRoleName());
            columnMap.put("tenant_id", bean.getTenantId());
            int success = userRoleService.updateUserRole(bean);
            if (success > 0) {
                return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,null);
            } else {
                throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
        }
    }

     
    @RequestMapping(value = "/userRole", method = RequestMethod.DELETE)
    public Object del(String id, HttpServletRequest request, HttpServletResponse response) {
        String[] split = id.split(",");
        int success = userRoleService.updateDeleteTimeById(split);
        if (success > 0) {
            return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,null);
        } else {
            throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
        }
    }

    //角色的下拉框
    @GetMapping("/userRole/box")
    public Object selectRole(@PathVariable("tenantId") String tenantId) {
        List<UserRole> roleList = userRoleService.selectRole(tenantId);
        return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,roleList);
    }


    //角色查看权限详情
    @RequestMapping(value = "/userRole/permission", method = RequestMethod.GET)
    public Object getPermissionByUser(HttpServletRequest request,
                                                         @RequestParam String roleId,
                                                         @PathVariable("tenantId") String tenantId) {

        List<Map<String, Object>> newListMaps = new ArrayList<>();
        QueryWrapper<UserApplication> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tenant_id", tenantId);

        List<UserApplication> userApplicationList = userApplicationService.selectList(queryWrapper);
        
       

        if (CollUtil.isNotEmpty(userApplicationList)) {
            for (UserApplication userApplication : userApplicationList) {
                Map<String, Object> map = new HashMap<>();
                Long applicationId = userApplication.getId();
                map.put("application_name", userApplication.getApplicationName());
                map.put("id", applicationId);
                List<TreeModel> treeList = userRoleService.getPermissionByRole(Long.parseLong(roleId), tenantId, applicationId);
                if (CollUtil.isNotEmpty(treeList)) {
                    map.put("tree", treeList);
                    newListMaps.add(map);
                }
                
                List<Long> list = userRoleService.getPermissionIdByRoleId(Long.parseLong(roleId));

                if (CollUtil.isNotEmpty(list)) {
                    map.put("checkId", list);
                    map.put("hasCheck", true);
                } else {
                    map.put("hasCheck", false);
                }
            }
        }
        
        return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,newListMaps);
//        return newListMaps;
    }


}
