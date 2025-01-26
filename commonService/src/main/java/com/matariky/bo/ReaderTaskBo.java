package com.matariky.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReaderTaskBo implements Serializable {

	private static final long serialVersionUID = 1L;

    private Long taskId;

    private Integer type;

    private Integer expirationTime;

    private String brandModel;

    private Integer application;
}
