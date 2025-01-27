package com.matariky.commonservice.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.matariky.annotation.DataScope;
import com.matariky.commonservice.base.bean.BasicBaseAntifakeDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Persistence Interface
 *
 * @author AUTOMATION
 */
public interface BasicBaseAntifakeDetailMapper extends BaseMapper<BasicBaseAntifakeDetail> {

    /**
     * Pagination 
     *
     * @param bean
     * @return
     */
    @DataScope(alias = "p")
    public List<BasicBaseAntifakeDetail> getBasicBaseAntifakeDetailAll(BasicBaseAntifakeDetail bean);


    /**
     * New  Method  
     *
     * @param bean
     * @return
     */
    public int createBasicBaseAntifakeDetail(BasicBaseAntifakeDetail bean);


    /**
     * Delete 
     *
     * @param id
     * @return
     */
    public int delBasicBaseAntifakeDetailById(@Param("id") Long id);

    /**
     *   Detail 
     *
     * @param id
     * @return
     */
    public BasicBaseAntifakeDetail getBasicBaseAntifakeDetailById(@Param("id") Long id);


}
