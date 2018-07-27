package job.newcoder.wangyi;

import java.util.*;
/*
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，
 * 判断数组中是否含有该整数
 * */
public class Main {
     public static boolean Find(int target, int[][] array) {
          int row = array.length;
          int column = array[0].length;
          boolean found = false;
          int i = 0;
          int j = column-1;
          while(row == 0||column == 0||array==null) {
               break;
          }
          while(i<row && j>0){
               if(array[i][j]<target) {
                    i++;
               }
               else if(array[i][j]>target){
                    j--;
               }
               else {
                    found = true;
                    break;
               }
          }
          return found;
     }
    public static void main(String[] args) {
          // TODO Auto-generated method stub
          Scanner sc = new Scanner(System.in);
          int row = sc.nextInt();
          int column = sc.nextInt();
          int[][] arr = new int[row][column];
          for(int i = 0;i<row;i++) {
               for(int j = 0;j<column;j++) {
                    arr[i][j] = sc.nextInt();
               }
          }
          int target = sc.nextInt();
          boolean result = Find(target,arr);
          System.out.println(result);
     }
}