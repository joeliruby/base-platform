package com.matariky.utils;

import javax.activation.MimetypesFileTypeMap;

/**
 * @description: 图片工具类
 * @author: bo.chen
 * @create: 2023/10/16 17:11
 **/
public class ImageUtils {
    private static MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap();

    static {
        mimetypesFileTypeMap.addMimeTypes("image png tif jpg jpeg bmp");
    }

    /**
     * @Description:  Wether 为图片
     * @Author: bo.chen
     * @Date: 2023/10/16 17:14
     * @param fileName
     * @return boolean
     **/
    public static boolean isImage(String fileName) {
        String mimetype = mimetypesFileTypeMap.getContentType(fileName);
        String type = mimetype.split("/")[NumberUtils.INTEGER_ZERO];
        return type.equals("image");
    }
}
