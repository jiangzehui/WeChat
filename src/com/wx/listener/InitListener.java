package com.wx.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.wx.util.Token;

public class InitListener implements ServletContextListener{
	Token tk = new Token();
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("微信服务端程序关闭......");
		
	}

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("微信服务端程序启动......");
		tk.getToken();
		
		
	}

}
