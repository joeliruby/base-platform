package com.matariky.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class FileUtil {

    /**
     * 转换MultipartFile对象为java.io.FileType 
     *
     * @param multipartFile
     * @return
     */
    public static File convertMultipartFileToFile(MultipartFile multipartFile) {
        File result = null;
        try {
            /**
             * UUID.randomUUID().toString()是javaJDK提供的一个自动 Generation 主键的方法。
             * UUID(Universally Unique Identifier)全局唯一标识符,是指在一台机器上 Generation 的数字，
             * 它保证对在同一时空中的所有机器都是唯一的，是由一个十六位的数字组成,表现出来的形式。
             * 由以下几部分的组合：当前日期和 Time (UUID的第一个部分与 Time 有关，如果你在 Generation 一个UUID之后，
             * 过几秒又 Generation 一个UUID，则第一个部分不同，其余相同)，时钟序列，
             * 全局唯一的IEEE机器识别号（如果有网卡，从网卡获得，没有网卡以其他方式获得），
             * UUID的唯一缺陷在于 Generation 的结果串会比较长。
             *
             *
             * File.createTempFile和File.createNewFile()的区别：
             *  后者只是创建文件，而前者可以给文件名加前缀和后缀
             */
            //这里对 Generation 的文件名加了UUID随机 Generation 的前缀,后缀是null
            result = File.createTempFile(UUID.randomUUID().toString(), null);
            multipartFile.transferTo(result);
            result.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据url获取文件对象
     *
     * @param fileUrl
     * @return
     */
    public static File downloadFile(String fileUrl) {
        File result = null;
        BufferedInputStream bis=null;
        BufferedOutputStream bos=null;
        try {
            result = File.createTempFile(UUID.randomUUID().toString(), null);
            URL url = new URL(fileUrl);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(3000);
            bis = new BufferedInputStream(connection.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(result));
            byte[] car = new byte[1024];
            int l = 0;
            while ((l = bis.read(car)) != -1) {
                bos.write(car, 0, l);
            }
            if(bis!=null)
            bis.close();
            if(bos!=null)
            bos.close();
        } catch (Exception e) {
        	try {
        	 if(bis!=null)
                 bis.close();
             if(bos!=null)
                 bos.close();
        	}
        	catch(IOException ioe) {
        		ioe.printStackTrace();
        	}
            e.printStackTrace();
        }
        finally{
        	try {
           	 if(bis!=null)
                    bis.close();
                if(bos!=null)
                    bos.close();
           	}
           	catch(IOException ioe) {
           		ioe.printStackTrace();
           	}
               
        }
        return result;
    }

    /**
     * @param request
     * @return
     */
    public static String getRealPath(HttpServletRequest request) {
        ServletContext sc = request.getSession().getServletContext();
        String uploadDir = sc.getRealPath("/upload");
        return uploadDir;
    }

    public static boolean saveFile(String savePath, String fileFullName, MultipartFile file) throws IOException {
        File uploadFile = new File(savePath + fileFullName);
        FileUtils.writeByteArrayToFile(new File(savePath, fileFullName), file.getBytes());
        return uploadFile.exists();
    }



}
