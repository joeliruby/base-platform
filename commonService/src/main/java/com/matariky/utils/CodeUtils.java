package com.matariky.utils;

import java.security.SecureRandom;
import java.util.Random;

public class CodeUtils {

    /**
     * @param digit Number of digits
     * @return Randomly generate a number with the specified number of digits
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

    public static String CreateNo() {
        String datetime = DateUtil.getCurrentTime();
        String random = getNum(6);
        String code = datetime + random;
        return code;
    }

    public static String CreateBatchCode() {
        String datetime = DateUtil.getCurrentTime();
        String random = getNum(4);
        String code = datetime + random;
        return code;
    }

    public enum CsType {
        EP("EP", "Employee"),
        CM("CM", "Cash Register"),
        S("S", "Store"),
        VIP("S", "VIP Member"),
        OD("OD", "Order"),
        GD("GD", "Product"),
        P("P", "Payment"),
        PR("PR", "Refund"),
        PT("PT", "Print");

        /**
         * Type
         */
        private String type;

        /**
         * Description
         */
        private String desc;

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
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public static String randomHexGenerator() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int num = secureRandom.nextInt(16); // Generate a random number between 0-15
            sb.append(Integer.toHexString(num)); // Convert the random number to a hexadecimal character
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
