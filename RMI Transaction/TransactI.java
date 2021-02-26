import java.rmi.Remote;

public interface TransactI extends Remote{
	public Customer[] execute(Customer[] customers, Query[] transactions) throws Exception;
}