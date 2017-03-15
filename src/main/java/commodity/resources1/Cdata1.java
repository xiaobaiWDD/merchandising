package main.java.commodity.resources1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.java.commodity.resources.Constant;
import main.java.commodity.resources.Random;
import main.java.mongoUtils.MongoDao1;

public class Cdata1 {
	
	public Cdata1(){}
	
	public static void loadData(){
		
		//ʱ��
//		GenerateTime timeSecon = new GenerateTime();
//		List<Calendar> timeCalendars = timeSecon.getDataTime();
		//��Ʒ����
		String[] cName =  {"����","ƤЬ","���","����","���޷�","����Ь","����Ь","�˶�Ь","�˶���","�ɿ���",
							"����","�ֱ�","����","�ޱ�","����","�¹�","�칫��","�칫��","���","ţ�п�"};
		//����
		double[] pPrice = new double[Constant.Number];
		for (int i = 0; i < Constant.Number; i++) {
			//�����������
			pPrice[i]=Random.rDouble(Constant.PPRICE);
		}
		//����
		double tTurnover=0;
		//�ۼ�
		double sPrice = 0;
		List<Integer> cId = Random.RcIdList();//Id
		MongoDao1 dao = new MongoDao1();
		//List<Commodity1> list = new ArrayList<Commodity1>();
		
		
		
		//ʱ��
		
		//ʱ���ʽ
		SimpleDateFormat sdf= new SimpleDateFormat("yy/MM/dd HH:mm:ss ");  
		
		//��ʼʱ��
		Calendar calStart = Calendar.getInstance();
		calStart.set(2017, 0, 1, 0, 0, 0);
		//ÿ��Ŀ�ʼ
		Calendar dayStart = (Calendar) calStart.clone();
		////ÿ��Ľ���
		Calendar dayEnd;
		
		//����ʱ��
		Calendar calEnd = Calendar.getInstance();
		calEnd.set(2017, 3, 1, 0, 0, 0);
		int i=0;
		int time=0;
		int dayOfYear=0;
		while (calStart.before(calEnd)) {	
			//ʱ����������ֵ
			time = Random.rInt(Constant.Time);
			//�ڿ�ʼʱ��Ļ���������һ�����ֵ����λ���룩
			calStart.add(Calendar.SECOND, time);
			//��¡��ʱ��ʱ��
			Calendar cal = (Calendar) calStart.clone();
			//ÿ��Ľ���
			dayEnd = cal;	
			
			//��dayStart������һ���00��00��00
			dayStart.set(dayEnd.get(Calendar.YEAR),dayEnd.get(Calendar.MONTH),
					dayEnd.get(Calendar.DAY_OF_MONTH),0,0,0);
			//�����������
			for (int j = 0; j < Constant.Number; j++) {
				pPrice[j]=Random.rDouble(Constant.PPRICE);
			}
			
			
			tTurnover=Random.rDouble(Constant.SPRICE);					
			int cIdSelect = Random.rInt(19);
			sPrice = tTurnover+pPrice[cIdSelect];
			
			Date date = cal.getTime();
			dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
			Commodity1 commodity1 = new Commodity1(i++, cId.get(cIdSelect), 
					cName[cIdSelect], pPrice[cIdSelect], sPrice, tTurnover, dayOfYear,date);
						
			dao.insertCommodityOne(commodity1);
			System.out.println(i);
		}
		//Calendar dayStart = timeCalendars.get(0);
		
		
		
		
//		for (int i = 0; i < timeCalendars.size(); i++) {
//			Calendar dayEnd = timeCalendars.get(i);			
//			if (dayStart.get(Calendar.DAY_OF_YEAR)!=dayEnd.get(Calendar.DAY_OF_YEAR)) {
//				
//				dayStart.set(dayEnd.get(Calendar.YEAR),dayEnd.get(Calendar.MONTH),
//						dayEnd.get(Calendar.DAY_OF_MONTH),0,0,0);
//				for (int j = 0; j < Constant.Number; j++) {
//					pPrice[j]=Random.rDouble(Constant.PPRICE);
//				}	
//			}
//			tTurnover=Random.rDouble(Constant.SPRICE);					
//			int cIdSelect = Random.rInt(19);
//			sPrice = tTurnover+pPrice[cIdSelect] ;
//			Commodity1 commodity1 = new Commodity1(i, cId.get(cIdSelect), 
//					cName[cIdSelect], pPrice[cIdSelect], sPrice, tTurnover, timeCalendars.get(i));
//			
//			list.add(commodity1);
//			
//			
//			//dao.insertCommodityOne(commodity1);
//		}
//		dao.insertCommodityAll(list);
	}


}
