package com.cg.bms.UI;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

import com.cg.bms.bean.Transaction;
import com.cg.bms.service.AccountService;
import com.cg.bms.service.AccountServiceImpl;

public class MainUI {

	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		AccountService service = new AccountServiceImpl();
		service.connect();
		int op = 0;
		
		do {
			System.out.println("Welcome To Payment Wallet XYZ!!");
			System.out.println();
			System.out.print("\n1->Create new account\n2->Account Details\n3->Deposit\n4->Withdrawl\n5->Transfer\n6->MiniStatement\n7->Exit");
			System.out.println();
			System.out.println();
			System.out.print("\nEnter your choice :");
			
			choice = sc.nextInt();
			
			switch (choice) {
			case 1: {
				
				System.out.print("\nEnter Your name : ");
				String name = sc.next();
				System.out.print("\nEnter the opening balance : ");
				double balance = sc.nextDouble();
				System.out.print("\nYour Account Number is : "+service.addAccount(name, balance));
				break;
			}
			case 2: {
				
				System.out.print("\nEnter your account number : ");
				long accountNumber = sc.nextLong();
				System.out.println(service.getAccount(accountNumber));
				break;
			}
			case 3: {
				
				System.out.print("\nEnter Your Account Number : ");
				long accountNumber = sc.nextLong();
				System.out.print("\nEnter Deposit Amount : ");
				double amount = sc.nextDouble();
				System.out.println("Your updated balance is : "+service.deposit(accountNumber, amount));
				break;
			}
			case 4: {
				System.out.print("\nEnter Your Account Number : ");
				long accountNumber = sc.nextLong();
				System.out.print("\nEnter Amount you want to withdrawl : ");
				double amount = sc.nextDouble();
				System.out.println("Your updated balance is : "+service.withdraw(accountNumber, amount));
				break;

			}
			case 5: {
				System.out.print("\nEnter your Account Number :");
				long accNo1 = sc.nextLong();
				System.out.print("\nEnter the Account to be recieved : ");
				long accNo2 = sc.nextLong();
				System.out.print("\nEnter the Amount to be Transfered : ");
				double amt = sc.nextDouble();
				System.out.println("Your updated balance is : "+service.transfer(accNo1, accNo2, amt));
				break;
			}
			case 6: {
				Transaction tc=new Transaction();
				System.out.print("\nEnter your Account Number :");
				long accountNumber = sc.nextLong();
				System.out.println();
				ResultSet rs = service.getTransactions(accountNumber);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				while(rs.next()) {
						
					for(int i = 1 ; i <= columnsNumber; i++){

					      System.out.print(rs.getString(i) + "	"); //Print one element of a row

					}
					System.out.println();
				}
				break;
			}
			case 7: System.exit(0);
			}
			System.out.print("\nDo you wish to continue? 1->yes, 2->No");
			op = sc.nextInt();

		} while (op != 2);

	}

}
