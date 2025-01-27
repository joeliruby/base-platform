package com.matariky.commonservice.base.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.matariky.commonservice.base.bean.BasicBaseRfidBinding;
import com.matariky.commonservice.base.bean.BasicBaseRfidInfo;
import com.matariky.commonservice.base.mapper.BasicBaseRfidBindingMapper;
import com.matariky.commonservice.base.mapper.BasicBaseRfidInfoMapper;
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
import com.matariky.utils.StringUtils;
import com.matariky.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *  Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
public class BasicBaseRfidBindingService extends BaseServiceImpl<BasicBaseRfidBindingMapper, BasicBaseRfidBinding> implements BaseService<BasicBaseRfidBinding> {

    @Autowired
    private BasicBaseRfidBindingMapper basicBaseRfidBindingMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CommonDictService commonDictService;
    @Autowired
    private CommonDictTypeService commonDictTypeService;
    @Autowired
    private BasicBaseRfidInfoMapper basicBaseRfidInfoMapper;

    /**
     *  Query   All 
     *
     * @param vo
     * @return
     */
    public List<BasicBaseRfidBindingInfoVO> getBasicBaseRfidBindingAll(BasicBaseRfidBindingListVO vo) {
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
        return basicBaseRfidBindingMapper.getBasicBaseRfidBindingAll(vo);
    }


    /**
     * New
     */
    @Transactional(rollbackFor = Exception.class)
    public int createBasicBaseRfidBindingWithOrg(BasicBaseRfidBindingAddVO addVO) {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);

        BasicBaseRfidInfo rfidInfo = basicBaseRfidInfoMapper.selectOne(Wrappers.lambdaQuery(BasicBaseRfidInfo.class)
                .eq(BasicBaseRfidInfo::getEpc, addVO.getEpc())
                .eq(BasicBaseRfidInfo::getTid, addVO.getTid())
                .eq(BasicBaseRfidInfo::getDeleteTime, 0));
        if (rfidInfo == null) {
            throw new QslException(MessageKey.BASE_RFID_INFO_NOT_EXIST);
        }

