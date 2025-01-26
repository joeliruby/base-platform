package com.matariky.commonservice.base.controller;

import com.matariky.commonservice.minio.utils.MinioUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/readerFile")
public class ReaderFileController {

    @Autowired
    MinioUtil minioUtil;

    @ApiOperation(value = "获取reader升级包文件")
    @GetMapping("/reader.tar.gz")
    public void test(HttpServletResponse response) throws IOException {
        InputStream stream = null;
        try {
            stream = minioUtil.download("device-upload-package", "reader.tar.gz");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=reader.tar.gz");
        ServletOutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = stream.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        stream.close();
        out.flush();
        out.close();
    }

}
