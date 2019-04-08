package com.cg.bms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.cg.bms.bean.Account;
import com.cg.bms.bean.Transaction;


public class AccountDAOImpl implements AccountDAO {
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	Connection con = null;

	@Override
	public void connect() throws Exception {
		// TODO Auto-generated method stub
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "hr", "hr");
	}

	@Override
	public Account getAccount(long accNo) throws Exception {
		// TODO Auto-generated method stub
		String query = "select * from Acc where Acc_No=" + accNo;
		Account acc = new Account();
		acc.setAcc_No(accNo);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		acc.setAcc_holder(rs.getString(2));
		acc.setAcc_bal(rs.getDouble(3));
		return acc;
	}

	@Override
	public ResultSet getTransaction(long accNo) throws Exception {
		// TODO Auto-generated method stub
		String query = "select * from Transaction where Acc_No=" + accNo;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);

		return rs;
	}

	@Override
	public void printAccountsTable() throws Exception {
		// TODO Auto-generated method stub
		String query="select * from Acc";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) {
			String data=rs.getLong(1)+": "+rs.getString(2)+": "+rs.getDouble(3);
			System.out.println(data);
		}
		st.close();
	}

	@Override
	public long addAccount(String name, double amt) throws Exception {
		// TODO Auto-generated method stub
		String transactionType = "Opening Balance";
		Account acc = new Account();
		String query1 = "select max(Acc_No) from Acc";
		Statement st1 = con.createStatement();
		ResultSet rs1 = st1.executeQuery(query1);
		long accNo = 0;
		if (rs1.next())
			accNo = rs1.getLong(1);
		acc.setAcc_No(accNo + 1);
		acc.setAcc_holder(name);
		acc.setAcc_bal(amt);
		st1.close();

		Transaction ts = new Transaction();
		String query2 = "select max(TRANSACTION_ID) from Transaction";
		Statement st2 = con.createStatement();
		ResultSet rs2 = st2.executeQuery(query2);
		long transactionId = 0;
		if (rs2.next())
			transactionId = rs2.getLong(1);
		ts.setTransactionID(transactionId + 1);
		ts.setAcc_No(acc.getAcc_No());
		ts.setTransactionType(transactionType);
		ts.setAmount(amt);
		ts.setAcc_bal(acc.getAcc_bal());
		LocalDateTime now = LocalDateTime.now();
		ts.setDateTime(dtf.format(now));
		st2.close();

		addTransaction(acc, transactionType, amt, ts);

		String query = "insert into Acc values (?,?,?)";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, "" + acc.getAcc_No());
		pst.setString(2, acc.getAcc_holder());
		pst.setString(3, "" + acc.getAcc_bal());
		pst.executeUpdate();

		pst.close();
		return acc.getAcc_No();
	}

	@Override
	public void addTransaction(Account acc, String transactionType, double amt, Transaction ts)
			throws Exception {
		// TODO Auto-generated method stub
		String query = "insert into Transaction values (?,?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setLong(1, ts.getTransactionID());
		pst.setLong(2, ts.getAcc_No());
		pst.setString(3, ts.getTransactionType());
		pst.setDouble(4, ts.getAmount());
		pst.setDouble(5, ts.getAcc_bal());
		pst.setString(6, ts.getDateTime());
		pst.executeUpdate();
		pst.close();
	}

	@Override
	public void printTransactionsTable() throws Exception {
		// TODO Auto-generated method stub
		String query = "select * from Transaction";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			String userData = rs.getLong(1) + " : " + rs.getLong(2) + " : " + rs.getString(3) + " : " + rs.getDouble(4)
					+ " : " + rs.getDouble(5) + " : " + rs.getString(6);
			System.out.println(userData);
		}
		st.close();
	}

	@Override
	public Double deposit(long accNo, double amt) throws Exception {
		// TODO Auto-generated method stub
		String transactionType = "Deposit";
		Account acc = balInc(accNo, amt);

		Transaction ts = new Transaction();
		String query1 = "select max(TRANSACTION_ID) from Transaction";
		Statement st1 = con.createStatement();
		ResultSet rs1 = st1.executeQuery(query1);
		long transactionId = 0;
		if (rs1.next())
			transactionId = rs1.getLong(1);
		ts.setTransactionID(transactionId + 1);
		ts.setAcc_No(acc.getAcc_No());
		ts.setTransactionType(transactionType);
		ts.setAmount(amt);
		ts.setAcc_bal(acc.getAcc_bal());
		LocalDateTime now = LocalDateTime.now();
		ts.setDateTime(dtf.format(now));
		st1.close();

		addTransaction(acc, transactionType, amt, ts);
		String query2 = "update Acc set Acc_bal = " + acc.getAcc_bal() + " where Acc_No = "
				+ acc.getAcc_No();
	
		PreparedStatement pst = con.prepareStatement(query2);
		pst.executeUpdate();
		System.out.println("Deposit Successful");
		pst.close();
		return acc.getAcc_bal();
	}

	@Override
	public Account balInc(long accNo, double amt) throws Exception {
		// TODO Auto-generated method stub
		String query = "select * from Acc where Acc_No=" + accNo;
		Account acc = new Account();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		acc.setAcc_No(rs.getLong(1));
		
		acc.setAcc_bal(rs.getDouble(3) + amt);
		st.close();
		return acc;
	}

	@Override
	public Double withdraw(long accNo, double amt) throws Exception {
		// TODO Auto-generated method stub
		String transactionType = "Withdraw";
		Account acc = balDec(accNo, amt);

		Transaction ts = new Transaction();
		String query1 = "select max(TRANSACTION_ID) from Transaction";
		Statement st1 = con.createStatement();
		ResultSet rs1 = st1.executeQuery(query1);
		long transactionId = 0;
		if (rs1.next())
			transactionId = rs1.getLong(1);
		ts.setTransactionID(transactionId + 1);
		ts.setAcc_No(acc.getAcc_No());
		ts.setTransactionType(transactionType);
		ts.setAmount(amt);
		ts.setAcc_bal(acc.getAcc_bal());
		LocalDateTime now = LocalDateTime.now();
		ts.setDateTime(dtf.format(now));
		st1.close();

		addTransaction(acc, transactionType, amt, ts);
		String query2 = "update Acc set acc_bal = " + acc.getAcc_bal() + " where Acc_No = "
				+ acc.getAcc_No();
		
		PreparedStatement pst = con.prepareStatement(query2);
		pst.executeUpdate();
		System.out.println("Withdraw Successful");
		pst.close();
		return acc.getAcc_bal();
	}

	@Override
	public Account balDec(long accNo, double amt) throws Exception {
		// TODO Auto-generated method stub
		String query = "select * from Acc where acc_No=" + accNo;
		Account acc = new Account();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		acc.setAcc_No(rs.getLong(1));
		acc.setAcc_bal(rs.getDouble(3) - amt);
		st.close();
		return acc;
	}

	@Override
	public Double transfer(long accNo1, long accNo2, double amt) throws Exception {
		// TODO Auto-generated method stub
		String transactionType = "Transfer-Out";
		Account acc1 = balDec(accNo1, amt);

		Transaction ts = new Transaction();
		String query1 = "select max(TRANSACTION_ID) from Transaction";
		Statement st1 = con.createStatement();
		ResultSet rs1 = st1.executeQuery(query1);
		long transactionId = 0;
		if (rs1.next())
			transactionId = rs1.getLong(1);
		ts.setTransactionID(transactionId + 1);
		ts.setAcc_No(acc1.getAcc_No());
		ts.setTransactionType(transactionType);
		ts.setAmount(amt);
		ts.setAcc_bal(acc1.getAcc_bal());
		LocalDateTime now = LocalDateTime.now();
		ts.setDateTime(dtf.format(now));
		st1.close();
		addTransaction(acc1, transactionType, amt, ts);

		transactionType = "Transfer-In";
		Account acc2 = balInc(accNo2, amt);
		ts.setTransactionID(transactionId + 1);
		ts.setAcc_No(acc2.getAcc_No());
		ts.setTransactionType(transactionType);
		ts.setAmount(amt);
		ts.setAcc_bal(acc2.getAcc_bal());
		ts.setDateTime(dtf.format(now));
		addTransaction(acc2, transactionType, amt, ts);
		System.out.println("Transfer Successfull");
		
		return acc1.getAcc_bal();

	}

}
