package exercise;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * SolutionJZ338
 */
public class SolutionJZ338 {

    public String[] permutation(String s) {
        boolean[] visited = new boolean[s.length()];
        Set<String> result = new HashSet<>();
        findString(s, visited, result, "");

        return result.toArray(new String[0]);

    }

    public void findString(String s, boolean[] visited, Set<String> result, String res) {
        boolean flag = true;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                flag = false;
                visited[i] = true;
                findString(s, visited, result, res+s.charAt(i));
                visited[i] = false;
            }
        }
        if (flag) {
            result.add(res);
        }
    }

    public static void main(String[] args) {
        String[] res = new SolutionJZ338().permutation("aab");
        System.out.println(res.length);
        for (String re : res) {
            System.out.println(re);
        }
    }
}