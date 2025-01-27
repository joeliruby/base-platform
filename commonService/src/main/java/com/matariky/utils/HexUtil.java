package com.matariky.utils;

import java.awt.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Sixteen -proof (abbreviated as HEX or down bidding 16) is a kind of inlet
 * system every 16 in 1 in mathematics. Generally, use numbers 0 to 9 and
 * alphabet A to F (where: a ~ f is 10 ~ 15 To. <br>
 * For example, the number of decimal system 57, in binary writing 111001, in
 * hexadecimal writing 39. <br>
 * Language like Java, C In order to distinguish the value of hexada and
 * decimal, it will add 0x to the front of the hexadecimal number. For example,
 * 0x20 is a decimal 32 instead of the decimal 20 <br>
 * <p>
 * Reference: https://my.oschina.net/xinxingegeya/blog/287476
 *
 */
public class HexUtil {

	/**
	 * Lowwriter arrays for the establishment of the output of hexadecimal
	 * characters
	 */
	private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };
	/**
	 * The uppercase character array for the establishment of the output of
	 * hexadecimal characters
	 */
	private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	/**
	 * Determine the given string Wether is hexadecimal number <br>
	 * If it is, you need to use the corresponding number Type Object
	 * <Code> Decode </code> method <br>
	 * <br>
	 * For example: {@Code Integer.Decode} Method decoding Inttype's hexadecimal
	 * number
	 *
	 * @param value value
	 * @Return Wether is hexadecimal
	 */
	public static boolean isHexNumber(String value) {
		final int index = (value.startsWith("-") ? 1 : 0);
		if (value.startsWith("0x", index) || value.startsWith("0X", index) || value.startsWith("#", index)) {
			try {
				Long.decode(value);
			} catch (NumberFormatException e) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Convert byte array to array of hexadecimal character array
	 *
	 * @param data byte[]
	 * @return hexadecimal char[]
	 */
	public static char[] encodeHex(byte[] data) {
		return encodeHex(data, true);
	}

	/**
	 * Convert byte array to array of hexadecimal character array
	 *
	 * @param str     String
	 * @param charset Code
	 * @return hexadecimal char[]
	 */
	public static char[] encodeHex(String str, Charset charset) {
		return encodeHex(StrUtil.bytes(str, charset), true);
	}

	/**
	 * Convert byte array to array of hexadecimal character array
	 *
	 * @param data        byte[]
	 * @param toLowerCase <code>true</code> Pass to a lowercase format ,
	 *                    <code>false</code>Change to a capital format
	 * @return hexadecimal char[]
	 */
	public static char[] encodeHex(byte[] data, boolean toLowerCase) {
		return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
	}

	/**
	 * Convert byte array to hexadecimal string
	 *
	 * @param data byte[]
	 * @return hexadecimal String
	 */
	public static String encodeHexStr(byte[] data) {
		return encodeHexStr(data, true);
	}

	/**
	 * Convert the byte array to a hexadecimal strings, and the result is a
	 * lowercase
	 *
	 * @param data    被 Code String
	 * @param charset Code
	 * @return hexadecimal String
	 */
	public static String encodeHexStr(String data, Charset charset) {
		return encodeHexStr(StrUtil.bytes(data, charset), true);
	}

	/**
	 * Convert the byte array to a hexadecimal string, the result is a lowercase,
	 * and the default code isUTF-8
	 *
	 * @param data Code string
	 * @return Sixteen -in -made String
	 */
	public static String encodeHexStr(String data) {
		return encodeHexStr(data, StandardCharsets.UTF_8);
	}

	/**
	 * Convert byte array to hexadecimal string
	 *
	 * @param data        byte[]
	 * @param toLowerCase <code>true</code> Pass to a lowercase format ,
	 *                    <code>false</code> Change to a capital format
	 * @return hexadecimal String
	 */
	public static String encodeHexStr(byte[] data, boolean toLowerCase) {
		return encodeHexStr(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
	}

	/**
	 * Convert the hexadecimal character array to a string, default Code UTF-8
	 *
	 * @param hexStr hexadecimal String
	 * @return String
	 */
	public static String decodeHexStr(String hexStr) {
		return decodeHexStr(hexStr, StandardCharsets.UTF_8);
	}

	/**
	 * Converts a hexadecimal character array to a string
	 *
	 * @param hexStr  hexadecimal String
	 * @param charset Charset
	 * @return String
	 */
	public static String decodeHexStr(String hexStr, Charset charset) {
		if (StrUtil.isEmpty(hexStr)) {
			return hexStr;
		}
		return decodeHexStr(hexStr.toCharArray(), charset);
	}

	/**
	 * Converts a hexadecimal character array to a string
	 *
	 * @param hexData hexadecimal char[]
	 * @param charset Charset
	 * @return String
	 */
	public static String decodeHexStr(char[] hexData, Charset charset) {
		return StrUtil.str(decodeHex(hexData), charset);
	}

	/**
	 * Converts a hexadecimal character array to a byte array
	 *
	 * @param hexData hexadecimal char[]
	 * @return byte[]
	 * @throws RuntimeException if the source hexadecimal character array has an odd
	 *                          length, a runtime exception will be thrown
	 */
	public static byte[] decodeHex(char[] hexData) {

		int len = hexData.length;

		if ((len & 0x01) != 0) {
			throw new RuntimeException("Odd number of characters.");
		}

		byte[] out = new byte[len >> 1];

		// two characters form the hex value.
		for (int i = 0, j = 0; j < len; i++) {
			int f = toDigit(hexData[j], j) << 4;
			j++;
			f = f | toDigit(hexData[j], j);
			j++;
			out[i] = (byte) (f & 0xFF);
		}

		return out;
	}

	/**
	 * Decodes a hexadecimal string into a byte array
	 *
	 * @param hexStr hexadecimal String
	 * @return byte[]
	 */
	public static byte[] decodeHex(String hexStr) {
		if (StrUtil.isEmpty(hexStr)) {
			return null;
		}
		return decodeHex(hexStr.toCharArray());
	}

	// ----------------------------------------------------------------------------------------
	// Color

	/**
	 * Converts {@link Color} to Hex format
	 *
	 * @param color {@link Color}
	 * @return Hex String
	 * @since 3.0.8
	 */
	public static String encodeColor(Color color) {
		return encodeColor(color, "#");
	}

	/**
	 * Converts {@link Color} to Hex format
	 *
	 * @param color  {@link Color}
	 * @param prefix prefix string, can be #, 0x, etc.
	 * @return Hex String
	 * @since 3.0.8
	 */
	public static String encodeColor(Color color, String prefix) {
		final StringBuilder builder = new StringBuilder(prefix);
		String colorHex;
		colorHex = Integer.toHexString(color.getRed());
		if (1 == colorHex.length()) {
			builder.append('0');
		}
		builder.append(colorHex);
		colorHex = Integer.toHexString(color.getGreen());
		if (1 == colorHex.length()) {
			builder.append('0');
		}
		builder.append(colorHex);
		colorHex = Integer.toHexString(color.getBlue());
		if (1 == colorHex.length()) {
			builder.append('0');
		}
		builder.append(colorHex);
		return builder.toString();
	}

	/**
	 * Converts a Hex color value to {@link Color}
	 *
	 * @param hexColor Hex color value, can start with # or 0x
	 * @return {@link Color}
	 * @since 3.0.8
	 */
	public static Color decodeColor(String hexColor) {
		return Color.decode(hexColor);
	}

	/**
	 * Converts the specified int value to a Unicode string format, commonly used to
	 * convert special characters (e.g. Chinese characters) to Unicode format
	 * If the converted string is less than 4 digits after u, it will be padded with
	 * 0 in front, for example:
	 *
	 * <pre>
	 * '我' => \u4f60
	 * </pre>
	 *
	 * @param value int value, can also be char
	 * @return Unicode representation
	 */
	public static String toUnicodeHex(int value) {
		final StringBuilder builder = new StringBuilder(6);

		builder.append("\\u");
		String hex = toHex(value);
		int len = hex.length();
		if (len < 4) {
			builder.append("0000", 0, 4 - len);// Pad with 0 if less than 4 digits
		}
		builder.append(hex);

		return builder.toString();
	}

	/**
	 * Converts the specified char value to a Unicode string format, commonly used
	 * to convert special characters (e.g. Chinese characters) to Unicode format
	 * If the converted string is less than 4 digits after u, it will be padded with
	 * 0 in front, for example:
	 *
	 * <pre>
	 * '我' => \u4f60
	 * </pre>
	 *
	 * @param ch char value
	 * @return Unicode representation
	 * @since 4.0.1
	 */
	public static String toUnicodeHex(char ch) {
		return "\\u" + //
				DIGITS_LOWER[(ch >> 12) & 15] + //
				DIGITS_LOWER[(ch >> 8) & 15] + //
				DIGITS_LOWER[(ch >> 4) & 15] + //
				DIGITS_LOWER[(ch) & 15];
	}

	/**
	 * Converts to a hexadecimal string
	 *
	 * @param value int value
	 * @return Hexadecimal string
	 * @since 4.4.1
	 */
	public static String toHex(int value) {
		return Integer.toHexString(value);
	}

	/**
	 * Converts to a hexadecimal string
	 *
	 * @param value int value
	 * @return Hexadecimal string
	 * @since 4.4.1
	 */
	public static String toHex(long value) {
		return Long.toHexString(value);
	}

	/**
	 * Converts the byte value to hexadecimal and adds it to {@link StringBuilder}
	 *
	 * @param builder     {@link StringBuilder}
	 * @param b           byte
	 * @param toLowerCase Whether to use lowercase
	 * @since 4.4.1
	 */
	public static void appendHex(StringBuilder builder, byte b, boolean toLowerCase) {
		final char[] toDigits = toLowerCase ? DIGITS_LOWER : DIGITS_UPPER;

		int high = (b & 0xf0) >>> 4;// High bits
		int low = b & 0x0f;// Low bits
		builder.append(toDigits[high]);
		builder.append(toDigits[low]);
	}

	// ----------------------------------------------------------------------------------------
	// Private method start

	/**
	 * Converts a byte array to a hexadecimal string
	 *
	 * @param data     byte[]
	 * @param toDigits char[] used to control output
	 * @return hexadecimal String
	 */
	private static String encodeHexStr(byte[] data, char[] toDigits) {
		return new String(encodeHex(data, toDigits));
	}

	/**
	 * Converts a byte array to a hexadecimal character array
	 *
	 * @param data     byte[]
	 * @param toDigits char[] used to control output
	 * @return hexadecimal char[]
	 */
	private static char[] encodeHex(byte[] data, char[] toDigits) {
		final int len = data.length;
		final char[] out = new char[len << 1];// len*2
		// two characters from the hex value.
		for (int i = 0, j = 0; i < len; i++) {
			out[j++] = toDigits[(0xF0 & data[i]) >>> 4];// High bits
			out[j++] = toDigits[0x0F & data[i]];// Low bits
		}
		return out;
	}

	/**
	 * Converts a hexadecimal character to an integer
	 *
	 * @param ch    hexadecimal char
	 * @param index position of the hexadecimal character in the char array
	 * @return integer
	 * @throws RuntimeException when ch is not a valid hexadecimal character, a
	 *                          runtime exception is thrown
	 */
	private static int toDigit(char ch, int index) {
		int digit = Character.digit(ch, 16);
		if (digit == -1) {
			throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
		}
		return digit;
	}

}