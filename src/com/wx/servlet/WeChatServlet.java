package com.wx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.wx.entity.ImgAndTxtMsg;
import com.wx.entity.TextMsg;
import com.wx.util.Api;
import com.wx.util.Token;
import com.wx.util.XmlUtil;

public class WeChatServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WeChatServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		System.out.println("signature=" + signature);
		System.out.println("timestamp=" + timestamp);
		System.out.println("nonce=" + nonce);
		System.out.println("echostr=" + echostr);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败
			// if (SignUtil.checkSignature(signature,timestamp, nonce)) {
			out.print(echostr);
			// }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
			out = null;
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String result = "success";
		try {
			Map<String, String> map = XmlUtil.parseXml(request);
			// 默认回复一个"success"
			System.out.println("******************");
			for (String key : map.keySet()) {

				System.out.println("Key = " + key + "---value = "
						+ map.get(key));

			}
			System.out.println("******************");
			String msgType = map.get("MsgType");
			String fromUserName = map.get("FromUserName");// 发送方帐号（一个OpenID）
			String toUserName = map.get("ToUserName"); // 开发者微信号

			if (msgType.equals("event")) {// 判断消息类型(event=点击菜单触发的消息类型)
				String event = map.get("Event");
				if (event.equals("CLICK")) {// 菜单点击事件
					String key = map.get("EventKey");
					if (key.equals("msg")) {// 获取个人信息
						String userMsg = Api
								.get("https://api.weixin.qq.com/cgi-bin/user/info?access_token="
										+ Token.wx_token
										+ "&openid="
										+ fromUserName + "&lang=zh_CN");
						JSONObject object = JSONObject.fromObject(userMsg);
						String nickname = object.getString("nickname");
						String sex = object.getString("sex").equals("1")?"男":"女";
						String city = object.getString("city");
						String province = object.getString("province");
						String country = object.getString("country");
						String headimgurl = object.getString("headimgurl");

						ImgAndTxtMsg msg = new ImgAndTxtMsg(toUserName,
								fromUserName, nickname, "性别："+sex+"\n城市："+province+city, headimgurl, "http://www.baidu.com");
						result = (msg.getXml());
						
					}

				} else if (event.equals("location_select")) {// 点击菜单跳转上报地理位置事件
				// String Location_X = map.get("Location_X");// 纬度
				// String Location_Y = map.get("Location_Y"); // 经度
				// String Label = map.get("Label"); // 位置信息
				// String Poiname = map.get("Poiname"); // poi点
				// TextMsg tmMsg = new TextMsg();
				// tmMsg.setFromUserName(fromUserName);
				// tmMsg.setToUserName(toUserName);
				// tmMsg.setContent("纬度："+
				// Location_X+"\n经度："+Location_Y+"\n位置信息："+Label+"\n"+Poiname);
				// result = (tmMsg.getXml());

				} else if (event.equals("VIEW")) {// 点击菜单跳转链接时的事件

				} else if (event.equals("scancode_waitmsg")) {// 点击扫码事件

					String ScanResult = map.get("ScanResult"); //
					TextMsg tmMsg = new TextMsg();
					tmMsg.setFromUserName(fromUserName);
					tmMsg.setToUserName(toUserName);
					tmMsg.setContent("扫描结果：" + ScanResult);
					result = (tmMsg.getXml());
				}
			} else if (msgType.equals("text")) {
				String content = map.get("Content"); // 用户发送的消息内容
				System.out.println(fromUserName + "给后台发送了消息：" + content);
				String userMsg = Api
						.get("http://apicloud.mob.com/v1/weather/query?key=appkey&city="
								+ URLEncoder.encode(content, "utf-8")
								+ "&province=");
				TextMsg tmMsg = new TextMsg();
				tmMsg.setFromUserName(fromUserName);
				tmMsg.setToUserName(toUserName);
				tmMsg.setContent(userMsg);
				result = (tmMsg.getXml());
			} else if (msgType.equals("location")) {

				System.out.println(fromUserName + "给后台发送了位置消息");
				String Location_X = map.get("Location_X");// 纬度
				String Location_Y = map.get("Location_Y"); // 经度
				String Label = map.get("Label"); // 位置信息
				TextMsg tmMsg = new TextMsg();
				tmMsg.setFromUserName(fromUserName);
				tmMsg.setToUserName(toUserName);
				tmMsg.setContent("纬度：" + Location_X + "\n经度：" + Location_Y
						+ "\n位置信息：" + Label);
				result = (tmMsg.getXml());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println(result);
		out.print(result);
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
