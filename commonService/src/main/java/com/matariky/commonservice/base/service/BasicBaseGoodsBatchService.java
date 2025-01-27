package com.matariky.commonservice.base.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.matariky.commonservice.base.bean.BasicBaseGoodsBatch;
import com.matariky.commonservice.base.mapper.BasicBaseGoodsBatchMapper;
import com.matariky.commonservice.base.mapper.BasicBaseRfidBindingMapper;
import com.matariky.commonservice.base.vo.*;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.constant.PermissionConstant;
import com.matariky.exception.QslException;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.utils.BeanUtils;
import com.matariky.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *  Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
public class BasicBaseGoodsBatchService extends BaseServiceImpl<BasicBaseGoodsBatchMapper, BasicBaseGoodsBatch> implements BaseService<BasicBaseGoodsBatch> {

    @Autowired
    private BasicBaseGoodsBatchMapper basicBaseGoodsBatchMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CommonDictService commonDictService;
    @Autowired
    private CommonDictTypeService commonDictTypeService;
    @Autowired
    private BasicBaseRfidBindingService basicBaseRfidBindingService;
    @Autowired
    private BasicBaseRfidBindingMapper basicBaseRfidBindingMapper;

    /**
     *  Query   All 
     *
     * @param vo
     * @return
     */
    public List<BasicBaseGoodsBatchResVO> getBasicBaseGoodsBatchAll(BasicBaseGoodsBatchListVO vo) {
        String hid = request.getHeader("id");
        String resourceIdDictKey = "dp" + hid.substring(0, hid.length() - 1);
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        CommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(TokenUtils.extractTenantIdFromHttpReqeust(request), PermissionConstant.DATA_ACCESS_PERMISSION);
        CommonDict dict = commonDictService.getCommonDictByIdTenantIdAndDictType(resourceIdDictKey, tenantId, commonDictType.getId());
        if (dict == null) {
            vo.setStrategyCode(PermissionConstant.COMMON_DATA_ACCESS_ALL);
        } else {
            vo.setStrategyCode(dict.getDictValue());
        }
        vo.setSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        vo.setOrgCode(TokenUtils.extractOrgCode(request));
        vo.setTenantId(tenantId);
        PageHelper.startPage(vo.getIndex(), vo.getPerPage());
        return basicBaseGoodsBatchMapper.getBasicBaseGoodsBatchAll(vo);
    }


    /**
     * New  Method  
     *
     * @param vo
     * @return
     */
    public int createBasicBaseGoodsBatch(BasicBaseGoodsBatchAddVO vo) {
        Long count = basicBaseGoodsBatchMapper.selectCount(Wrappers.lambdaQuery(BasicBaseGoodsBatch.class)
                .eq(BasicBaseGoodsBatch::getBatchCode, vo.getBatchCode())
                .eq(BasicBaseGoodsBatch::getGoodsId, vo.getGoodsId())
                .eq(BasicBaseGoodsBatch::getDeleteTime, 0));
        if (count > 0) {
            throw new QslException(MessageKey.GOODS_BATCHCODE_NOT_REPEAT);
        }
        BasicBaseGoodsBatch add = new BasicBaseGoodsBatch();
        BeanUtils.copyProperties(vo, add);
        Long userId = Long.valueOf(TokenUtils.extractUserIdFromHttpReqeust(request));
        add.setCreateBy(userId);
        add.setCreateTime(System.currentTimeMillis());
        add.setUpdateBy(userId);
        add.setUpdateTime(System.currentTimeMillis());
        add.setTenantId(TokenUtils.extractTenantIdFromHttpReqeust(request));
        add.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        add.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
        return basicBaseGoodsBatchMapper.createBasicBaseGoodsBatch(add);
    }

    /**
     *   Update  Method  
     *
     * @param updateVO
     * @return
     */
    public int updateBasicBaseGoodsBatch(BasicBaseGoodsBatchUpdateVO updateVO) {
        Long count = basicBaseGoodsBatchMapper.selectCount(Wrappers.lambdaQuery(BasicBaseGoodsBatch.class)
                .eq(BasicBaseGoodsBatch::getBatchCode, updateVO.getBatchCode())
                .eq(BasicBaseGoodsBatch::getDeleteTime, 0)
                .eq(BasicBaseGoodsBatch::getGoodsId, updateVO.getGoodsId())
                .ne(BasicBaseGoodsBatch::getId, updateVO.getId()));
        if (count > 0) {
            throw new QslException(MessageKey.GOODS_BATCHCODE_NOT_REPEAT);
        }
        BasicBaseGoodsBatch update = new BasicBaseGoodsBatch();
        BeanUtils.copyProperties(updateVO, update);
        update.setUpdateBy(Long.valueOf(TokenUtils.extractUserIdFromHttpReqeust(request)));
        update.setUpdateTime(System.currentTimeMillis());
        return basicBaseGoodsBatchMapper.updateBasicBaseGoodsBatch(update);
    }

    /**
     * Delete   Method  
     *
     * @param id
     * @return
     */
    public int delBasicBaseGoodsBatchById(Long id) {
        return basicBaseGoodsBatchMapper.delBasicBaseGoodsBatchById(id);
    }

    /**
     * Query Object By ID  
     *
     * @param id
     * @return
     */
    public BasicBaseGoodsBatchResVO getBasicBaseGoodsBatchById(Long id) {
        return basicBaseGoodsBatchMapper.getBasicBaseGoodsBatchById(id);
    }

    /**
     *  Item  Batch Binding Label 
     *
     * @param vo
     */
    public void batchGoodsBind(BatchGoodsBindVO vo) {
        vo.getList().forEach(item -> {
            BasicBaseRfidBindingAddVO addVO = new BasicBaseRfidBindingAddVO();
            BeanUtils.copyProperties(item, addVO);
            addVO.setGoodsId(vo.getGoodsId());
            Long rfidCode = basicBaseRfidBindingService.getRfidCode();
            addVO.setTagCode(rfidCode);
            basicBaseRfidBindingService.createBasicBaseRfidBindingWithOrg(addVO);
        });
    }

    /**
     *  Item  Batch  Binding   Detail 
     *
     * @param vo
     * @return
     */
    public List<GoodsBatchInfoVO> goodsBatchInfo(BatchInfoVO vo) {
        if (vo.getCreateTime() != null) {
            vo.setCreateTimeEnd(vo.getCreateTime() + 24 * 60 * 60 * 1000L);
        }
        List<GoodsBatchInfoVO> list = basicBaseRfidBindingMapper.goodsBatchInfo(vo);
        return list;
    }
}
