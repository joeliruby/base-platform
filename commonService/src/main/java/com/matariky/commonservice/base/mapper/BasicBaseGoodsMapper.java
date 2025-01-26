package com.matariky.commonservice.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.matariky.annotation.DataScope;
import com.matariky.commonservice.base.bean.BasicBaseGoods;
import com.matariky.commonservice.base.vo.BasicBaseGoodsInfoVo;
import com.matariky.commonservice.base.vo.BasicBaseGoodsListVO;
import com.matariky.commonservice.base.vo.GoodsOptionInfo;
import com.matariky.model.QueryDataIsolation;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Persistence Interface
 *
 * @author AUTOMATION
 */
public interface BasicBaseGoodsMapper extends BaseMapper<BasicBaseGoods> {

    /**
     *  Query 扩展字段
     * @param tenantId
     * @return
     */
    public  List<String> selectFieldExtend(@Param("tenantId") Long  tenantId);

    /**
     *  Query 所有分页
     */
    @DataScope(alias = "g")
    public List<HashMap> getBasicBaseGoodsAll(@Param("params") BasicBaseGoodsListVO vo);

    /**
     * New方法
     *
     * @param bean
     * @return
     */
    public int createBasicBaseGoods(BasicBaseGoods bean);

    /**
     * 删除方法
     *
     * @param id
     * @return
     */
    public int delBasicBaseGoodsById(Long id);


    /**
     *  Item -下拉选
     *
     * @param queryDataIsolation
     * @return
     */
    @DataScope(alias = StringUtils.EMPTY)
    public List<GoodsOptionInfo> getOptionList(QueryDataIsolation queryDataIsolation);

    /**
     *  Label  Data  Generation 表  Wether 存在 该 Item 
     *
     * @param goodsId
     * @return
     */
    public Integer getGoodsCountFromRfidfactory(@Param("goodsId") Long goodsId);

    /**
     *  Label  Print 表  Wether 存在 该 Item 
     *
     * @param goodsId
     * @return
     */
    public Integer getGoodsCountFromRfidPrint(@Param("goodsId") Long goodsId);

    /**
     *  Query 详情
     * @return
     */
    public BasicBaseGoods getBasicBaseGoodsById(@Param("id") Long id);
}
