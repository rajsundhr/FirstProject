import java.util.*;
public class Customer {
	
       static int id=1;
       int c_id;
       String name;
       String en_pwd;
       long AcNo;
       double balance;
       int t_id;
       int count=0;
       ArrayList<Transaction> t_list;
       
       Customer(String name, double balance,String en_pwd)   {
    	   this.name = name;
    	   this.balance = balance;
    	   this.en_pwd = en_pwd;
    	   c_id = id;
    	   AcNo = id+100;
    	   id++;
    	   t_id=1;
    	   t_list = new ArrayList<>();
    	   Addtransaction(new Transaction ("Account Opening",this.balance,this.balance));
       }
       
       Customer(String name,String en_pwd)   {
    	   this.name = name;
    	   this.balance = 10000; //default value
    	   this.en_pwd = en_pwd;
    	   c_id = id;
    	   AcNo = id+100;
    	   id++;
    	   t_id=1;
    	   t_list = new ArrayList<>();
    	   Addtransaction(new Transaction("Account Opening",balance,balance));
       }

	public void Addtransaction(Transaction transaction) {
		transaction.t_id = this.t_id++; 
		t_list.add(transaction);
		if(!transaction.type.equals("Account Opening") && !transaction.type.contains("CashDeposit From") && !transaction.type.contains("Fee"))
		 count++;
		if(count==3) { //actually 10 transactions,but for testing easiness,it is assumed to be 3
			count=0;
			System.out.println("Maintenance Fee for 3 transactions: Rs.100");
			balance = balance-100;
			Addtransaction(new Transaction("Maintanence Fee",100,balance));
		}
	}
	
	public void ShowAllTransaction() {
		System.out.println("**********************************************************************************************");
        System.out.println("Customer Name : "+name);
		System.out.println("Account Number: "+AcNo);
		System.out.println("Balance       : "+balance);
		System.out.println("**********************************************************************************************");
        System.out.println("All your Transactions  : ");
		for(Transaction t:t_list) {
			t.show();
		}
	}

	public void show() {
		System.out.format("Customer Id :%3d  ",c_id);
		System.out.format("Customer Name : %-10s  ",name);
		System.out.format("Account Number: %-5d",AcNo);
		System.out.format("Balance: %.2f",balance);
		System.out.println();
		
	}
       
}
