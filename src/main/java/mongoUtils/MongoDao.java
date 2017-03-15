package main.java.mongoUtils;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import main.java.commodity.resources.Commod;
import main.java.commodity.resources.Commodity;
import main.java.commodity.resources.Constant;
import main.java.commodity.resources1.Commodity1;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Field;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
/**
 * �����ݿ�Ĳ�������
 * @author Mathartsys.xiaobai
 *
 */

public class MongoDao {
	MongoDBUtils dbUtils = new MongoDBUtils();
	MongoOperations operations = dbUtils.getMongoOps(); 
	
	
	//��ѯ����
	/**
	 * ���ݸ���id��������
	 * @param id
	 * @return
	 */
	public Commodity findCommodityById(int id) {
		Commodity commodity = operations.findOne(new Query(Criteria.where("_id").is(id)), Commodity.class, Constant.COLL_NAME);
		return commodity ;
	}
	/**
	 * ���ҷ��ϸ�����ƷID�ĵ�һ��ֵ
	 * @param id
	 * @return
	 */
	public Commodity findCommodityFirstByCId(int id) {
		Commodity commodity = operations.findOne(new Query(Criteria.where("cId").is(id)), Commodity.class, Constant.COLL_NAME);
		return commodity ;
	}
	/**
	 * ���ҷ��ϸ�����ƷID��ȫ��ֵ
	 * @param id
	 * @return
	 */
	public List<Commodity> findCommodityAllByCId(int id){
		List<Commodity> list = new ArrayList<Commodity>();
		list = operations.find(new Query(Criteria.where("cId").is(id)), Commodity.class, Constant.COLL_NAME);
		return list;
		}
	
	/**
	 * ���ҷ��ϸ������ڵ�ȫ��ֵ
	 * @param date
	 * @return
	 */
	public List<Commodity> findCommodityAllByDate(String date){
		List<Commodity> list = new ArrayList<Commodity>();
		list = operations.find(new Query(Criteria.where("date").is(date)), Commodity.class, Constant.COLL_NAME);
		return list;
		}
	/**
	 * �������ݿ�ָ�������е�ȫ������
	 * @return
	 */
	
	public List<Commodity> findCommodityAll(){
		
		return operations.findAll(Commodity.class, Constant.COLL_NAME);
		}
	
	/**
	 * ���ҷ��ϸ������Ƶ�ֵ
	 * @param name
	 * @return
	 */

	public List<Commodity> findCommodityAllByName(String name) {
		List<Commodity> list = new ArrayList<Commodity>();
		list = operations.find(new Query(Criteria.where("cName").is(name)), Commodity.class, Constant.COLL_NAME);
		return list;
	}
	
