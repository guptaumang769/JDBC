package com.cg.bms.bean;

public class Transaction {

	private long transactionID;
	private long acc_No;
	private String transactionType;
	private double amount;
	private double acc_bal;
	private String dateTime;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public Transaction(long transactionID, long acc_No, String transactionType, double amount, double acc_bal,
			String dateTime) {
		super();
		this.transactionID = transactionID;
		this.acc_No = acc_No;
		this.transactionType = transactionType;
		this.amount = amount;
		this.acc_bal = acc_bal;
		this.dateTime = dateTime;
	}

	public long getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
	}

	public long getAcc_No() {
		return acc_No;
	}

	public void setAcc_No(long acc_No) {
		this.acc_No = acc_No;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAcc_bal() {
		return acc_bal;
	}

	public void setAcc_bal(double acc_bal) {
		this.acc_bal = acc_bal;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "Transaction [transactionID=" + transactionID + ", acc_No=" + acc_No + ", transactionType="
				+ transactionType + ", amount=" + amount + ", acc_bal=" + acc_bal + ", dateTime=" + dateTime + "]";
	}
	
	

}
