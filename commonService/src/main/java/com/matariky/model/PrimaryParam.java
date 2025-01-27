package com.matariky.model;

import com.matariky.commonservice.upload.constant.MessageKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class PrimaryParam extends BaseDataIsolation {

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
