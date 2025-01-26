package com.matariky.commonservice.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.matariky.annotation.DataScope;
import com.matariky.commonservice.base.bean.BasicBaseRfidBinding;
import com.matariky.commonservice.base.vo.BasicBaseRfidBindingInfoVO;
import com.matariky.commonservice.base.vo.BasicBaseRfidBindingListVO;
import com.matariky.commonservice.base.vo.BatchInfoVO;
import com.matariky.commonservice.base.vo.GoodsBatchInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Persistence Interface
 *
 * @author AUTOMATION
 */
public interface BasicBaseRfidBindingMapper extends BaseMapper<BasicBaseRfidBinding> {

    /**
     *  Query 所有分页
     */
    @DataScope(alias = "b")
    public List<BasicBaseRfidBindingInfoVO> getBasicBaseRfidBindingAll(@Param("params") BasicBaseRfidBindingListVO vo);


    /**
     * New方法
     *
     * @param bean
     * @return
     */
    public int createBasicBaseRfidBinding(BasicBaseRfidBinding bean);


    /**
     *  Item 批次绑定详情
     * @param vo
     * @return
     */
    public List<GoodsBatchInfoVO> goodsBatchInfo(@Param("params") BatchInfoVO vo);
}
