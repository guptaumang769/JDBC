package com.cg.bms.service;

import java.sql.ResultSet;

import com.cg.bms.bean.Account;
import com.cg.bms.dao.AccountDAO;
import com.cg.bms.dao.AccountDAOImpl;

public class AccountServiceImpl implements AccountService {

	AccountDAO dao=new AccountDAOImpl();
	@Override
	public void connect() throws Exception {
		// TODO Auto-generated method stub
		dao.connect();
	}

	@Override
	public Account getAccount(long accNo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getAccount(accNo);
	}

	@Override
	public long addAccount(String name, double amt) throws Exception {
		// TODO Auto-generated method stub
		return dao.addAccount(name, amt);
	}

	@Override
	public ResultSet getTransactions(long accNo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getTransaction(accNo);
	}

	@Override
	public Double deposit(long accNo, double amt) throws Exception {
		// TODO Auto-generated method stub
		return dao.deposit(accNo, amt);
	}

	@Override
	public Double withdraw(long accNo, double amt) throws Exception {
		// TODO Auto-generated method stub
		return dao.withdraw(accNo, amt);
	}

	@Override
	public Double transfer(long accNo1, long accNo2, double amt) throws Exception {
		// TODO Auto-generated method stub
		return dao.transfer(accNo1, accNo2, amt);
	}

}
