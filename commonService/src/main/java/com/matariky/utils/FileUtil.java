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
     * Convert Multipartfile Object to java.io.Filetype
     *
     * @param multipartFile
     * @return
     */
    public static File convertMultipartFileToFile(MultipartFile multipartFile) {
        File result = null;
        try {
            /**
             * UUID.RANDOMUUID (). Tostring () is the One Automatic Generation Primary Key
             * provided by Javajdk
             * Method.
             * UUID (Universally Unique Identifier) ​​global Unique identifier refers to the
             * number of generation on a machine,
             * It guarantees that all machines in the same time are unique, and it is
             * composed of one 16 digit number, which shows a form.
             * Combination of the following parts: Current date is related to time (UUID's
             * first one part and time, if you are in the Generation One
             * UUID after UUID
             * ,
             * After a few seconds, the Generation One UUID, then the first one is
             * different, the rest is the same), the clock sequence,
             * Global UNIQUE's IEEE machine recognition number (if there is a network card,
             * get from the network card, no network card is obtained in other ways),
             * UUID's unique defect is that the results of Generation will be longer.
             *
             *
             * File.createTempfile and File.createnewfile ():
             * The latter is just a Create file, and the former can add prefix and suffix to
             * the File Name
             */
            // Add the prefix of the UUID random generation to the file name of Generation,
            // and the suffix is ​​NULL
            result = File.createTempFile(UUID.randomUUID().toString(), null);
            multipartFile.transferTo(result);
            result.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * According to the URL RETRIEVE file Object
     *
     * @param fileUrl
     * @return
     */
    public static File downloadFile(String fileUrl) {
        File result = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
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
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        } catch (Exception e) {
            try {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            } catch (IOException ioe) {
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
