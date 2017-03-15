package main.java.commodity.resources;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * ����ʱ������
 * ʱ��Ĭ��Ϊ2017��
 * @author Mathartsys.xiaobai
 *
 */
public class GenerateDate {
	public GenerateDate() {
		
	}
	
	private  int[] dateNumber = {31,28,31,30,31,30,31,31,30,31,30,31};
	
	/**
	 * ���������б�
	 * @return
	 */
	public List<Calendar> getDate(){
		List<Calendar> date = new ArrayList<Calendar>();
		for (int i = 0; i < Constant.DATEMONTH; i++) {
			for (int j = 1; j <= dateNumber[i]; j++) {
				Calendar calendar = Calendar.getInstance();
				calendar.set(2017, i, j);
				date.add(calendar);
			}
		}
		
		return date;
		}

	/**
	 * �������б�ת��Ϊ�ַ����б�������
	 * @param date
	 * @return
	 */
	public List<String> dateToString(List<Calendar> date) {
		List<String> dateList = new ArrayList<String>();
		for (int i = 0; i < date.size(); i++) {
			String date1 = new SimpleDateFormat("yyyyMMdd").format(date.get(i).getTime());
			dateList.add(date1);
		}
		return dateList;	
	}

}
