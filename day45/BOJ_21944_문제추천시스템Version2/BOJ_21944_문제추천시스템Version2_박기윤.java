package BOJ.DataStructure.Map_Set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_21944 {

    private final static String NEWLINE = "\n";

    public static TreeSet<Problem> treeSet = new TreeSet<>();
    public static List<TreeSet<Problem>> setList = new ArrayList<>();
    public static Map<Integer, Problem> hashMap = new HashMap<>();

    public static class Problem implements Comparable<Problem> {

        int number;
        int difficulty;
        int type;

        Problem(int difficulty, int type) {
            this.difficulty = difficulty;
            this.type = type;
        }

        Problem(int number, int difficulty, int type) {
            this.number = number;
            this.difficulty = difficulty;
            this.type = type;
        }

        public int compareTo(Problem p) {
            if (p.difficulty == difficulty) {
                return number - p.number;
            }
            return difficulty - p.difficulty;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i <= 100; i++) {
            setList.add(new TreeSet<>());
        }
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            add(st);
        }
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "recommend":
                    sb.append(recommend(st) + NEWLINE);
                    break;
                case "recommend2":
                    sb.append(recommend2(st) + NEWLINE);
                    break;
                case "recommend3":
                    sb.append(recommend3(st) + NEWLINE);
                    break;
                case "add":
                    add(st);
                    break;
                case "solved":
                    solved(st);
                    break;
            }
        }
        System.out.println(sb);
    }

    private static int recommend(StringTokenizer st) {
        int type = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        if (x == 1) {
            return setList.get(type).last().number;
        } else {
            return setList.get(type).first().number;
        }
    }

    private static int recommend2(StringTokenizer st) {
        int x = Integer.parseInt(st.nextToken());
        if (x == 1) {
            return treeSet.last().number;
        } else {
            return treeSet.first().number;
        }
    }

    private static int recommend3(StringTokenizer st) {
        int x = Integer.parseInt(st.nextToken());
        int diff = Integer.parseInt(st.nextToken());
        Problem problem = x == 1 ? treeSet.ceiling(new Problem(0, diff, 0)) : treeSet.lower(new Problem(0, diff, 0));
        if (problem == null) {
            return -1;
        } else {
            return problem.number;
        }
    }

    private static void add(StringTokenizer st) {
        int number = Integer.parseInt(st.nextToken());
        int difficulty = Integer.parseInt(st.nextToken());
        int type = Integer.parseInt(st.nextToken());
        setList.get(type).add(new Problem(number, difficulty, type));
        treeSet.add(new Problem(number, difficulty, type));
        hashMap.put(number, new Problem(difficulty, type));
    }

    private static void solved(StringTokenizer st) {
        int num = Integer.parseInt(st.nextToken());
        if (!hashMap.containsKey(num)) return;
        int difficulty = hashMap.get(num).difficulty;
        int type = hashMap.get(num).type;
        setList.get(type).remove(new Problem(num, difficulty, type));
        treeSet.remove(new Problem(num, difficulty, type));
        hashMap.remove(num);
    }
}
