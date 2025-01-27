package com.matariky.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.matariky.commonservice.base.service.ExcelService;
import com.matariky.commonservice.minio.utils.MinioUtil;
import com.matariky.excel.ExcelSelected;
import com.matariky.excel.ExcelSelectedResolve;
import com.matariky.excel.SelectedSheetWriteHandler;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EasyExcelUtil {

    public static void exportExcelPath(String targetPath, String excelName, String sheetName, Class<?> type,
            List<?> data) {
        targetPath = targetPath + "\\";
        // Connect the specified string to the end of the string( Configuration The
        // absolute path of the file)
        String fileName = targetPath.concat(excelName).concat(ExcelTypeEnum.XLSX.getValue());

        // Start Export
        EasyExcel.write(fileName, type).sheet(sheetName).doWrite(data);
    }

    public static void exportExcelWeb(HttpServletResponse response, String excelName, String sheetName, Class<?> type,
            List<Object> data) {

        // ConfigurationcontentTypeType
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");

        try {
            // URLEncoder.encodeCan prevent Chinese garbled
            excelName = URLEncoder.encode(excelName, "UTF-8");

            // Configuration Request header
            response.setHeader("Content-disposition",
                    "attachment;filename=" + excelName + ExcelTypeEnum.XLSX.getValue());

            // Start Export
            EasyExcel.write(response.getOutputStream(), type).sheet(sheetName).doWrite(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportExcelUploadPath(String excelName, String sheetName, Class<?> type, List<?> data,
            MinioUtil minioUtil) {
        String bucket = "rfidfactory";
        try {
            minioUtil.createBucket(bucket);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            InputStream stream = exportExcelStream(excelName, sheetName, type, data);
            minioUtil.uploadFile(stream, bucket, excelName + ".xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Export Excel return FileInputStream
     */
    public static FileInputStream exportExcelStream(String fileName, String sheetName, Class<?> type, List<?> data) {
        try {
            // Temporary directory temporary File Name
            String tmp = System.getProperty("java.io.tmpdir");
            File file = new File(tmp + fileName + ".xlsx");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ExcelWriter excelWriter = EasyExcel.write(fileOutputStream).build();
            WriteSheet writeSheet = EasyExcel.writerSheet(0, sheetName).head(type).build();
            excelWriter.write(data, writeSheet);
            excelWriter.finish();
            return new FileInputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create Will Export sheet（sheet The page contains a column with a drop -down
     * box）
     *
     * @param head      Export Head Information and Configuration
     * @param sheetNo   sheet index
     * @param sheetName sheet Name
     * @param <T>       Generic
     * @return sheet
     */
    public static <T> WriteSheet writeSelectedSheet(Class<T> head, Integer sheetNo, String sheetName,
            ExcelService excelService, String tenantId) {
        Map<Integer, ExcelSelectedResolve> selectedMap = resolveSelectedAnnotation(head, excelService, tenantId);

        return EasyExcel.writerSheet(sheetNo, sheetName)
                .head(head)
                .registerWriteHandler(new SelectedSheetWriteHandler(selectedMap))
                .build();
    }

    /**
     * Analyze the drop -down solution in the head class
     *
     * @param head Surface
     * @param <T>  Generic
     * @return Map<Drop -down box index, Drop -down box Content> map
     */
    private static <T> Map<Integer, ExcelSelectedResolve> resolveSelectedAnnotation(Class<T> head,
            ExcelService excelService, String tenantId) {
        Map<Integer, ExcelSelectedResolve> selectedMap = new HashMap<>();

        // getDeclaredFields(): Back to the attributes of all declarations；getFields():
        // return publicType property
        Field[] fields = head.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            // Analysis annotation Information
            ExcelSelected selected = field.getAnnotation(ExcelSelected.class);
            ExcelProperty property = field.getAnnotation(ExcelProperty.class);
            if (selected != null) {
                ExcelSelectedResolve excelSelectedResolve = new ExcelSelectedResolve();
                String[] source = excelSelectedResolve.resolveSelectedSource(selected, excelService);
                if (source != null && source.length > 0) {
                    excelSelectedResolve.setSource(source);
                    excelSelectedResolve.setFirstRow(selected.firstRow());
                    excelSelectedResolve.setLastRow(selected.lastRow());
                    if (property != null && property.index() >= 0) {
                        selectedMap.put(property.index(), excelSelectedResolve);
                    } else {
                        selectedMap.put(i, excelSelectedResolve);
                    }
                }
            }
        }
        return selectedMap;
    }

    public static boolean isIDNumber(String IDNumber) {
        if (IDNumber == null || "".equals(IDNumber)) {
            return false;
        }
        // Define the regular expression of judging the User ID number (15 or 18 -bit,
        // the last one can be a letter)
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|"
                +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        // Assume that 18 -bit ID number: 4100011991010101123X 410001 19910101 123X
        // ^The beginning
        // [1-9] ONE 4 in the first place 1-9
        // \\ d {5} Five digits 10001 (first six provinces, cities and counties)
        // (18 | 19 | 20) 19 (Perhaps the value range of the current stage 18xx-20xx
        // year)
        // \\ d {2} 91 (year)
        // ((0 [1-9]) | (10 | 11 | 12)) 01 (month)
        // (([0-2] [1-9]) | 10 | 20 | 30 | 31) 01 (date)
        // \\ d {3} Three -digit number 123 (the seventeenth number of strange numbers
        // represents male, and the even number represents female)
        // [0-9xx] 0123456789xx. ONE X (eighteen digits for verification value)
        // $ end

        // Assume that 15 -digit ID number: 410001910101123 410001 910101 123
        // ^The beginning
        // [1-9] ONE 4 in the first place 1-9
        // \\ d {5} Five digits 10001 (first six provinces, cities and counties)
        // \\ d {2} 91 (year)
        // ((0 [1-9]) | (10 | 11 | 12)) 01 (month)
        // (([0-2] [1-9]) | 10 | 20 | 30 | 31) 01 (date)
        // \\ d {3} Three -digit number 123 (the fifteenth of the fifteenth digits
        // represents male, and the even number represents female), 15 digits of ID
        // cards without X
        // $ end
        boolean matches = IDNumber.matches(regularExpression);

        // Determine the 18th school verification value
        if (matches) {

            if (IDNumber.length() == 18) {
                try {
                    char[] charArray = IDNumber.toCharArray();
                    // The top 17 weighted factors
                    int[] idCardWi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
                    // This is the verification code corresponding to the 11 -bit remaining after 11
                    String[] idCardY = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
                        return true;
                    } else {
                        return false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return false;
        }
        return matches;
    }

    public static boolean isMobile(String phone) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        // Verify mobile phone number
        String s2 = "^[1](([3|5|6|7|8|9][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$";
        if (StringUtils.isNotBlank(phone)) {
            p = Pattern.compile(s2);
            m = p.matcher(phone);
            b = m.matches();
        }
        return b;
    }
}
