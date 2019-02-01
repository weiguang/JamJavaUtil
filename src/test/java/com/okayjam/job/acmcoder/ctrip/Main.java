package com.okayjam.job.acmcoder.ctrip;

import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/21 19:36.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);
    public static void main (String[] args) {
        //func1();
        //delChar(sn.nextLine());

            Scanner in = new Scanner(System.in);
            int res;

            int _A_size = 0;
            _A_size = Integer.parseInt(in.nextLine().trim());
            int[] _A = new int[_A_size];
            int _A_item;
            for(int _A_i = 0; _A_i < _A_size; _A_i++) {
                _A_item = Integer.parseInt(in.nextLine().trim());
                _A[_A_i] = _A_item;
            }

            res = missMinNum(_A);
            System.out.println(String.valueOf(res));



    }
    static int findMinMis(int[] A) {
        int min = -1;
        for (int i = 0; i < A.length; i++) {
            if(A[i] > 0) {
                if(min == -1) min = A[i];
                else if(A[i] < min) min = A[i];
            }
        }
        if(min == 1) return 1;
        else return min - 1;
    }

    static int missMinNum(int arr[])
    {
        int l = 0; //l表示已经从1到L已经出现（左边界），l的初值为0。
        int r = arr.length; //如果一个数字过大（不合法），就会被扔掉，用r表示这个右边界，r初始值为arr长度。
        int temp;
        while (l < r)
        {
            if (arr[l] == l + 1)
            {
                l++;
            }
            else if (arr[l]> r || arr[l] <= l || arr[arr[l] - 1] == arr[l])//不合法
            {
                arr[l] = arr[--r];
            }
            else//合法但是没有在理想的位置上
            {
                temp = arr[l];
                arr[l] = arr[arr[l] - 1];
                arr[temp - 1] = temp;

            }
        }
        return l + 1;
    }


    static void func1() {
        int n = sn.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sn.nextInt();
        }
        int m = sn.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = sn.nextInt();
        }
        System.out.println(f(a,n,b,m));

    }
    static double  f(int A[], int m, int B[], int n) {
        int[]C = new int[m+n];
        int id1, id2, id3;
        id1 = id2 = id3 = 0;
            while(id1 < m && id2 < n) {
            while(id1 < m && id2 < n && A[id1] <= B[id2]) C[id3++] = A[id1++];
            while(id1 < m && id2 < n && B[id2] <= A[id1]) C[id3++] = B[id2++];
        }
            while(id1 < m) C[id3++] = A[id1++];
            while(id2 < n) C[id3++] = B[id2++];
            if((id3 & 0x1) == 1) {
            id1 = C[id3>>1];
            return (double)id1;
        }
            else {
            id1 = C[id3>>1];
            id2 = C[(id3>>1)-1];
            return ((double)id1 + (double)id2) / 2.0;
        }
    }

    public static int middleNumberOfTwoArr(int[] arr1,int[] arr2){  
                int start1= 0;  
                int start2= 0;  
                int end1 = arr1.length-1;  
                int end2 = arr2.length-1;  
                  
                int midIndex1;  
                int midIndex2;  
  
                while(start1<end1 || start2<end2){  
                    midIndex1 = (start1 + end1)/2;   
                    midIndex2 = (start2 + end2)/2;   
                    if(arr1[midIndex1] == arr2[midIndex2]){  
                                return arr1[midIndex1];  
                    }  
                      
                    if (arr1[midIndex1] < arr2[midIndex2]){  
                                if(((end1-start1)%2 == 0) && ((end2-start2)%2==0)){  
                                    start1 = midIndex1;  
                                    end2 = midIndex2;  
                                }else {  
                                    start1 = midIndex1+1;  
                                    end2 = midIndex2;  
                                }  
                    }else {  
                                if(((end1-start1)%2 == 0) && ((end2-start2)%2==0)){  
                                    start2 = midIndex2;  
                                    end1 = midIndex1;  
                                }else {  
                                    start2 = midIndex2+1;  
                                    end1 = midIndex1;  
                                }  
                    }  
                }

                return (arr1[start1]<arr2[start2]? arr1[start1]:arr2[start2]);  
    }

    static void delChar(String s) {
        char a[] = new char[256];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(a[(int)c] == 0) {
                System.out.print(c); a[(int)c] = c;
            }
        }
    }
}
