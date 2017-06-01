package util;

/**
 *
 *@author jam
 *@e-mail weiguang978@163.com
 *
 *@description: My sort
 * */
public class Sort{
	public static void swap(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	/**
	* MergeSort
	**/
	public static void merge(int[] data, int start, int mid, int end) {
		int i = start, j = mid + 1, k = 0;
		int[] temp = new int[end - start + 1];
		while(i <= mid && j <= end) {
			if(data[i] > data[j]) {
				temp[k++] = data[j++];
			}else{
				temp[k++] = data[i++];
			}
		}
		while (i <= mid) temp[k++] = data[i++];
		while (j <= end) temp[k++] = data[j++];
		k = start;
		for (int element : temp) {
			data[k++] = element;
		}
	}

	public static void mergeSort(int[] data, int start, int end) {
		if (start < end) {
			int mid = (start + end) / 2;
			mergeSort(data, start, mid);
			mergeSort(data, mid + 1, end);
			merge(data, start, mid, end);
		}
	}
	/**
	* InsertionSort
	**/
	public static void insertionSort(int[] data) {
		for (int i = 1; i < data.length; i++) {
			for (int j = i; j > 0 && data[j] < data[j-1]; j--) {
				swap(data, j, j - 1 );
			}
		}
	}
	public static void insertionSort1(int[] data) {
		for (int i = 1; i < data.length; i++) {
			int temp = data[i], j = 0;
			for (j = i ; j > 0 ; j--) {
				if(data[j - 1] > temp){
					data[j] = data[j - 1];
				}else break;
			}
			data[j] = temp;
		}
	}
	
	/**
	* SelectSort
	**/
	public static void selectSort(int[] data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = i + 1; j < data.length; j++) {
				if (data[i] > data[j]) {
					swap(data, i, j);
				}
			}
		}
	}

	/**
	* BubbleSort
	**/
	public static void bubbleSort(int[] data) {
		boolean exchange = false;  //improved
		for (int i = data.length - 1; i > 0; i--) {
			exchange = false;
			for(int j = 0; j < i; j++ ) {
				if (data[j] > data[j + 1]) {
					swap(data, j, j + 1);
					exchange = true;
				}
			}
			if(!exchange){ //no change, so data is order 
				return;	
			}

		}
	}
	
	/**
	 * QuickSort
	 */
	public static void quickSort(int[] data, int start, int end){
		if (start >= end) return;
	    	int key = data[start];
		int i = start;
		int j = end;
		while (i < j) {
			while (data[j] > key && i < j) j--;
			data[i] = data[j];
			while (data[i] < key && i < j) i++;
			data[j] = data[i];
		}
		data[i] = key;
		quickSort(data, start, i-1);
		quickSort(data, j+1, end);
	} 

	public static void main(String[] args){
		int[] array=new int[]{11,213,134,44,77,78,23,43};
        	long startTime = System.nanoTime();
		quickSort(array, 0, array.length-1);
		//bubbleSort(array);
		//selectSort(array);
		//insertionSort1(array);
		//mergeSort(array, 0, array.length - 1);

		long endTime = System.nanoTime();
		long diffTime = endTime - startTime;
		for (int i = 0; i < array.length; i++) {
            		System.out.print( array[i] + " ");
        	}
		System.out.println("\n Time cost(nano):" + diffTime);
	}
}
