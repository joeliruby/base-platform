package com.matariky.commonservice.message.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.matariky.annotation.DataScope;
import com.matariky.commonservice.message.bean.Message;
import com.matariky.commonservice.message.param.QueryMessageParam;
import com.matariky.commonservice.message.vo.MessageVo;
import com.matariky.mybatis.EnhanceBaseMapper;
import org.apache.ibatis.annotations.Param;

public interface MessageMapper extends EnhanceBaseMapper<Message> {

    @DataScope
    Page<MessageVo> selectMessagePage(Page<?> page, @Param("params") QueryMessageParam params);
}
