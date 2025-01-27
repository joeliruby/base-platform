package com.matariky.utils;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class ExtendedPageInfo<T> extends PageInfo<T> {
    private String code; // New Field

    public ExtendedPageInfo(List<T> list, int navigatePages, String code) {
        super(list, navigatePages);
        this.code = code;
    }

    // 省略getter和setter Method

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
