package com.matariky.commonservice.message.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.matariky.annotation.DataScope;
import com.matariky.commonservice.message.bean.Message;
import com.matariky.commonservice.message.param.QueryMessageParam;
import com.matariky.commonservice.message.vo.MessageVo;
import com.matariky.mybatis.EnhanceBaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @description: 消息mapper
 * @author: bo.chen
 * @create: 2023/10/19 10:37
 **/
public interface MessageMapper extends EnhanceBaseMapper<Message> {


    /**
     * @Description: 分页获取消息 Data 
     * @Author: bo.chen
     * @Date: 2023/11/16 10:10
     * @param page
     * @param params
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.matariky.commonservice.message.vo.MessageVo>
     **/
    @DataScope
    Page<MessageVo> selectMessagePage(Page<?> page, @Param("params") QueryMessageParam params);
}
