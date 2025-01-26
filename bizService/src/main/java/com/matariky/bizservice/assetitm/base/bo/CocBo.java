package com.matariky.bizservice.assetitm.base.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CocBo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer seqNo;

	private String fromTo;

	private String location;

	private Integer status;

	private String readerCode;

	private String ip;

	private String operator;

	private String remark;

	private Long time;

}
