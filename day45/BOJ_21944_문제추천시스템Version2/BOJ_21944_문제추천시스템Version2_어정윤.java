package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21944_문제추천시스템Version2_어정윤 {

    static class Problem {

        int number;
        int level;

        public Problem(int number, int level) {
            this.number = number;
            this.level = level;
        }
    }

    private static final List<Integer> SOLVED_EASY_PROBLEMS = new ArrayList<>();
    private static final List<Integer> SOLVED_DIFFICULT_PROBLEMS = new ArrayList<>();
    private static final String RECOMMEND = "recommend";
    private static final String ADD = "add";
    private static final String SOLVED = "solved";
    private static final String DELIMITER = " ";
    private static final int PROBLEM = 0;
    private static final int LEVEL = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        PriorityQueue<Problem> mostEasyProblems = new PriorityQueue<>((problem1, problem2) -> {
            if (problem1.level == problem2.level) {
                return problem1.number - problem2.number;
            }
            return problem1.level - problem2.level;
        });
        PriorityQueue<Problem> mostDifficultProblems = new PriorityQueue<>((problem1, problem2) -> {
            if (problem1.level == problem2.level) {
                return problem2.number - problem1.number;
            }
            return problem2.level - problem1.level;
        });
        for (int i = 0; i < n; i++) {
            String[] inputs = bufferedReader.readLine().split(DELIMITER);
            int number = Integer.parseInt(inputs[PROBLEM]);
            int level = Integer.parseInt(inputs[LEVEL]);
            mostEasyProblems.add(new Problem(number, level));
            mostDifficultProblems.add(new Problem(number, level));
        }

        int m = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            String operator = stringTokenizer.nextToken();
            if (operator.equals(RECOMMEND)) {
                int x = Integer.parseInt(stringTokenizer.nextToken());
                if (x == 1) {
                    int number = mostDifficultProblems.poll().number;
                    while (SOLVED_DIFFICULT_PROBLEMS.contains(number)) {
                        SOLVED_DIFFICULT_PROBLEMS.remove(Integer.valueOf(number));
                        number = mostDifficultProblems.poll().number;
                    }
                    System.out.println(number);
                } else {
                    int number = mostEasyProblems.poll().number;
                    while (SOLVED_EASY_PROBLEMS.contains(number)) {
                        SOLVED_EASY_PROBLEMS.remove(Integer.valueOf(number));
                        number = mostEasyProblems.poll().number;
                    }
                    System.out.println(number);
                }
            } else if (operator.equals(ADD)) {
                int number = Integer.parseInt(stringTokenizer.nextToken());
                int level = Integer.parseInt(stringTokenizer.nextToken());
                mostEasyProblems.add(new Problem(number, level));
                mostDifficultProblems.add(new Problem(number, level));
            } else if (operator.equals(SOLVED)) {
                int number = Integer.parseInt(stringTokenizer.nextToken());
                SOLVED_EASY_PROBLEMS.add(number);
                SOLVED_DIFFICULT_PROBLEMS.add(number);
            }
        }
    }
}
