package com.cg.bms.dao;

import java.sql.ResultSet;

import com.cg.bms.bean.Account;
import com.cg.bms.bean.Transaction;


public interface AccountDAO {

	public void connect() throws Exception;
	
	public Account getAccount(long accNo) throws Exception;
	
	public ResultSet getTransaction(long accNo) throws Exception;
	
	public void printAccountsTable() throws Exception;
	
	public long addAccount(String name, double amt) throws Exception;
	
	public void addTransaction(Account acc, String transactionType, double amt, Transaction ts) throws Exception;
	
	public void printTransactionsTable() throws Exception;
	
	public Double deposit(long accNo, double amt) throws Exception;
	
	public Account balInc(long accNo, double amt) throws Exception;
	
	public Double withdraw(long accNo, double amt) throws Exception;
	
	public Account balDec(long accNo, double amt) throws Exception;
	
	public Double transfer(long accNo1, long accNo2, double amt) throws Exception;

}
