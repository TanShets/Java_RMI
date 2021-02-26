import java.rmi.*;

public class Server{
	public static void main(String[] args) throws Exception{
		TransactC obj = new TransactC();
		Naming.rebind("TRANSACT", obj);
		System.out.println("Initiated");
	}
}