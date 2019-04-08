package com.cg.bms.bean;

public class Account {

	private long acc_No;
	private String acc_holder;
	private double acc_bal;

	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(long acc_No, String acc_holder, double acc_bal) {
		super();
		this.acc_No = acc_No;
		this.acc_holder = acc_holder;
		this.acc_bal = acc_bal;
	}

	public long getAcc_No() {
		return acc_No;
	}

	public void setAcc_No(long acc_No) {
		this.acc_No = acc_No;
	}

	public String getAcc_holder() {
		return acc_holder;
	}

	public void setAcc_holder(String acc_holder) {
		this.acc_holder = acc_holder;
	}

	public double getAcc_bal() {
		return acc_bal;
	}

	public void setAcc_bal(double acc_bal) {
		this.acc_bal = acc_bal;
	}

	@Override
	public String toString() {
		return "Account [acc_No=" + acc_No + ", acc_holder=" + acc_holder + ", acc_bal=" + acc_bal + "]";
	}
	
	
}
