package com.fdm.wealthnow.common;

public class FundsBalance {
	private int userId; 
	
	
	public CashBalance(int userId) {
		this.userId=userId;
	}
	
	public long getBalance(int userId) {
		//Get the balance for that userId from SQL database
		//Create a variable, bal, of long type to store the balance
		
		//long bal = select bal from user_table where id=userId
		//return bal;
	
	}
	
	//for any transaction, update the balance first followed by getting the balance
	public void updateBalance(int userId, long amount, AddorRemoveFunds updatefunds){
		long total=0;
		//get the bal in the user_table in SQL database
		if(updatefunds.equals("deposit") ) {
			total = bal + amount;
		}	
		else {
			if(bal < amount) {
				throw new Exception("Not enough funds to withdraw");
			}
			else {
				total = bal - amount;
			}
		}
		
		//update the bal in the user_table in SQL database 
		//set bal=total where id=userId
	}
	
}
