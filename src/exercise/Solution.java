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

    public static int[] decode(int[] encoded, int first) {
        int[] arr = new int[encoded.length+1];
        arr[0] = first;
        for (int i = 0; i < encoded.length; i++) {
            arr[i+1] = arr[i] ^ encoded[i];
        }
        return arr;
    }

    public static int minDistance(String word1, String word2) {
        int[][] matrix = new int[word1.length()+1][word2.length()+1];
        for (int i = 0; i < word2.length()+1; i++) {
            matrix[0][i] = i;
        }
        for (int i = 1; i < word1.length()+1; i++) {
            matrix[i][0] = i;
        }
        for (int i = 1; i < word1.length()+1; i++) {
            for (int j = 1; j < word2.length()+1; j++) {
                int copy, insert, delete;
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    copy = matrix[i-1][j-1];
                }
                else {
                    copy = matrix[i-1][j-1] +1;
                }
                insert = matrix[i][j-1] + 1;
                delete = matrix[i-1][j] +1;
                int min = Math.min(Math.min(copy, insert), delete);
                matrix[i][j] = min;
            }
        }
        return matrix[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
        System.out.println(minDistance("intention", "execution"));;
    }

}
