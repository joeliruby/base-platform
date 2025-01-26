package com.matariky.automation.controller;

import com.matariky.automation.bean.Db;
import com.matariky.automation.bean.DbBean;
import com.matariky.automation.bean.RequestData;
import com.matariky.automation.bean.logs;
import com.matariky.automation.utils.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

	@Value("${file.url}")
	private String url;

	@GetMapping("index")
	public String index(HttpServletRequest request, Model model, @PageableDefault Pageable pageable) {
		model.addAttribute("pag", pageable.getPageNumber());
		model.addAttribute("pags", pageable.getPageSize());
		return "logs/index";
	}

	@GetMapping("admin/index")
	public String admin(Model model, Pageable pageable) {
		return "administrator/index";
	}

	@GetMapping("admin/loginDB")
	@ResponseBody
	public RequestData loginDB(Db bean) {
		RequestData r = new RequestData();
		try {
			r.setCode("SUCCESS");
			r.setListData(LoginDb.login_DB_Mysql_TABLE(bean));
			r.setMsg("SUCCESS READING TABLE！");
		} catch (Exception e) {
			r.setCode("ERROR");
			r.setMsg("ERROR CONNECTING TO DB:" + e.getMessage());
			LoginDb.colseDb();
			return r;
		}
		return r;
	}

	@GetMapping("admin/loginDBBytable")
	@ResponseBody
	public RequestData loginDBBytable(Db bean) {
		RequestData r = new RequestData();
		try {
			r.setCode("SUCCESS");
			r.setListData(LoginDb.login_DB_mysql_Table_TYPE(bean));
			r.setMsg("SUCCESS READING TABLE ATTRIBUTES！");
		} catch (Exception e) {
			r.setCode("ERROR");
			r.setMsg("ERROR CONNECTING TO DB:" + e.getMessage());
			LoginDb.colseDb();
			return r;
		}
		return r;
	}

	@GetMapping("admin/downloadZip")
	public void loginDBBytable(String key, HttpServletResponse response) {
		try {
			ZipUtils.downLoadZip(key + ".zip", url + "/zip" + key + "/" + key + ".zip", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("admin/exprot")
	@ResponseBody
	public RequestData index(HttpServletRequest request, DbBean model) {
		RequestData r = new RequestData();
		r.setCode("SUCCESS");
		String paramKey = CheckedSession.check(request);
		String key = paramKey.replace(":", "_");
		Map<String, String> map = new HashMap<>();
		try {
			map = GenerateFile.initDir(url, key, model.getMk(), GenerateJava.captureName(model.getTablename()));
		} catch (IOException e) {
			r.setCode("ERROR");
			r.setMsg("ERROR GENERATING FILE:" + e.getMessage());
			return r;
		}

		try {
			GenerateFile.writeTxtFile(GenerateJava.GenerateJavaCode(model), new File(map.get("bean")));
			GenerateFile.writeTxtFile(GenerateMapper.get_mapper(model), new File(map.get("mapper")));
			GenerateFile.writeTxtFile(GenerateMapper.get_mapperXml(model), new File(map.get("mapperxml")));
			GenerateFile.writeTxtFile(GenerateService.get_Service(model), new File(map.get("service")));
			GenerateFile.writeTxtFile(GenerateController.get_controller(model), new File(map.get("controller")));
		} catch (Exception e) {
			r.setCode("ERROR");
			r.setMsg("ERROR WRITING FILE" + e.getMessage());
			return r;
		}
		try {
			ZipUtils.toZip(url + "/" + key, new FileOutputStream(new File(url + "/zip" + key + "/" + key + ".zip")),
					true);
		} catch (Exception e) {
			r.setCode("ERROR");
			r.setMsg("ERROR ZIPPING FILE:" + e.getMessage());
			return r;
		}
		DelFile.delFolder(url + "/" + key);
		logs log = new logs();
		log.setIp(IpUtil.getIpAddr(request));
		log.setDb(model.getDbtype());
		log.setPageurl(model.getCompages());
		log.setDbip(model.getDbip());
		log.setDbname(model.getDbname());
		log.setDbpassword(model.getDbpassword());
		log.setDbusername(model.getDbusername());
		log.setTablename(model.getTablename());
		log.setDownurl(key + ".zip");
		r.setMsg("SUCCESS GENERATING FILE！DOWNLOAD UNDER SUBMIT BUTTON.");
		r.setKey(key);

		return r;
	}

}
