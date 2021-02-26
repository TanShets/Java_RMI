import java.util.Scanner;
import java.rmi.*;

public class Client{
	public final static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws Exception{
		double[] arr;
		SortI obj = (SortI)Naming.lookup("SORT");

		System.out.print("Enter the number of elements in the array: ");
		int n = sc.nextInt();

		arr = new double[n];
		System.out.print("Enter the numbers: ");

		int i;
		for(i = 0; i < n; i++){
			arr[i] = sc.nextDouble();
		}

		double[] sorted_array = obj.mergeSort(arr);
		System.out.print("The Sorted Array: ");
		for(i = 0; i < sorted_array.length; i++)
			System.out.print(sorted_array[i] + " ");
		
	}
}