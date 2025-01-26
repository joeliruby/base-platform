package com.matariky.commonservice.message.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 用户消息实体类
 * @author: bo.chen
 * @create: 2023/10/19 10:33
 **/
@Data
@TableName("common_message")
public class Message implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    private Long id;

    /**
     * 操作人机构编码
     */
    private String operatorOrgCode;

    /**
     * 操作人自己机构编码
     */
    private String operatorSelfOrgCode;

    /**
     *  Tenant 编码
     */
    private String tenantId;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息Type 
     */
    private Integer msgType;

    /**
     * 对象id
     */
    private String target;


    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 修改人
     */
    private Long updatedBy;

    /**
     * 创建 Time 
     */
    private Long createTime;

    /**
     * 修改 Time 
     */
    private Long updateTime;

    /**
     * 删除 Time 
     */
    private Long deleteTime;
}
