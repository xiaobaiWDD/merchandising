package main.java.commodity.resources1;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * ʵ����Ʒ�ĸ�������
 * @author Mathartsys.xiaobai
 */

@Document
public class Commodity1 implements Serializable{
	@Id
	private int Id;
	
	private int cId;// ��ƷID
	private String cName;// ��Ʒ����
	private double pPrice;// ����
	private double sPrice;// �ۼ�
	private double tTurnover;// ӯ��
	//private Calendar date;//�۳�����
	private int dayOfYear;//ʱ����һ���е�����
	private Date date;//�۳�����
	
	public Commodity1() {
		// TODO Auto-generated constructor stub
	}

	public Commodity1(int id,int cId, String cName,double pPrice, double sPrice,
			double tTurnover,int dayOfYear, Date date) {
		super();
		this.Id = id;
		this.cId = cId;
		this.cName = cName;
		this.pPrice = pPrice;
		this.sPrice = sPrice;
		this.tTurnover = tTurnover;
		this.dayOfYear = dayOfYear;
		this.date=date;
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

	
	
	public int getDayOfYear() {
		return dayOfYear;
	}

	public void setDayOfYear(int dayOfYear) {
		this.dayOfYear = dayOfYear;
	}
	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss ");
		return "Commodity [cId=" + cId + ", cName=" + cName +  ", pPrice=" + pPrice
				+ ", sPrice=" + sPrice + ", tTurnover=" + tTurnover +", date="
						+ sdf.format(date.getTime()) + "]";
	}

}
