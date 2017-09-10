package com.wx.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlUtil {
	public static Map<String, String> map = new HashMap<String, String>();
	
	public static Map<String, String> parseXml(HttpServletRequest request)
			throws Exception {
		// 将解析结果存储在HashMap中
		map = new HashMap<String, String>();

		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		 Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		formatXml(elementList);

		// 释放资源
		inputStream.close();
		inputStream = null;

		return map;
	}
	
	/**
	 * 递归遍历所有子节点
	 * @param elementList
	 */
	private static void formatXml(List<Element> elementList){
		for (Element e : elementList) {

			if (e.elements().size() > 0) {//判断是否还存在子标签
				List<Element> chindList = e.elements();
				formatXml(chindList);
			} else {
				map.put(e.getName(), e.getText());
			}
		}
	}

}
