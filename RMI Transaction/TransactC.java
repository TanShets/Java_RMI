import java.rmi.server.*;

class Data{
	public static Customer[] customers = {};
	public static boolean[] permissions = {};
	public static int counter = 0;
	public static boolean available = true;
}

class Transaction extends Thread{
	Query transaction;
	public Transaction(Query transaction){
		super();
		this.transaction = transaction;
	}

	public void run(){
		while(!Data.permissions[this.transaction.id1] || !Data.permissions[this.transaction.id2]){

		}

		Data.permissions[this.transaction.id1] = false;
		Data.permissions[this.transaction.id2] = false;

		if(this.transaction.isDeposit){
			if(Data.customers[this.transaction.id1].getBalance() >= this.transaction.amount){
				Data.customers[this.transaction.id1].setBalance(Data.customers[this.transaction.id1].getBalance() - this.transaction.amount);
				Data.customers[this.transaction.id2].setBalance(Data.customers[this.transaction.id2].getBalance() + this.transaction.amount);
				System.out.print("Transferred " + this.transaction.amount + " from ");
				System.out.println(Data.customers[this.transaction.id1].getId() + " to " + Data.customers[this.transaction.id2].getId());
			}
			else{
				System.out.print("Failed to transfer " + this.transaction.amount + " from ");
				System.out.println(Data.customers[this.transaction.id1].getId() + " to " + Data.customers[this.transaction.id2].getId());
			}
		}
		else{
			if(Data.customers[this.transaction.id2].getBalance() >= this.transaction.amount){
				Data.customers[this.transaction.id2].setBalance(Data.customers[this.transaction.id2].getBalance() - this.transaction.amount);
				Data.customers[this.transaction.id1].setBalance(Data.customers[this.transaction.id1].getBalance() + this.transaction.amount);
				System.out.print("Transferred " + this.transaction.amount + " from ");
				System.out.println(Data.customers[this.transaction.id2].getId() + " to " + Data.customers[this.transaction.id1].getId());
			}
			else{
				System.out.print("Failed to transfer " + this.transaction.amount + " from ");
				System.out.println(Data.customers[this.transaction.id2].getId() + " to " + Data.customers[this.transaction.id1].getId());
			}
		}

		while(!Data.available);
		Data.available = false;
		Data.counter++;
		Data.available = true;

		Data.permissions[this.transaction.id1] = true;
		Data.permissions[this.transaction.id2] = true;
	}
}

public class TransactC extends UnicastRemoteObject implements TransactI{

	public TransactC() throws Exception{
		super();
	}

	public Customer[] execute(Customer[] customers, Query[] transactions) throws Exception{
		Data.customers = customers;
		boolean[] permissions = new boolean[customers.length];
		int i;
		for(i = 0; i < customers.length; i++)
			permissions[i] = true;
		Data.permissions = permissions;
		Data.counter = transactions.length;

		for(i = 0; i < transactions.length; i++){
			Transaction ts = new Transaction(transactions[i]);
			try{
				ts.start();
			}
			catch(Exception e){}
		}
		return Data.customers;
	}
}