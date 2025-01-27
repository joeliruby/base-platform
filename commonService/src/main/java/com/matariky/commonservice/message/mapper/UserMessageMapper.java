package com.matariky.commonservice.message.mapper;

import com.matariky.commonservice.message.bean.UserMessage;
import com.matariky.mybatis.EnhanceBaseMapper;

public interface UserMessageMapper extends EnhanceBaseMapper<UserMessage> {

    int saveOrUpdate(UserMessage userMessage);
}
