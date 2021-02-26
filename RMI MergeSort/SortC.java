import java.rmi.server.*;

public class SortC extends UnicastRemoteObject implements SortI{

	public SortC() throws Exception{
		super();
	}

	private void merge(double[] a, int start, int length){
		for(int x = 0; x < length; x++)
			System.out.print(a[x] + " ");
		System.out.println();

		int i, mid = length / 2, max_pos, min_pos;
		double temp = -1, temp2 = -1;
		if(a[start] <= a[start + mid]){
			temp = a[start + mid];
		}
		else{
			temp = a[start];
			a[start] = a[start + mid];
		}
		a[start + mid] = -1;

		for(i = 1; i < mid; i++){
			min_pos = this.min(a[start + i], temp, a[start + i + mid], 0);
			switch(min_pos){
				case 1:{
					temp2 = a[start + i];
					a[start + i] = temp;
					temp = temp2;
					break;
				}
				case 2:{
					temp2 = a[start + i];
					a[start + i] = a[start + i + mid];
					a[start + i + mid] = temp2;
					break;
				}
			}
			//Min value already at start + i so only need to look out for temp
			max_pos = this.max(a[start + i], temp, a[start + i + mid], 0);
			switch(max_pos){
				case 1:{
					temp2 = temp;
					temp = a[start + i + mid];
					a[start + i + mid - 1] = temp2;
					a[start + i + mid] = -1;
					break;
				}
				default:{
					a[start + i + mid - 1] = a[start + i + mid];
					a[start + i + mid] = -1;
				}
			}
		}

		if(length % 2 == 1){
			if(temp >= a[start + length - 1]){
				a[start + length - 2] = temp;
				temp = a[start + length - 1];
				a[start + length - 1] = -1;
			}
			else{
				a[start + length - 2] = a[start + length - 1];
				a[start + length - 1] = -1;
			}
		}

		mid = start + length / 2;
		while(mid < start + length - 1){
			if(temp < a[mid]){
				temp += a[mid];
				a[mid] = temp - a[mid];
				temp = temp - a[mid];
			}
			mid++;
		}
		a[start + length - 1] = temp;
	}

	private double max(double a, double b, double c){
		return Math.max(Math.max(a, b), c);
	}

	private int max(double a, double b, double c, int x){
		double max_val = Math.max(Math.max(a, b), c);
		if(max_val == a)
			return 0;
		else if(max_val == b)
			return 1;
		else
			return 2;
	}

	private double min(double a, double b, double c){
		return Math.min(Math.min(a, b), c);
	}

	private int min(double a, double b, double c, int x){
		double min_val = Math.min(Math.min(a, b), c);
		if(min_val == a)
			return 0;
		else if(min_val == b)
			return 1;
		else
			return 2;
	}

	private double mid(double a, double b, double c){
		double maxi = this.max(a, b, c);
		double mini = this.min(a, b, c);

		if(maxi == a)
			return Math.max(b, c);
		else if(maxi == b)
			return Math.max(a, c);
		else
			return Math.max(a, b);
	}

	public void mergeSort(double[] a, int start, int length){
		double temp, temp1, temp2;
		int min_pos, max_pos, i;
		switch(length){
			case 0:
				return;
			case 1:
				return;
			case 2:{
				if(a[start] > a[start + 1]){
					temp = a[start];
					a[start] = a[start + 1];
					a[start + 1] = temp;
				}
				break;
			}
			case 3:{
				min_pos = this.min(a[start], a[start + 1], a[start + 2], 0);

				if(min_pos != 0){
					temp = a[start + min_pos];
					a[start + min_pos] = a[start];
					a[start] = temp;
				}

				max_pos = this.max(a[start], a[start + 1], a[start + 2], 0);
				if(max_pos != 2){
					temp = a[start + max_pos];
					a[start + max_pos] = a[start + 2];
					a[start + 2] = temp;
				}
				break;
			}
			default:{
				System.out.print("Array before: ");
				for(i = 0; i < a.length; i++)
					System.out.print(a[i] + " ");
				System.out.println();
				this.mergeSort(a, start, length / 2);
				this.mergeSort(a, start + length / 2, length - length / 2);
				this.merge(a, start, length);
			}
		}
	}

	public double[] mergeSort(double[] a){
		this.mergeSort(a, 0, a.length);
		return a;
	}
}