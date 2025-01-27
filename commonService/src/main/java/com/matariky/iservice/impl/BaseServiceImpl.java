package com.matariky.iservice.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.github.pagehelper.util.StringUtil;
import com.matariky.commonservice.datasharing.bean.CommonSharingPool;
import com.matariky.commonservice.datasharing.service.CommonSharingPoolService;
import com.matariky.constant.PermissionConstant;
import com.matariky.iservice.BaseService;
import com.matariky.mybatis.MybatisPlusDataScopeInterceptor;
import com.matariky.utils.TokenUtils;

/**
 * Base Service 类 ,所有Service都要继承
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> implements BaseService<T> {
    @Autowired
    protected M baseDao;

    @Autowired
    // @Lazy
    private CommonSharingPoolService sharingPoolService;

    @Autowired
    protected MybatisPlusDataScopeInterceptor mybatisPlusDataScopeInterceptor;

    /**
     * Retrieve Pagination Object
     *
     * @param params            Pagination Query Parameter
     * @param defaultOrderField 默认排序字段
     * @param isAsc             排序方式
     */
    protected IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
        // Pagination Parameter
        long curPage = 1;
        long limit = 10;

        if (params.get(Constant.PAGE) != null) {
            curPage = Long.parseLong((String) params.get(Constant.PAGE));
        }
        if (params.get(Constant.LIMIT) != null) {
            limit = Long.parseLong((String) params.get(Constant.LIMIT));
        }

        // Pagination Object
        Page<T> page = new Page<>(curPage, limit);

        // Pagination Parameter
        params.put(Constant.PAGE, page);

        // 排序字段
        String orderField = (String) params.get(Constant.ORDER_FIELD);
        String order = (String) params.get(Constant.ORDER);
        // 前端字段排序
        if (StringUtils.isNotBlank(orderField) && StringUtils.isNotBlank(order)) {
            if (Constant.ASC.equalsIgnoreCase(order)) {
                return page.addOrder(Stream.of(orderField.split(",")).map(OrderItem::asc).collect(Collectors.toList()));
            } else {
                return page
                        .addOrder(Stream.of(orderField.split(",")).map(OrderItem::desc).collect(Collectors.toList()));
            }
        }

        // 没有排序字段 ,则不排序
        if (StringUtil.isEmpty(defaultOrderField)) {
            return page;
        }

        // 默认排序
        if (isAsc) {
            page.addOrder(Stream.of(defaultOrderField.split(",")).map(OrderItem::asc).collect(Collectors.toList()));
        } else {
            page.addOrder(Stream.of(defaultOrderField.split(",")).map(OrderItem::desc).collect(Collectors.toList()));
        }

        return page;
    }

    protected void strategyCodeToParams(Map<String, Object> params, HttpServletRequest request) {
        String strategyCode = (String) params.get("strategyCode");
        if (StringUtil.isEmpty(strategyCode))
            strategyCode = PermissionConstant.COMMON_DATA_ACCESS_PRIVATE;// By default only visible by owner
        switch (strategyCode) {
            case PermissionConstant.COMMON_DATA_ACCESS_PRIVATE:// Visible to owner with special sharing rules
                Map<String, List<String>> sharingOrgCodes0 = extractedSharingOrgCodes(request);
                params.put("selfOrgCode", TokenUtils.extractSelfOrgCode(request));
                params.putAll(sharingOrgCodes0);
                break;
            case PermissionConstant.COMMON_DATA_ACCESS_ALL:// All visible to all without special sharing rules
                break;
            case PermissionConstant.COMMON_DATA_ACCESS_ORG:// Visible to organizations of same or upper level
                Map<String, List<String>> sharingOrgCodes3 = extractedSharingOrgCodes(request);
                params.put("orgCode", TokenUtils.extractOrgCode(request));
                params.putAll(sharingOrgCodes3);
                break;
            case PermissionConstant.COMMON_DATA_ACCESS_LEVEL:// Visible to organizations of same level with special
                                                             // sharing rules
                Map<String, List<String>> sharingOrgCodes2 = extractedSharingOrgCodes(request);
                params.put("orgCode", TokenUtils.extractOrgCode(request));
                params.putAll(sharingOrgCodes2);
                break;
            default:
                break;
        }
    }

    public Map<String, List<String>> extractedSharingOrgCodes(HttpServletRequest request) {
        List<CommonSharingPool> obtainedBySelfOrgCodes0 = sharingPoolService
                .selectByOrgCode(TokenUtils.extractSelfOrgCode(request));
        List<CommonSharingPool> obtainedByOrgCodes0 = sharingPoolService
                .selectByOrgCode(TokenUtils.extractOrgCode(request));
        obtainedByOrgCodes0.addAll(obtainedBySelfOrgCodes0);
        List<String> selfOrgCodes = new ArrayList<String>();
        List<String> orgCodes = new ArrayList<String>();
        for (CommonSharingPool shareingPoolItem : obtainedByOrgCodes0) {

            if (shareingPoolItem.getOriginalOwnerOrgCode().startsWith("ind_")) {
                selfOrgCodes.add(shareingPoolItem.getOriginalOwnerOrgCode());
            } else {
                orgCodes.add(shareingPoolItem.getOriginalOwnerOrgCode());
            }

        }

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("sharingSelfOrgCodes", selfOrgCodes);
        map.put("sharingOrgCodes", orgCodes);
        return map;
    }

    // protected <T> PageData<T> getPageData(List<?> list, long total, Class<T>
    // target){
    // List<T> targetList = ConvertUtils.sourceToTarget(list, target);
    //
    // return new PageData<>(targetList, total);
    // }
    //
    // protected <T> PageData<T> getPageData(IPage page, Class<T> target){
    // return getPageData(page.getRecords(), page.getTotal(), target);
    // }

    protected Map<String, Object> paramsToLike(Map<String, Object> params, String... likes) {
        for (String like : likes) {
            String val = (String) params.get(like);
            if (StringUtils.isNotBlank(val)) {
                params.put(like, "%" + val + "%");
            } else {
                params.put(like, null);
            }
        }
        return params;
    }

    /**
     * <p>
     * 判断 Data 库 Operation Wether 成功
     * </p>
     * <p>
     * 注意！！ 该 Method 为 Integer 判断 ,不可传入 int 基本Type
     * </p>
     *
     * @param result Data 库 Operation返回影响条数
     * @return boolean
     */
    protected static boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

    protected Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), BaseServiceImpl.class, 1);
    }

    /**
     * <p>
     * 批量 Operation SqlSession
     * </p>
     */
    protected SqlSession sqlSessionBatch() {
        return SqlHelper.sqlSessionBatch(currentModelClass());
    }

    /**
     * 释放sqlSession
     *
     * @param sqlSession session
     */
    protected void closeSqlSession(SqlSession sqlSession) {
        SqlSessionUtils.closeSqlSession(sqlSession, GlobalConfigUtils.currentSessionFactory(currentModelClass()));
    }

    /**
     * RetrieveSqlStatement
     *
     * @param sqlMethod
     * @return
     */
    protected String sqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.table(currentModelClass()).getSqlStatement(sqlMethod.getMethod());
    }

    @Override
    public boolean insert(T entity) {
        return BaseServiceImpl.retBool(baseDao.insert(entity));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertBatch(Collection<T> entityList) {
        return insertBatch(entityList, 100);
    }

    /**
     * 批量插入
     *
     * @param entityList
     * @param batchSize
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertBatch(Collection<T> entityList, int batchSize) {
        SqlSession batchSqlSession = sqlSessionBatch();
        int i = 0;
        String sqlStatement = sqlStatement(SqlMethod.INSERT_ONE);
        try {
            for (T anEntityList : entityList) {
                batchSqlSession.insert(sqlStatement, anEntityList);
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            batchSqlSession.flushStatements();
        } finally {
            closeSqlSession(batchSqlSession);
        }
        return true;
    }

    @Override
    public boolean updateById(T entity) {
        return BaseServiceImpl.retBool(baseDao.updateById(entity));
    }

    @Override
    public boolean update(T entity, Wrapper<T> updateWrapper) {
        return BaseServiceImpl.retBool(baseDao.update(entity, updateWrapper));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBatchById(Collection<T> entityList) {
        return updateBatchById(entityList, 30);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        if (CollectionUtils.isEmpty(entityList)) {
            throw new IllegalArgumentException("Error: entityList must not be empty");
        }
        SqlSession batchSqlSession = sqlSessionBatch();
        int i = 0;
        String sqlStatement = sqlStatement(SqlMethod.UPDATE_BY_ID);
        try {
            for (T anEntityList : entityList) {
                MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
                param.put(Constants.ENTITY, anEntityList);
                batchSqlSession.update(sqlStatement, param);
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            batchSqlSession.flushStatements();
        } finally {
            closeSqlSession(batchSqlSession);
        }
        return true;
    }

    @Override
    public T selectById(Serializable id) {
        return baseDao.selectById(id);
    }

    @Override
    public boolean deleteById(Serializable id) {

        return SqlHelper.retBool(baseDao.deleteById(id));
    }

    @Override
    public boolean deleteBatchIds(Collection<? extends Serializable> idList) {
        return SqlHelper.retBool(baseDao.deleteBatchIds(idList));
    }

}
