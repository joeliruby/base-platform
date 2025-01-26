package com.matariky.commonservice.area.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.jsonwebtoken.lang.Collections;
import com.matariky.commonservice.area.bean.CommonArea;
import com.matariky.commonservice.area.bean.CommonAreaVo;
import com.matariky.commonservice.area.service.CommonAreaService;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;

/**
*Controller
* @author AUTOMATION
*/
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
public class CommonAreaController {

	@Value("${message.locale}")	String locale;
	@Value("${root.area.dict.key}") String rootAreaDictKey;
	@Autowired
	private CommonAreaService commonAreaService;

	@Autowired
	private CommonDictService commonDictService;

	 
	@RequestMapping("/commonArea/list")
	public Page<CommonArea> list(HttpServletRequest request,CommonArea bean, @PathVariable("tenantId") String tenantId, @RequestParam("index") int pageIndex, @RequestParam("perPage") int perPage,  @RequestHeader("Authorization") String jwt) {
		PageHelper.startPage(pageIndex, perPage);
		Page<CommonArea> page = commonAreaService.getCommonAreaAll();
		return page;
	}
	
	@RequestMapping("/commonArea/subNodes/{nodeId}")
	public List<CommonAreaVo> subNodes(HttpServletRequest request,  @PathVariable("tenantId") String tenantId, @PathVariable("nodeId") Long nodeId) {
		if(nodeId == -1L) {
			Long rootNodeId=Long.parseLong(commonDictService.getCommonDictByIdTenantIdAndDictType(rootAreaDictKey, tenantId, null).getDictValue());
			return commonAreaService.subNodesById(rootNodeId);
		}
		List<CommonAreaVo> caList=commonAreaService.subNodesById(nodeId);
		return caList;
	}

	
	@RequestMapping(value = "/commonArea/parent/{parentId}",method = RequestMethod.GET)
	public Object getAreaByParentId(HttpServletRequest request, HttpServletResponse response,@PathVariable("parentId") Long parentId, @PathVariable String tenantId) {
		try{
			List<CommonArea> areaList= commonAreaService.getAreaByParentId(parentId);
				if(Collections.isEmpty(areaList)){
					throw new QslException(MessageKey.SUB_AREA_NOT_EXIST);
				}else{
					return areaList;
				}
		}catch (Exception e){
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	 
	@RequestMapping(value = "/commonArea",method = RequestMethod.POST)
	public ResponseEntity<String> save(@RequestBody CommonArea bean,HttpServletRequest request, HttpServletResponse response) {
		try{
			boolean success = commonAreaService.insert(bean);
				if(success){
					return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
				}else{
					throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
				}
		}catch (Exception e){
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	 
	@RequestMapping(value = "/commonArea",method = RequestMethod.PUT)
	public ResponseEntity<String> update(@RequestBody CommonArea bean,HttpServletRequest request, HttpServletResponse response) {
		try{
			Boolean success = commonAreaService.updateById(bean);
				if(success ){
					return new ResponseEntity<String>("SUCCESS",  HttpStatus.OK);
				}else{
					throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
				}
		}catch (Exception e){
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	 
	@RequestMapping(value = "/commonArea",method = RequestMethod.DELETE)
	public ResponseEntity<String> del(String id,HttpServletRequest request, HttpServletResponse response) {
		try{
			Boolean success = commonAreaService.deleteById(Long.parseLong(id));
				if(success ){
					return new ResponseEntity<String>("SUCCESS",  HttpStatus.OK);
				}else{
					throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
				}
		}catch (Exception e){
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

}