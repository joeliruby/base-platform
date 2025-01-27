package com.matariky.utils;

import java.nio.charset.StandardCharsets;

/**
 * Unified resource positioning related tools
 *
 *
 */
public class URLUtil {

	/**
	 * Standardized URL string, including:
	 *
	 * <pre>
	 * 1. multiple/replace it with one
	 * </pre>
	 *
	 * @param url url string
	 * @Return Standardized URL string
	 */
	public static String normalize(String url) {
		return normalize(url, false, false);
	}

	/**
	 * Standardized URL string, including:
	 *
	 * <pre>
	 * 1. multiple/replace it with one
	 * </pre>
	 *
	 * @param url           url string
	 * @param Isencodebody  Wether Turning the Chinese and Speicial characters in
	 *                      the Body part of the URL (excluding HTTP: and/)
	 * @param IsencodeParam Wether to turn to the Chinese and Speicial characters in
	 *                      the Parameter part in the URL
	 * @Return Standardized URL string
	 * @SINCE 4.4.1
	 */
	public static String normalize(String url, boolean isEncodeBody, boolean isEncodeParam) {
		if (StrUtil.isBlank(url)) {
			return url;
		}
		final int sepIndex = url.indexOf("://");
		String pre;
		String body;
		if (sepIndex > 0) {
			pre = StrUtil.subPre(url, sepIndex + 3);
			body = StrUtil.subSuf(url, sepIndex + 3);
		} else {
			pre = "http://";
			body = url;
		}

		final int paramsSepIndex = StrUtil.indexOf(body, '?');
		String params = null;
		if (paramsSepIndex > 0) {
			params = StrUtil.subSuf(body, paramsSepIndex + 1);
			body = StrUtil.subPre(body, paramsSepIndex);
		}

		// Remove the beginning \ or/
		body = body.replaceAll("^[\\\\/]+", StrUtil.EMPTY);
		// Replace multiple \ or/as/ Single /
		body = body.replace("\\", "/").replaceAll("//+", "/");
		if (isEncodeBody) {
			body = URLEncoder.DEFAULT.encode(body, StandardCharsets.UTF_8);
			if (params != null) {
				params = "?" + URLEncoder.DEFAULT.encode(params, StandardCharsets.UTF_8);
			}
		}
		return pre + body + StrUtil.nullToEmpty(params);
	}
}