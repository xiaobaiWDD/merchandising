package main.java.commodity.resources;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
/**
 * ���ɸ��������
 * @author Mathartsys.xiaobai
 *
 */
public class Random {

	
	public Random() {
	// TODO Auto-generated constructor stub
	}
	
	/**
	 * ����double���͵������
	 * @param number
	 * @return
	 */
	public static double rDouble(int number){
		DecimalFormat dcmFmt = new DecimalFormat("0.00");
		double d = Math.random()*number;
		String d1 = dcmFmt.format(d);
		return 	Double.valueOf(d1);
	}
	
	/**
	 * ����int���͵������
	 * @param number
	 * @return
	 */
	public static int rInt(int number) {
		return (int) Math.round(Math.random()*number);
	}
	
	/**
	 * ����int���͵Ĳ��ظ��������
	 * @return
	 */
	public static List<Integer> RcIdList() {
		List<Integer> cId = new ArrayList<Integer>();
		while (cId.size()<Constant.Number) {
			int id = rInt(Constant.CID);
			if (!cId.contains(id)) {
				cId.add(id);
			}	
		}
		return cId;
		
	}
	

}
