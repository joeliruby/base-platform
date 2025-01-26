package com.matariky.commonservice.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.matariky.annotation.DataScope;
import com.matariky.commonservice.base.bean.BasicBasePcVersion;
import com.matariky.commonservice.base.vo.BasicBasePcVersionListVO;
import com.matariky.commonservice.base.vo.BasicBasePcVersionQueryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Persistence Interface
 *
 * @author AUTOMATION
 */
public interface BasicBasePcVersionMapper extends BaseMapper<BasicBasePcVersion> {
    /**
     *  Query 所有分页
     */
    @DataScope(alias = "p")
    public List<BasicBasePcVersionListVO> getBasicBasePcversionAll(@Param("params") BasicBasePcVersionQueryVO vo);


    /**
     * New方法
     */
    public int createBasicBasePcversion(BasicBasePcVersion bean);


    /**
     * 删除方法
     */
    public int delBasicBasePcversionById(Long id);



}
