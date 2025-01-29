package com.matariky.commonservice.minio.controller;

import com.matariky.commonservice.minio.utils.MinioUtil;
import com.matariky.utils.AjaxResult;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api/v1/tenant/{tenantId}/file")
public class FileController {

	@Autowired
	MinioUtil minioUtil;

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult fileupload(@RequestParam MultipartFile uploadfile, @RequestParam String bucket,
			@RequestParam(required = false) String objectName) throws Exception {
		minioUtil.createBucket(bucket);
		if (objectName != null) {
			minioUtil.uploadFile(uploadfile.getInputStream(), bucket,
					objectName + "/" + uploadfile.getOriginalFilename());
		} else {
			minioUtil.uploadFile(uploadfile.getInputStream(), bucket, uploadfile.getOriginalFilename());
		}
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
	}

	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	@ResponseBody
	public void downloadFile(@RequestParam String bucket, @RequestParam String objectName,
			HttpServletResponse response) throws Exception {
		InputStream stream = minioUtil.download(bucket, objectName);
		ServletOutputStream output = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment;filename="
				+ URLEncoder.encode(objectName.substring(objectName.lastIndexOf("/") + 1), "UTF-8"));
		response.setContentType("application/octet-stream");
		response.setCharacterEncoding("UTF-8");
		IOUtils.copy(stream, output);
	}

}