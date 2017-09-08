package com.wx.entity;
/**
 * 回复文本消息
 * @author Administrator
 *
 */
public class TextMsg {
	private String toUserName;// 接收方帐号（收到的OpenID）
	private String fromUserName;// 开发者微信号
	private long createTime;// 消息创建时间 （整型）
	private String msgType;// text
	private String content;// 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）

	public String getXml() {
		return "<xml><ToUserName><![CDATA["+fromUserName+"]]></ToUserName><FromUserName><![CDATA["+toUserName+"]]></FromUserName><CreateTime>"+createTime+"</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA["+content+"]]></Content></xml>";
	}

	public TextMsg() {
		super();
		msgType = "text";
		createTime = System.currentTimeMillis();
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
