package com.okayjam.code.leetcode.solution;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 690. 员工的重要性
 *
 * @author JamChen jamchen@tencent.com
 * @date 2026/10/13 15:19
 **/
public class Solution690 {

    public int getImportance(List<Employee> employees, int id) {
        HashMap<Integer, Employee> employeeHashMap = new HashMap<>();
        for (Employee employee : employees) {
            employeeHashMap.put(employee.id, employee);
        }
        int ans = 0;
        Deque<Integer> queue = new LinkedList<>();
        queue.add(id);
        while (!queue.isEmpty()) {
            Integer pop = queue.pop();
            Employee employee1 = employeeHashMap.get(pop);
            if (employee1 == null) continue;
            ans += employee1.importance;
            queue.addAll(employee1.subordinates);
        }
        return ans;
    }

    public static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    };
}
