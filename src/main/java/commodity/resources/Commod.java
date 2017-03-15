package main.java.commodity.resources;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * ʵ�ְ�������ѯ�������
 * @author Mathartsys.xiaobai
 */

@Document
public class Commod implements Serializable{
	
	private int cId;// ��ƷID
	private String cName;// ��Ʒ����
	private int sNumber;// �۳�����
	private double pPrice;// ����
	private double sPrice;// �ۼ�
	private double tTurnover;// ӯ��

	public Commod() {
		// TODO Auto-generated constructor stub
	}

	public Commod(int cId, String cName, int sNumber,
			double pPrice, double sPrice, double tTurnover) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.sNumber = sNumber;
		this.pPrice = pPrice;
		this.sPrice = sPrice;
		this.tTurnover = tTurnover;
	}


	public int getcId() {
		return cId;
	}

	public String getcName() {
		return cName;
	}

	public int getsNumber() {
		return sNumber;
	}


	public double getpPrice() {
		return pPrice;
	}


	public double getsPrice() {
		return sPrice;
	}

	public double gettTurnover() {
		return tTurnover;
	}

	@Override
	public String toString() {
		return "Commodity [cId=" + cId + ", cName=" + cName +  ", sNumber=" + sNumber + ", pPrice=" + pPrice
				+ ", sPrice=" + sPrice + ", tTurnover=" + tTurnover +"]";
	}

}
