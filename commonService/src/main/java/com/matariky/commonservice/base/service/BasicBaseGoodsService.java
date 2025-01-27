package com.matariky.commonservice.base.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.bean.*;
import com.matariky.commonservice.base.mapper.*;
import com.matariky.commonservice.base.vo.BasicBaseGoodsListVO;
import com.matariky.commonservice.base.vo.GoodsOptionInfo;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.constant.PermissionConstant;
import com.matariky.exception.QslException;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.model.QueryDataIsolation;
import com.matariky.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;

/**
 *  Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
public class BasicBaseGoodsService extends BaseServiceImpl<BasicBaseGoodsMapper, BasicBaseGoods> implements BaseService<BasicBaseGoods> {

    @Autowired
    private BasicBaseGoodsMapper basicBaseGoodsMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CommonDictService commonDictService;
    @Autowired
    private CommonDictTypeService commonDictTypeService;
    @Autowired
    private BasicBaseCreaterfidFactoryMapper basicBaseCreaterfidFactoryMapper;
    @Autowired
    private BasicBaseCreaterfidPrintMapper basicBaseCreaterfidPrintMapper;
    @Autowired
    private BasicBaseRfidBindingMapper basicBaseRfidBindingMapper;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicBaseFormExtendMapper basicBaseFormExtendMapper;

    /**
     *  Pagination Query 
     *
     * @return
     */
    public PageInfo<Map> getBasicBaseGoodsAll(BasicBaseGoodsListVO vo) {
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
        /**
         *  Query  Augment Field 
         */
        List<String> extendFieldList = basicBaseGoodsMapper.selectFieldExtend(TokenUtils.longify(tenantId));
        vo.setExtendFieldList(extendFieldList);
        Page<Object> page = PageHelper.startPage(vo.getIndex(), vo.getPerPage());
        List<HashMap> list = basicBaseGoodsMapper.getBasicBaseGoodsAll(vo);
        list.stream().forEach(item -> {
            if (Long.valueOf(item.get("a").toString()) > 0
                    || Long.valueOf(item.get("b").toString()) > 0
                    || Long.valueOf(item.get("c").toString()) > 0
                    || Long.valueOf(item.get("d").toString()) > 0
                    || Long.valueOf(item.get("e").toString()) > 0) {
                item.put("isUsedByOther", true);
            } else {
                item.put("isUsedByOther", false);
            }
        });
        List<Map> newList = new ArrayList<>();
        list.stream().forEach(item -> {
            Iterator<Map.Entry> iterator = item.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = iterator.next();
                String key = entry.getKey().toString();
                if (key.equals("a") || key.equals("b") || key.equals("c") || key.equals("d") || key.equals("e")) {
                    iterator.remove();
                }
            }
            Map<String, Object> newMap = commonService.mapToCamelCase(item);
            newList.add(newMap);
        });


        PageInfo<Map> pageInfo = new PageInfo(newList);
        pageInfo.setList(newList);
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }


    /**
     * New
     *
     * @param addVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void createBasicBaseGoodsWithOrg(HashMap addVO) {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        Long nameCount = basicBaseGoodsMapper.selectCount(Wrappers.lambdaQuery(BasicBaseGoods.class)
                .eq(BasicBaseGoods::getGoodsName, addVO.get("goodsName"))
                .eq(BasicBaseGoods::getDeleteTime, 0)
                .eq(BasicBaseGoods::getTenantId, tenantId));
        if (nameCount > 0) {
            throw new QslException(MessageKey.GOODS_NAME_NOT_REPEAT);
        }
        Long codeCount = basicBaseGoodsMapper.selectCount(Wrappers.lambdaQuery(BasicBaseGoods.class)
                .eq(BasicBaseGoods::getGoodsCode, addVO.get("goodsCode"))
                .eq(BasicBaseGoods::getDeleteTime, 0)
                .eq(BasicBaseGoods::getTenantId, tenantId));
        if (codeCount > 0) {
            throw new QslException(MessageKey.GOODS_CODE_NOT_REPEAT);
        }
        long userId = Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request));
        String operatorOrgCode = TokenUtils.extractOrgCode(request);
        String operatorSelfOrgCode = TokenUtils.extractSelfOrgCode(request);
        BasicBaseGoods add = new BasicBaseGoods();
        add.setGoodsCode(addVO.get("goodsCode") != null ? addVO.get("goodsCode").toString() : null);
        add.setGoodsName(addVO.get("goodsName") != null ? addVO.get("goodsName").toString() : null);
        add.setGoodsImage(addVO.get("goodsImage") != null ? addVO.get("goodsImage").toString() : null);
        add.setGoodsDescribe(addVO.get("goodsDescribe") != null ? addVO.get("goodsDescribe").toString() : null);
        add.setRemark(addVO.get("remark") != null ? addVO.get("remark").toString() : null);
        add.setOperatorOrgCode(operatorOrgCode);
        add.setOperatorSelfOrgCode(operatorSelfOrgCode);
        add.setCreateTime(System.currentTimeMillis());
        add.setCreateBy(userId);
        add.setTenantId(tenantId);
        add.setUpdateBy(userId);
        add.setUpdateTime(System.currentTimeMillis());
        basicBaseGoodsMapper.createBasicBaseGoods(add);

        BasicBaseFormExtend extend = new BasicBaseFormExtend();
        extend.setCreateBy(userId);
        extend.setCreateTime(System.currentTimeMillis());
        extend.setUpdateBy(userId);
        extend.setUpdateTime(System.currentTimeMillis());
        extend.setTenantId(tenantId);
        extend.setOperatorOrgCode(operatorOrgCode);
        extend.setOperatorSelfOrgCode(operatorSelfOrgCode);
        extend.setBusinessId(add.getId());

        Boolean flag = false;
        Iterator<Map.Entry> iterator = addVO.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            if (key.startsWith("field")) {
                flag = true;
                try {
                    Class readerClass = extend.getClass();
                    Field field = readerClass.getDeclaredField(key);
                    field.setAccessible(true);
                    field.set(extend, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (flag) {
            basicBaseFormExtendMapper.createBasicBaseFormExtend(extend);
        }
    }

    /**
     * Update
     *
     * @param updateVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateBasicBaseGoods(HashMap updateVO) {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        Long nameCount = basicBaseGoodsMapper.selectCount(Wrappers.lambdaQuery(BasicBaseGoods.class)
                .eq(BasicBaseGoods::getGoodsName, updateVO.get("goodsName"))
                .eq(BasicBaseGoods::getDeleteTime, 0)
                .eq(BasicBaseGoods::getTenantId, tenantId)
                .ne(BasicBaseGoods::getId, updateVO.get("id")));
        if (nameCount > 0) {
            throw new QslException(MessageKey.GOODS_NAME_NOT_REPEAT);
        }
        Long codeCount = basicBaseGoodsMapper.selectCount(Wrappers.lambdaQuery(BasicBaseGoods.class)
                .eq(BasicBaseGoods::getGoodsCode, updateVO.get("goodsCode"))
                .eq(BasicBaseGoods::getDeleteTime, 0)
                .eq(BasicBaseGoods::getTenantId, tenantId)
                .ne(BasicBaseGoods::getId, updateVO.get("id")));
        if (codeCount > 0) {
            throw new QslException(MessageKey.GOODS_CODE_NOT_REPEAT);
        }
        long userId = Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request));
        BasicBaseGoods update = new BasicBaseGoods();
        update.setGoodsName(updateVO.get("goodsName") != null ? updateVO.get("goodsName").toString() : null);
        update.setGoodsImage(updateVO.get("goodsImage") != null ? updateVO.get("goodsImage").toString() : null);
        update.setGoodsDescribe(updateVO.get("goodsDescribe") != null ? updateVO.get("goodsDescribe").toString() : null);
        update.setId(Long.valueOf(updateVO.get("id").toString()));
        update.setUpdateTime(System.currentTimeMillis());
        update.setUpdateBy(userId);
        basicBaseGoodsMapper.updateById(update);

        Object extendId = updateVO.get("extendId");
        boolean isHaveExtendField = updateVO.keySet().stream().anyMatch(key -> key.toString().contains("field"));
        if (isHaveExtendField && extendId == null) {
            /**
             * New Augment Field 
             */
            BasicBaseFormExtend extend = new BasicBaseFormExtend();
            extend.setCreateBy(userId);
            extend.setCreateTime(System.currentTimeMillis());
            extend.setUpdateBy(userId);
            extend.setUpdateTime(System.currentTimeMillis());
            extend.setTenantId(tenantId);
            extend.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
            extend.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
            extend.setBusinessId(Long.valueOf(updateVO.get("id").toString()));
            Boolean flag = false;
            Iterator<Map.Entry> iterator = updateVO.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = iterator.next();
                String key = entry.getKey().toString();
                if (key.startsWith("field")) {
                    flag = true;
                    try {
                        Class readerClass = extend.getClass();
                        Field field = readerClass.getDeclaredField(key);
                        field.setAccessible(true);
                        field.set(extend, entry.getValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (flag) {
                basicBaseFormExtendMapper.createBasicBaseFormExtend(extend);
            }
        } else if (isHaveExtendField && extendId != null) {
            /**
             *   Update Augment Field 
             */
            BasicBaseFormExtend extend = new BasicBaseFormExtend();
            Iterator<Map.Entry> iterator = updateVO.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = iterator.next();
                String key = entry.getKey().toString();
                String value = entry.getValue() != null ? entry.getValue().toString() : "";
                if (key.startsWith("field")) {
                    try {
                        Class readerClass = extend.getClass();
                        Field field = readerClass.getDeclaredField(key);
                        field.setAccessible(true);
                        field.set(extend, value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            extend.setId(Long.valueOf(extendId.toString()));
            extend.setUpdateBy(userId);
            extend.setUpdateTime(System.currentTimeMillis());
            basicBaseFormExtendMapper.updateBasicBaseFormExtend(extend);
        }

    }

    /**
     * Delete 
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void delBasicBaseGoodsById(Long id, Long extendId) {
        Integer count = basicBaseGoodsMapper.getGoodsCountFromRfidfactory(id);
        if (count > 0) {
            throw new QslException(MessageKey.GOODS_USED);
        }
        Long factoryCount = basicBaseCreaterfidFactoryMapper.selectCount(Wrappers.lambdaQuery(BasicBaseCreaterfidFactory.class)
                .eq(BasicBaseCreaterfidFactory::getGoodsId, id)
                .eq(BasicBaseCreaterfidFactory::getDeleteTime, 0));
        if (factoryCount > 0) {
            throw new QslException(MessageKey.GOODS_USED);
        }
        Long printCount = basicBaseCreaterfidPrintMapper.selectCount(Wrappers.lambdaQuery(BasicBaseCreaterfidPrint.class)
                .eq(BasicBaseCreaterfidPrint::getGoodsId, id)
                .eq(BasicBaseCreaterfidPrint::getDeleteTime, 0));
        if (printCount > 0) {
            throw new QslException(MessageKey.GOODS_USED);
        }
        Long bindCount = basicBaseRfidBindingMapper.selectCount(Wrappers.lambdaQuery(BasicBaseRfidBinding.class)
                .eq(BasicBaseRfidBinding::getGoodsId, id)
                .eq(BasicBaseRfidBinding::getDeleteTime, 0));
        if (bindCount > 0) {
            throw new QslException(MessageKey.GOODS_USED);
        }
        Integer rfidPrintCount = basicBaseGoodsMapper.getGoodsCountFromRfidPrint(id);
        if (rfidPrintCount > 0) {
            throw new QslException(MessageKey.GOODS_USED);
        }
        basicBaseGoodsMapper.delBasicBaseGoodsById(id);

        basicBaseFormExtendMapper.delBasicBaseFormExtendById(extendId);
    }


    /**
     *  Item  -   Drop Down Box Pagination 
     *
     * @param
     */
    public List<GoodsOptionInfo> optionList() {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        QueryDataIsolation vo = new QueryDataIsolation();
        String hid = request.getHeader("id");
        String resourceIdDictKey = "dp" + hid.substring(0, hid.length() - 1);
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
        List<GoodsOptionInfo> optionList = basicBaseGoodsMapper.getOptionList(vo);
        optionList.stream().forEach(item -> {
            item.setLabel(item.getGoodsName() + "(" + item.getGoodsCode() + ")");
        });
        return optionList;
    }

    /**
     *  Query   Detail 
     *
     * @param id
     */
    public Map<String, Object> getBasicBaseGoodsById(Long id, Long extendId) {
        BasicBaseGoods info = basicBaseGoodsMapper.getBasicBaseGoodsById(id);
        Map<String, Object> map = BeanUtil.beanToMap(info);
        if (extendId != null) {
            BasicBaseFormExtend extend = basicBaseFormExtendMapper.getBasicBaseFormExtendById(extendId);
            if (extend != null) {
                map.put("extendId", extend.getId());
                Map<String, Object> extendMap = BeanUtil.beanToMap(extend);
                Iterator<Map.Entry<String, Object>> iterator = extendMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> entry = iterator.next();
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (key.startsWith("field")) {
                        map.put(key, value);
                    }
                }
            }
        }
        return map;
    }
}
