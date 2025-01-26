package com.matariky.commonservice.commondict.mapper;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.matariky.mybatis.EnhanceBaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.matariky.commonservice.commondict.bean.CommonDict;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface CommonDictMapper extends EnhanceBaseMapper<CommonDict> {
	 
	public List<CommonDict> getCommonDictAll(@Param("params")Map<String, Object> params);
	 
	public int getCommonDictAllCount(@Param("params")Map<String, Object> params);
	 
	public int createCommonDict(CommonDict bean);
	 
	public int updateCommonDict(@Param("params") CommonDict bean);
	 
	public int delCommonDictById(Long id);
	 
	public CommonDict getCommonDictById(Long id);
	//根据字典key和 Tenant id Query  Data 修改dict_value中配置的json
	public void updateValueByKeyAndTenantId(@Param("dictTypeId")String dictTypeId,@Param("dictKey") String dictKey,@Param("dictValue") String dictValue, @Param("tenantId")String tenantId);

		@Override
	int insert(CommonDict entity);


	@Override
    int deleteById(Serializable id);


	 @Override
    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);


	 @Override
    int delete(@Param(Constants.WRAPPER) Wrapper<CommonDict> queryWrapper);

     @Override
    int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);


	 @Override
    int updateById(@Param(Constants.ENTITY) CommonDict entity);


	 @Override
    int update(@Param(Constants.ENTITY) CommonDict entity, @Param(Constants.WRAPPER) Wrapper<CommonDict> updateWrapper);


	 @Override
    CommonDict selectById(Serializable id);


	 @Override
    List<CommonDict> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);


	 @Override
    List<CommonDict> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);


	 //@Override
    //CommonDict selectOne(@Param(Constants.WRAPPER) Wrapper<CommonDict> queryWrapper);


	 @Override
    Long selectCount(@Param(Constants.WRAPPER) Wrapper<CommonDict> queryWrapper);


	 @Override
    List<CommonDict> selectList(@Param(Constants.WRAPPER) Wrapper<CommonDict> queryWrapper);


	@Override
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<CommonDict> queryWrapper);


	 @Override
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<CommonDict> queryWrapper);


    Page<CommonDict> selectPage(Page<CommonDict> page, @Param(Constants.WRAPPER) Wrapper<CommonDict> queryWrapper);

    Page<Map<String, Object>> selectMapsPage(Page<CommonDict> page, @Param(Constants.WRAPPER) Wrapper<CommonDict> queryWrapper);


	@Select(" select dict.id, dict.dict_value as dictValue from common_dict dict , common_dict_type dt where dt.dict_type_key=#{dictTypeKey} and dict.dict_key=#{dictKey} and dict.dict_type_id=dt.id and dict.tenant_id=#{tenantCode} limit 1")
	public CommonDict getDictionaryItemByTypeAndKey(@Param("dictTypeKey") String dictTypeKey, @Param("dictKey") String dictKey, @Param("tenantCode") String tenantCode);


	@Select(" select dict.id, dict.dict_value as dictValue from common_dict dict , common_dict_type dt where dt.id=#{id} and dict.dict_key=#{dictKey} and dict.dict_type_id=dt.id and dict.tenant_id=#{tenantCode} limit 1")
	public CommonDict getDictionaryItemById(@Param("id") String id, @Param("dictKey") String dictKey, @Param("tenantCode") String tenantCode);


	@Select("select d.dict_key,d.dict_value, dt.dict_type_key from common_dict d, common_dict_type dt where d.tenant_id=#{tenantId} and dt.tenant_id = #{tenantId} and dt.dict_type_key like concat(#{locale},'_INIT_%') and d.dict_type_id=dt.id")
    public List<Map<String, Object>> initialList(@Param("tenantId") String tenantId, @Param("locale") String locale);


	public int updateDeleteTimeById(@Param("array")String[] id);


    @Select("select dict_value from common_dict where tenant_id =#{tenantId} and dict_key=#{dictKey}")
	public String getDictValueByTenantIdAndKey(@Param("tenantId") String extractTenantIdFromHttpReqeust, @Param("dictKey") String storageDictType);

    @Select("select dict_value from common_dict where tenant_id =#{tenantId} and dict_key=#{permissionId} AND dict_type_id =#{dictTypeId}")
	public String getDictValueByTenantIdAndKeyAndDictTypeId(@Param("tenantId")String tenantId,@Param("permissionId")String permissionId,@Param("dictTypeId")String dictTypeId);

	public CommonDict getCommonDictByIdTenantIdAndDictType(@Param("permissionId")String permissionId, @Param("tenantId") String tenantId,@Param("dictTypeId") Long id);

	@Select("select id from user_permission where tenant_id = #{tenantId} and orig_id = #{origId} and is_active = 1 limit 1")
	Long getPermissionId(@Param("tenantId") String tenantId, @Param("origId") String origId);

}
