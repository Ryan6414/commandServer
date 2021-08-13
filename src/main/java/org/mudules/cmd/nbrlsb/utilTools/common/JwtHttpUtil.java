package org.mudules.cmd.nbrlsb.utilTools.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JwtHttpUtil {
	public static void saveUser(String path,String params){
		CloseableHttpResponse response= null;
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost post = new HttpPost(path);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("params", params));
			post.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
		 response = client.execute(post);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				assert response != null;
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void saveUser(String path){
		CloseableHttpResponse response = null;
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpGet get = new HttpGet(path);
		 response = client.execute(get);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				assert response != null;
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
		String res = "";
		StringBuffer buffer = new StringBuffer();
		HttpURLConnection httpUrlConn = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			URL url = new URL(requestUrl);
			httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			httpUrlConn.setRequestProperty("Accept", "text/plain");
			 httpUrlConn.setRequestProperty("Content-Type", "application/json");
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod)) {
				httpUrlConn.connect();
			}

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			res = buffer.toString();
			System.out.println(res);

		} catch (ConnectException ce) {
			log.info("Weixin server connection timed out.");
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				httpUrlConn.disconnect();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		return res;
	}

}
