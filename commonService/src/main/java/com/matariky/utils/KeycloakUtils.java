package com.matariky.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson2.JSONObject;

import cn.hutool.http.HttpStatus;
import io.jsonwebtoken.io.IOException;

public class KeycloakUtils {

	public static Map<String, String> tokenToMap(String keycloakToken) {
		if (StringUtils.isEmpty(keycloakToken))
			return null;
		Map<String, String> map = JSONObject.parseObject(keycloakToken, Map.class);
		return map;
	}

	public static void main(String[] args) {
		tokenToMap(null);
	}

	public static String getToken(String loginName, String pazzword, String keycloakUrl, String keycloakRealm,
			String keycloakSecret, String keycloakClientId, String keycloakGrantType) {

		HttpClient httpclient = new DefaultHttpClient();
		// Prepare a request object
		HttpPost getTokenPost = new HttpPost(
				keycloakUrl + "/realms/" + keycloakRealm + "/protocol/openid-connect/token");
		getTokenPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", loginName));
		nvps.add(new BasicNameValuePair("password", pazzword));
		nvps.add(new BasicNameValuePair("client_id", keycloakClientId));
		nvps.add(new BasicNameValuePair("grant_type", keycloakGrantType));
		nvps.add(new BasicNameValuePair("client_secret", keycloakSecret));

		// Execute the request
		HttpResponse response;
		try {
			getTokenPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			response = httpclient.execute(getTokenPost);
			if (response.getStatusLine().getStatusCode() != HttpStatus.HTTP_OK) {
				return null;
			} else {
				HttpEntity entity = response.getEntity();

				// If the response does not enclose an entity, there is no need
				// to worry about connection release
				if (entity != null) {
					InputStream instream = entity.getContent();
					try {

						BufferedReader reader = new BufferedReader(
								new InputStreamReader(instream));
						// do something useful with the response
						return reader.readLine();

					} catch (IOException ex) {

						// In case of an IOException the connection will be released
						// back to the connection manager automatically
						// throw ex;
						return null;
					} catch (RuntimeException ex) {

						// In case of an unexpected exception you may want to abort
						// the HTTP request in order to shut down the underlying
						// connection and release it back to the connection manager.
						getTokenPost.abort();
						// throw ex;
						return null;
					} finally {

						// Closing the input stream will trigger connection release
						instream.close();
						httpclient.getConnectionManager().shutdown();
					}

					// When HttpClient instance is no longer needed,
					// shut down the connection manager to ensure
					// immediate deallocation of all system resources

				}
			}
		} catch (java.io.IOException e) {
			return null;
		}

		// Examine the response status

		// Get hold of the response entity

		return null;
	}

	public static void cacheToken(Long id, String keycloakToken) {

	}

}
