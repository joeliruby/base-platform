package com.matariky.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

/**
 * @description: Paging tool class
 * @author: bo.chen
 * @create: 2023/9/6 10:13
 **/
public class PageUtils {

    /**
     * @Description: Encapsulate pageinfo object
     * @Author: bo.chen
     * @Date: 2023/9/6 11:05
     * @param dataList
     * @param count
     * @param pageIndex
     * @param pageSize
     * @return com.github.pagehelper.PageInfo
     **/
    public static PageInfo getPageInfo(List dataList, long count, int pageIndex, int pageSize) {
        PageInfo pageInfo = new PageInfo(dataList);
        pageInfo.setTotal(count);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageIndex);
        pageInfo.setPages((int) count / pageSize);
        if (count % pageSize > NumberUtils.INTEGER_ZERO) {
            pageInfo.setPages(pageInfo.getPageSize() + NumberUtils.INTEGER_ONE);
        }
        pageInfo.setPages(Integer.parseInt(new Long(count % new Long(pageSize) == 0 ? count % new Long(pageSize) : count % new Long(pageSize) + 1).toString()));
        return pageInfo;
    }

    /**
     * @Description: page convert pageInfo
     * @Author: bo.chen
     * @Date: 2023/9/12 20:16
     * @param page
     * @return com.github.pagehelper.PageInfo
     **/
    public static PageInfo getPageInfo(Page page) {
        PageInfo pageInfo = new PageInfo(page.getRecords());
        pageInfo.setTotal(page.getTotal());
        pageInfo.setPageSize((int)page.getSize());
        pageInfo.setPageNum((int)page.getCurrent());
        pageInfo.setPages((int)page.getPages());
        return pageInfo;
    }

    private PageUtils() {

    }

}
