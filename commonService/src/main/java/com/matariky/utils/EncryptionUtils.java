package com.matariky.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtils {

    /**
     * Since MD5 and SHA-1 are developed from MD4, their structure and strength have
     * many similarities
     * The biggest difference of Sha-1 and MD5 is that its abstract is 32 bites
     * longer than MD5 summary (1byte = 8bit, which is equivalent to 4BYTE
     * , More than 8 characters more than MD5 after converting hexadecimal).
     * For forcibly attack,: MD5 is the 2128 Quantity level Operation, SHA-1 is the
     * 2160 Quantity level Operation.
     * Difficulty for the two packets of the same abstract: MD5 is 264 is the
     * quantity level of Operation, SHA-1 is 280 Quantity level
     * Operation.
     * Therefore, SHA-1 has a greater intensity for forced attacks. However, since
     * SHA-1's cycle steps are more than MD5 (80:64) and the cache to be processed
     * is large (160 bit: 128 Bit)
     * , SHA-1 runs slower than MD5.
     * 
     * @param source   The encryption string needs to be encrypted
     * @param hashType encryption Type （MD5 and SHA）
     * @return
     */
    public static String getHash(String source, String hashType) {
        // It is used to convert bytes into hexadecimal representations
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

        try {
            MessageDigest md = MessageDigest.getInstance(hashType);
            md.update(source.getBytes()); // By using Update Method to process Data to make the specified Byte array
                                          // UPDATE Abstract (why do you need to use Update Method first
                                          // Why not use in some MD5 METHOD？)
            byte[] encryptStr = md.digest(); // Get the ciphertext to complete the hash calculation, generate 128 -bit
                                             // long integer
            char str[] = new char[16 * 2]; // If each byte is expressed in hexadecimal, use two characters
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { // From the ONE byte Start, convert to the conversion of hexadecimal
                                           // characters for each ONE byte
                byte byte0 = encryptStr[i]; // Take the i -Le byte
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // Take the number of numbers in the height of the byte, >>>
                                                         // for the logical right movement, move the symbol position to
                                                         // the right
                str[k++] = hexDigits[byte0 & 0xf]; // Take 4 -bit digital conversion by byte
            }
            return new String(str); // After changing the result, convert to a string
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param source   The encryption string needs to be encrypted
     * @param hashType encryption Type （MD5 and SHA）
     * @return
     */
    public static String getHash2(String source, String hashType) {
        StringBuilder sb = new StringBuilder();
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance(hashType);
            md5.update(source.getBytes());
            for (byte b : md5.digest()) {
                sb.append(String.format("%02X", b)); // 10 inlet -to -6 hexadecimal, x means output in the form of
                                                     // hexadecimal, 02 indicates that less than two places are not
                                                     // enough to make up 0 output in front of the 0 output
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param source   The encryption string needs to be encrypted
     * @param hashType encryption Type （MD5 and SHA）
     * @return
     */
    public static String getHash3(String source, String hashType) {
        // It is used to convert bytes into hexadecimal representations
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

        StringBuilder sb = new StringBuilder();
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance(hashType);
            md5.update(source.getBytes());
            byte[] encryptStr = md5.digest();
            for (int i = 0; i < encryptStr.length; i++) {
                int iRet = encryptStr[i];
                if (iRet < 0) {
                    iRet += 256;
                }
                int iD1 = iRet / 16;
                int iD2 = iRet % 16;
                sb.append(hexDigits[iD1] + "" + hexDigits[iD2]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }
}