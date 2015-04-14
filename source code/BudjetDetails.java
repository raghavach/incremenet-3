package com.example.bussinessanalsis;

import java.io.Serializable;

public class BudjetDetails implements Serializable {
	String budgetname , price,budgettype, startdate,enddate,month;

	

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getBudgetname() {
		return budgetname;
	}

	public void setBudgetname(String budgetname) {
		this.budgetname = budgetname;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBudgettype() {
		return budgettype;
	}

	public void setBudgettype(String budgettype) {
		this.budgettype = budgettype;
	}

	
}
