package org.zerock.domain;

public class LedBoardVO {
	
	private Integer num;
	private String contents;
	private Integer total;
	private String regdate;
	
	
	public Integer getTotal(){
		return total;
	}
	public void setTotal(Integer total){
		this.total = total;
	}
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getDate() {
		return regdate;
	}
	public void setDate(String date) {
		this.regdate = date;
	}
	
	@Override
	public String toString() {
		return "LedBoardVO [num=" + num + ", contents=" + contents + ", date=" + regdate + "]";
	}

}
