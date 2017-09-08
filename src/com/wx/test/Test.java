package com.wx.test;

import java.io.IOException;
import java.io.StringReader;   
import javax.xml.parsers.DocumentBuilder;   
import javax.xml.parsers.DocumentBuilderFactory;   
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;   
import org.w3c.dom.Element;   
import org.w3c.dom.Node;   
import org.w3c.dom.NodeList;   
import org.xml.sax.InputSource; 
import org.xml.sax.SAXException;

public class Test {

	/**
	 * @param args
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader("<xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName><FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName><CreateTime>1408090606</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[scancode_waitmsg]]></Event><EventKey><![CDATA[611]]></EventKey><ScanCodeInfo><ScanType><![CDATA[qrcode]]></ScanType><ScanResult><![CDATA[1]]></ScanResult></ScanCodeInfo></xml>")));

		Element root = doc.getDocumentElement();
		NodeList books = root.getChildNodes();
		if (books != null) {
			for (int i = 0; i < books.getLength(); i++) {
				Node book = books.item(i);
				
				if(book.getFirstChild().getNodeValue()==null){
					
					
					
					System.out.println("111节点=" + book.getNodeName() + "\ttext="
							+ book.getLastChild().getTextContent());
				}else{
					System.out.println("2节点=" + book.getNodeName() + "\ttext="
							+ book.getLastChild().getNodeValue());
				}
				
			}
		}

	}

}
