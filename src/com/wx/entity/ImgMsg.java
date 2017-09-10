package com.wx.entity;

public class ImgMsg {
	private String toUserName;// 接收方帐号（收到的OpenID）
	private String fromUserName;// 开发者微信号
	private String mediaId;// 
	
	public String getXml(){
		return "<xml><ToUserName><![CDATA["+fromUserName+"]]></ToUserName><FromUserName><![CDATA["+toUserName+"]]></FromUserName><CreateTime>"+System.currentTimeMillis()+"</CreateTime><MsgType><![CDATA[image]]></MsgType><Image><MediaId><![CDATA["+mediaId+"]]></MediaId></Image></xml>";
	}
	
	public ImgMsg(String toUserName, String fromUserName, String mediaId) {
		super();
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.mediaId = mediaId;
	}

	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	
	
	

}
