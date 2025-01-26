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
     * 分页列表
     *
     * @param bean
     * @return
     */
    @DataScope(alias = "p")
    public List<BasicBaseAntifakeDetail> getBasicBaseAntifakeDetailAll(BasicBaseAntifakeDetail bean);


    /**
     * New方法
     *
     * @param bean
     * @return
     */
    public int createBasicBaseAntifakeDetail(BasicBaseAntifakeDetail bean);


    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int delBasicBaseAntifakeDetailById(@Param("id") Long id);

    /**
     * 详情
     *
     * @param id
     * @return
     */
    public BasicBaseAntifakeDetail getBasicBaseAntifakeDetailById(@Param("id") Long id);


}
