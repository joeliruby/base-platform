package com.matariky.userservice.controller;

import com.matariky.annotation.RequirePermission;
import com.matariky.annotation.VerifyTenantId;
import com.matariky.userservice.bean.UserGroup;
import com.matariky.userservice.service.UserGroupService;
import com.matariky.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/tenant/{tenantId}")
@Component
public class GroupController {
    @Autowired
    UserGroupService groupService;

    //	@UserLoginToken
    @GetMapping("/group/")
    @RequirePermission
    @VerifyTenantId
    public AjaxResult searchGroupByNamePrefix(@PathVariable("tenantId") String tenantId, @RequestParam("filter") String groupNamePrefix, String jwt) {
        List<UserGroup> groupList = groupService.searchByGroupNamePrefix(tenantId, groupNamePrefix);
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        for (UserGroup group : groupList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", group.getGroupName());
            map.put("id", group.getId().toString());
            map.put("value", group.getId().toString());
            map.put("org_code", group.getOrgCode());
            mapList.add(map);
        }
        returnMap.put("start", 0);
        returnMap.put("size", mapList.size());
        returnMap.put("total", mapList.size());
        returnMap.put("data", mapList);

        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, returnMap);
    }


}
