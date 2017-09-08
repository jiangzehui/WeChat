package com.wx.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

public class Api {
	
	
	public static String get(String path) {
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);//设置连接超时
            connection.setReadTimeout(5000);//设置数据读取超时
            connection.setRequestMethod("GET");//设置请求方式
            connection.setUseCaches(true);//设置是否使用缓存  默认是true
            connection.setRequestProperty("Content-Type", "application/json");//设置请求中的媒体类型信息。
            connection.addRequestProperty("Connection", "Keep-Alive");//设置客户端与服务连接类型

            connection.connect();
            int code = connection.getResponseCode();
            if (code == 200) {
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                baos.close();
                is.close();
                byte[] byteArray = baos.toByteArray();
                final String result = new String(byteArray, "utf-8");
                return result;
                
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }

    public static String post(String path, HashMap<String, String> paramsMap) {
        try {
            Iterator<Map.Entry<String, String>> it = paramsMap.entrySet().iterator();
            StringBuilder sb=new StringBuilder();
            int poi=0;
            while (it.hasNext()) {
                if(poi>0){
                    sb.append("&");
                }
                Map.Entry entry = it.next();
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                sb.append(String.format("%s=%s",key,URLEncoder.encode(value,"utf8")));
                poi++;

            }
            String params = sb.toString();
            System.out.println("result="+params);
            // 请求的参数转换为byte数组
            byte[] postData = params.getBytes();

            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);//设置连接超时
            connection.setReadTimeout(5000);//设置数据读取超时
            connection.setRequestMethod("POST");//设置请求方式
            connection.setUseCaches(false);//Post请求不能使用缓存
            connection.setDoOutput(true);//Post请求必须设置允许输出 默认false
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(true);//设置本次连接是否自动处理重定向
            connection.setRequestProperty("accept", "*/*");
            connection.addRequestProperty("Connection", "Keep-Alive");//设置客户端与服务连接类型
            //connection.connect();
            // 发送请求参数
            DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
            dos.write(postData);
            dos.flush();
            dos.close();
            int code = connection.getResponseCode();
            if (code == 200) {
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                baos.close();
                is.close();
                byte[] byteArray = baos.toByteArray();
                final String result = new String(byteArray);
                System.out.println("result="+result);
                return result;
                
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public static String post_json(String path,JSONObject object) {

        try {
            //创建连接
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.connect();

            //POST请求
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());
           

            out.write(object.toString().getBytes("utf8"));
            out.flush();
            out.close();

            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            
            reader.close();
            // 断开连接
            connection.disconnect();
            return sb.toString();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

}
