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
     *  Query 所有分页
     */
    @DataScope(alias = "a")
    public List<BasicBaseAppVersionListVO> getBasicBaseAppversionAll(@Param("params") BasicBaseAppVersionQueryVO vo);


    /**
     * New方法
     */
    public int createBasicBaseAppversion(BasicBaseAppVersion bean);


    /**
     * 删除方法
     */
    public int delBasicBaseAppversionById(Long id);


    /**
     * 获取 Print 机app
     *
     * @return
     */
    public BasicBaseAppVersion getBasicBasePrintApp();

}
