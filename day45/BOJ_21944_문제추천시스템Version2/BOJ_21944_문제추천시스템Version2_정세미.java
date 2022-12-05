import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_문제추천시스템Version2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int M;
    static TreeSet<int[]> totalSet;
    static TreeSet<int[]>[] groupSet;
    static Map<Integer, int[]> map;

    public static void main(String[] args) throws Exception {
        init();
        process();
        System.out.println(sb);
    }

    static void init() throws Exception {
        int N = Integer.parseInt(br.readLine());
        Comparator<int[]> comparator = (a, b) -> {
            if (a[0] == b[0] && a.length == 3) {
                return a[2] - b[2];
            }
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        };
        totalSet = new TreeSet<>(comparator);
        groupSet = new TreeSet[101];
        for (int i = 1; i <= 100; i++) {
            groupSet[i] = new TreeSet<>(comparator);
        }
        map = new HashMap<>();
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            add();
        }
        M = Integer.parseInt(br.readLine());
    }

    static void process() throws Exception {
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            if ("recommend".equals(op)) {
                recommend();
            } else if ("recommend2".equals(op)) {
                recommend2();
            } else if ("recommend3".equals(op)) {
                recommend3();
            } else if ("solved".equals(op)) {
                delete();
            } else if ("add".equals(op)) {
                add();
            }
        }
    }

    public static void recommend() {
        int g = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        sb.append(x == 1 ? findHardest(groupSet[g], g) : findEasiest(groupSet[g], g)).append("\n");
    }

    public static void recommend2() {
        int x = Integer.parseInt(st.nextToken());
        sb.append(x == 1 ? findHardest(totalSet, null) : findEasiest(totalSet, null)).append("\n");
    }

    public static int findHardest(TreeSet<int[]> set, Integer g) {
        int[] problem = set.last();
        while (isDeleted(g, problem)) {
            delete(problem[0], problem[1], g == null ? problem[2] : g);
            problem = set.last();
        }
        return problem[0];
    }

    public static int findEasiest(TreeSet<int[]> set, Integer g) {
        int[] problem = set.first();
        while (isDeleted(g, problem)) {
            delete(problem[0], problem[1], g == null ? problem[2] : g);
            problem = set.first();
        }
        return problem[0];
    }

    public static void recommend3() {
        int x = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        sb.append(x == 1 ? findHarderThan(l) : findEasierThan(l)).append("\n");
    }

    public static int findHarderThan(int l) {
        int[] s = new int[]{0, l};
        int[] problem = totalSet.ceiling(s);
        while (problem != null && isDeleted(null, problem)) {
            delete(problem[0], problem[1], problem[2]);
            problem = totalSet.ceiling(s);
        }
        return problem == null ? -1 : problem[0];
    }

    public static int findEasierThan(int l) {
        int[] s = new int[]{0, l};
        int[] problem = totalSet.floor(s);
        while (problem != null && isDeleted(null, problem)) {
            delete(problem[0], problem[1], problem[2]);
            problem = totalSet.floor(s);
        }
        return problem == null ? -1 : problem[0];
    }

    public static boolean isDeleted(Integer g, int[] problem) {
        if (g == null) {
            g = problem[2];
        }
        int[] currentProblem;
        return (currentProblem = map.get(problem[0])) == null
                || currentProblem[0] != problem[1]
                || currentProblem[1] != g;
    }

    public static void delete() {
        int p = Integer.parseInt(st.nextToken());
        map.remove(p);
    }

    public static void delete(int p, int l, int g) {
        totalSet.remove(new int[]{p, l, g});
        groupSet[g].remove(new int[]{p, l});
    }

    public static void add() {
        int p = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        totalSet.add(new int[]{p, l, g});
        groupSet[g].add(new int[]{p, l});
        map.put(p, new int[]{l, g});
    }
}
