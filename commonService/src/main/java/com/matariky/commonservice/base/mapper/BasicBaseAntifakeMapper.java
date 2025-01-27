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
     *  Query   All 
     */
    @DataScope(alias = "a")
    public List<BasicBaseAntifake> getBasicBaseAntifakeAll(@Param("params") BasicBaseAntifake bean);

    /**
     * New  Method  
     *
     * @param bean
     * @return
     */
    public int createBasicBaseAntifake(BasicBaseAntifake bean);

    /**
     * Delete 
     *
     * @param id
     * @return
     */
    public int delBasicBaseAntifakeById(@Param("id") Long id);

    /**
     *   Detail 
     *
     * @param id
     */
    public BasicBaseAntifake getBasicBaseAntifakeById(@Param("id") Long id);

}
