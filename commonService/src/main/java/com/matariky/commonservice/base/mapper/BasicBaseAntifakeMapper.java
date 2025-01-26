package com.matariky.commonservice.base.mapper;

import com.matariky.annotation.DataScope;
import com.matariky.commonservice.base.bean.BasicBaseAntifake;
import com.matariky.mybatis.EnhanceBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Persistence Interface
 *
 * @author AUTOMATION
 */
public interface BasicBaseAntifakeMapper extends EnhanceBaseMapper<BasicBaseAntifake> {

    /**
     *  Query 所有分页
     */
    @DataScope(alias = "a")
    public List<BasicBaseAntifake> getBasicBaseAntifakeAll(@Param("params") BasicBaseAntifake bean);

    /**
     * New方法
     *
     * @param bean
     * @return
     */
    public int createBasicBaseAntifake(BasicBaseAntifake bean);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int delBasicBaseAntifakeById(@Param("id") Long id);

    /**
     * 详情
     *
     * @param id
     */
    public BasicBaseAntifake getBasicBaseAntifakeById(@Param("id") Long id);

}
