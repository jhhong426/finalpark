package org.zerock.domain;

import java.util.Arrays;
import java.util.Date;

public class LedgerVO {
	
	private Integer num;
	private Integer boardNum;
	private Integer divide;
	private Integer amount;
	private Integer category;
	public String processCategory;
	private String context;
	private byte[] photo;
	private String regdate;
	public String processDivide;
	
	@Override
	public String toString() {
		
		if(divide==1)
		{
			processDivide = "수입";
		}
		else if(divide==0){
			processDivide="지출";
		}else{
			processDivide="뭐여";
		}
		
		
		if(divide==0){
		switch(category){
		case 0 :processCategory="기타"; break;
		case 1 :processCategory="식사";break;
		case 2 :processCategory="문화";break;
		case 3 :processCategory="교육";break;
		case 4 :processCategory="뷰티";break;
		case 5 :processCategory="학습";break;
		}
		}else if(divide==1){
		switch(category){
		case 0 :processCategory="기타";break;
		case 1 :processCategory="주수입";break;
		case 2 :processCategory="부수입";break;
		case 3 :processCategory="대출/금융";break;
		}	
			
		}
		
		return "{num:"+num+",divide:'"+processDivide+"',amount:"+amount+",category:'"+processCategory+"',context:'"+context+"',photo:'"+Arrays.toString(photo)+"',regdate:'"+regdate+"'}";
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(Integer boardNum) {
		this.boardNum = boardNum;
	}
	public Integer getDivide() {
		return divide;
	}
	public void setDivide(Integer div) {
		this.divide = div;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
}
