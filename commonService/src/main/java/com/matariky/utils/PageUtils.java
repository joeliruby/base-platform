package com.matariky.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

public class PageUtils {

    public static <T> PageInfo<T> getPageInfo(List<T> dataList, long count, int pageIndex, int pageSize) {
        PageInfo<T> pageInfo = new PageInfo<>(dataList);
        pageInfo.setTotal(count);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageIndex);
        pageInfo.setPages((int) count / pageSize);
        if (count % pageSize > NumberUtils.INTEGER_ZERO) {
            pageInfo.setPages(pageInfo.getPageSize() + NumberUtils.INTEGER_ONE);
        }
        pageInfo.setPages(Integer.parseInt(
                new Long(count % new Long(pageSize) == 0 ? count % new Long(pageSize) : count % new Long(pageSize) + 1)
                        .toString()));
        return pageInfo;
    }

    public static <T> PageInfo<T> getPageInfo(Page<T> page) {
        PageInfo<T> pageInfo = new PageInfo<>(page.getRecords());
        pageInfo.setTotal(page.getTotal());
        pageInfo.setPageSize((int) page.getSize());
        pageInfo.setPageNum((int) page.getCurrent());
        pageInfo.setPages((int) page.getPages());
        return pageInfo;
    }

    private PageUtils() {

    }

}
