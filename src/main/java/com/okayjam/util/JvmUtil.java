package com.okayjam.util;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2022/08/05 10:15
 **/
public class JvmUtil {
	public static void loadMemory() {
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
		//堆内存使用情况
		MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();
		//初始的总内存
		long totalMemorySize = memoryUsage.getInit()/ (1024 * 1024);
		//已使用的内存
		long usedMemorySize = memoryUsage.getUsed()/ (1024 * 1024);
		//最大的内存
		long maxMemorySize = memoryUsage.getMax()/ (1024 * 1024);
		// 非堆使用内存
		MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
		System.out.println("Head: " + memoryString(memoryUsage));
		System.out.println("NonHead: " + memoryString(nonHeapMemoryUsage));
	}

	public static String memoryString(MemoryUsage usage) {
		StringBuffer buf = new StringBuffer();
		buf.append("init = " + usage.getInit() + "(" + (usage.getInit() >> 20) + "M) ");
		buf.append("used = " + usage.getUsed() + "(" + (usage.getUsed()  >> 20) + "M) ");
		buf.append("committed = " + usage.getCommitted() + "(" +
				(usage.getCommitted() >> 20) + "M) " );
		buf.append("max = " + usage.getMax() + "(" + (usage.getMax() >> 20) + "M)");
		return buf.toString();
	}

}
