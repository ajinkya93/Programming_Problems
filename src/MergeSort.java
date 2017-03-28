public class MergeSort {

	static int helper[];
	
	public static void mergeSort(int low, int high, int a[]){
		if(low < high){
			int mid = low + (high - low)/2;
			mergeSort(low,mid,a);
			mergeSort(mid + 1, high, a);
			merge(low,mid,high,a);
		}
	}
	
	public static void merge(int low, int mid, int high, int a[]){
		
		for(int i = low; i <= high; i++)
			helper[i] = a[i];
		int i = low;
		int j = mid + 1;
		int k = low;
		while((i <= mid ) && (j <= high)){
			if(helper[i] <= helper [j]){
				a[k] = helper[i];
				i++;
			}
			else{
				a[k] = helper[j];
				j++;
			}
			k++;
		}
		while(i <= mid){
			a[k] = helper[i];
			i++;
			k++;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = { 6,5,4,3,2,1 };
		helper = new int[test.length];
		System.out.println("Array before sorting");
		for(int i = 0; i < test.length; i++)
			System.out.print(test[i]+" \t ");
		System.out.println();
		mergeSort(0,(test.length)-1,test);
		System.out.println("Array after sorting");
		for(int i = 0; i < test.length; i++)
			System.out.print(test[i]+" \t ");
	}

}
