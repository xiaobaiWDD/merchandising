package main.java.servlet;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PolarAxisLocation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

public class DrawHistogram {

	private int width;// ͼ����

	private int height; // ͼ��߶�

	private String chartTitle;// ͼ�����

	private String subtitle;// ������

	private String xTitle;// X�����

	private String yTitle;// Y�����

	private String[] cutline;// ͼ������

	private String category[]; // ͳ������

	private double[][] data;// ��ͼ����

	private String servletURI = "/DisplayChart";// ӳ��·��

	public DrawHistogram() {
		this.width = 1260;
		this.height = 600;
	}
	/*
	public DrawHistogram(List<Commodity> list) {
		this();
		if (list.size()>0) {
			this.chartTitle = "���·���������";
			this.subtitle = "------ʱ�䣺2017��";
			this.xTitle = "����";
			this.yTitle = "������ ";
			this.cutline = new String[] { "��������", "����", "ӯ��" };
			String[] str1 = new String[list.size()];
			double[][] d1 = new double[3][list.size()];
			for (int i = 0; i <list.size(); i++) {
				Commodity commodity = list.get(i);
				str1[i]=commodity.getDate();
				d1[0][i]=commodity.getsNumber();
				d1[1][i]=commodity.getsPrice();
				d1[2][i]=commodity.getpPrice();
			}
			this.category=str1;
			this.data=d1;
					
		}
	}
	*/
	public DrawHistogram(String chartTitle,String subtitle, String xTitle,
			String yTitle, String[] cutline,String[] category, double[][] data) {
		this();
		this.chartTitle = chartTitle;
		this.subtitle = subtitle;
		this.xTitle = xTitle;
		this.yTitle = yTitle;
		this.cutline = cutline;
		this.category = category;
		this.data = data;
	}

	public DrawHistogram(String chartTitle,
			String subtitle, String xTitle, String yTitle, String[] cutline,
			String[] category, double[][] data, String servletURI) {
		this();
		this.chartTitle = chartTitle;
		this.subtitle = subtitle;
		this.xTitle = xTitle;
		this.yTitle = yTitle;
		this.cutline = cutline;
		this.category = category;
		this.data = data;
		this.servletURI = servletURI;
	}

	public String draw(HttpSession session, String contextPath) {

		// ������ͼ���ݼ�
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int m = 0; m < cutline.length; m++) {
			for (int n = 0; n < category.length; n++) {
				dataset.addValue(data[m][n], cutline[m], category[n]);
			}
		}

		// ����ͼ�����

		JFreeChart chart = ChartFactory.createLineChart(
				chartTitle, // ͼ�����
				xTitle, // X�����
				yTitle, // Y�����
				dataset, // ��ͼ���ݼ�
				PlotOrientation.VERTICAL, // ����ͼ���Ʒ���
				true, // ��ʾͼ��
				true, // ���ñ�׼������
				false // ��������
				);
		//�����������
	 	CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
        CategoryAxis domainAxis = categoryplot.getDomainAxis();  
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("����", Font.PLAIN, 25));  
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
        domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
        numberaxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
        chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 14));
        
        //����ͼƬ����ɫ
        //chart.setBackgroundPaint(new java.awt.Color(189,235,255));
        
        CategoryPlot plot = (CategoryPlot) chart.getPlot();      
        plot.setBackgroundPaint(new Color(239,251,255));//����ͼƬ��ǽ��ı���ɫ
        plot.setRangeGridlinePaint(Color.black);//����ͼƬ�и����ߵ���ɫ        
        plot.setNoDataMessage("û�����ͳ������"); // û������ʱ��ʾ����Ϣ  
        
       //����������ɫ
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesPaint(2, Color.BLUE);
        renderer.setSeriesPaint(3, Color.CYAN);
        
        //����ͼ�����ҷ���ʾ
        chart.getLegend().setVisible(true);
        chart.getLegend().setPosition(RectangleEdge.RIGHT);
        
        
		// ��Ӹ�����
		chart.addSubtitle(new TextTitle(subtitle));     	
		ChartRenderingInfo info = new ChartRenderingInfo(
				new StandardEntityCollection());
		// ����ָ����ʽ��ͼƬ��������ͼƬ����
		String fileName = "";
		try {
			fileName = ServletUtilities.saveChartAsPNG(chart, width, height,
					info, session);
		} catch (IOException e) {
			System.out.println("------ �ڻ���ͼƬʱ�׳��쳣���������£�");
			e.printStackTrace();
		}
		// ��֯ͼƬ���·��
		String graphURL = contextPath + servletURI + "?filename=" + fileName;
		// ����ͼƬ���·��
		return graphURL;

	}
}