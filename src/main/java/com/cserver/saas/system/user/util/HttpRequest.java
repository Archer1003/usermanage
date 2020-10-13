package com.cserver.saas.system.user.util;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
/**
 * 发送put请求创建docker应用实例
 * @author Admin
 *
 */
public class HttpRequest {
	public static String sendPost(String url,String jsonStr){
		CloseableHttpClient httpClient =HttpClients.createDefault();
		String res=null;
		HttpPost  method=new HttpPost(url);
		StringEntity strEn=null;
			try {
				strEn = new StringEntity(jsonStr,"utf-8");
				method.setEntity(strEn);
				method.setHeader("Content-Type", "application/json;charset=UTF-8");
				HttpResponse resp=httpClient.execute(method);
				HttpEntity htttEntity= resp.getEntity();
				res=EntityUtils.toString(htttEntity,"utf-8");
				httpClient.close();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		return res;
	}
	public static String sendPostS(String url,String jsonStr){
		CloseableHttpClient httpClient =HttpClients.createDefault();
		String res=null;
		Map<String, Object> map = new HashMap<String, Object>();
		HttpPost  method=new HttpPost(url);
		StringEntity strEn=null;
			try {
				strEn = new StringEntity(jsonStr,"utf-8");
				method.setEntity(strEn);
				method.setHeader("Content-Type", "application/json");
				HttpResponse resp=httpClient.execute(method);
				int code = resp.getStatusLine().getStatusCode();
				Header[] h = resp.getAllHeaders();
				HttpEntity htttEntity= resp.getEntity();
				res=EntityUtils.toString(htttEntity,"utf-8");
				map = addHeader(res,code,h);
				httpClient.close();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		return res;
	}
	public static String sendGet(String url){
		CloseableHttpClient httpClient =HttpClients.createDefault();
		String res=null;
		HttpGet  method=new HttpGet(url);
		//StringEntity strEn=null;
			try {
			//	strEn = new StringEntity(jsonStr,"utf-8");
				
				//method.setEntity(strEn);
				method.setHeader("Content-Type", "application/json");
				HttpResponse resp=httpClient.execute(method);
				HttpEntity htttEntity= resp.getEntity();
				res=EntityUtils.toString(htttEntity,"utf-8");
				httpClient.close();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		return res;
	}
	public static String sendPut(String url,String jsonStr){
		CloseableHttpClient httpClient =HttpClients.createDefault();
		String res=null;
		HttpPut method=new HttpPut(url);
		StringEntity strEn=null;
			try {
				strEn = new StringEntity(jsonStr,"utf-8");
				method.setEntity(strEn);
				method.setHeader("Content-Type", "application/json");
				HttpResponse resp=httpClient.execute(method);
				HttpEntity htttEntity= resp.getEntity();
				res=EntityUtils.toString(htttEntity,"utf-8");
				httpClient.close();
			} catch (UnsupportedEncodingException e) {
			
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			};
		return res;
	}
	public static String sendPut(String url){
		CloseableHttpClient httpClient =HttpClients.createDefault();
		String res=null;
		HttpPut method=new HttpPut(url);
			try {
				method.setHeader("Content-Type", "application/json");
				HttpResponse resp=httpClient.execute(method);
				HttpEntity htttEntity= resp.getEntity();
				res=EntityUtils.toString(htttEntity,"utf-8");
				httpClient.close();
			} catch (UnsupportedEncodingException e) {
			
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			};
		return res;
	}
	/**
     * 带参数的Delete请求
     * 
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
	public static int sendDelete(String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		int resStatusCode = 0;
		URIBuilder uriBuilder;
		try {
			uriBuilder = new URIBuilder(url);
			HttpDelete httpDelete = new HttpDelete(uriBuilder.build());
			HttpResponse response = httpClient.execute(httpDelete);
			resStatusCode = response.getStatusLine().getStatusCode();
			httpClient.close();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 返回
		return resStatusCode;
	}
	public static Map<String,Object> addHeader(String res, int code, Header[] head){
		
		Map<String,Object> map = new HashMap<String,Object>();
		String h = "[{";
		for(int i=0; i<head.length; i++){
			String n = head[i].getName();
			String v = head[i].getValue();
			h += "\"" + n + "\":" + "\"" + v + "\",";
		}
		h = h.substring(0,h.length()-1);
		h += "}]";
		map.put("body", res);
		map.put("header", h);
		map.put("code", code);
		return map;
	}
}
