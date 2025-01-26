package com.matariky.mybatis;

import cn.hutool.core.lang.func.Func1;
import cn.hutool.core.lang.func.LambdaUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.matariky.annotation.DataScope;
import com.matariky.model.QueryDataIsolation;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.reflection.property.PropertyNamer;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description: Basic mapper enhancement class
 * @author: bo.chen
 * @create: 2023/9/06 9:37
 **/
public interface EnhanceBaseMapper<T> extends BaseMapper<T> {

    /**
     * @Description:  Query 集合并且根据权限 Data 隔离
     * @Author: bo.chen
     * @Date: 2023/10/19 11:28
     * @param dataIsolation
     * @param queryWrapper
     * @return java.util.List<T>
     **/
    @DataScope(alias = StringUtils.EMPTY, paramName = "dataIsolation")
    List<T> selectList(@Param("dataIsolation") QueryDataIsolation dataIsolation, @Param("ew") Wrapper<T> queryWrapper);

    /**
     * @Description: Query results and convert them into maps
     * @Author: bo.chen
     * @Date: 2023/9/06 9:53
     * @param ids
     * @param value
     * @return java.util.Map<K, V>
     **/
    default <K, V> Map<K, V> selectToMap(Collection<Long> ids, SFunction<T, K> key, SFunction<T, V> value) {
        List<T> list =  this.selectList(new QueryWrapper<T>().in("id", ids)
                .select(StringUtils.camelToUnderline(PropertyNamer.methodToProperty(LambdaUtils.extract(key).getImplMethodName())),
                StringUtils.camelToUnderline(PropertyNamer.methodToProperty(LambdaUtils.extract(value).getImplMethodName()))));
        Map<K, V> map;
        if (CollectionUtils.isNotEmpty(list)) {
            map = list.stream().collect(Collectors.toMap(key , value, (k1, k2) -> k1));
        } else {
            map = Collections.emptyMap();
        }
        return map;
    }

    /**
     * @Description: Query results and convert them into maps
     * @Author: bo.chen
     * @Date: 2023/9/06 9:53
     * @param ids
     * @return java.util.Map<K, V>
     **/
    default <K> Map<K, T> selectToMap(Collection<Long> ids, SFunction<T, K> key) {
        List<T> list =  this.selectList(new QueryWrapper<T>().in("id", ids));
        Map<K, T> map;
        if (CollectionUtils.isNotEmpty(list)) {
            map = list.stream().collect(Collectors.toMap(key , Function.identity(), (k1, k2) -> k1));
        } else {
            map = Collections.emptyMap();
        }
        return map;
    }

    /**
     * @Description: Query results and convert them into maps
     * @Author: bo.chen
     * @Date: 2023/9/06 9:53
     * @param queryWrapper
     * @param key
     * @param value
     * @return java.util.Map<K, V>
     **/
    default <K, V> Map<K, V> selectToMap(LambdaQueryWrapper<T> queryWrapper, SFunction<T, K> key, SFunction<T, V> value) {
       List<T> list =  this.selectList(queryWrapper.select(key, value));
       Map<K, V> map;
       if (CollectionUtils.isNotEmpty(list)) {
           map = list.stream().collect(Collectors.toMap(key , value, (k1, k2) -> k1));
       } else {
           map = new HashMap<>();
       }
       return map;
    }

    /**
     * @Description: Query results and convert them into maps
     * @Author: bo.chen
     * @Date: 2023/9/06 9:53
     * @param queryWrapper
     * @param key
     * @return java.util.Map<K, T>
     **/
    default <K> Map<K, T> selectToMap(Wrapper<T> queryWrapper, SFunction<T, K> key) {
        List<T> list = this.selectList(queryWrapper);
        Map<K, T> map;
        if (CollectionUtils.isNotEmpty(list)) {
            map = list.stream().collect(Collectors.toMap(key , Function.identity(), (k1, k2) -> k1));
        } else {
            map = new HashMap<>();
        }
        return map;
    }

    /**
     * @Description: Get query wrapper object
     * @Author: bo.chen
     * @Date: 2023/9/06 11:29
     * @return com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<T>
     **/
    default LambdaQueryWrapper<T> qw() {
        return new LambdaQueryWrapper<T>();
    }

    /**
     * @Description: Obtain and modify the wrapper object
     * @Author: bo.chen
     * @Date: 2023/9/06 11:29
     * @return com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<T>
     **/
    default LambdaUpdateWrapper<T> uw() {
        return new LambdaUpdateWrapper<T>();
    }

    /**
     * @Description: Batch Insert
     * @Author: bo.chen
     * @Date: 2023/9/06 16:22
     * @param list
     * @return int
     **/
    int insertBatchSomeColumn(Collection<T> list);

