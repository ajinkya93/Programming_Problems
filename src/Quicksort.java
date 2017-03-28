
public class Quicksort {

	public static void quicksort(int low, int high, int a[]){
		int i = low, j = high;
		int key = a[high];
		if(i > j)
			return;
	//	while(i <= j){
			while(a[i] < key)
				i++;
			while(a[j] > key)
				j--;
			if(i <= j){
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				i++;
				j--;
			}
	//	}
		if(low < j)
			quicksort(low,j,a);
		if(i < high)
			quicksort(i,high,a);
	}
	
	public static void main(String args[]){
		int[] test = { 6,5,4,3,2,1 };
		System.out.println("Array before sorting");
		for(int i = 0; i < test.length; i++)
			System.out.print(test[i]+" \t ");
		System.out.println();
		quicksort(0,(test.length)-1,test);
		System.out.println("Array after sorting");
		for(int i = 0; i < test.length; i++)
			System.out.print(test[i]+" \t ");
	}
}
