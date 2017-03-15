package main.java.commodity.resources;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * ʵ����Ʒ�ĸ�������
 * @author Mathartsys.xiaobai
 */

@Document
public class Commodity implements Serializable{
	@Id
	private int Id;
	
	private int cId;// ��ƷID
	private String cName;// ��Ʒ����
	private int sNumber;// �۳�����
	private double pPrice;// ����
	private double sPrice;// �ۼ�
	private double tTurnover;// ӯ��
	private String date;//�۳�����

	public Commodity() {
		// TODO Auto-generated constructor stub
	}

	public Commodity(int id,int cId, String cName, int sNumber,
			double pPrice, double sPrice, double tTurnover,String date) {
		super();
		this.Id = id;
		this.cId = cId;
		this.cName = cName;
		this.sNumber = sNumber;
		this.pPrice = pPrice;
		this.sPrice = sPrice;
		this.tTurnover = tTurnover;
		this.date = date;
	}

	
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public int getsNumber() {
		return sNumber;
	}

	public void setsNumber(int sNumber) {
		this.sNumber = sNumber;
	}

	public double getpPrice() {
		return pPrice;
	}

	public void setpPrice(double pPrice) {
		this.pPrice = pPrice;
	}

	public double getsPrice() {
		return sPrice;
	}

	public void setsPrice(double sPrice) {
		this.sPrice = sPrice;
	}

	public double gettTurnover() {
		return tTurnover;
	}

	public void settTurnover(double tTurnover) {
		this.tTurnover = tTurnover;
	}

	
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Commodity [cId=" + cId + ", cName=" + cName +  ", sNumber=" + sNumber + ", pPrice=" + pPrice
				+ ", sPrice=" + sPrice + ", tTurnover=" + tTurnover +", date="
						+ date + "]";
	}

}
