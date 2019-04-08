package com.cg.bms.service;

import java.sql.ResultSet;

import com.cg.bms.bean.Account;

public interface AccountService {

	public void connect() throws Exception;
	
	public Account getAccount(long accNo) throws Exception;
	
	public long addAccount(String name, double amt) throws Exception;
	
	public ResultSet getTransactions(long accNo) throws Exception;
	
	public Double deposit(long accNo, double amt) throws Exception;
	
	public Double withdraw(long accNo, double amt) throws Exception;
	
	public Double transfer(long accNo1, long accNo2, double amt) throws Exception;
}
