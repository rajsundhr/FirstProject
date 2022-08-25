import java.util.*;

public class BankingApp {
	int choice;
	int flag;
	Scanner scan = new Scanner(System.in);
	ArrayList <Customer> customers = new ArrayList<>();

	public void banking() {
		customers.add(new Customer("Raj",10000,"bcBC23"));//"bcBC23" is the encrypted password of "abAB12"
		customers.add(new Customer("Anand",20000,"bcBC23"));
		customers.add(new Customer("Srinivasan",30000,"bcBC23"));
		customers.add(new Customer("manick",40000,"bcBC23"));
		customers.add(new Customer("velu",50000,"bcBC23"));
		System.out.println("Welcome to ABC Banking Application");
		do {
		System.out.println("**********************************************************************************************");
		System.out.println("1. Show All Customers");
		System.out.println("2. Add new Customer ");
		System.out.println("3. Login ");
		System.out.println("4. Show Top N Customers by Balance ");
		System.out.println("5. Exit ");
		System.out.println("\nPlease Enter your option");
		choice = scan.nextInt();
		scan.nextLine();
		switch (choice) {
			case 1: showAllCustomers(); break;
			case 2: addCustomer(); break;
			case 3: int c_id;
			        String password; int count=3;
			        Customer customer;
			        System.out.println("**********************************************************************************************");
					System.out.println("Welcome to Login Portal");
					System.out.println("Enter your Customer Id");
					c_id = scan.nextInt();
					customer = findCustomer(c_id);
					if (customer==null) {
						System.out.println("Wrong CustomerId");
					    break; }
					while(count!=0) {
					System.out.println("Enter your password");
					password = scan.next();
					password = Encryption(password);
					if (password.equals(customer.en_pwd)) 
							break;
					else {
						count--;
						if(count==0) {
							System.out.println("Sorry! Try Login later");
							break;
						}
						else 
				         	System.out.println("Oh! Wrong Password: "+(count)+" attempts only left");
					  } 
					}
					if(count==0)
						break;
			        Login(customer);
			        break;
			case 4: Topncustomers(); 
				     break;
			case 5:  return ;
			default: System.out.println("Invalid input\nTry again");
		}
		
		} while(choice!=5);
	}

	private void Topncustomers() {
		int n,index;
		double max;
		System.out.println("Enter the n value");
		n=scan.nextInt();
		if(n<=0 || n>=customers.size()) {
			System.out.println("Invalid n value, n value will be 1 to "+(customers.size()-1));
			return;
		}
		System.out.println("**********************************************************************************************");
		System.out.println("Top "+n+" Customers by Balance:");
		ArrayList<Customer> copy = new ArrayList<>(customers);
		for(int i=1;i<=n;i++) {
			index=0;max=Double.MIN_VALUE;
			for(int j=0;j<copy.size();j++) {
				if(max<copy.get(j).balance) {
					index=j; max=copy.get(j).balance;
				}		
			}
			copy.get(index).show();
			copy.remove(index);
		}
		
	}

