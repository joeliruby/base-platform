package com.matariky.model;

import com.matariky.commonservice.upload.constant.MessageKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 主要 Parameter 类
 * @author: bo.chen
 * @create: 2023/9/7 16:54
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PrimaryParam extends BaseDataIsolation implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Primary Key
     */
    @NotNull(message = MessageKey.BIZ_TASK_ENDED)
    private Long id;
}
