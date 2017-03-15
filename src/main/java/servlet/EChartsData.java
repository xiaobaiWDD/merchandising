package main.java.servlet;

import java.util.Calendar;
import java.util.Map;


import main.java.mongoUtils.MongoDao1;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Pie;
import com.mongodb.DBObject;

public class EChartsData {
	
	public Option selectRemoveCauses(Calendar calendarStart,Calendar calendarEnd) {
		
		MongoDao1 dao1 =new MongoDao1();
		Iterable<DBObject> list = dao1.mongoGroupOnMonth1(calendarStart,calendarEnd);
		System.out.println(list);
		//����Option  
	    Option option = new Option();  
	    option.title("�޳�ҩƷ").tooltip(Trigger.axis).legend("��Ԫ��"); 
	    
	    //����Ϊֵ��  
	    option.xAxis(new ValueAxis().boundaryGap(0d, 0.01));  
	    //������Ŀ��  
	    CategoryAxis category = new CategoryAxis();  
	    //��״����  
	    Bar bar = new Bar("��Ԫ��");  
	    //��ͼ����  
	    Pie pie = new Pie("��Ԫ��");  
	    
	    //ѭ������  
	    for (DBObject dbObject:list) {  
	        //������Ŀ  
	    	Map<String, String> map = (Map<String, String>) dbObject.get("_id");
	        category.data(map.get("cName"));  
	        //System.out.println(map.get("cName"));
	        //��Ŀ��Ӧ����״ͼ  
	        bar.data(dbObject.get("sumtTurnover"));  
	        //��ͼ����  
	        //pie.data(map.get("cName").toString(), dbObject.get("sumtTurnover")));  
	    }  
	    
	    //������Ŀ��  
	    option.yAxis(category);  
	    //��ͼ��Բ�ĺͰ뾶  
	    pie.center(900,380).radius(100);  
	    //��������  
	    option.series(bar, pie);  
	    //����ҩƷ���ֹ�����ͼ���������������180������grid���Կ�ECharts�Ĺٷ��ĵ�  
	    option.grid().x(180);  
	    //����Option  
	    return option;  
		
	}

}
