package org.zerock.domain;

public class LedSearchCriteria extends Criteria{

	private String year;
	private String month;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	@Override
	public String toString() {
		return super.toString() + " LedSearchCriteria [year=" + year + ", month="
				+ month + "]";
	}
}