        Long rfidCount = basicBaseRfidBindingMapper.selectCount(Wrappers.lambdaQuery(BasicBaseRfidBinding.class)
                .eq(BasicBaseRfidBinding::getRfidId, rfidInfo.getId())
                .eq(BasicBaseRfidBinding::getDeleteTime, 0));
        if (rfidCount > 0) {
            throw new QslException(MessageKey.BASE_EPC_NOT_REPEAT);
        }
        Long epcCount = basicBaseRfidBindingMapper.selectCount(Wrappers.lambdaQuery(BasicBaseRfidBinding.class)
                .eq(BasicBaseRfidBinding::getEpc, addVO.getEpc())
                .eq(BasicBaseRfidBinding::getDeleteTime, 0));
        if (epcCount > 0) {
            throw new QslException(MessageKey.BASE_EPC_NOT_REPEAT);
        }
        if (StringUtils.isNotBlank(addVO.getTid())) {
            Long tidCount = basicBaseRfidBindingMapper.selectCount(Wrappers.lambdaQuery(BasicBaseRfidBinding.class)
                    .eq(BasicBaseRfidBinding::getTid, addVO.getTid())
                    .eq(BasicBaseRfidBinding::getDeleteTime, 0));
            if (tidCount > 0) {
                throw new QslException(MessageKey.BASE_TID_NOT_REPEAT);
            }
        }
        long userId = Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request));
        BasicBaseRfidBinding add = new BasicBaseRfidBinding();
        BeanUtils.copyProperties(addVO, add);
        add.setCreateBy(userId);
        add.setUpdateBy(userId);
        add.setCreateTime(System.currentTimeMillis());
        add.setUpdateTime(System.currentTimeMillis());
        add.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
        add.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        add.setTenantId(tenantId);
        add.setRfidId(rfidInfo.getId());
        return basicBaseRfidBindingMapper.createBasicBaseRfidBinding(add);
    }

    /**
     * New- Multiple Item  Binding 
     *
     * @param batchAddVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void createBasicBaseRfidBindingWithOrgBatch(BasicBaseRfidBindingBatchAddVO batchAddVO) {
        batchAddVO.getEpcAndTidList().stream().forEach(item -> {
            BasicBaseRfidBindingAddVO addVO = new BasicBaseRfidBindingAddVO();
            addVO.setGoodsId(batchAddVO.getGoodsId());
            addVO.setTid(item.getTid());
            addVO.setEpc(item.getEpc());
            addVO.setDeviceId(batchAddVO.getDeviceId());
            this.createBasicBaseRfidBindingWithOrg(addVO);
        });
    }


    /**
     *   Update  Method  
     *
     * @param updateVO
     * @return
     */
    public int updateBasicBaseRfidBinding(BasicBaseRfidBindingUpdateVO updateVO) {
        Long rfidCount = basicBaseRfidBindingMapper.selectCount(Wrappers.lambdaQuery(BasicBaseRfidBinding.class)
                .eq(BasicBaseRfidBinding::getRfidId, updateVO.getRfidId())
                .ne(BasicBaseRfidBinding::getId, updateVO.getId())
                .eq(BasicBaseRfidBinding::getDeleteTime, 0));
        if (rfidCount > 0) {
            throw new QslException(MessageKey.BASE_RFID_NOT_REPEAT);
        }
        Long goodsIdCount = basicBaseRfidBindingMapper.selectCount(Wrappers.lambdaQuery(BasicBaseRfidBinding.class)
                .eq(BasicBaseRfidBinding::getGoodsId, updateVO.getRfidId())
                .ne(BasicBaseRfidBinding::getId, updateVO.getId())
                .eq(BasicBaseRfidBinding::getDeleteTime, 0));
        if (goodsIdCount > 0) {
            throw new QslException(MessageKey.BASE_GOODSID_NOT_REPEAT);
        }
        Long epcCount = basicBaseRfidBindingMapper.selectCount(Wrappers.lambdaQuery(BasicBaseRfidBinding.class)
                .eq(BasicBaseRfidBinding::getEpc, updateVO.getRfidId())
                .ne(BasicBaseRfidBinding::getId, updateVO.getId())
                .eq(BasicBaseRfidBinding::getDeleteTime, 0));
        if (epcCount > 0) {
            throw new QslException(MessageKey.BASE_EPC_NOT_REPEAT);
        }
        Long tidCount = basicBaseRfidBindingMapper.selectCount(Wrappers.lambdaQuery(BasicBaseRfidBinding.class)
                .eq(BasicBaseRfidBinding::getTid, updateVO.getRfidId())
                .ne(BasicBaseRfidBinding::getId, updateVO.getId())
                .eq(BasicBaseRfidBinding::getDeleteTime, 0));
        if (tidCount > 0) {
            throw new QslException(MessageKey.BASE_TID_NOT_REPEAT);
        }

        BasicBaseRfidBinding update = new BasicBaseRfidBinding();
        BeanUtils.copyProperties(updateVO, update);
        update.setUpdateBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request)));
        update.setUpdateTime(System.currentTimeMillis());
        return basicBaseRfidBindingMapper.updateById(update);
    }


    /**
     * Delete 
     *
     * @param id
     */
    public Integer deleteById(Long id) {
        return basicBaseRfidBindingMapper.update(null, Wrappers.lambdaUpdate(BasicBaseRfidBinding.class)
                .set(BasicBaseRfidBinding::getDeleteTime, System.currentTimeMillis())
                .eq(BasicBaseRfidBinding::getId, id));

    }

    /**
     *   Retrieve Label  Incremental  Code 
     */
    public Long getRfidCode() {
        Long rfidCode = 1L;
        BasicBaseRfidBinding bind = basicBaseRfidBindingMapper.selectOne(Wrappers.lambdaQuery(BasicBaseRfidBinding.class)
                .eq(BasicBaseRfidBinding::getDeleteTime, 0)
                .orderByDesc(BasicBaseRfidBinding::getTagCode)
                .last("limit 1"));
        if (bind != null && bind.getTagCode() != null) {
            rfidCode = bind.getTagCode() + 1;
        }
        return rfidCode;
    }

}
