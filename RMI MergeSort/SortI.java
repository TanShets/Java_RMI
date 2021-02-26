import java.rmi.Remote;

public interface SortI extends Remote{
	public double[] mergeSort(double[] a) throws Exception;
}