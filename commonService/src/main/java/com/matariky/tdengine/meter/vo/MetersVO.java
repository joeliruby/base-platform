package com.matariky.tdengine.meter.vo;

import lombok.Data;

import java.util.Date;


@Data
public class MetersVO {

    private Date ts;

    private Float current;

    private Integer voltage;

    private Float phase;

    private String code;

}
