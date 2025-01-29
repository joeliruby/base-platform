package com.matariky.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class PageUtilsTest {

    @InjectMocks
    private PageUtils pageutils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPageInfoWithList() {
        // Given
        List<String> dataList = Arrays.asList("item1", "item2", "item3");
        long count = 3;
        int pageIndex = 1;
        int pageSize = 2;

        // When
        PageInfo<String> pageInfo = PageUtils.getPageInfo(dataList, count, pageIndex, pageSize);

        // Then
        assertThat(pageInfo.getList()).isEqualTo(dataList);
        assertThat(pageInfo.getTotal()).isEqualTo(count);
        assertThat(pageInfo.getPageSize()).isEqualTo(pageSize);
        assertThat(pageInfo.getPageNum()).isEqualTo(pageIndex);
        assertThat(pageInfo.getPages()).isEqualTo(2);
    }

    @Test
    void testGetPageInfoWithPage() {
        // Given
        Page<String> page = new Page<>(1, 2);
        page.setRecords(Arrays.asList("item1", "item2"));
        page.setTotal(3);

        // When
        PageInfo<String> pageInfo = PageUtils.getPageInfo(page);

        // Then
        assertThat(pageInfo.getList()).isEqualTo(page.getRecords());
        assertThat(pageInfo.getTotal()).isEqualTo(page.getTotal());
        assertThat(pageInfo.getPageSize()).isEqualTo((int) page.getSize());
        assertThat(pageInfo.getPageNum()).isEqualTo((int) page.getCurrent());
        assertThat(pageInfo.getPages()).isEqualTo((int) page.getPages());
    }

    // Add more test methods for other methods in PageUtils
}
