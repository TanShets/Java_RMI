import java.util.Scanner;
import java.rmi.*;

public class Client{
	public final static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws Exception{
		Customer[] customers;
		Query[] transactions;
		int n, n1, id1, id2, i;
		double amt;
		TransactI obj = (TransactI)Naming.lookup("TRANSACT");

		System.out.print("Enter the number of customers: ");
		n = sc.nextInt();

		customers = new Customer[n];
		System.out.print("Enter the number of transactions: ");
		n1 = sc.nextInt();
		transactions = new Query[n1];

		System.out.print("Enter the balance for each Customer: ");

		for(i = 0; i < n; i++){
			customers[i] = new Customer(i, sc.nextDouble());
		}

		System.out.print("Enter the sender, receiver and amount for each transaction: ");
		for(i = 0; i < n1; i++){
			id1 = sc.nextInt();
			id2 = sc.nextInt();
			amt = sc.nextDouble();

			transactions[i] = new Query(id1, id2, true, amt);
		}

		customers = obj.execute(customers, transactions);
		System.out.println("Current amounts: ");
		for(i = 0; i < n; i++)
			System.out.println(customers[i].getId() + ": " + customers[i].getBalance());
	}
}