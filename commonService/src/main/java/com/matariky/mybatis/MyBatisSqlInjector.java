package com.matariky.mybatis;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;

import java.util.List;

/**
 * @description: Custom SQL injector
 * @author: bo.chen
 * @create: 2023/8/25 16:15
 **/
public class MyBatisSqlInjector extends DefaultSqlInjector  {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        /** 增加批插入  Method   **/
        methodList.add(new InsertBatchSomeColumn());
        return methodList;
    }
}
