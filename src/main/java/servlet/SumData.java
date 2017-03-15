package main.java.servlet;

import java.util.List;

import main.java.mongoUtils.MongoDao;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

public class SumData {
	private String[] cutline;// ͼ������
	private String category[]; // ͳ������
	private double[][] data;// ��ͼ���� 
	private String subtitle;//������
	private String xTitle;//x������
	private String yTitle;//y������
	

	public SumData() {
		// TODO Auto-generated constructor stub
	}
	
	public SumData(List<String> cutline1,String startDate,String endDate,int monthNumber) {
		//����xy������
		String xTitle1 ="��Ʒ����";
		String yTitle1 ="���۳ɹ�";
		//��ѯ���
		MongoDao dao = new MongoDao();
		BasicDBList dbList = dao.mongoGroupOnMonth(startDate, endDate);
	
		//x������
		String cat[] = new String[dbList.size()];
		//����
		double[][] data1 = new double[cutline1.size()][dbList.size()];
		
		//ͼ��
		String[] cut = new String[cutline1.size()];
		for (int i = 0; i < cutline1.size(); i++) {
			cut[i]=cutline1.get(i);
		}
		
		if (dbList!=null) {
			for (int i = 0; i < dbList.size(); i++) {
				//��ȡ��ѯ����
				DBObject obj = (DBObject) dbList.get(i);
				//���ú������ֵ
				cat[i]=(String) obj.get("cName");
				//��data[][]д������
				for (int k = 0; k < cutline1.size(); k++) {
					switch (cut[k]) {
					case "����":
						double pPrice = Double.valueOf(obj.get("sumpPrice").toString());
						double count = Double.valueOf(obj.get("count").toString());
						double averagepPrice = pPrice/count;
						data1[k][i]= averagepPrice;
						break;
					case "�ۼ�":
						double sPrice = Double.valueOf(obj.get("sumsPrice").toString());
						double count1 = Double.valueOf(obj.get("count").toString());
						double averagesPrice = sPrice/count1;
						data1[k][i]= averagesPrice;
						break;
					case "��������":
						double sNumber = Double.valueOf(obj.get("sumsNumber").toString());
						data1[k][i]= sNumber;
						break;
					case "ӯ��":
						double tTurnover = Double.valueOf(obj.get("sumtTurnover").toString());
						data1[k][i]= tTurnover;
						break;

					default:
						break;
					}
				}
			}
		}	
		
		this.subtitle="             "+ "ͳ��ʱ�䣺2017��"+monthNumber+"��";
		this.data =data1;
		this.category=cat;
		this.xTitle=xTitle1;
		this.yTitle=yTitle1;
		this.cutline=cut;
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

}
