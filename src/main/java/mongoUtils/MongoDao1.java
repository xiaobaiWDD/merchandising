package main.java.mongoUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import main.java.commodity.resources.Commod;
import main.java.commodity.resources.Constant;
import main.java.commodity.resources1.Commod1;
import main.java.commodity.resources1.Commodity1;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * �����ݿ�Ĳ�������
 * @author Mathartsys.xiaobai
 *
 */

public class MongoDao1 {
	MongoDBUtils dbUtils = new MongoDBUtils();
	MongoOperations operations = dbUtils.getMongoOps(); 
	
	
	//��ѯ����
	/**
	 * ���ݸ���id��������
	 * @param id
	 * @return
	 */
	public Commodity1 findCommodityById(int id) {
		Commodity1 commodity = operations.findOne(new Query(Criteria.where("_id").is(id)), Commodity1.class, Constant.COLL_NAME1);
		return commodity ;
	}
	
	
	
	
	/**
	 * ���ҷ��ϸ�����ƷID�ĵ�һ��ֵ
	 * @param id
	 * @return
	 */
	public Commodity1 findCommodityFirstByCId(int id) {
		Commodity1 commodity = operations.findOne(new Query(Criteria.where("cId").is(id)), Commodity1.class, Constant.COLL_NAME1);
		return commodity ;
	}
	/**
	 * ���ҷ��ϸ�����ƷID��ȫ��ֵ
	 * @param id
	 * @return
	 */
	public List<Commodity1> findCommodityAllByCId(int id){
		List<Commodity1> list = new ArrayList<Commodity1>();
		list = operations.find(new Query(Criteria.where("cId").is(id)), Commodity1.class, Constant.COLL_NAME1);
		return list;
		}
	
	/**
	 * ���ҷ��ϸ������ڵ�ȫ��ֵ
	 * @param date
	 * @return
	 */
	public List<Commodity1> findCommodityAllByDate(String date){
		List<Commodity1> list = new ArrayList<Commodity1>();
		list = operations.find(new Query(Criteria.where("date").is(date)), Commodity1.class, Constant.COLL_NAME1);
		return list;
		}
	/**
	 * �������ݿ�ָ�������е�ȫ������
	 * @return
	 */
	
	public List<Commodity1> findCommodityAll(){
		
		return operations.findAll(Commodity1.class, Constant.COLL_NAME1);
		}
	
	/**
	 * ���ҷ��ϸ������Ƶ�ֵ
	 * @param name
	 * @return
	 */

	public List<Commodity1> findCommodityAllByName(String name) {
		List<Commodity1> list = new ArrayList<Commodity1>();
		list = operations.find(new Query(Criteria.where("cName").is(name)), Commodity1.class, Constant.COLL_NAME1);
		return list;
	}
	
	/**
	 * ���ݸ�����������ѯ����С��ĳ��ֵ������
	 * @param condition
	 * @param num
	 * @param name
	 * @return
	 */
	public List<Commodity1> findCommodity(String condition,double num,String name) {
		List<Commodity1> list = new ArrayList<Commodity1>();
		Commodity1 commodity = new Commodity1();
		switch (condition) {
		case "lt":
			list = operations.find(new Query(Criteria.where(name).lt(num)), Commodity1.class, Constant.COLL_NAME1);
			break;
		case "lte":
			list = operations.find(new Query(Criteria.where(name).lte(num)), Commodity1.class, Constant.COLL_NAME1);
			break;
		case "gt":
			list = operations.find(new Query(Criteria.where(name).gt(num)), Commodity1.class, Constant.COLL_NAME1);
			break;
		case "gte":
			list = operations.find(new Query(Criteria.where(name).gte(num)), Commodity1.class, Constant.COLL_NAME1);
			break;

		default:
			break;
		}
		
		return list;
		
	}
	
