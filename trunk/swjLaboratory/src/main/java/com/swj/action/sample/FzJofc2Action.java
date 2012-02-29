package com.swj.action.sample;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.swj.action.BaseAction;

import jofc2.model.Chart;
import jofc2.model.Text;
import jofc2.model.axis.XAxis;
import jofc2.model.axis.YAxis;
import jofc2.model.elements.LineChart;

// jofc2 �Ĳ�����
public class FzJofc2Action extends BaseAction {
	
	private static final long serialVersionUID = 1517169530990076947L;

	private InputStream inputStream;
	
	// jump to jofc2.jsp
	public String gotoJofc2() throws Exception{
		
		log.info("FzJofc2Action.gotoJofc2");
		return "gotoJofc2";
	}
	
	// jofc2 test
	public String jofc2Test() throws Exception{
		
		log.info("FzJofc2Action.jofc2Test");
		LineChart lineChart = new LineChart();
		// ��������
		lineChart.setFontSize(15);
		// ��������Ƶ�������ʾ������
		//lineChart.setTooltip("#val#%");
		// ����˳�����ø������ֵ
		LineChart.Dot dot1 = new LineChart.Dot(70);
		LineChart.Dot dot2 = new LineChart.Dot(85);
		LineChart.Dot dot3 = new LineChart.Dot(44);
		LineChart.Dot dot4 = new LineChart.Dot(67);
		LineChart.Dot dot5 = new LineChart.Dot(90);
		LineChart.Dot dot6 = new LineChart.Dot(64);
		LineChart.Dot dot7 = new LineChart.Dot(28);
		LineChart.Dot dot8 = new LineChart.Dot(99);
		LineChart.Dot dot9 = new LineChart.Dot(66);
		LineChart.Dot dot10 = new LineChart.Dot(77);
		LineChart.Dot dot11 = new LineChart.Dot(88);
		LineChart.Dot dot12 = new LineChart.Dot(11);
		// ����˳��ѵ���뵽ͼ����
		lineChart.addDots(dot1);
		lineChart.addDots(dot2);
		lineChart.addDots(dot3);
		lineChart.addDots(dot4);
		lineChart.addDots(dot5);
		lineChart.addDots(dot6);
		lineChart.addDots(dot7);
		lineChart.addDots(dot8);
		lineChart.addDots(dot9);
		lineChart.addDots(dot10);
		lineChart.addDots(dot11);
		lineChart.addDots(dot12);
		// X ��
		XAxis x = new XAxis();
		x.addLabels("1�·�");
		x.addLabels("2�·�");
		x.addLabels("3�·�");
		x.addLabels("4�·�");
		x.addLabels("5�·�");
		x.addLabels("6�·�");
		x.addLabels("7�·�");
		x.addLabels("8�·�");
		x.addLabels("9�·�");
		x.addLabels("10�·�");
		x.addLabels("11�·�");
		x.addLabels("12�·�");
		// Y�����ֵ
		long max = 100;
		YAxis y = new YAxis();
		y.setMax(max+0.0);
		// ����
		y.setSteps(5);
		// ����ͼ�ı���
		Chart flashChart = new Chart("����ͼ","font-size:12px;color:#ff0000;");
		// ��ͼ���뵽ͼ��
		flashChart.addElements(lineChart);
		// Y������
		Text yText = new Text("jofc2",Text.createStyle(20,"#000000",Text.TEXT_ALIGN_LEFT));
		flashChart.setYAxis(y);
		flashChart.setXAxis(x);
		// ����y����ʾ����
		flashChart.setYLegend(yText);
		inputStream = new ByteArrayInputStream(flashChart.toString().getBytes("utf-8"));
		return "jofc2Test";
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
