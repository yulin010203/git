package com.mai.json;

import java.util.Date;
import java.util.List;

public class MyBean {

	private String id = null;
	private String name = null;
	private Date date = null;
	private List cardNum = null;
	private String[] cardType = {"身份证", "银行卡" , "公车卡"};
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List getCardNum() {
		return cardNum;
	}
	public void setCardNum(List cardNum) {
		this.cardNum = cardNum;
	}
	public String[] getCardType() {
		return cardType;
	}
	public void setCardType(String[] cardType) {
		this.cardType = cardType;
	}
	
	
}