	/**
	 * ���ݸ���������ѯ���ϵ��ں͸���ָ��ֵ���б�
	 * @param condition
	 * @param number
	 * @return
	 */
	public List<Commodity1> findCommodityBySNumber(String condition,int number) {
		List<Commodity1> list = new ArrayList<Commodity1>();
		Commodity1 commodity = new Commodity1();
		switch (condition) {
		case "lt":
			list = operations.find(new Query(Criteria.where("sNumber").lt(number)), Commodity1.class, Constant.COLL_NAME1);
			break;
		case "lte":
			list = operations.find(new Query(Criteria.where("sNumber").lte(number)), Commodity1.class, Constant.COLL_NAME1);
			break;
		case "gt":
			list = operations.find(new Query(Criteria.where("sNumber").gt(number)), Commodity1.class, Constant.COLL_NAME1);
			break;
		case "gte":
			list = operations.find(new Query(Criteria.where("sNumber").gte(number)), Commodity1.class, Constant.COLL_NAME1);
			break;

		default:
			break;
		}
		
		return list;
		
	}
	
	/**
	 * �������ƺ��������ϲ�ѯ
	 * @param name
	 * @param date
	 * @return
	 */
	public List<Commodity1> findCommodityNameAndDate(String name,String date){
		List<Commodity1> list = new ArrayList<Commodity1>();
		list =operations.find(new Query(Criteria.where("cName").is(name).and("date").is(date)) ,Commodity1.class, Constant.COLL_NAME1);
		return list;
	}
	
	/**
	 * ������ƷId����Ʒ���Ʒ����ѯ
	 * @return
	 */
	public BasicDBList mongoGroup() {
		GroupBy groupBy = GroupBy.key("cId","cName").initialDocument("{sumsNumber:0,sumpPrice:0,sumsPrice:0,"
				+ "sumtTurnover:0,count:0}")
				.reduceFunction("function(doc, out)"
				+ "{out.sumsNumber+= doc.sNumber,out.sumpPrice+=doc.pPrice,"
				+ "out.sumsPrice+=doc.sPrice,out.sumtTurnover+=doc.tTurnover,out.count++}")
				.finalizeFunction("function(out){return out;}");

		GroupByResults<Commod> res = operations.group(Constant.COLL_NAME1, groupBy, Commod.class);
	
		DBObject obj = res.getRawResults();
		
		BasicDBList dbList = (BasicDBList) obj.get("retval");

		return dbList;

		}
	
	
	
	/**
	 * ͳ��һ��ʱ���ڵ�����״��
	 * @return
	 */
	public BasicDBList mongoGroupOnMonth(Calendar startDate,Calendar endDate) {
		
		long date1 = startDate.getTimeInMillis();
		long date2 = endDate.getTimeInMillis();
		
		int dayOfYear1 = startDate.get(Calendar.DAY_OF_YEAR);
		int dayOfYear2 = endDate.get(Calendar.DAY_OF_YEAR);
		System.out.println(dayOfYear1);
		System.out.println(dayOfYear2);
		
		
		//�����ѯ���
		GroupBy groupBy = GroupBy.key("cId","cName").initialDocument("{sumpPrice:0,sumsPrice:0,"
				+ "sumtTurnover:0,count:0}")
				.reduceFunction("function(doc, out){"
				//+ "if(doc.date>="+date1+" && doc.date<="+date2+"){"
				+ "if(doc.dayOfYear>="+dayOfYear1+" && doc.dayOfYear<="+dayOfYear2+"){"
				+ "out.sumpPrice+=doc.pPrice,out.sumsPrice+=doc.sPrice,"
				+"out.sumtTurnover+=doc.tTurnover,out.count++"	
				+"}"
				+ "}").finalizeFunction("function(out){return out;}");


		GroupByResults<Commod1> res = operations.group(Constant.COLL_NAME1, groupBy, Commod1.class);
		DBObject obj = res.getRawResults();		
		BasicDBList dbList = (BasicDBList) obj.get("retval");
		return dbList;
		}
	
	
	
	
	/**
	 * ͳ��һ��ʱ���ڵ���������
	 * @param startDate ��ʼʱ��
	 * @param endDate   ����ʱ��
	 * @return
	 */
	public Iterable<DBObject> mongoGroupOnMonth1(Calendar startDate,Calendar endDate) {		
		int dayOfYear1 = startDate.get(Calendar.DAY_OF_YEAR);
		int dayOfYear2 = endDate.get(Calendar.DAY_OF_YEAR);	
		//match �����ڹ������ݣ�ֻ��������������ĵ�
		//��ʼʱ��ͽ���ʱ��
        BasicDBObject[] array = {    
                new BasicDBObject("dayOfYear", new BasicDBObject("$gte",dayOfYear1)),    
                new BasicDBObject("dayOfYear", new BasicDBObject("$lte",dayOfYear2))};    
        BasicDBObject cond = new BasicDBObject();    
        cond.put("$and", array);    
        DBObject match = new BasicDBObject("$match", cond);   
        
        //group ���������е��ĵ����飬������ͳ�ƽ����
        //���÷����id
        DBObject groupId = new BasicDBObject( "cId","$cId");
        groupId.put("cName", "$cName");
        DBObject groupFields = new BasicDBObject( "_id",groupId);   

        //���ò�ѯͳ�Ƶ���Ŀ
        //sumpPrice:�����ܺ�, sumsPrice:�ۼ��ܺͣ� sumtTurnover�������ܺͣ�count������
        groupFields.put("sumpPrice", new BasicDBObject( "$sum", "$pPrice"));
        groupFields.put("sumsPrice", new BasicDBObject( "$sum", "$sPrice"));
        groupFields.put("sumtTurnover", new BasicDBObject( "$sum", "$tTurnover"));
        groupFields.put("count", new BasicDBObject( "$sum", 1));  
        DBObject group = new BasicDBObject("$group", groupFields);  
       
        //ƴ�Ӿۺ����
        List<DBObject> listAggregate = new ArrayList<DBObject>() ;
        listAggregate.add(match);
        listAggregate.add(group);
        //��ѯ
        AggregationOutput output = operations.getCollection(Constant.COLL_NAME1).aggregate(listAggregate);    
        Iterable<DBObject> list= output.results();  
		return list;
		
		}
	
	
	
