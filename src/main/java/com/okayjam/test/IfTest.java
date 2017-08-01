package com.okayjam.test;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/7/20 10:49.
 *
 * interface have a default method, request jdk 1.8 over
 */
public interface IfTest {
	int a = 0;
	// default jdk 1.8
	default  public  void fun() {
		System.out.println(" default method,request jdk 1.8+!");
	}
}
