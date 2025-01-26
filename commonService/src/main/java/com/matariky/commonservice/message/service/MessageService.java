package com.matariky.commonservice.message.service;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.matariky.commonservice.message.bean.Message;
import com.matariky.commonservice.message.bean.UserMessage;
import com.matariky.commonservice.message.mapper.MessageMapper;
import com.matariky.commonservice.message.mapper.UserMessageMapper;
import com.matariky.commonservice.message.param.QueryMessageParam;
import com.matariky.commonservice.message.vo.MessageVo;
import com.matariky.id.SnowflakeIdWorker;
import com.matariky.model.PrimaryParam;
import com.matariky.model.QueryDataIsolation;
import com.matariky.utils.BeanUtils;
import com.matariky.utils.NumberUtils;

/**
 * @description: 消息服务类
 * @author: bo.chen
 * @create: 2023/10/19 11:17
 **/
@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMessageMapper userMessageMapper;

    /**
     * @Description: 或者最后几条消息
     * @Author: bo.chen
     * @Date: 2023/10/19 13:23
     * @param params
     * @param limit
     * @return java.util.List<com.matariky.commonservice.message.vo.MessageVo>
     **/
    public List<MessageVo> getLastMessageList(QueryDataIsolation params, int limit) {
        List<Message> messageList = messageMapper.selectList(params, messageMapper.qw().orderByDesc(Message::getCreateTime).last("limit "  + limit));
        if (CollectionUtils.isNotEmpty(messageList)) {
            return BeanUtils.copyProperties(messageList, MessageVo.class);
        }
        return Collections.emptyList();
    }

    /**
     * @Description: 分页获取消息
     * @Author: bo.chen
     * @Date: 2023/11/16 10:22
     * @param params
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.matariky.commonservice.message.vo.MessageVo>
     **/
    public Page<MessageVo> getPage(QueryMessageParam params) {
        return messageMapper.selectMessagePage(new Page<MessageVo>(params.getIndex(), params.getPerPage()), params);
    }

    /**
     * @Description: 设置已读
     * @Author: bo.chen
     * @Date: 2023/11/16 10:24
     * @param param
     **/
    public void setRead(PrimaryParam param) {
        if (NumberUtils.INTEGER_TWO.equals(param.getApplication())) {
            UserMessage userMessage = new UserMessage();
            userMessage.setId(SnowflakeIdWorker.getId());
            userMessage.setUserId(param.getUserId());
            userMessage.setMessageId(param.getId());
            userMessage.setIsRead(Boolean.TRUE);
            userMessageMapper.saveOrUpdate(userMessage);
        }
    }
}
