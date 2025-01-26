package com.matariky.utils;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 单据编号 Generation  Rule 
 *
 * @author chenyajun
 */
public class CodeUtils
{

//    public static void main(String[] args){
//        CodeUtils codeUtils = new CodeUtils();
//
//        String code = CodeUtils.CreateCode(OrderType.DIC);
//
//        System.out.println(code);
//    }

    /**
     * @param digit 位数
     * @return 随机 Generation digit位数的数字
     */
    public static String getNum(int digit) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < digit; i++) {
            if (i == 0 && digit > 1)
                str.append(new Random().nextInt(9) + 1);
            else
                str.append(new Random().nextInt(10));
        }
        return str.toString();
    }




    public static String CreateNo(){
        String datetime = DateUtil.getCurrentTime();
        String random = getNum(6);
        String code=datetime+random;
        return code;
    }

    public static String CreateBatchCode(){
        String datetime = DateUtil.getCurrentTime();
        String random = getNum(4);
        String code=datetime+random;
        return code;
    }

    public enum CsType{
        EP("EP","员工"),
        CM("CM","收银机"),
        S("S","店铺"),
        VIP("S","会员"),
        OD("OD","订单"),
        GD("GD","商品"),
        P("P","支付"),
        PR("PR","退款"),
        PT("PT"," Print "),
        ;
        /**
         * Type 
         */
        private String type;

        /**
         * 描述
         */
        private String desc ;

        CsType(String type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }
    }

    public static String generateRandomString(int length) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public static String randomHexGenerator(){
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int num = secureRandom.nextInt(16); //  Generation 0-15的随机数
            sb.append(Integer.toHexString(num)); // 将随机数转换为16进制字符
        }
        String hexString = sb.toString();
        return hexString;
    }

    public static String generateEpc(int length) {
        String HEX_CHARACTERS = "0123456789ABCDEF";
        Random RANDOM = new SecureRandom();
        if (length < 1) {
            throw new IllegalArgumentException("Length must be a positive integer.");
        }
        StringBuilder hexString = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            hexString.append(HEX_CHARACTERS.charAt(RANDOM.nextInt(HEX_CHARACTERS.length())));
        }
        return hexString.toString();
    }
}
