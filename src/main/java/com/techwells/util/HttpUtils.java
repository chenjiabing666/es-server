package com.techwells.util;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Map;

public class HttpUtils {

    /**
     * 将get请求返回的json内容封装到Map中返回
     * @param url  请求的url
     * @return   Map
     * @throws Exception
     */
    public static  Map<String,Object> doGet(String url)throws  Exception{
        HttpClient client=new DefaultHttpClient();  //创建HttpClient
        HttpGet get=new HttpGet(URI.create(url));  //创建HttpGet
        HttpResponse response=client.execute(get);  //执行get请求
        //如果请求成功
        if (response.getStatusLine().getStatusCode()==200){
            HttpEntity entity=response.getEntity();  //获取实体返回的内容
            BufferedReader reader=new BufferedReader(new InputStreamReader(entity.getContent()));  //创建缓存流读取返回的内容
            StringBuilder sb=new StringBuilder();
            //一行一行读取
            for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
                sb.append(temp);
            }
            Gson gson=new Gson();  //创建Gosn操作Json
            return gson.fromJson(sb.toString(),Map.class);  //转换Json字符串到Map中
        }
        return null;

    }


    /**
     * 发送Post请求，请求的参数是xml
     * @param url  链接
     * @param requestParamxml  xml参数
     * @return
     * @throws IOException
     */
    public static String doPost(String url,String requestParamxml) throws IOException {

        HttpClient client=new DefaultHttpClient();
        HttpPost post=new HttpPost(url);   //创建Post请求

        //设置请求配置
        RequestConfig config=RequestConfig.custom()
                .setConnectionRequestTimeout(150000)  //连接请求时间
                .setConnectTimeout(150000)      //连接服务器主机超时时间
                .setSocketTimeout(60000)   //设置读取数据的响应时间
                .build();
        post.setConfig(config);

        //设置请求实体
        post.setEntity(new StringEntity(requestParamxml,"UTF-8"));

        //添加头信息，告诉浏览器传入的参数是xml格式的
        post.addHeader("Content-Type","text/xml");

        //执行请求
        HttpResponse response=client.execute(post);

        //如果响应成功，返回数据
        if (response.getStatusLine().getStatusCode()==200){
            HttpEntity entity=response.getEntity();
            BufferedReader reader=new BufferedReader(new InputStreamReader(entity.getContent()));  //创建缓存流读取返回的内容
            StringBuilder sb=new StringBuilder();
            //一行一行读取
            for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
                sb.append(temp);
            }
            return sb.toString();  //直接返回字符串
        }
        return null;  //如果响应失败，返回null
    }


}
