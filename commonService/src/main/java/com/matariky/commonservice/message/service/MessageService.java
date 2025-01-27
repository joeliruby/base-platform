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

@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMessageMapper userMessageMapper;

    public List<MessageVo> getLastMessageList(QueryDataIsolation params, int limit) {
        List<Message> messageList = messageMapper.selectList(params,
                messageMapper.qw().orderByDesc(Message::getCreateTime).last("limit " + limit));
        if (CollectionUtils.isNotEmpty(messageList)) {
            return BeanUtils.copyProperties(messageList, MessageVo.class);
        }
        return Collections.emptyList();
    }

    public Page<MessageVo> getPage(QueryMessageParam params) {
        return messageMapper.selectMessagePage(new Page<MessageVo>(params.getIndex(), params.getPerPage()), params);
    }

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
