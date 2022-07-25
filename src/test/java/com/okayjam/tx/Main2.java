package com.okayjam.tx;

import java.util.Scanner;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2020/08/11 19:34
 **/
public class Main2 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        m1();
    }
  static void m1() {
      int t = scanner.nextInt();
      while(t-- > 0) {
          String s = scanner.next();
          long all1 = 0;
          for (int i = 0; i < s.length(); i++) {
              if (s.charAt(i) == '1') {
                  all1++;
              }
          }
          long min = Math.abs(s.length() - all1 - all1);
          int tmin = s.length() % 2;
          int i = 0;
          int j = 0;
          int[] re = new int[2];
          re[0] = 1; re[1] = s.length();
          for ( i = 0; i < s.length() ; i++) {
//              int i1 = s.charAt(i) == '1' ? 1 : 0;
              int i1 = 0;
              for ( j = i ; j < s.length(); j++) {
                   i1 += s.charAt(j) == '1' ? 1 : 0;
                  int i0 = (j-i) - i1+1;
                  long ex1 = all1 - i1;
                  long ex0 = s.length() - all1 - i0;
                  long sum1 = ex1 + i0;
                  long sum0 = ex0 + i1;
                  long sum = Math.abs(sum1 - sum0);
                  if (sum < min) {
                      min = sum;
                      re[0] = i+1; re[1] = j+1;
                  }
                  if(min == tmin) {
                      break;
                  }
              }
              if(min == tmin) {
                  break;
              }
          }
          System.out.println(re[0] + " " + re[1]);
      }
  }

}
