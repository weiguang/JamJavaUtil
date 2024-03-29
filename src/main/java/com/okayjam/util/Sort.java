package com.okayjam.util;


/**
 *  My sort
 * Created by Weiguang Chen(chen2621978@gmail.com) on 2017/7/14 11:16.
 * */
public class Sort{

	public static void swap(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	public  static int compare(int a, int b){
		return a - b;
	}
	public static int compare1(int a, int b){
		int f = b % 2 - a % 2 ;
		//if(f == 0) {f = a - b;}
		return f;
	}

	/**
	 *  二分查找
	 * @param data 数组需要是递增的
	 * @param key 要查找的内容
	 * @return  Found:index; Not Found: -1
	 */
	public static int binarySearch(final int[] data, int key) {
		if(data == null || data.length ==0) return -1;
		int low = 0, high = data.length - 1;
		int mid;
		while(low <= high) {
			mid = (low + high) / 2;
			if(key < data[mid]) high = mid - 1;
			else if(key > data[mid]) low = mid + 1;
			else return mid;
		}
		return -1;
	}

	public static int binarySearch1(final int[] data, int key, int low, int high) {
		if(data == null || data.length ==0) return -1;
		if (low == high)  return key == data[low] ? low : -1;
		int mid = (low + high) / 2;
		if(key < data[mid])  return binarySearch1(data, key,low,  mid - 1);
		else if(key > data[mid])  return binarySearch1(data, key, mid + 1, high);
		else return mid;
	}

	/**
	 * MergeSort
	 * 稳定
	 * nlg(n)
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
	 * InsertionSort 1
	 * 稳定
	 * n^2
	**/
	public static void insertionSort(int[] data) {
		for (int i = 1; i < data.length; i++) {
			for (int j = i; j > 0 && data[j] < data[j-1]; j--) {
				swap(data, j, j - 1 );
			}
		}
	}

	/**
	 * InsertionSort 2
	 **/
	public static void insertionSort1(int[] data) {
		for (int i = 1; i < data.length; i++) {
			int temp = data[i], j;
			for (j = i; j > 0; j--) {
				if(data[j - 1] > temp){
					data[j] = data[j - 1];
				}else break;
			}
			data[j] = temp;
		}
	}

	/**
	 * 选择和冒泡排序的不同
	 * 冒泡排序是先从一整段开始，从第一个数开始，通过与 相邻的两个数 对比，不断把大的数后移（一般把最大的移到最后，）
	 *
	 * 选择排序是先从一整段开始，从第一个数开始，然后和不断比较后面的数，如果发现比第一个数小就与第一个数交换
	 * 固定 一个位置，然后该位置与后面的数分别对比，如果后面的小就与后面的数交换（一般把最小移到前面）
	 */

	/**
	 * SelectSort
	 * 不稳定
	 * n^2
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
	 * 稳定
	 * n^2
	**/
	public static void bubbleSort(int[] data) {
		boolean exchange = false;  //improved
		for (int i = data.length - 1; i > 0; i--) {
			exchange = false;
			for(int j = 0; j < i; j++ ) {
				if (compare1(data[j],data[j + 1]) > 0 ) {
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
	 * 不稳定
	 * nlg(n)
	 */
	public static void quickSort(int[] data, int start, int end){
		if (start >= end) return;
	    	int key = data[start];
		int i = start;
		int j = end;
		while (i < j) {
			while ( compare(data[j],key) >= 0 && i < j) j--;
			data[i] = data[j];
			while (compare(data[i], key) <= 0 && i < j) i++;
			data[j] = data[i];
		}
		data[i] = key;
		quickSort(data, start, i-1);
		quickSort(data, j+1, end);
	} 

	public static void main(String[] args){
		int[] array=new int[]{11,213,134,44,77,78,23,43,134,56,100};
//		int[] array=new int[]{11,11,11,11,11,11,11,12,11,11,12};
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
		System.out.println("binary Search:" + binarySearch(array,100) );
		System.out.println("binary Search:" + binarySearch1(array,100, 0, array.length) );
	}
}