    /**
     * @Description: Aggregating Query Field Values and Other Fields
     * @Author: bo.chen
     * @Date: 2023/9/06 9:12
     * @param sumColumn 聚合字段
     * @param columns 其他 Query 字段
     * @param consumer 条件处理
     * @return T
     **/
    default <T> List<T> selectSum(Consumer<LambdaQueryWrapper<T>> consumer, Func1<T, ?> sumColumn, Func1<T, ?>... columns) {
        return this.selectSum(consumer, Arrays.asList(sumColumn), columns);
    }


    /**
     * @Description: Aggregating Query Field Values and Other Fields
     * @Author: bo.chen
     * @Date: 2023/9/06 9:12
     * @param sumColumns 聚合字段
     * @param columns 其他 Query 字段
     * @param consumer 条件处理
     * @return T
     **/
    default <T> List<T> selectSum(Consumer<LambdaQueryWrapper<T>> consumer, List<Func1<T, ?>> sumColumns, Func1<T, ?>... columns) {
        List<String>  selectFields = new ArrayList<>();
        for (Func1<T, ?> sumColumn : sumColumns) {
            String field = StringUtils.camelToUnderline(LambdaUtil.getFieldName(sumColumn));
            selectFields.add("sum("+ field +") AS " + field);
        }
        for (Func1<T, ?> column : columns) {
            selectFields.add(StringUtils.camelToUnderline(LambdaUtil.getFieldName(column)));
        }
        LambdaQueryWrapper<T> queryWrapper = new QueryWrapper<T>().select(selectFields.stream().toArray(String[]::new)).lambda();
        consumer.accept(queryWrapper);
        return this.selectList((Wrapper) queryWrapper);
    }

    /**
     * @Description: Aggregate Query Field Values
     * @Author: bo.chen
     * @Date: 2023/9/06 20:09
     * @param column
     * @param consumer
     * @return C
     **/
    default <T, C> C selectSumColumnValue(Consumer<LambdaQueryWrapper<T>> consumer, Func1<T, C> column) {
        String columnName = StringUtils.camelToUnderline(LambdaUtil.getFieldName(column));
        LambdaQueryWrapper<T> queryWrapper = new QueryWrapper<T>().select("sum("+ columnName +") AS " + columnName).lambda();
        consumer.accept(queryWrapper);
        T t = (T) this.selectOne((Wrapper) queryWrapper);
        if (Objects.nonNull(t)) {
            try {
                return column.call(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * @Description: Query Field Value
     * @Author: bo.chen
     * @Date: 2023/9/06 9:38
     * @param queryWrapper
     * @param column
     * @return C
     **/
    default <C> C selectColumnValue(@Param("ew") LambdaQueryWrapper<T> queryWrapper, SFunction<T, C> column) {
        queryWrapper.select(column);
        T t =  this.selectOne(queryWrapper);
        if (Objects.nonNull(t)) {
            return column.apply(t);
        }
        return null;
    }

    /**
     * @Description: Query Field Value
     * @Author: bo.chen
     * @Date: 2023/9/06 9:38
     * @param queryWrapper
     * @param column
     * @return C
     **/
    default <C> List<C> selectColumnValues(@Param("ew") LambdaQueryWrapper<T> queryWrapper, SFunction<T, C> column) {
        queryWrapper.select(column);
        List<T> list =  this.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().map(column).distinct().collect(Collectors.toList());
        }
        return null;
    }

    /**
     * @Description: Query Field Value
     * @Author: bo.chen
     * @Date: 2023/9/06 17:07
     * @param id
     * @param column
     * @return C
     **/
    default <C> C selectColumnValue(Long id, Func1<T, C> column) {
        T t =  this.selectOne(new QueryWrapper<T>().eq("id", id).select(StringUtils.camelToUnderline(LambdaUtil.getFieldName(column))));
        if (Objects.nonNull(t)) {
            try {
                return column.call(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * @Description: Determine whether the data exists based on the conditions
     * @Author: bo.chen
     * @Date: 2023/9/06 14:35
     * @param queryWrapper
     * @return boolean
     **/
    default boolean exists(@Param("ew") Wrapper<T> queryWrapper) {
        Long count =  this.selectCount(queryWrapper);
        return Objects.nonNull(count) && count > NumberUtils.INTEGER_ZERO;
    }

    /**
     * @Description: Query Data Count
     * @Author: bo.chen
     * @Date: 2023/9/06 18:12
     * @param queryWrapper
     * @return int
     **/
    default long selectNCount(@Param("ew") Wrapper<T> queryWrapper) {
        Long count =  this.selectCount(queryWrapper);
        return Objects.nonNull(count) ? count : NumberUtils.INTEGER_ZERO;
    }

    /**
     * @Description: update data
     * @Author: bo.chen
     * @Date: 2023/9/06 19:29
     * @param updateWrapper
     * @return int
     **/
    default  int update(@Param("ew") Wrapper<T> updateWrapper) {
        return this.update(null, updateWrapper);
    }


}
