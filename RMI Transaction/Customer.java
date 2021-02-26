import java.io.*;
class Query implements Serializable{
	public int id1, id2;
	public boolean isDeposit;
	public double amount;

	public Query(int id1, int id2, boolean isDeposit, double amount){
		this.id1 = id1;
		this.id2 = id2;
		this.isDeposit = isDeposit;
		this.amount = amount;
	}
}

public class Customer implements Serializable{
	private int id;
	//private String name;
	private double balance;

	public Customer(int id, double balance){
		this.id = id;
		//this.name = name;
		this.balance = balance;
	}

	public int getId(){
		return this.id;
	}

	public double getBalance(){
		return this.balance;
	}

	public void setId(int id){
		this.id = id;
	}

	public void setBalance(double balance){
		this.balance = balance;
	}
}