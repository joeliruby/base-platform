package com.matariky.tdengine.meter.bean;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Meters {

    private Timestamp ts;

    private Float current;

    private Integer voltage;

    private Float phase;

    private String code;

}
