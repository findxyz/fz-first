package com.fzxml.dom4j;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jTest {
	public static void main(String[] args) {
		SAXReader saxReader =  new SAXReader();
		try {
		/**
		 *  ��ͨ�� org.dom4j.io.SAXReader ��������XML�ĵ�
		 */
		Document xmlDoc = saxReader.read(new FileInputStream(new File("dom4jTest.xml")));
		/**
		 *  ȡ��XML�ĵ��ĸ�Ԫ�ؼ���ֵ
		 */
		Element rootElement = xmlDoc.getRootElement(); //ȡ��XML�ĵ��ĸ�Ԫ��
		System.out.println("���ڵ�Ԫ��:"+rootElement.getName());  //���ڵ�Ԫ��
		Iterator subElementsIt = rootElement.elementIterator(); //��Iterator���ϵõ���Ԫ���µ�������Ԫ��
		while(subElementsIt.hasNext()){
			
			Element subElement = (Element)subElementsIt.next();
			System.out.println("���ڵ���Ԫ��:"+subElement.getName());
			
			//��Ԫ�ص�class���Ե�����
			System.out.println(subElement.attribute("class").getName()
					+"="
					+subElement.attribute("class").getValue());
			
			Iterator subAttributeIt = subElement.attributeIterator();
			while(subAttributeIt.hasNext()){
				Attribute subAttribute = (Attribute)subAttributeIt.next();
				System.out.println("��Ԫ��������:"+subAttribute.getName()+"-"+subAttribute.getValue()); //Ԫ������
			}
			
			Iterator sub2ElementsIt = subElement.elementIterator("property"); //��"property"����Iterator���ϵõ���Ԫ���µ�������Ԫ��
			while(sub2ElementsIt.hasNext()){
				Element sub2Element = (Element)sub2ElementsIt.next();
				System.out.println("��Ԫ����Ԫ��:"+sub2Element.getName());
				
				Iterator sub2AttributeIt = sub2Element.attributeIterator();
				while(sub2AttributeIt.hasNext()){
					Attribute sub2Attribute = (Attribute)sub2AttributeIt.next();
					System.out.println("��Ԫ����Ԫ��������:"+sub2Attribute.getName()+"-"+sub2Attribute.getValue());
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
