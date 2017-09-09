package com.wx.entity;

public class ImgAndTxtMsg {
	private String toUserName;// 接收方帐号（收到的OpenID）
	private String fromUserName;// 开发者微信号
	private long createTime;// 消息创建时间 （整型）
	private String msgType;// news
	private String articleCount;// 图文消息个数，限制为8条以内
	private String title;// 图文消息标题
	private String description;// 图文消息描述
	private String picUrl;// 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	private String url;// 点击图文消息跳转链接
	
	public String getXml(){
		return "<xml><ToUserName><![CDATA["+fromUserName+"]]></ToUserName><FromUserName><![CDATA["+toUserName+"]]></FromUserName><CreateTime>"+createTime+"</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>1</ArticleCount><Articles><item><Title><![CDATA["+title+"]]></Title> <Description><![CDATA["+description+"]]></Description><PicUrl><![CDATA["+picUrl+"]]></PicUrl><Url><![CDATA["+url+"]]></Url></item></Articles></xml>";
	}
	
	
	
	public ImgAndTxtMsg(String toUserName, String fromUserName, String title,
			String description, String picUrl, String url) {
		super();
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = System.currentTimeMillis();
		this.title = title;
		this.description = description;
		this.picUrl = picUrl;
		this.url = url;
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
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
