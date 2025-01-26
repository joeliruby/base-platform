package com.matariky.automation.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.matariky.automation.bean.DataBaseBean;
import com.matariky.automation.bean.DbBean;
import com.matariky.automation.bean.RequestData;
import com.matariky.automation.utils.CheckedSession;
import com.matariky.automation.utils.LoginDb;
import com.matariky.automation.utils.WordUtil;

@Controller
public class DesignController {

	@Value("${file.url}")
	private String url;

	@GetMapping("design/index")
	public String admin(Model model, Pageable pageable) {
		return "design/index";
	}

	@PostMapping("design/exprot")
	@ResponseBody
	public RequestData index(HttpServletRequest request, DbBean model) {
		RequestData r = new RequestData();
		String key = CheckedSession.check(request);
		if (Objects.nonNull(model.getZdname())) {
			r.setCode("ERROR");
			r.setMsg("Error reading database Info");
			return r;
		}
		String[] tables = model.getZdname().split(",");
		List<DataBaseBean> list = new ArrayList<DataBaseBean>();
		for (String tabkey : tables) {
			String[] akey = tabkey.split("@");
			model.setTablename(akey[0]);
			DataBaseBean bean = new DataBaseBean();
			bean.setTablename(akey[0]);
			bean.setCtablename(akey[1]);
			try {
				bean.setList(LoginDb.login_DB_mysql_Table_TYPE(model));
				LoginDb.colseDb();
			} catch (Exception e) {
				LoginDb.colseDb();
				r.setCode("ERROR");
				r.setMsg("ERROR CONNECTING DB" + e.getMessage());
				return r;
			}

			list.add(bean);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
		dataMap.put("date", sdf.format(new Date()));
		dataMap.put("model", model);
		dataMap.put("tab", list);

		String keys = key + "(" + model.getDbname() + ")";

		try {
			WordUtil.createWord(dataMap, "sjksj.ftl", url, keys + ".doc");
		} catch (Exception e) {
			r.setCode("ERROR");
			r.setMsg("ERROR GENERATING WORD DOCUMENT:" + e.getMessage());
			return r;
		}
		try {
			r.setKey(keys);
			r.setCode("SUCCESS");
			r.setMsg("EXPORT SUCCESS！");
		} catch (Exception e) {
			r.setCode("ERROR");
			r.setMsg("EXCEPTION:" + e.getMessage());
			return r;
		}
		return r;
	}

	@GetMapping("design/download")
	public void download(String key, HttpServletResponse response) {
		try {
			WordUtil.downLoad(key + ".doc", url + "/" + key + ".doc", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
