package main.java.servlet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.java.commodity.resources.Commodity;
import main.java.mongoUtils.MongoDao;

public class JspData {
	
	private String[] cutline;// ͼ������
	private String category[]; // ͳ������
	private double[][] data;// ��ͼ���� 
	private String subtitle;//������
	private String xTitle;//x������
	private String yTitle;//y������
	private String Name;//��ѯ����Ʒ����
	

	public JspData(){}
	
	public JspData(List<String> cutline,int id,int monthNumber,int dayNumber) throws ParseException{
		
		//�������ݿ⣬���ݴ����id��ѯ����
		MongoDao dao = new MongoDao();
		List<Commodity> list = new ArrayList<Commodity>();	
		list = dao.findCommodityAllByCId(id);
		
		//����ѡ���ͼ��ͼ��
		int datasize = cutline.size();
		String[] cut = new String[datasize];
		for (int i = 0; i < datasize; i++) {
			cut[i]=cutline.get(i);
		}
		
		String cat[] = new String[dayNumber]; 
		double[][] data1 = new double[datasize][dayNumber];// ��ͼ����
		
		//����ʱ���ʽ
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");	
		
		for (int i = 0,j = 0; i < list.size(); i++) {
			//ȡ���б��е����ݲ��������
			Commodity commodity = list.get(i);
			String s = commodity.getDate();
			//����õ�����ת��Ϊ���ڸ�ʽ
			Date date =  formatter.parse(s);
			Calendar cal=Calendar.getInstance();  
			cal.setTime(date);
			
			if (cal.get(Calendar.MONTH)==(monthNumber-1)) {
				//��ò�ѯ�·��е�ÿһ��
				cat[j]=commodity.getDate().substring(6);
				for (int k = 0; k < datasize; k++) {
					switch (cut[k]) {
					case "����":
						data1[k][j]=commodity.getpPrice();
						break;
					case "�ۼ�":
						data1[k][j]=commodity.getsPrice();
						break;
					case "��������":
						data1[k][j]=commodity.getsNumber();
						break;
					case "ӯ��":
						data1[k][j]=commodity.gettTurnover();
						break;

					default:
						break;
					}
				}
//				data1[0][j]=commodity.getsNumber();
//				data1[1][j]=commodity.getsPrice();
//				data1[2][j]=commodity.getpPrice();
				j++;
			}
		}
		this.Name = list.get(0).getcName();
		this.subtitle="                  ͳ��ʱ�䣺2017��"+monthNumber+"��";
		this.xTitle = "ʱ��";
		this.yTitle = "����";
		this.category=cat;
		this.data =data1;
		this.cutline =cut;
	}

	public String[] getCutline() {
		return cutline;
	}

	public String[] getCategory() {
		return category;
	}

	public double[][] getData() {
		return data;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public String getxTitle() {
		return xTitle;
	}

	public String getyTitle() {
		return yTitle;
	}
	public String getName() {
		return Name;
	}
}