	/**
	 * ͳ��һ��ʱ���ڣ�ĳ����Ʒ������״��
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	
	public Iterable<DBObject> mongoGroupOnMonthOneDay(int dayOfYear1,int dayOfYear2,String cName) {		  
        BasicDBObject[] array = {    
                new BasicDBObject("dayOfYear", new BasicDBObject("$gte",dayOfYear1)),    
                new BasicDBObject("dayOfYear", new BasicDBObject("$lt",dayOfYear2)),
                new BasicDBObject("cName", cName)};
        BasicDBObject cond = new BasicDBObject();    
        cond.put("$and", array);    
        DBObject match = new BasicDBObject("$match", cond);   
        
        //group 
  
        DBObject groupId = new BasicDBObject( "cId","$cId");
        groupId.put("cName", "$cName");
        DBObject groupFields = new BasicDBObject( "_id",groupId);   

        groupFields.put("sumpPrice", new BasicDBObject( "$sum", "$pPrice"));
        groupFields.put("sumsPrice", new BasicDBObject( "$sum", "$sPrice"));
        groupFields.put("sumtTurnover", new BasicDBObject( "$sum", "$tTurnover"));
        groupFields.put("count", new BasicDBObject( "$sum", 1));  
        DBObject group = new BasicDBObject("$group", groupFields);  
       
        List<DBObject> listAggregate = new ArrayList<DBObject>() ;
        listAggregate.add(match);
        listAggregate.add(group);
        
        AggregationOutput output = operations.getCollection(Constant.COLL_NAME1).aggregate(listAggregate);    
        Iterable<DBObject> list= output.results();  
		return list;
		
		}
	
	/**
	 * ͳ��һ��ʱ���ڵ�����״��
	 * @return
	 */
	public  Iterable<DBObject> mongoGroupSometime(Calendar startDate,Calendar endDate) {
		int dayOfYear1 = startDate.get(Calendar.DAY_OF_YEAR);
		int dayOfYear2 = endDate.get(Calendar.DAY_OF_YEAR);	
		DBObject group = (DBObject) JSON.parse("{'$group': {_id : '$cId', Number:{$sum:'$sPrice'},"
				+ "sumprice:{$sum:'$pPrice'},sumt:{$sum:'$tTurnover'},count:{$sum:1}}}");  
		DBObject match = (DBObject) JSON.parse("{'$match': {'dayOfYear':{ '$gte' : "+dayOfYear1+", '$lte' :"+dayOfYear2+"}}}");  


		List<DBObject> listAggregate = new ArrayList<DBObject>() ;
	    listAggregate.add(match);
	    listAggregate.add(group);
		AggregationOutput output = operations.getCollection(Constant.COLL_NAME1).aggregate(listAggregate);    
	    Iterable<DBObject> list= output.results();  
		return list;
		}
	
	
	
	
	
	
	
	
	//�������
	/**
	 * ����һ��Commodity����
	 * @param commodity
	 */
	public void insertCommodityOne(Commodity1 commodity) {
		operations.insert(commodity, Constant.COLL_NAME1);
	}
	
