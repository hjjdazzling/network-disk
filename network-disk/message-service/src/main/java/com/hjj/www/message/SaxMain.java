package com.hjj.www.message;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author haojunjie
 * @create 2020-06-03 19:27
 */
@Slf4j
public class SaxMain {
	public static void main(String[] args) {
		SAXReader reader = new SAXReader();
		Document document=  readDocument("C:\\Users\\Administrator\\AppData\\Roaming\\LanxinSoftCustom\\Resource\\Docs\\LeadSec_fw.xml",
				reader);

		Element rootElemnt = document.getRootElement();
		System.out.println(rootElemnt.element("metadata"));
		System.out.println(checkXml(rootElemnt));
	}

	private static  Document readDocument(String fileName, SAXReader reader) {
		Document document = null;
		try {
			File file = new File(fileName);
			document = reader.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return document;
	}


	public static boolean checkXml(Element root) {
		if (!"OpenIOC".equals(root.getName())) {
			return false;
		}

		if (root.element("metadata") == null) {
			return false;
		}

		if (root.element("parameters") == null) {
			return false;
		}


		return true;
	}
}
