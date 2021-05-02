package exercise;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    public int getImportance(List<Employee> employees, int id) {
        Employee curr = null;
        for (Employee employee : employees) {
            if(employee.id == id) {
                curr = employee;
                break;
            }
        }
        int result = curr.importance;
        for (int i: curr.subordinates) {
            result += getImportance(employees, i);
        }
        return result;
    }
}
