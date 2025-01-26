package com.matariky.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.google.protobuf.ServiceException;
import com.matariky.commonservice.minio.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * EasyExcel实现Excel Export 功能（工具类）
 *
 * @author chenyajun
 */
public class EasyExcelUtil {

    /**
     *  Export Excel文件到指定路径下
     *
     * @param excelName  Excel文件 Name
     * @param sheetName  Sheet页 Name
     * @param type       Excel需要转换的Type （对应实体类.class）
     * @param data       Excel需要 Export 的 Data 
     * @author chenyajun
     */
    public static void exportExcelPath(String targetPath,String excelName, String sheetName, Class type, List<?> data) {
        targetPath=targetPath+"\\";
        // 将指定的字符串连接到该字符串的末尾（设置文件的绝对路径）
        String fileName = targetPath.concat(excelName).concat(ExcelTypeEnum.XLSX.getValue());

        // 开始 Export 
        EasyExcel.write(fileName, type).sheet(sheetName).doWrite(data);
    }

    /**
     *  Export Excel文件到Web服务器下
     *
     * @param response  响应消息
     * @param excelName Excel文件 Name
     * @param sheetName Sheet页 Name
     * @param type      Excel需要转换的Type （对应实体类.class）
     * @param data      Excel需要 Export 的 Data 
     * @author chenyajun
     */
    public static void exportExcelWeb(HttpServletResponse response, String excelName, String sheetName, Class type, List<Object> data) {

        // 设置contentTypeType 
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");

        try {
            // 这里URLEncoder.encode可以防止中文乱码
            excelName = URLEncoder.encode(excelName, "UTF-8");

            // 设置请求头
            response.setHeader("Content-disposition", "attachment;filename=" + excelName + ExcelTypeEnum.XLSX.getValue());

            // 开始 Export 
            EasyExcel.write(response.getOutputStream(), type).sheet(sheetName).doWrite(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void exportExcelUploadPath(String excelName, String sheetName, Class type, List<?> data,MinioUtil minioUtil) {
        String bucket="rfidfactory";
        try {
            minioUtil.createBucket(bucket);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            InputStream stream = exportExcelStream(excelName, sheetName, type, data);
            minioUtil.uploadFile(stream, bucket, excelName+".xlsx");
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     *  Export Excel返回FileInputStream
     */
    public static FileInputStream exportExcelStream(String fileName, String sheetName, Class type, List<?> data) {
        try {
            // 临时目录 临时文件名
            String tmp = System.getProperty("java.io.tmpdir");
            File file = new File(tmp + fileName + ".xlsx");
            FileOutputStream fileOutputStream =  new FileOutputStream(file);
            ExcelWriter excelWriter = EasyExcel.write(fileOutputStream).build();
            WriteSheet writeSheet = EasyExcel.writerSheet(0,sheetName).head(type).build();
            excelWriter.write(data, writeSheet);
            excelWriter.finish();
            return new FileInputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