	private void Login(Customer customer) {
		int choice;
		long account;
		String password="";
		String type;
		Customer transact;
		double amount;
		do {
		System.out.println("**********************************************************************************************");
		System.out.println("1. Show Account Details");
		System.out.println("2. ATM Withdrawal");
		System.out.println("3. Cash Deposit ");
		System.out.println("4. Money Transfer ");
		System.out.println("5. Show All Transactions ");
		System.out.println("6. Password Change ");
		System.out.println("7. Logout and go to Main Menu ");
		System.out.println("\nPlease Enter your option");
		choice = scan.nextInt();
		scan.nextLine();
		switch (choice) {
			case 1: System.out.println("**********************************************************************************************");
			        System.out.println("Your Account Details "); 
			        customer.show();
			        break;
			case 2: System.out.println("Enter an amount to withdraw");
			        amount = scan.nextDouble();
			        if(customer.balance-amount<2000) {
			        	System.out.println("Entered Amount is incorrect, Minbalance Rs.2000 should be maintained\nTry again");
			        }
			        else {
			        	customer.balance -= amount;
			        	customer.Addtransaction(new Transaction("ATM Withdrawal",amount,customer.balance));
			        	System.out.println("Rs."+amount+" withdrawn Successfully"+"\nNewbalance is "+customer.balance);
			        	}
			        break;
			case 3: System.out.println("Enter an amount to Deposit");
	                  	 amount = scan.nextDouble();
	        	    	customer.balance += amount;
	        	    	customer.Addtransaction(new Transaction("Cash Deposit",amount,customer.balance));
	        	    	System.out.println("Rs."+amount+" Deposited Successfully"+"\nNewbalance is "+customer.balance);
	        	   	 break;
			case 4: System.out.println("Enter Account number to which money transferred");
	               		 account = scan.nextLong();
	               		 transact = findCustomer(account);
					if (transact==null) {
						System.out.println("Wrong Account Number\nTry again");
					    break; }
	               		 System.out.println("Enter an amount to transfer");
			        amount = scan.nextDouble();
	               		 if(customer.balance-amount<2000) {
	        	    		System.out.println("Entered Amount is incorrect, Minbalance Rs.2000 should be maintained\nTry again");
	                	 }
	                	else {
	        	   	 customer.balance -= amount;
	        	   	 transact.balance += amount;
	        	   	 type = "CashDeposit To "+transact.AcNo;
	        	    	customer.Addtransaction(new Transaction(type,amount,customer.balance));
	        	    	type = "CashDeposit From "+customer.AcNo;
	        	    	transact.Addtransaction(new Transaction(type,amount,transact.balance));
	        	    	System.out.println("Rs."+amount+" Deposited Successfully to "+transact.AcNo+"\nYour Newbalance is "+customer.balance);
	        	    	}
	                	break;
			case 5: customer.ShowAllTransaction();
			        break;
			case 6:  int count =3;
			        while(count!=0) {
				    System.out.println("Enter your password");
			        password = scan.next();
			        password = Encryption(password);
			        if (password.equals(customer.en_pwd)) 
					break;
			        else {
				    count--;
				    if(count==0) {
					System.out.println("Sorry! Try Login later");
					break;
				    }
				    else 
					System.out.println("Oh! Wrong Password: "+(count)+" attempts only left");
			        } }
			        if(count==0)
				    break;
			        scan.nextLine();
			        password=(Encryption(getpwd()));
			        customer.en_pwd=password;
			        System.out.println("Password changed Successfully");
	        	   	 break;
			case 7: System.out.println("Logout Successfully\nRedirect to Main Menu");
    	            		return;     
			default: System.out.println("Invalid input\nTry again");
		     }
	  }while(choice!=7);
	}

	private Customer findCustomer(int c_id) {
		for(Customer c: customers) {
			if(c.c_id==c_id)
				return c; 
			}
		return null;
	}
	
	private Customer findCustomer(long account) {
		for(Customer c: customers) {
			if(c.AcNo==account)
				return c; 
			}
		return null;
	}

	private void addCustomer() {
		String name,password;
		System.out.println("Welcome to Add customer portal");
		System.out.println("Enter Customer Name: ");
		name = scan.nextLine();
		password = getpwd();
		System.out.println(Encryption(password));
		Customer customer=new Customer(name,Encryption(password));
		customers.add(customer);
		System.out.println("Customer Added Successfully");
		customer.show();
	}

	private String Encryption(String password) {
		StringBuilder res = new StringBuilder();
		for(char c: password.toCharArray()) {
			if (c>='a'&& c<='z') {
				if(c=='z')
					res.append('a');
				else
					res.append((char)(c+1)); //encryption is changing each character to next character in ASCII table
			}
			else if (c>='A'&& c<='Z') {
				if(c=='Z')
					res.append('A');
				else
					res.append((char)(c+1));
			}
			else if (c>='0'&& c<='9') {
				if(c=='9')
					res.append('0');
				else
					res.append((char)(c+1));
			}
		}
		return res.toString();
	}

	private String getpwd() {
		String ps1,ps2,res="";
		int flag=0;
		while(flag==0)  {
		System.out.println("Enter the password (0-9) (a-z) (A-Z)");
		ps1=scan.nextLine();
		if(checkpwd(ps1)==0) {
			System.out.println("Oh!");
			System.out.println("Password Should contains atleast 2 alphabet, 2 lowercase letter and 2 upper case letter");
			System.out.println("No Special Character allowed\nTry again ");
			System.out.println("**********************************************************************************************");
			continue;
		}
		else {
		System.out.println("ReEnter the password");
		ps2=scan.nextLine();
		if(ps1.equals(ps2)) {
			res =  ps1; 
			flag=1;
		}
		else {
			System.out.println("Oh! Passwords are not match, Try again");
			flag=0;
		    }
		    }
		}
		return res;
	}

	private int checkpwd(String ps1) {
		//checking Password Should contains atleast 2 alphabet, 2 lowercase letter,2 upper case letter
		int n=0,l=0,u=0;
		for(char c:ps1.toCharArray()) {
			if(c>='a'&&c<='z')
				l++;
			else if (c>='A'&&c<='Z')
				u++;
			else if (c>='0'&&c<='9')
				n++;
			else 
				return 0;
		}
		if( l<2 || n<2 || u<2 )
		    return 0;
		else 
			return 1;
	}

	private void showAllCustomers() {
		
		System.out.println("**********************************************************************************************");
		System.out.println("All Customer Details ");
		for(Customer c: customers )
		c.show();
	}

}