	/**
	 * ���ݸ�����������ѯ����С��ĳ��ֵ������
	 * @param condition
	 * @param num
	 * @param name
	 * @return
	 */
	public List<Commodity> findCommodity(String condition,double num,String name) {
		List<Commodity> list = new ArrayList<Commodity>();
		Commodity commodity = new Commodity();
		switch (condition) {
		case "lt":
			list = operations.find(new Query(Criteria.where(name).lt(num)), Commodity.class, Constant.COLL_NAME);
			break;
		case "lte":
			list = operations.find(new Query(Criteria.where(name).lte(num)), Commodity.class, Constant.COLL_NAME);
			break;
		case "gt":
			list = operations.find(new Query(Criteria.where(name).gt(num)), Commodity.class, Constant.COLL_NAME);
			break;
		case "gte":
			list = operations.find(new Query(Criteria.where(name).gte(num)), Commodity.class, Constant.COLL_NAME);
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
	public List<Commodity> findCommodityBySNumber(String condition,int number) {
		List<Commodity> list = new ArrayList<Commodity>();
		Commodity commodity = new Commodity();
		switch (condition) {
		case "lt":
			list = operations.find(new Query(Criteria.where("sNumber").lt(number)), Commodity.class, Constant.COLL_NAME);
			break;
		case "lte":
			list = operations.find(new Query(Criteria.where("sNumber").lte(number)), Commodity.class, Constant.COLL_NAME);
			break;
		case "gt":
			list = operations.find(new Query(Criteria.where("sNumber").gt(number)), Commodity.class, Constant.COLL_NAME);
			break;
		case "gte":
			list = operations.find(new Query(Criteria.where("sNumber").gte(number)), Commodity.class, Constant.COLL_NAME);
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
	public List<Commodity> findCommodityNameAndDate(String name,String date){
		List<Commodity> list = new ArrayList<Commodity>();
		list =operations.find(new Query(Criteria.where("cName").is(name).and("date").is(date)) ,Commodity.class, Constant.COLL_NAME);
		return list;
	}
	
	
	/**
	 * ���ݸ�������ͳ��
	 * @param name
	 * @param date
	 * @return
	 */
	public List<Commodity> findCommodityOnMonth(String name,String startDate,String endDate){
		List<Commodity> list = new ArrayList<Commodity>();
		list =operations.find(new Query(Criteria.where("cName").is(name).and("date").gte(startDate).lte(endDate)) ,Commodity.class, Constant.COLL_NAME);
		
		
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
//		GroupBy groupBy = GroupBy.key("cId").initialDocument("{count:0}")
//				.reduceFunction("function(doc, out){out.count++}")
//				.finalizeFunction("function(out){return out;}");

		GroupByResults<Commod> res = operations.group(Constant.COLL_NAME, groupBy, Commod.class);
	
		DBObject obj = res.getRawResults();
		
		BasicDBList dbList = (BasicDBList) obj.get("retval");

		return dbList;

		}
	
	
	/**
	 * ������ƷId����Ʒ���Ʒ����ѯ
	 * @return
	 */
	public BasicDBList mongoGroupOnMonth(String startDate,String endDate) {
		GroupBy groupBy = GroupBy.key("cId","cName").initialDocument("{sumsNumber:0,sumpPrice:0,sumsPrice:0,"
				+ "sumtTurnover:0,count:0}")
				.reduceFunction("function(doc, out){"
				+ "if(doc.date>="+startDate+" && doc.date<="+endDate+"){"
				
				+ "out.sumsNumber+= doc.sNumber,out.sumpPrice+=doc.pPrice,"
				+ "out.sumsPrice+=doc.sPrice,out.sumtTurnover+=doc.tTurnover,out.count++"	
				+"}"
				+ "}").finalizeFunction("function(out){return out;}");
//		GroupBy groupBy = GroupBy.key("cId").initialDocument("{count:0}")
//				.reduceFunction("function(doc, out){out.count++}")
//				.finalizeFunction("function(out){return out;}");

		GroupByResults<Commod> res = operations.group(Constant.COLL_NAME, groupBy, Commod.class);
	
		DBObject obj = res.getRawResults();
		
		BasicDBList dbList = (BasicDBList) obj.get("retval");

		return dbList;

		}
	
	
	
	
	//�������
	/**
	 * ����һ��Commodity����
	 * @param commodity
	 */
	public void insertCommodityOne(Commodity commodity) {
		operations.insert(commodity, Constant.COLL_NAME);
	}
	
	/**
	 * ��������б��е�ȫ������
	 * @param list
	 */
	public void insertCommodityAll(List<Commodity> list) {
		for (int i = 0; i < list.size(); i++) {
			insertCommodityOne(list.get(i));
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	//ɾ������
	/**
	 * ɾ�����ϸ���id�ĵ�һ��ֵ
	 * @param id
	 */
	public void removeCommodityById(int id) {
		Commodity commodity = findCommodityById(id);
		operations.remove(commodity, Constant.COLL_NAME);	
	}
	
	/**
	 * ɾ�����ϸ�����ƷID������ֵ
	 * @param id
	 */
	public void removeCommodityByCId(int id) {
		List<Commodity> list = new ArrayList<Commodity>();
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
			List<Commodity> list = new ArrayList<Commodity>();
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
			List<Commodity> list = new ArrayList<Commodity>();
			list = findCommodityAllByName(date);
			for (int i = 0; i < list.size(); i++) {
				removeCommodityById(list.get(i).getId());
			}
		}
	
	
	/**
	 * ɾ�������е���������
	 */
	public void removeCommodityAll() {
		List<Commodity> list = findCommodityAll();
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
		operations.updateFirst(new Query(Criteria.where("cId").is(id)),new Update().set("cName", name), Commodity.class, Constant.COLL_NAME);
		
	}
	
	/**
	 * ��������id�ı���Ʒ�۳�����
	 * @param id
	 * @param number
	 */
	public void updataCommoditysNumber(int id,int number) {
		operations.updateFirst(new Query(Criteria.where("cId").is(id)),new Update().set("sNumber", number), Commodity.class, Constant.COLL_NAME);
		
	}
	/**
	 * ��������id�ı���Ʒ����
	 * @param id
	 * @param price
	 */
	public void updataCommoditypPrice(int id,double price) {
		operations.updateFirst(new Query(Criteria.where("cId").is(id)),new Update().set("pPrice", price), Commodity.class, Constant.COLL_NAME);
		
	}
	
	/**
	 * ��������id�ı���Ʒ�ۼ�
	 * @param id
	 * @param price
	 */
	public void updataCommoditysPrice(int id,double price) {
		operations.updateFirst(new Query(Criteria.where("cId").is(id)),new Update().set("sPrice", price), Commodity.class, Constant.COLL_NAME);
	}
	
	/**
	 * ��������id�ı���Ʒ�ĳ�������
	 * @param id
	 * @param date
	 */
	public void updataCommoditydate(int id,String date) {
		operations.updateFirst(new Query(Criteria.where("cId").is(id)),new Update().set("date", date), Commodity.class, Constant.COLL_NAME);
		
	}
	
	/**
	 * ��������id��������Commodity����
	 * @param id
	 * @param commodity
	 */
	public void updataCommodityOne(int id,Commodity commodity){
		updataCommodityName(id, commodity.getcName());
		updataCommoditysNumber(id, commodity.getsNumber());
		updataCommoditypPrice(id, commodity.getpPrice());
		updataCommoditysPrice(id, commodity.getsPrice());
		updataCommoditydate(id, commodity.getDate());
	}
	
	/**
	 * ���б��е�����Commodity�������
	 * @param list
	 */
	public void updataCommodityAll(List<Commodity> list){
		for (int i = 0; i < list.size(); i++) {
			updataCommodityOne(list.get(i).getcId(), list.get(i));
		}
	}
	

}
