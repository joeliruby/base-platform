package com.matariky.iservice;

import java.io.Serializable;
import java.util.Collection;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

/**
 * Base Service Interface ,所有Service Interface都要继承
 *
 */
public interface BaseService<T> {

    /**
     * <p>
     * Insert a record（选择字段 ,策略插入）
     * </p>
     *
     * @param entity 实体 Object
     */
    boolean insert(T entity);

    /**
     * <p>
     * 插入（批量） ,该 Method 不支持 Oracle、SQL Server
     * </p>
     *
     * @param entityList 实体 Object 集合
     */
    boolean insertBatch(Collection<T> entityList);

    /**
     * <p>
     * 插入（批量） ,该 Method 不支持 Oracle、SQL Server
     * </p>
     *
     * @param entityList 实体 Object 集合
     * @param batchSize  插入 Batch Quantity
     */
    boolean insertBatch(Collection<T> entityList, int batchSize);

    /**
     * <p>
     * 根据 ID 选择 Update
     * </p>
     *
     * @param entity 实体 Object
     */
    boolean updateById(T entity);

    /**
     * <p>
     * Update By Entity
     * </p>
     *
     * @param entity        实体 Object
     * @param updateWrapper 实体 Object 封装 Operation类
     *                      {@link com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper}
     */
    boolean update(T entity, Wrapper<T> updateWrapper);

    /**
     * <p>
     * 根据ID 批量Update
     * </p>
     *
     * @param entityList 实体 Object 集合
     */
    boolean updateBatchById(Collection<T> entityList);

    /**
     * <p>
     * 根据ID 批量Update
     * </p>
     *
     * @param entityList 实体 Object 集合
     * @param batchSize  Update Batch Quantity
     */
    boolean updateBatchById(Collection<T> entityList, int batchSize);

    /**
     * <p>
     * Query By ID
     * </p>
     *
     * @param id Primary KeyID
     */
    T selectById(Serializable id);

    /**
     * <p>
     * Delete By ID
     * </p>
     *
     * @param id Primary KeyID
     */
    boolean deleteById(Serializable id);

    /**
     * <p>
     * Delete Batch By IDs
     * </p>
     *
     * @param idList Primary KeyID Pagination
     */
    boolean deleteBatchIds(Collection<? extends Serializable> idList);
}