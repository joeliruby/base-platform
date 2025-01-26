package com.matariky.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class ExcelTemplateUtil {
	
	/**
     * Download Import 文件
     * @param response
     * @param inFileName
     * @param outFileNam
     */
    public void downloadExcel(HttpServletResponse response, String inFileName, String outFileNam) {
        InputStream inputStream = null;
        try {
            response.reset();
            //设置输出文件格式
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(outFileNam.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            ServletOutputStream outputStream = response.getOutputStream();
            inputStream = this.getClass().getResourceAsStream("/template/"+inFileName);
            byte[] buff = new byte[1024];
            int length;
            while ((length = inputStream.read(buff)) != -1) {
                outputStream.write(buff, 0, length);
            }
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
 
                try {
                    inputStream.close();
                } catch (IOException e) {
                    //log.error("关闭资源出错" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }


}
