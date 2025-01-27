package com.matariky.utils;

import javax.activation.MimetypesFileTypeMap;

public class ImageUtils {
    private static MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap();

    static {
        mimetypesFileTypeMap.addMimeTypes("image png tif jpg jpeg bmp");
    }

    public static boolean isImage(String fileName) {
        String mimetype = mimetypesFileTypeMap.getContentType(fileName);
        String type = mimetype.split("/")[NumberUtils.INTEGER_ZERO];
        return type.equals("image");
    }
}
