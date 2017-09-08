package com.wx.entity;

public class ScanCodeMsg {
	private String toUserName;// 接收方帐号（收到的OpenID）
	private String fromUserName;// 开发者微信号
	private long createTime;// 消息创建时间 （整型）
	private String msgType;// text
	private String content;// 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）


}
