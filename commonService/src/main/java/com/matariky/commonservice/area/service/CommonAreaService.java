package com.matariky.commonservice.area.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;

import com.matariky.commonservice.area.bean.CommonArea;
import com.matariky.commonservice.area.bean.CommonAreaVo;
import com.matariky.commonservice.area.mapper.CommonAreaMapper;
import com.matariky.iservice.impl.BaseServiceImpl;

/**
 * Business Inteface Implementation
 * 
 * @author AUTOMATION
 */
@Service
public class CommonAreaService extends BaseServiceImpl<CommonAreaMapper, CommonArea> {

	@Autowired
	private CommonAreaMapper commonAreaMapper;

	public Page<CommonArea> getCommonAreaAll() {
		return commonAreaMapper.getCommonAreaAll();
	}

	public int getCommonAreaAllCount() {
		return commonAreaMapper.getCommonAreaAllCount();
	}

	public int createCommonArea(CommonArea bean) {
		return commonAreaMapper.createCommonArea(bean);
	}

	public int updateCommonArea(CommonArea bean) {
		return commonAreaMapper.updateCommonArea(bean);
	}

	public int delCommonAreaById(int id) {
		return commonAreaMapper.delCommonAreaById(id);
	}

	public CommonArea getCommonAreaById(int id) {
		return commonAreaMapper.getCommonAreaById(id);
	}

	public List<CommonArea> getAreaByParentId(Long parentId) {
		return commonAreaMapper.getAreaByParentId(parentId);
	}

	public List<CommonAreaVo> subNodesById(Long nodeId) {
		return commonAreaMapper.subNodesById(nodeId);
	}

}
