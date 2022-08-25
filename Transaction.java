
public class Transaction {
         
		 int t_id;
         String type;
         double balance;
         double amount;
         
       Transaction (String type, double amount, double balance) {
     		this.t_id=-1;
     		this.type = type;
     		this.amount = amount;
     		this.balance = balance;
     	}


		public void show() {
			
			System.out.format("Transaction id:%5d   ", t_id);
			System.out.format("Transaction Type:%-20s   ", type);
			System.out.format("Transaction Amount:%.2f   ", amount);
			System.out.format("Balance:%.2f   ", balance);
			System.out.println();
		}
       
         
}
