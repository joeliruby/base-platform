package com.matariky.commonservice.message.mapper;

import com.matariky.commonservice.message.bean.UserMessage;
import com.matariky.mybatis.EnhanceBaseMapper;

/**
 * @description: 用户消息mapper
 * @author: bo.chen
 * @create: 2023/10/19 10:37
 **/
public interface UserMessageMapper extends EnhanceBaseMapper<UserMessage> {

    /**
     * @Description: 保存或修改
     * @Author: bo.chen
     * @Date: 2023/11/16 10:35
     * @param userMessage
     * @return int
     **/
    int saveOrUpdate(UserMessage userMessage);
}
