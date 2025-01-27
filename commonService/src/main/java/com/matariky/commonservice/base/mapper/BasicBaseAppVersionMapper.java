package com.matariky.commonservice.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.matariky.annotation.DataScope;
import com.matariky.commonservice.base.bean.BasicBaseAppVersion;
import com.matariky.commonservice.base.vo.BasicBaseAppVersionListVO;
import com.matariky.commonservice.base.vo.BasicBaseAppVersionQueryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Persistence Interface
 *
 * @author AUTOMATION
 */
public interface BasicBaseAppVersionMapper extends BaseMapper<BasicBaseAppVersion> {

    /**
     *  Query   All 
     */
    @DataScope(alias = "a")
    public List<BasicBaseAppVersionListVO> getBasicBaseAppversionAll(@Param("params") BasicBaseAppVersionQueryVO vo);


    /**
     * New  Method  
     */
    public int createBasicBaseAppversion(BasicBaseAppVersion bean);


    /**
     * Delete   Method  
     */
    public int delBasicBaseAppversionById(Long id);


    /**
     *   Retrieve Printer App
     *
     * @return
     */
    public BasicBaseAppVersion getBasicBasePrintApp();

}
