package com.ershiyi.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Http工具类
 *
 * @author liyong
 */
public class HttpHelper {

    private static String charSet = "UTF-8";
    private static CloseableHttpClient httpClient = null;
    private static CloseableHttpResponse response = null;
    private static PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();

    /**
     * http的post请求（用于请求json格式的参数）
     *
     * @param url
     * @param jsonStr
     * @return
     */
    public static String doHttpPost(String url, String jsonStr) {
        try {
            httpClient = HttpClients.custom().setConnectionManager(manager).setConnectionManagerShared(true).build();
            // 创建httpPost
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Accept", "application/json");

            StringEntity entity = new StringEntity(jsonStr, charSet);
            entity.setContentType("application/json");
            entity.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            httpPost.setEntity(entity);
            // 发送post请求
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Get请求API
     *
     * @param url 地址
     * @return
     */
    public static String
    requestGetAPI(String url) {
        String result = "";
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet requests = new HttpGet(url);
            //设置超时时间
            RequestConfig requestConfig =  RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000).build();
            requests.setConfig(requestConfig);
            HttpResponse response = httpClient.execute(requests);
            HttpEntity httpEntity = response.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数,请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}
