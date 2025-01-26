package com.matariky.bizservice.job;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

	private static final long serialVersionUID = 1L;

    private String code;

    private  String message;

    private Boolean success;
}
