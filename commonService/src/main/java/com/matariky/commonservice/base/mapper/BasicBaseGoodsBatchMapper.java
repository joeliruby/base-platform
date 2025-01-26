package com.matariky.commonservice.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.matariky.annotation.DataScope;
import com.matariky.commonservice.base.bean.BasicBaseGoodsBatch;
import com.matariky.commonservice.base.vo.BasicBaseGoodsBatchListVO;
import com.matariky.commonservice.base.vo.BasicBaseGoodsBatchResVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Persistence Interface
 *
 * @author AUTOMATION
 */
public interface BasicBaseGoodsBatchMapper extends BaseMapper<BasicBaseGoodsBatch> {

    /**
     *  Query 所有分页
     *
     * @param vo
     * @return
     */
    @DataScope(alias = "t")
    public List<BasicBaseGoodsBatchResVO> getBasicBaseGoodsBatchAll(@Param("params") BasicBaseGoodsBatchListVO vo);


    /**
     * New方法
     *
     * @param bean
     * @return
     */
    public int createBasicBaseGoodsBatch(BasicBaseGoodsBatch bean);


    /**
     * 修改方法
     *
     * @param bean
     * @return
     */
    public int updateBasicBaseGoodsBatch(@Param("params") BasicBaseGoodsBatch bean);


    /**
     * 删除方法
     */
    public int delBasicBaseGoodsBatchById(@Param("id") Long id);


    /**
     * 根据ID Query 对象的方法
     *
     * @param id
     * @return
     */
    public BasicBaseGoodsBatchResVO getBasicBaseGoodsBatchById(@Param("id") Long id);


}
