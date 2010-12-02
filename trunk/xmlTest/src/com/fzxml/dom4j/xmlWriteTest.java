package com.fzxml.dom4j;

import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class xmlWriteTest {
	
	//����dom����
	public static Document createDocument(){
		//����һ��dom����
		Document document = DocumentHelper.createDocument();
		//����һ�����ڵ�
		Element rootElement = document.addElement("top");
		rootElement
			.addElement("class")
			.addAttribute("name", "zzo")
			.addElement("property")
			.addAttribute("id", "001")
			.addAttribute("password", "001");
		return document;
	}
	
	/**
	 * д��xml�ļ���ַ
	 * @param document ����Ҫд�������
	 * @param outFile �ļ���ŵĵ�ַ
	 */
	public static void writeDocument(Document document, String outFile){
		try{
			//Ҫ��ŵ��ļ�
			FileWriter fileWriter = new FileWriter(outFile);
			//OutoutFormat���������ʽ
			OutputFormat xmlFormat = OutputFormat.createPrettyPrint();
			//�����ļ�����
			xmlFormat.setEncoding("GB2312");
			//����д�ļ�����
			XMLWriter xmlWriter = new XMLWriter(fileWriter,xmlFormat);
			//д���ļ�
			xmlWriter.write(document);
			//�ر�
			xmlWriter.close();
		}catch(IOException e){
			System.out.println("�ļ�û���ҵ�");
			e.printStackTrace();
		}
    }
	
	//xml���ɲ���
	public static void main(String[] args){
		String outFile = "xmlCreateTest.xml";
		xmlWriteTest.writeDocument(xmlWriteTest.createDocument(), outFile);
		System.out.println("Ok,it's done!");
	}
}
