package com.okayjam.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Weiguang <chen2621978@gmail.com>
 * @date 2019/05/31 14:59
 **/
public class CollectionUtil {

    /**
     * 默认分页大小
     */
    public static int PAGE_SIZE = 2000;

    /**
     * 将List分页, 默认 PAGE_SIZE 每页
     * @param list 需要分页的list
     * @return 返回分页
     */
    public static<T> List<List<T>> subList(List<T> list) {
        return subList(list, PAGE_SIZE);
    }

    /**
     * 将List分页
     * @param list 需要分页的list
     * @param pageSize 一页大小
     * @return 返回分页
     */
    public static<T> List<List<T>> subList(List<T> list, int pageSize) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        if (pageSize <= 0) {
            pageSize = PAGE_SIZE;
        }
        List<List<T>> resultList = new ArrayList<>();
        List<T> subList;
        int totalCount = list.size();
        //分多少次处理
        int requestCount = totalCount / pageSize;

        int toIndex = 0;
        for (int i = 0; i < requestCount; i++) {
            int fromIndex = i * pageSize;
            //如果总数少于PAGE_SIZE,为了防止数组越界,toIndex直接使用totalCount即可
            toIndex = Math.min(totalCount, (i + 1) * pageSize);
            subList = list.subList(fromIndex, toIndex);
            resultList.add(subList);
            //总数不到一页或者刚好等于一页的时候,只需要处理一次就可以退出for循环了
        }
        if (toIndex < totalCount) {
            resultList.add(list.subList(toIndex, totalCount));
        }
        return resultList;
    }

}
