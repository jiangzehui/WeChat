package com.wx.util;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 获取token
 * @author Administrator
 *
 */
public class Token {

	public static String url = "https://api.weixin.qq.com";
	public static String wx_token;
	private String appID = "wxf403bd0e9921c95a";
	private String appsecret = "6c86614e797aeb68ea211e2dc5ce9097";
	private final ScheduledExecutorService schedule=Executors.newScheduledThreadPool(1);
	
	/**
	 * 定时去微信获取token
	 */
	public void  getToken() {
		
		final Runnable runnable=new Runnable(){

			public void run() {
				System.out.println("执行获取token任务......");
				String result = Api.get(url
						+ "/cgi-bin/token?grant_type=client_credential&appid=" + appID
						+ "&secret=" + appsecret);
				JSONObject obj = JSONObject.fromObject(result);
				wx_token = obj.getString("access_token");
				System.out.println("获取到了token=" + wx_token);
				
			}
			
		};
		schedule.scheduleAtFixedRate(runnable, 1, 2*50*60, TimeUnit.SECONDS);
		
		
		
		
	}
	
	public  String getTokenMain(){
		String result = Api.get(url
				+ "/cgi-bin/token?grant_type=client_credential&appid=" + appID
				+ "&secret=" + appsecret);
		JSONObject obj = JSONObject.fromObject(result);
		wx_token = obj.getString("access_token");
		System.out.println("获取到了token=" + wx_token);
		return wx_token;
	}

	public static void main(String[] args) {
		//String token = Token.getToken();
		String token = new Token().getTokenMain();
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject ob1 = new JSONObject();
		ob1.put("type", "click");
		ob1.put("name", "我的信息");
		ob1.put("key", "msg");
		
		JSONObject ob2 = new JSONObject();
		ob2.put("name", "更多");
		JSONArray array2 = new JSONArray();
		
		JSONObject ob21 = new JSONObject();
		ob21.put("type", "scancode_waitmsg");
		ob21.put("name", "扫一扫");
		ob21.put("key", "scan");
		array2.add(ob21);
		
		JSONObject ob22 = new JSONObject();
		ob22.put("type", "location_select");
		ob22.put("name", "发送位置");
		ob22.put("key", "location");
		array2.add(ob22);
		
		JSONObject ob23 = new JSONObject();
		ob23.put("type", "pic_photo_or_album");
		ob23.put("name", "发送图片");
		ob23.put("key", "pic");
		array2.add(ob23);
		
		ob2.put("sub_button", array2);
		array.add(ob1);
		array.add(ob2);
		object.put("button", array);
		
		System.out.println(Api.post_json(url+"/cgi-bin/menu/create?access_token="+token, object));
	}
}
