package com.matariky.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper=false)
public class BaseDataIsolation implements Serializable {

    /**
	 *
	 */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

	/**
     * tenant Code
     */
    @TableField(exist = false)
    private  String tenantId;

    /**
     * 国际化标识
     */
    @TableField(exist = false)
    private String local;

    /**
     *  User id
     */
    @TableField(exist = false)
    private Long userId;

    /**
     *   App 
     */
    @TableField(exist = false)
    private Integer application;
}