	/**
	 * ��������б��е�ȫ������
	 * @param list
	 */
	public void insertCommodityAll(List<Commodity1> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i);
			insertCommodityOne(list.get(i));
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	//ɾ������
	/**
	 * ɾ�����ϸ���id�ĵ�һ��ֵ
	 * @param id
	 */
	public void removeCommodityById(int id) {
		Commodity1 commodity = findCommodityById(id);
		operations.remove(commodity, Constant.COLL_NAME1);	
	}
	
	/**
	 * ɾ�����ϸ�����ƷID������ֵ
	 * @param id
	 */
	public void removeCommodityByCId(int id) {
		List<Commodity1> list = new ArrayList<Commodity1>();
		list = findCommodityAllByCId(id);
		for (int i = 0; i < list.size(); i++) {
			removeCommodityById(list.get(i).getId());
		}
	}
	
	
	/**
	 * ɾ�����ϸ������Ƶ�����ֵ
	 * @param name
	 */
		public void removeCommodityByName(String name) {
			List<Commodity1> list = new ArrayList<Commodity1>();
			list = findCommodityAllByName(name);
			for (int i = 0; i < list.size(); i++) {
				removeCommodityById(list.get(i).getId());
			}
		}
		
	/**
	 * ɾ�����ϸ������Ƶ�����ֵ
	 * @param date
	 */
		public void removeCommodityBy(String date) {
			List<Commodity1> list = new ArrayList<Commodity1>();
			list = findCommodityAllByName(date);
			for (int i = 0; i < list.size(); i++) {
				removeCommodityById(list.get(i).getId());
			}
		}
	
	
	/**
	 * ɾ�������е���������
	 */
	public void removeCommodityAll() {
		List<Commodity1> list = findCommodityAll();
		for (int i = 0; i < list.size(); i++) {
			removeCommodityById(list.get(i).getId());
		}
	}
	
	
	
	
	
	
	
	
	
	//���²���
	/**
	 * ��������id�ı���Ʒ����
	 * @param id
	 * @param name
	 */
	public void updataCommodityName(int id,String name) {
		operations.updateFirst(new Query(Criteria.where("cId").is(id)),new Update().set("cName", name), Commodity1.class, Constant.COLL_NAME1);
		
	}
	

	/**
	 * ��������id�ı���Ʒ����
	 * @param id
	 * @param price
	 */
	public void updataCommoditypPrice(int id,double price) {
		operations.updateFirst(new Query(Criteria.where("cId").is(id)),new Update().set("pPrice", price), Commodity1.class, Constant.COLL_NAME1);
		
	}
	
	/**
	 * ��������id�ı���Ʒ�ۼ�
	 * @param id
	 * @param price
	 */
	public void updataCommoditysPrice(int id,double price) {
		operations.updateFirst(new Query(Criteria.where("cId").is(id)),new Update().set("sPrice", price), Commodity1.class, Constant.COLL_NAME1);
	}
	
	/**
	 * ��������id�ı���Ʒ�ĳ�������
	 * @param id
	 * @param date
	 */
	public void updataCommoditydate(int id,Calendar date) {
		operations.updateFirst(new Query(Criteria.where("cId").is(id)),new Update().set("date", date), Commodity1.class, Constant.COLL_NAME1);
		
	}
	
	/**
	 * ��������id��������Commodity����
	 * @param id
	 * @param commodity
	 */
//	public void updataCommodityOne(int id,Commodity1 commodity){
//		updataCommodityName(id, commodity.getcName());
//		updataCommoditypPrice(id, commodity.getpPrice());
//		updataCommoditysPrice(id, commodity.getsPrice());
//		updataCommoditydate(id, commodity.getDate());
//	}
//	
	/**
	 * ���б��е�����Commodity�������
	 * @param list
	 */
//	public void updataCommodityAll(List<Commodity1> list){
//		for (int i = 0; i < list.size(); i++) {
//			updataCommodityOne(list.get(i).getcId(), list.get(i));
//		}
//	}
	

}
